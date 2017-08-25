package de.brueckner.fph.acceptance.contentRoll;


import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.managers.ContentManager;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.BACK_TO_LAST_VIEW_HEADER_BUTTON;

public class RollWeightAcceptanceTest extends AbstractApplicationSettingsTest {
    /**
     * The name of the roll used in tests
     */
    static final String CONST_ROLL_NAME = "A 160413 1449";
    /**
     * The UID of the roll used in tests
     */
    static final String CONST_ROLL_UID = "roll:A160413064950:1460530190433:1460535116127";
    static final String EDIT_BUTTON_LEFT = ".leftRollContainer > div.cols.leftColumn > div.pullDown > div:nth-child(5) > div.weight > span.dButtonWrapper.pull-right";
    static final String EDIT_BUTTON_RIGHT = ".leftRollContainer > div.cols.rightColumn.rightColumnOpacity > div > div:nth-child(4) > div.weight > span.dButtonWrapper.pull-right > span";
    static final String INPUT_LEFT = ".leftRollContainer > div.cols.leftColumn > div.pullDown > div:nth-child(5) > div.weight > span.editInPlace > span.inputCover.ng-scope > number-input > div > input";
    static final String INPUT_RIGHT = ".leftRollContainer > div.cols.rightColumn.rightColumnOpacity > div > div:nth-child(4) > div.weight > span.editInPlace > span.inputCover.ng-scope > number-input > div > input";
    static final String VALUE_RIGHT = ".leftRollContainer > div.cols.rightColumn.rightColumnOpacity > div > div:nth-child(4) > div.weight > span.bolded.valueField";
    static final String VALUE_LEFT = ".leftRollContainer > div.cols.leftColumn > div.pullDown > div:nth-child(5) > div.weight > span.bolded.valueField";
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RollWeightAcceptanceTest.class);

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
        contentManager.waitContentSpinner();
        //test if the roll is selected
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".rollCampaignName"), CONST_ROLL_NAME));
    }

    /**
     * Method used to set and test the setting of a new weight.
     * <br/>
     * Please consider that the method is also testing the value displayed after save.
     *
     * @param newValue     the new value of the weight.
     * @param layoutColumn teh current layout to be used to see which form to use.
     */
    private void setNewWeight(String newValue, LayoutColumn layoutColumn, String editButtonSelector, String input, String value) {

        WebElement editInPlaceIcon = getWebDriver().findElement(By.cssSelector(editButtonSelector));
        editInPlaceIcon.click();

        //wait for the edit text field to be shown
        //get the text field used to input new value
        WebElement inputCover = getWebDriver().findElement(By.cssSelector(input));
        inputCover.clear();
        inputCover.sendKeys(newValue);
        //get the save button
        Keyboard pressEnter = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        pressEnter.pressKey(Keys.ENTER);

        contentManager.waitContentSpinner();
        //get the label containing the new newly set value
        WebElement valueLabel = getWebDriver().findElement(By.cssSelector(value));
        Assert.assertEquals(valueLabel.getText().trim(), newValue);
        contentManager.waitContentSpinner();
    }

    /**
     * Tests the value displayed for the current roll in the roll table of the related campaign.
     *
     * @param newValue the value to be tested
     */
    private void testWeightOnCampaignRolls(String newValue) {
        //select the campaign to which the roll belongs
        timelineManager.clickCampaign("campaign:160413014141759:1460511701759:1460690794472");
        contentManager.waitContentSpinner();

        //reload the UI in order to fix a Selenium problem
        WebElement reload = getWebDriver().findElement(By.cssSelector(ContentManager.RELOAD_HEADER_BUTTON));
        reload.click();
        applicationManager.waitLogo();
        //wait for the overview to be displayed
        String xPath = "//span[../../../div[contains(@class, 'campaignName') and contains(@class, 'splittableName')] and not(contains(@class, 'campaignFlagSpan'))]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        contentManager.waitContentSpinner();
        //select the Rolls tab
        contentManager.openTab(ContentManager.ROLLS_TAB_TEXT);
        //test the weight in the rolls table
        xPath = "//div[contains(@class, 'ngRow') and ./div[contains(@class, 'col_name_rollLabelId')]/div/div/span[text()='" + CONST_ROLL_NAME + "'] and ./div[contains(@class, 'col_name_weight')]/div/div/span[text()='" + newValue + "']]";
        WebElement weightCell = getWebDriver().findElement(By.xpath(xPath));
    }

    @Test
    public void rollWeightAcceptance() {
        logger.info("rollWeightAcceptance");
        prepareForTest();

        //CHANGE the weight on the LEFT column
        //get the edit button
        setNewWeight("123", LayoutColumn.LEFT, EDIT_BUTTON_LEFT, INPUT_LEFT, VALUE_LEFT);
        testWeightOnCampaignRolls("123");


        //select back the roll
        WebElement historyBack = getWebDriver().findElement(By.cssSelector(BACK_TO_LAST_VIEW_HEADER_BUTTON));
        historyBack.click();
        contentManager.waitContentSpinner();

        //CANCEL the editing
        String xPath = "//span[@ng-click='rpc.startWeightEdit()' and parent::div[@class='weight'] and ancestor::div[contains(@class, 'leftColumn')]]";
        WebElement editInPlaceIcon = getWebDriver().findElement(By.xpath(xPath));
        editInPlaceIcon.click();
        xPath = "//input[@maxlength='5' and ancestor::div[contains(@class, 'leftColumn')]]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        //get the text field used to input new value
        WebElement inputCover = getWebDriver().findElement(By.xpath(xPath));
        inputCover.clear();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tooltip-inner")));
        //test the tooltip
        List<WebElement> tooltip = getWebDriver().findElements(By.cssSelector(".tooltip-inner"));
        Assert.assertEquals(tooltip.get(0).getText().trim(), "The value should be positive");
        //inset a new value
        inputCover.sendKeys("456");
        //get the cancel button and press it
        xPath = "//span[contains(@class, 'db-CancelWhiteClose') and ancestor::div[contains(@class, 'leftColumn')]]";
        WebElement cancelButton = getWebDriver().findElement(By.xpath(xPath));
        cancelButton.click();
        xPath = "//span[contains(@class, 'valueField') and following-sibling::span[contains(text(), 'kg')] and ancestor::div[contains(@class, 'leftColumn')]]";
        WebElement valueLabel = getWebDriver().findElement(By.xpath(xPath));
        Assert.assertEquals(valueLabel.getText().trim(), "123");

        setNewWeight("1", LayoutColumn.LEFT, EDIT_BUTTON_LEFT, INPUT_LEFT, VALUE_LEFT);

        //expanded details
        WebElement toggleRollDetails = getWebDriver().findElement(By.xpath("//div[@class='toggleRollDetails']/span"));
        toggleRollDetails.click();
        contentManager.waitContentSpinner();

        //test CHANGE on the RIGHT column
        setNewWeight("123", LayoutColumn.RIGHT, EDIT_BUTTON_RIGHT, INPUT_RIGHT, VALUE_RIGHT);
        testWeightOnCampaignRolls("123");

        historyBack = getWebDriver().findElement(By.cssSelector(BACK_TO_LAST_VIEW_HEADER_BUTTON));
        historyBack.click();
        contentManager.waitContentSpinner();

        toggleRollDetails = getWebDriver().findElement(By.xpath("//div[@class='toggleRollDetails']/span"));
        toggleRollDetails.click();
        contentManager.waitContentSpinner();

        setNewWeight("1", LayoutColumn.LEFT, EDIT_BUTTON_RIGHT, INPUT_RIGHT, VALUE_RIGHT);

    }

    @Test
    public void rollWeightReturnEscapeAcceptance() {
        logger.info("rollWeightReturnEscapeAcceptance");
        prepareForTest();

        //compact details
        String xPath = "//span[@ng-click='rpc.startWeightEdit()' and parent::div[@class='weight'] and ancestor::div[contains(@class, '" + LayoutColumn.LEFT.htmlClass + "')]]";
        WebElement editInPlaceIcon = getWebDriver().findElement(By.xpath(xPath));
        editInPlaceIcon.click();
        //wait for the edit text field to be shown
        xPath = "//input[@maxlength='5' and ancestor::div[contains(@class, '" + LayoutColumn.LEFT.htmlClass + "')]]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        //get the text field used to input new value
        WebElement inputCover = getWebDriver().findElement(By.xpath(xPath));
        inputCover.clear();
        inputCover.sendKeys("123");
        //save with Enter
        applicationManager.pressEnter(); // teh save should be performed here
        contentManager.waitContentSpinner();
        //get the label containing the new newly set value
        xPath = "//span[contains(@class, 'valueField') and following-sibling::span[contains(text(), 'kg')] and ancestor::div[contains(@class, '" + LayoutColumn.LEFT.htmlClass + "')]]";
        WebElement valueLabel = getWebDriver().findElement(By.xpath(xPath));
        Assert.assertEquals(valueLabel.getText().trim(), "123");
        contentManager.waitContentSpinner();


        //Test the behavior on CANCEL
        xPath = "//span[@ng-click='rpc.startWeightEdit()' and parent::div[@class='weight'] and ancestor::div[contains(@class, '" + LayoutColumn.LEFT.htmlClass + "')]]";
        editInPlaceIcon = getWebDriver().findElement(By.xpath(xPath));
        editInPlaceIcon.click();
        //wait for the edit text field to be shown
        xPath = "//input[@maxlength='5' and ancestor::div[contains(@class, '" + LayoutColumn.LEFT.htmlClass + "')]]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        //get the text field used to input new value
        inputCover = getWebDriver().findElement(By.xpath(xPath));
        inputCover.clear();
        inputCover.sendKeys("1");
        applicationManager.pressEnter(); // save new value: 1

        xPath = "//span[contains(@class, 'valueField') and following-sibling::span[contains(text(), 'kg')] and ancestor::div[contains(@class, '" + LayoutColumn.LEFT.htmlClass + "')]]";
        valueLabel = getWebDriver().findElement(By.xpath(xPath));
        Assert.assertEquals(valueLabel.getText().trim(), "1");
        contentManager.waitContentSpinner();

        xPath = "//span[@ng-click='rpc.startWeightEdit()' and parent::div[@class='weight'] and ancestor::div[contains(@class, '" + LayoutColumn.LEFT.htmlClass + "')]]";
        editInPlaceIcon = getWebDriver().findElement(By.xpath(xPath));
        editInPlaceIcon.click();
        //wait for the edit text field to be shown
        xPath = "//input[@maxlength='5' and ancestor::div[contains(@class, '" + LayoutColumn.LEFT.htmlClass + "')]]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        //get the text field used to input new value
        inputCover = getWebDriver().findElement(By.xpath(xPath));
        inputCover.clear();
        inputCover.sendKeys("123");
        //press ESC for cancel
        Keyboard Enter = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        Enter.pressKey(Keys.ESCAPE);
        xPath = "//span[contains(@class, 'valueField') and following-sibling::span[contains(text(), 'kg')] and ancestor::div[contains(@class, '" + LayoutColumn.LEFT.htmlClass + "')]]";
        valueLabel = getWebDriver().findElement(By.xpath(xPath));
        Assert.assertEquals(valueLabel.getText().trim(), "1");

    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(GENERAL_SETTINGS_SCRIPT_NAME);
    }

    private enum LayoutColumn {

        LEFT("leftColumn"),
        RIGHT("rightColumn");
        String htmlClass;

        LayoutColumn(String htmlClass) {
            this.htmlClass = htmlClass;
        }
    }
}
