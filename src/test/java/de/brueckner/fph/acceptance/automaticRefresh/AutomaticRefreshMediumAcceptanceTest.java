package de.brueckner.fph.acceptance.automaticRefresh;

import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static de.brueckner.fph.managers.CampaignManager.CAMPAIGN_NAME_INPUT;
import static de.brueckner.fph.managers.CampaignManager.CAMPAIGN_PLANNED_QUANTITY_INPUT;
import static de.brueckner.fph.managers.ContentManager.*;

public class AutomaticRefreshMediumAcceptanceTest extends AbstractAutomaticRefreshTest {

    private static final Logger logger = LoggerFactory.getLogger(AutomaticRefreshMediumAcceptanceTest.class);

    /*Medium
    This case represents the situation when data is modified, has a big impact on the UI but none of the navigation components will be
    affected and nothing can generate an error. The only effect will be the inconsistency of the displayed data.
    The application has to be reinitialized but previous state will be restored.
    Application behavior:
    reload all components;
    restore previous state;
    Events that will trigger this type of refresh:
    changing the properties of a campaign (name, product name, planed quantity);*/

    String startDate = "2016-04-11 06:00:00";
    String endDate = "2016-04-11 15:00:00";

    @Test
    public void editProductCampaign() {

        logger.info("editProductCampaign");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_EE.getUsername(), LoginCredentials.USER_EE.getPassword());
        }
        timelineManager.setNewDateForDatePicker(startDate, endDate);

        secondTimelineManager.setNewDateForDatePicker(startDate, endDate);
        secondContentManager.waitContentSpinner();

        WebElement campaignClick = secondDriver.findElement(By.cssSelector("*[uid='campaign:160411100229908:1460368949908:1460383722345']"));
        campaignClick.click();
        secondTimelineManager.waitTimelineSpinner();
        secondContentManager.waitContentSpinner();

        timelineManager.clickCampaign("campaign:160411100229908:1460368949908:1460383722345");

        campaignManager.open();

        //for Product name
        campaignManager.productDropdown();
        campaignManager.productOption2();
        campaignManager.save();
        campaignManager.close();

        checkAutomaticRefreshNotification();

        WebElement prodName = secondDriver.findElement(By.cssSelector(CAMPAIGN_OVERVIEW_PRODUCT));
        Assert.assertEquals(prodName.getText().trim(), "23um Plain");

    }

    @Test
    public void editNameCampaign() {

        logger.info("editNameCampaign");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_EE.getUsername(), LoginCredentials.USER_EE.getPassword());
        }
        timelineManager.setNewDateForDatePicker(startDate, endDate);

        secondTimelineManager.setNewDateForDatePicker(startDate, endDate);
        secondContentManager.waitContentSpinner();

        WebElement campaignClick = secondDriver.findElement(By.cssSelector("*[uid='campaign:160411100229908:1460368949908:1460383722345']"));
        campaignClick.click();
        secondTimelineManager.waitTimelineSpinner();
        secondContentManager.waitContentSpinner();

        timelineManager.clickCampaign("campaign:160411100229908:1460368949908:1460383722345");
        campaignManager.open();

        //for Campaign name
        WebElement name = getWebDriver().findElement(By.cssSelector(CAMPAIGN_NAME_INPUT));
        applicationManager.sendKeysScript(name, "TEST");

        campaignManager.save();
        campaignManager.close();

        checkAutomaticRefreshNotification();

        WebElement campaignName = secondDriver.findElement(By.cssSelector(CAMPAIGN_OVERVIEW_NAME));
        Assert.assertEquals(campaignName.getText().trim(), "TEST");

        WebElement contentName = secondDriver.findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText().trim(), "TEST");

    }

    @Test
    public void editPlannedQuantity() {

        logger.info("editPlannedQuantity");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_EE.getUsername(), LoginCredentials.USER_EE.getPassword());
        }
        timelineManager.setNewDateForDatePicker(startDate, endDate);

        secondTimelineManager.setNewDateForDatePicker(startDate, endDate);
        secondContentManager.waitContentSpinner();

        WebElement campaignClick = secondDriver.findElement(By.cssSelector("*[uid='campaign:160411100229908:1460368949908:1460383722345']"));
        campaignClick.click();
        secondTimelineManager.waitTimelineSpinner();
        secondContentManager.waitContentSpinner();

        timelineManager.clickCampaign("campaign:160411100229908:1460368949908:1460383722345");

        campaignManager.open();

        //for Planned Quantity
        WebElement plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        plannedQuantity.clear();
        plannedQuantity.sendKeys("1.74");

        campaignManager.save();
        campaignManager.close();

        checkAutomaticRefreshNotification();

        WebElement plannedQ = secondDriver.findElement(By.cssSelector(".plannedValue.blueText"));
        Assert.assertEquals(plannedQ.getText().trim(), "1.7  t");

    }
}
