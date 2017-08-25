package de.brueckner.fph.acceptance.rollsAsFavorites;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.RollsAsFavoritesOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static de.brueckner.fph.managers.ContentManager.FAVORITES_BUTTON;

public class ChangeProductDeleteFavoriteRollsAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(ChangeProductDeleteFavoriteRollsAcceptanceTest.class);

    @Test
    public void changeProductDeleteFavoriteRollsAcceptanceTest() {
        logger.info("ChangeProductDeleteFavoriteRollsAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //rolls are predefined as favorite and benchmark in this period
        timelineManager.setNewDateForDatePicker("2016-04-13 01:00:00", "2016-04-15 03:00:00");

        //change the product of the campaign
        timelineManager.clickCampaign("campaign:160413014141759:1460511701759:1460690794472");
        contentManager.waitContentSpinner();
        campaignManager.open();

        //for Product name
        campaignManager.productDropdown();
        campaignManager.productOption1();

        campaignManager.save();
        campaignManager.close();

        timelineManager.waitTimelineSpinner();
        contentManager.waitContentSpinner();

        //check if the previous benchmark roll is now a normal one
        timelineManager.setNewDateForDatePicker("2016-04-14 22:00:00", "2016-04-15 22:00:00");

        treeManager.openTree();

        timelineManager.clickRoll("roll:A160415010243:1460682163918:1460688316670");

        contentManager.waitContentSpinner();

        List<WebElement> treeItem = getWebDriver().findElements(By.cssSelector("li.k-item .k-image"));
        Assert.assertEquals(treeItem.get(6).getAttribute("src"), getApplicationURL() + "/resources/img/tree/statuses/notChecked.png");

        //rollback (set the roll back to benchmark
        WebElement favoriteButton = getWebDriver().findElement(By.cssSelector(FAVORITES_BUTTON));
        favoriteButton.click();

        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_BENCHMARK.getOptionText());

    }

    @AfterMethod
    public void restoreCampaigns() throws IOException {
        getMongoUtils().executeScript(CAMPAIGNS_SCRIPT_NAME);
    }

}

