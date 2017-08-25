package de.brueckner.fph.acceptance.componentCampaignManager;

import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR;

public class HeaderButtonCampaignAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(HeaderButtonCampaignAcceptanceTest.class);

    @Test
    public void headerButtonCampaignAcceptanceTest() {

        logger.info("HeaderButtonCampaignAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        campaignManager.open();
        campaignManager.campaignButton();
        campaignManager.waitSpinnerCM();

        List<WebElement> dates = getWebDriver().findElements(By.cssSelector(".campaignManager .tick"));
        Assert.assertEquals(dates.get(24).getText(), DateUtils.date("2016-05-09 11:00:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR));
        Assert.assertEquals(dates.get(70).getText(), DateUtils.date("2016-05-11 09:00:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR));

    }
}