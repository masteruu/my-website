package de.brueckner.fph.acceptance.applicationSettings;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.managers.CampaignManager;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.RollIdOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static de.brueckner.fph.managers.ContentManager.*;

public class ApplicationSettingsRollIDAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsRollIDAcceptanceTest.class);

    String startDateString = "2016-04-12 21:00:00";
    String endDateString = "2016-04-13 03:00:00";

    @Test //roll id BMS_ID
    public void applicationSettingsRollID_BMS_ID_AcceptanceTest() {
        logger.info("applicationSettingsRollID_BMS_ID_AcceptanceTest");
        testChangeRollId(RollIdOption.OPTION_BMS_ID.getOptionText(), "A 160413 0605");
    }

    @Test  //roll id CUSTOMER_ID
    public void applicationSettingsRollID_CUSTOMER_ID_AcceptanceTest() {
        logger.info("applicationSettingsRollID_CUSTOMER_ID_AcceptanceTest");
        testChangeRollId(RollIdOption.OPTION_CUSTOMER_ID.getOptionText(), "A160412220525");
    }

    @Test  //roll id TECHNICAL_ID
    public void applicationSettingsRollID_TECHNICAL_ID_AcceptanceTest() {
        logger.info("applicationSettingsRollID_TECHNICAL_ID_AcceptanceTest");

        testChangeRollId(RollIdOption.OPTION_TECHNICAL_ID.getOptionText(), "A160412220525");

    }

    private void testChangeRollId(String optionText, String rollID) {
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker(startDateString, endDateString);

        timelineManager.clickRoll("roll:A160412220525:1460498725459:1460504913271");
        contentManager.waitContentSpinner();

        applicationSettingsManager.setRollId(optionText);
        checkRollIdBreadcrumb(rollID);
        checkRollIdRollOverview(rollID);
        checkRollIdTimeline(rollID);
        checkRollIdTimelineTooltip(rollID);
        checkRollIdContent(rollID);
        checkRollIdRollsTable(rollID);
        checkRollIsTree(rollID);
        checkRollIsTreeTooltip(rollID);
        checkRollIdCampaignManager(rollID);
    }

    private void checkRollIdCampaignManager(String rollID) {
        CampaignManager campaignManager = new CampaignManager(getWebDriver());
        campaignManager.open();
        WebLocator nameInCampaignManager = new WebLocator().setText(rollID);
        nameInCampaignManager.assertReady();
    }

    private void checkRollIsTree(String rollID) {
        timelineManager.clickRoll("roll:A160412220525:1460498725459:1460504913271");
        treeManager.openTree();
        WebLocator treeContainer = new WebLocator().setId("tree");
        WebLocator nameInTree = new WebLocator(treeContainer).setText(rollID);
        nameInTree.assertReady();
    }

    private void checkRollIsTreeTooltip(String rollID) {
        Assert.assertEquals(applicationManager.getTreeTooltipTitle(5), rollID);
    }

    private void checkRollIdRollsTable(String rollID) {
        timelineManager.clickCampaign("campaign:160412114726310:1460461646310:1460511701759");
        contentManager.waitContentSpinner();

        contentManager.openTab(ROLLS_TAB_TEXT);
        WebLocator checkRollInTable = new WebLocator().setText(rollID).setRenderMillis(5000);
        checkRollInTable.assertReady();
    }

    private void checkRollIdContent(String rollID) {
        WebElement checkID = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(checkID.getText(), rollID, "roll ID in content does not have the correct format");
    }

    private void checkRollIdTimeline(String rollID) {
        WebElement checkIDs = getWebDriver().findElement(By.cssSelector("#timelineMainSvg g.rollStatusLane g:nth-child(4) text"));
        Assert.assertEquals(checkIDs.getText(), rollID, "roll ID in timeline does not have the correct format");
    }

    private void checkRollIdTimelineTooltip(String rollID) {
        Assert.assertEquals(applicationManager.getTimelineTooltipTitleText("*[uid='roll:A160412220525:1460498725459:1460504913271']"), rollID);
    }

    private void checkRollIdRollOverview(String rollID) {
        WebElement checkID = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(checkID.getText(), rollID, "roll ID in roll overview does not have the correct format");
    }

    private void checkRollIdBreadcrumb(String rollID) {
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160411_11T25u_400m_4.1t", "PRODUCTIVE", rollID));
    }
}