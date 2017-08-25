package de.brueckner.fph.acceptance.componentCampaignManager;


import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.CampaignManager.*;
import static de.brueckner.fph.managers.ContentManager.*;

public class CampaignManagerEditAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerEditAcceptanceTest.class);

    @Test
    public void campaignManagerEditAcceptance() {

        logger.info("CampaignManagerEditAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        timelineManager.clickCampaign("campaign:160413014141759:1460511701759:1460690794472");
        contentManager.waitContentSpinner();

        campaignManager.open();

        //for Product name
        campaignManager.productDropdown();
        campaignManager.productOption3();

        //for campaign name
        WebElement name = getWebDriver().findElement(By.cssSelector(CAMPAIGN_NAME_INPUT));
        applicationManager.sendKeysScript(name, "TEST");

        //for planned quantity
        WebElement plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        plannedQuantity.clear();
        plannedQuantity.sendKeys("1.74");

        //save changes
        campaignManager.save();
        campaignManager.close();

        WebElement reloadTooltip = getWebDriver().findElement(By.cssSelector(RELOAD_HEADER_BUTTON));
        reloadTooltip.click();
        applicationManager.waitLogo();

        //check name in timeline
        WebElement campaign = getWebDriver().findElement(By.cssSelector("*[uid='campaign:160413014141759:1460511701759:1460690794472']"));
        Assert.assertEquals(campaign.getText(), "TEST", "Campaign name in timeline is not ok");

        //check product
        WebElement productName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_PRODUCT));
        Assert.assertEquals(productName.getText().trim(), "25um Plain", "Campaign product is not ok");


        //check campaign name in breadcrumb
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "TEST"));

        //check campaign name
        WebElement campaignName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_NAME));
        Assert.assertEquals(campaignName.getText().trim(), "TEST", "Campaign name is not ok");

        //check name in content
        WebElement contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "TEST", "Campaign name in content is not ok");

        //check campaign name in tree
        treeManager.openTree();
        List<WebElement> nameTree = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        Assert.assertEquals(nameTree.get(1).getText(), "TEST");

        campaignManager.open();

        //check planned quantity in campaign manager
        productName = getWebDriver().findElement(By.cssSelector(PRODUCT_DROPDOWN));
        Assert.assertEquals(productName.getText(), "25um Plain", "Campaign product name in campaign manager is not ok");

        //check campaign name in campaign manager
        name = getWebDriver().findElement(By.cssSelector(CAMPAIGN_NAME_INPUT));
        Assert.assertEquals(name.getAttribute("value"), "TEST", "Campaign name in campaign manager is not ok");

        //check planned quantity in campaign manager
        plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        Assert.assertEquals(plannedQuantity.getAttribute("value"), "1.7", "Campaign planned quantity in campaign manager is not ok");

        //enter/return looses focus on planned quantity
        plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        plannedQuantity.clear();
        plannedQuantity.sendKeys("123456.78");
        applicationManager.pressEnter();

        plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        Assert.assertEquals(plannedQuantity.getAttribute("value"), "123,456.8");

        //arrows affect tons
        WebElement down = getWebDriver().findElement(By.cssSelector(".campaignManager .down"));
        down.click();
        plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        Assert.assertEquals(plannedQuantity.getAttribute("value"), "123,455.8", "Campaign planned quantity format in campaign manager is not ok");

    }
}
