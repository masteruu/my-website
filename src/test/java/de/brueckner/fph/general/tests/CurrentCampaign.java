package de.brueckner.fph.general.tests;

import de.brueckner.fph.general.AbstractGeneralTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CurrentCampaign extends AbstractGeneralTest {

    private static final Logger logger = LoggerFactory.getLogger(CurrentCampaign.class);

    @Test
    public void currentCampaignOverviewTest() {

        //open tree
        treeManager.openTree();
        logger.info("Tree was opened");

        //check campaign name in tree to match the name in content
        //check that the current campaign has no end time
        openCurrentCampaign();

        logger.info("Current campaign is opened successfully");

        WebElement endDateTime = getWebDriver().findElement(By.cssSelector(".contentEndPeriodContainer"));
        Assert.assertEquals(endDateTime.getText().trim(), "...");

        logger.info("The current campaign has no end time");

    }

    @Test
    public void currentCampaignTabsTest() {

        //open tree
        treeManager.openTree();
        logger.info("Tree was opened");

        //check campaign name in tree to match the name in content
        //check that the current campaign has no end time
        openCurrentCampaign();

        logger.info("Current campaign is opened successfully");

        checkRollsTab();
        checkMaterialTab();
        //checkAlertTab();
        checkThicknessTab();

    }

    void openCurrentCampaign() {
        List<WebElement> currentCampaign = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        currentCampaign.get(1).click();
        contentManager.waitContentSpinner();
    }
}
