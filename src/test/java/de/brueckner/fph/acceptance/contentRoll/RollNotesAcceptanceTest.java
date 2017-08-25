package de.brueckner.fph.acceptance.contentRoll;

import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.MATERIAL_TAB_TEXT;
import static de.brueckner.fph.managers.ContentManager.NOTES_TAB_TEXT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class RollNotesAcceptanceTest extends AbstractApplicationSettingsTest {
    /**
     * The logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RollNotesAcceptanceTest.class);
    /**
     * The name of the roll used in tests
     */
    static final String CONST_ROLL_NAME = "A 160413 1449";
    /**
     * The UID of the roll used in tests
     */
    static final String CONST_ROLL_UID = "roll:A160413064950:1460530190433:1460535116127";

    /**
     * Setup the environment by:
     * <ul>
     * <li>perform login</li>
     * <li>set the date range</li>
     * <li>select the roll</li>
     * <li>open the Notes tab</li>
     * </ul>
     */
    private void prepareForTest() {
        //perform the login
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");
        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll(CONST_ROLL_UID);
        //test if the roll is selected
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".rollCampaignName"), CONST_ROLL_NAME));
        //click on the Notes tab
        contentManager.openTab(NOTES_TAB_TEXT);
    }

    /**
     * Set the new value in the text from the notes tab.
     * <br/>
     * The following steps are performed:
     * <ul>
     * <li>locate the text field containing the text</li>
     * <li>update the content of the text field to the newly specified value</li>
     * <li>wait for save button to be available</li>
     * <li><get the save button and press it</li>
     * <li>wait for the content to be refreshed</li>
     * <li>test that the value after the refresh is the one specified as input</li>
     * </ul>
     *
     * @param newValue
     */
    private void setNewText(String newValue) {
        //save a note
        WebElement rollNoteText = getWebDriver().findElement(By.cssSelector("#rollNoteText"));
        //change the text
        applicationManager.sendKeysScript(rollNoteText, newValue);
        contentManager.waitContentSpinner();
        //wait for the button to become enabled
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.not(ExpectedConditions.attributeContains(By.cssSelector(".dbSaveDiskWhiteClose"), "class", "disabled")));
        //new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".dbSaveDiskWhiteClose")));
        WebElement dbSaveClose = getWebDriver().findElement(By.cssSelector(".dbSaveDiskWhiteClose"));
        dbSaveClose.click();
        contentManager.waitContentSpinner();
        //test the saving
        rollNoteText = getWebDriver().findElement(By.cssSelector("#rollNoteText"));
        Assert.assertEquals(rollNoteText.getAttribute("value"), newValue);
    }

    /**
     * Helper method storing the duplicate code used to prepare the call for the confirmation on tab changed when the
     * text of the note is updated.
     * <br/>
     * The following actions are performed:
     * <ul>
     * <li>get the control contaiing the text for the note</li>
     * <li>call opening of the material tab</li>
     * <li>wait for the confirmation to be displayed</li>
     * <li>test the content of the confirmation dialog.</li>
     * </ul>
     */
    private void setupNotificationOnChange() {
        WebElement rollNoteText = getWebDriver().findElement(By.cssSelector("#rollNoteText"));
        applicationManager.sendKeysScript(rollNoteText, "2");
        contentManager.openTab(MATERIAL_TAB_TEXT);
        //wait that the notification to be displayed
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notificationContainer")));
        //get the notification
        WebElement notificationContainer = getWebDriver().findElement(By.cssSelector(".notificationContainer"));
        //asert notification content
        Assert.assertEquals(notificationContainer.getText(), "Save changes\n" +
                "Do you want to save changes before leaving the page?\n" +
                "YES\n" +
                "NO");
    }

    /**
     * The test covering the following cases:
     * <ul>
     * <li>save a value</li>
     * <li>cancel editing</li>
     * <li>confirmation on tab change - confirm data loss</li>
     * <li>confirmation on tab change - confirm save</li>
     * </ul>
     */
    @Test
    public void rollNotesSaveCancelAcceptanceTest() {
        logger.info("rollNotesSaveCancelAcceptanceTest");
        prepareForTest();

        //SAVE
        setNewText("TEST TEXT");

        //CANCEL
        WebElement rollNoteText = getWebDriver().findElement(By.cssSelector("#rollNoteText"));
        applicationManager.sendKeysScript(rollNoteText, "CANCEL TEST TEXT");
        //get the cancel button
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.not(ExpectedConditions.attributeContains(By.cssSelector(".dbCancelWhiteClose"), "class", "disabled")));
        WebElement dbCancelClose = getWebDriver().findElement(By.cssSelector(".dbCancelWhiteClose"));
        dbCancelClose.click();
        contentManager.waitContentSpinner();
        //test the saving
        rollNoteText = getWebDriver().findElement(By.cssSelector("#rollNoteText"));
        Assert.assertEquals(rollNoteText.getAttribute("value"), "TEST TEXT");

        //testing the SAVE on the CONFIRMATION when the tab is changed
        setupNotificationOnChange();
        //get the buttons from the dialog
        WebElement confirmButton = getWebDriver().findElement(By.xpath("//button[contains(@class, 'confirmButtons') and contains(text(), 'No')]"));
        confirmButton.click();
        contentManager.waitContentSpinner();
        contentManager.openTab(NOTES_TAB_TEXT);
        contentManager.waitContentSpinner();
        rollNoteText = getWebDriver().findElement(By.cssSelector("#rollNoteText"));
        Assert.assertEquals(rollNoteText.getAttribute("value"), "TEST TEXT");

        //testing the CANCEL on the CONFIRMATION when the tab is changed
        setupNotificationOnChange();
        confirmButton = getWebDriver().findElement(By.xpath("//button[contains(@class, 'confirmButtons') and contains(text(), 'Yes')]"));
        confirmButton.click();
        contentManager.waitContentSpinner();
        contentManager.openTab(NOTES_TAB_TEXT);
        contentManager.waitContentSpinner();
        rollNoteText = getWebDriver().findElement(By.cssSelector("#rollNoteText"));
        Assert.assertEquals(rollNoteText.getAttribute("value"), "2");

        //RESTORE the original value
        setNewText("");
    }

    /**
     * The test covering the note representation in timeline.
     */
    @Test
    public void rollNotesTimelineIconsAcceptanceTest() {
        logger.info("rollNotesTimelineIconsAcceptanceTest");

        //setup environment
        prepareForTest();

        //save a note
        setNewText("TEST 1");

        //check note tooltip in timeline
        WebElement rollNote = getWebDriver().findElement(By.cssSelector(".rollNote"));
        MouseUtils.mouseOver(rollNote, getWebDriver());
        WebElement noteTooltip = getWebDriver().findElement(By.cssSelector(".timelinePopover "));
        Assert.assertEquals(noteTooltip.getText().trim(), DateUtils.date("2016-04-13 06:49:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "  -  " + DateUtils.date("2016-04-13 08:11:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "\n" +
                "TEST 1");

        //restore the value
        setNewText("");
    }

    @Test
    public void rollNotesTreeIconsAcceptance() {
        logger.info("rollNotesTreeIconsAcceptance");
        //setup environment
        prepareForTest();

        //add a roll note
        setNewText("TEST 1");

        //check note tooltip in tree
        treeManager.openTree();
        WebElement noteInTree = getWebDriver().findElement(By.cssSelector(".rollNote.notTreeClick"));
        MouseUtils.mouseOver(noteInTree, getWebDriver());
        WebElement treePopover = getWebDriver().findElement(By.cssSelector(".treePopover"));
        Assert.assertEquals(treePopover.getText().trim(), DateUtils.date("2016-04-13 06:49:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "  -  " + DateUtils.date("2016-04-13 08:11:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "\n" +
                "TEST 1");

        //restore the value
        setNewText("");
    }
}
