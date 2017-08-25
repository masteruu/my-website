package de.brueckner.fph.acceptance.componentCampaignManager;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.CampaignManager.CAMPAIGN_NAME_INPUT;
import static de.brueckner.fph.managers.CampaignManager.CUT_ANIMATION_DURATION_MILLISECONDS;
import static de.brueckner.fph.managers.ContentManager.*;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_PREVIOUS_ELEMENT;

public class CampaignManagerCutAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerCutAcceptanceTest.class);

    @Test
    public void campaignManagerCutAcceptance() {

        logger.info("CampaignManagerCutAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-11 06:00:00", "2016-04-11 15:00:00");

        campaignManager.open();
        campaignManager.cutButton();

        WebLocator myCut = new WebLocator().setClasses("possibleCut").setAttribute("transform", "translate( 1239.294869876543 0)");
        myCut.click();

        Utils.sleep(CUT_ANIMATION_DURATION_MILLISECONDS);

        campaignManager.productDropdown();
        campaignManager.productOption1();

        WebElement name = getWebDriver().findElement(By.cssSelector(CAMPAIGN_NAME_INPUT));
        applicationManager.sendKeysScript(name, "TEST");

        campaignManager.save();
        campaignManager.close();
        timelineManager.waitTimelineSpinner();
        contentManager.waitContentSpinner();

        String tooltipText = applicationManager.getTooltipContainerText("*[uid='campaign:160411100229908:1460368949908:1460376335896']");
        Assert.assertEquals(tooltipText, "160411_11P19u_400m_3.6t\n" +
                "11.04.16  12:02:29\n" +
                "11.04.16  14:05:352 h 03 min", "The cut was hot performed correctly");

        //check name and product for the new campaign
        WebElement campaignName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_NAME));
        Assert.assertEquals(campaignName.getText().trim(), "TEST", "Campaign name is not ok");
        WebElement productName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_PRODUCT));
        Assert.assertEquals(productName.getText().trim(), "19um Plain", "Campaign product is not ok");

        //check rolls tabs for new and old campaign
        contentManager.openTab(ROLLS_TAB_TEXT);

        List<WebElement> roll = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(roll.get(2).getText(), "  A 160411 2005", "Other roll ID expected in rolls table");

        //go to the old campaign
        WebElement nextArrow = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        nextArrow.click();

        contentManager.waitContentSpinner();

        roll = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(roll.get(2).getText(), "  A 160411 1802", "Other roll ID expected in rolls table");

        contentManager.openTab(OVERVIEW_TAB_TEXT);

        //check campaign name and product for the old campaign
        campaignName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_NAME));
        Assert.assertEquals(campaignName.getText().trim(), "160411_11P19u_400m_ 3.6t", "Campaign name is not ok");
        productName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_PRODUCT));
        Assert.assertEquals(productName.getText().trim(), "19um Plain", "Campaign product is not ok");

    }
}