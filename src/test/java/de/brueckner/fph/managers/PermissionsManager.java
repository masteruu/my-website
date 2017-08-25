package de.brueckner.fph.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.*;
import static de.brueckner.fph.managers.NoteManager.NOTE_PRIVATE_CHECKBOX;

public class PermissionsManager {

    private static final Logger logger = LoggerFactory.getLogger(PermissionsManager.class);

    private WebDriver driver;
    private ApplicationManager applicationManager;
    private ApplicationSettingsManager applicationSettingsManager;
    private ContentManager contentManager;
    private NoteManager noteManager;
    private TimelineManager timelineManager;
    private CampaignManager campaignManager;
    private StateReportManager stateReportManager;

    public PermissionsManager(WebDriver driver) {
        this.driver = driver;
        applicationManager = new ApplicationManager(driver);
        applicationSettingsManager = new ApplicationSettingsManager(driver);
        noteManager = new NoteManager(driver);
        contentManager = new ContentManager(driver);
        timelineManager = new TimelineManager(driver);
        campaignManager = new CampaignManager(driver);
        stateReportManager = new StateReportManager(driver);
    }

    public void canAccessApplicationSettingsLowSettings() {

        //can access application settings low
        //check that all dropdowns are clickable (not disabled)
        applicationSettingsManager.openSettings();

        for (int i = 0; i < 9; i++) {
            List<WebElement> appSettingsHigh = driver.findElements(By.cssSelector(".settingsList .select2-chosen"));
            appSettingsHigh.get(i).click();
            new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-result-label")));

            WebElement selectResult = driver.findElement(By.cssSelector(".select2-result-label"));
            selectResult.click();
            new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-result-label")));
        }
    }

    public void cantAccessApplicationSettingsHighSettings() {

        //check that all application settings high inputs are disabled
        applicationSettingsManager.openSettings();

        List<WebElement> appSettingsLow = driver.findElements(By.cssSelector(".base-number-input"));
        Assert.assertEquals(appSettingsLow.get(0).getAttribute("disabled"), "true");
        Assert.assertEquals(appSettingsLow.get(1).getAttribute("disabled"), "true");
        Assert.assertEquals(appSettingsLow.get(2).getAttribute("disabled"), "true");
        Assert.assertEquals(appSettingsLow.get(3).getAttribute("disabled"), "true");
        Assert.assertEquals(appSettingsLow.get(4).getAttribute("disabled"), "true");
    }

    public void cantAccessStateReport() {

        try {
            WebElement stateReportButton = driver.findElement(By.cssSelector(".stateReportButton"));
            stateReportButton.click();
            Assert.fail();
            logger.info("State Report is active was found");
        } catch (NoSuchElementException e) {
            logger.info
                    ("State Report is not present");
        }
    }

    public void canAccessStateReport() {
        stateReportManager.open();
    }

    public void canCreateEditNote() {

        //add a note with a user
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.save();

        //delete the note
        noteManager.openNote();
        noteManager.deleteNote();
    }

    public void cantCreateEditNote() {
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".timelineHeader .notes.inactive")));
    }

    public void canChangeRollWeight() {

        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");

        contentManager.waitContentSpinner();

        //click on the weight button
        WebElement weightRollEditButton = driver.findElement(By.cssSelector(ROLL_WEIGHT_EDIT_BUTTON_COMPACTED));
        weightRollEditButton.click();

        //check if the weight input is present
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ROLL_WEIGHT_EDIT_INPUT_COMPACTED)));

    }

    public void cantChangeRollWeight() {
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");

        contentManager.waitContentSpinner();

        //the edit weight button is disabled
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ROLL_WEIGHT_EDIT_BUTTON_COMPACTED_DISABLED)));

    }

    public void canAddRollNote() {
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        contentManager.waitContentSpinner();

        //add a roll note
        contentManager.openTab(NOTES_TAB_TEXT);
        contentManager.waitContentSpinner();

        WebElement rollNote = driver.findElement(By.cssSelector(ROLL_NOTES_TEXT));
        rollNote.sendKeys("Text on roll note");

        WebElement saveRollNotes = driver.findElement(By.cssSelector(ROLL_NOTES_SAVE_BUTTON));
        saveRollNotes.click();
        contentManager.waitContentSpinner();

        //delete the note
        rollNote = driver.findElement(By.cssSelector(ROLL_NOTES_TEXT));
        rollNote.clear();
        contentManager.waitContentSpinner();

        saveRollNotes = driver.findElement(By.cssSelector(ROLL_NOTES_SAVE_BUTTON));
        saveRollNotes.click();
        contentManager.waitContentSpinner();
    }

    public void cantAddRollNote() {
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        contentManager.waitContentSpinner();

        //add a roll note
        contentManager.openTab(NOTES_TAB_TEXT);
        contentManager.waitContentSpinner();

        WebElement rollNotesText = driver.findElement(By.cssSelector(ROLL_NOTES_TEXT));
        Assert.assertEquals(rollNotesText.getAttribute("disabled"), "true");
    }

    public void canChangeVisualQuality() {
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        contentManager.waitContentSpinner();

        //click on the visual quality dropdown
        WebElement labQuality = driver.findElement(By.cssSelector(".visualQualityValue .select2-chosen"));
        labQuality.click();

        //check that the dropdown is opened
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select2-result-label")));
    }

    public void cantChangeVisualQuality() {
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".visualQuality .select2-container-disabled")));
    }

    public void cantChangeLaboratoryQuality() {

        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");

        contentManager.openTab(QUALITY_TAB_TEXT);

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".angularControllerContainer > div.tab-content.scrollbar > div > div > div > div > div.select2-container.select2-container-disabled.labQualityValue")));
    }

    public void canChangeLaboratoryQuality() {
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");

        contentManager.openTab(QUALITY_TAB_TEXT);

        WebElement openQualityDropdown = driver.findElement(By.cssSelector(".labQualityValue  .select2-chosen"));
        openQualityDropdown.click();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select2-result-label")));

    }

    public void canAccessCampaignManager() {
        campaignManager.open();
    }

    public void cantAccessCampaignManager() {
        WebElement disabledCM = driver.findElement(By.cssSelector(".topSettings li:nth-child(1)"));
        Assert.assertEquals(disabledCM.getCssValue("pointer-events"), "none", "The campaign manager can be opened by user view");
    }

    public void canAddPrivateNote() {

        //add a note
        noteManager.noteButton();
        noteManager.addNote();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_PRIVATE_CHECKBOX)));

    }

    public void cantAddPrivateNote() {

        //add a note
        noteManager.noteButton();
        noteManager.addNote();

        try {
            driver.findElement(By.cssSelector(NOTE_PRIVATE_CHECKBOX)).click();
            Assert.fail();
            logger.info("Private checkbox does not exist");
        } catch (NoSuchElementException e) {
            logger.info
                    ("Private checkbox exists");
        }

        //check number of machine part + caused by dropdowns
        List<WebElement> dropdowns = driver.findElements(By.cssSelector(".noteFormContainer .select2-container"));
        Assert.assertEquals(dropdowns.size(), 2);

    }

    public void cantAccessApplicationSettingsLowSettings() {

        applicationSettingsManager.openSettings();
        List<WebElement> disabled = driver.findElements(By.cssSelector("#settingsModal .select2-container-disabled"));
        Assert.assertEquals(disabled.get(0).getText().trim(), "Frd");
        Assert.assertEquals(disabled.get(0).getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(disabled.get(0).getCssValue("background-image"), "none");
        Assert.assertEquals(disabled.get(0).getCssValue("border"), "0px none rgb(51, 51, 51)");
        Assert.assertEquals(disabled.get(1).getText().trim(), "Scale");
        Assert.assertEquals(disabled.get(1).getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(disabled.get(1).getCssValue("background-image"), "none");
        Assert.assertEquals(disabled.get(1).getCssValue("border"), "0px none rgb(51, 51, 51)");
        Assert.assertEquals(disabled.get(2).getText().trim(), "dd.MM.yyyy");
        Assert.assertEquals(disabled.get(2).getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(disabled.get(2).getCssValue("background-image"), "none");
        Assert.assertEquals(disabled.get(2).getCssValue("border"), "0px none rgb(51, 51, 51)");
        Assert.assertEquals(disabled.get(3).getText().trim(), "dd.MM.yy");
        Assert.assertEquals(disabled.get(3).getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(disabled.get(3).getCssValue("background-image"), "none");
        Assert.assertEquals(disabled.get(3).getCssValue("border"), "0px none rgb(51, 51, 51)");
        Assert.assertEquals(disabled.get(4).getText().trim(), "HH:mm:ss");
        Assert.assertEquals(disabled.get(4).getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(disabled.get(4).getCssValue("background-image"), "none");
        Assert.assertEquals(disabled.get(4).getCssValue("border"), "0px none rgb(51, 51, 51)");
        Assert.assertEquals(disabled.get(5).getText().trim(), "HH:mm");
        Assert.assertEquals(disabled.get(5).getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(disabled.get(5).getCssValue("background-image"), "none");
        Assert.assertEquals(disabled.get(5).getCssValue("border"), "0px none rgb(51, 51, 51)");
        Assert.assertEquals(disabled.get(6).getText().trim(), "Frd");
        Assert.assertEquals(disabled.get(6).getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(disabled.get(6).getCssValue("background-image"), "none");
        Assert.assertEquals(disabled.get(6).getCssValue("border"), "0px none rgb(51, 51, 51)");
        Assert.assertEquals(disabled.get(7).getText().trim(), "Laboratory");
        Assert.assertEquals(disabled.get(7).getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(disabled.get(7).getCssValue("background-image"), "none");
        Assert.assertEquals(disabled.get(7).getCssValue("border"), "0px none rgb(51, 51, 51)");
        Assert.assertEquals(disabled.get(8).getText().trim(), "BMS_ID");
        Assert.assertEquals(disabled.get(8).getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(disabled.get(8).getCssValue("background-image"), "none");
        Assert.assertEquals(disabled.get(8).getCssValue("border"), "0px none rgb(51, 51, 51)");

    }
}
