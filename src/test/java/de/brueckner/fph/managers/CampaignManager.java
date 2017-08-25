package de.brueckner.fph.managers;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.util.DateFormatShortOption;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.MouseUtils;
import de.brueckner.fph.util.TimeFormatShortOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;

public class CampaignManager {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManager.class);

    private static final String CAMPAIGN_MANAGER_MENU_ITEM_CSS_SELECTOR = ".topSettings li:nth-child(1)";
    public static final String PRODUCT_DROPDOWN = ".campaignManager .select2-chosen";
    private static final String PRODUCT_OPTION1 = ".select2-results li:nth-child(1)";
    private static final String PRODUCT_OPTION2 = ".select2-results li:nth-child(2)";
    private static final String PRODUCT_OPTION3 = ".select2-results li:nth-child(3)";
    private static final String SAVE = ".campaignManager .dialogButton.dbSaveDiskClose";
    private static final String CLOSE = ".campaignManager .dialogButton.dbCancelClose";
    private static final String SHIFT_BUTTON = ".campaignManager .shiftMode";
    private static final String EDIT_BUTTON = ".campaignManager .editMode";
    private static final String CUT_BUTTON = ".campaignManager .cutMode";
    private static final String CAMPAIGN_BUTTON = ".campaignManager .lastCampaignClick";
    private static final String GOTONOW_BUTTON = ".campaignManager .goToCurrent";
    private static final String LAST8H_BUTTON = ".campaignManager .last8Hours";
    private static final String LAST24H_BUTTON = ".campaignManager .last24Hours";
    public static final String CAMPAIGN_NAME_INPUT = ".campaignManager #campaignNameInput";
    public static final String CAMPAIGN_PLANNED_QUANTITY_INPUT = ".campaignManager .base-number-input";
    public static final int CUT_ANIMATION_DURATION_MILLISECONDS = 2000;

    /**
     * Spinners
     */
    private static final String TIMELINE_SPINNER_CSS_SELECTOR = ".campaignManager #timeline .contentSpinner";
    private static final String CONTENT_SPRINNET_CSS_SELECTOR = ".spinnerTransparent";

    public static final String CM_LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR = ".campaignManager #timeline .paddingContainer .leftSideText";
    public static final String CM_RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR = ".campaignManager #timeline .paddingContainer .rightSideText";
    private static final String DATE_PICKER_START_INPUT_SELECTOR = ".daterangepicker_start_input .input-mini";
    private static final String DATE_PICKER_END_INPUT_SELECTOR = ".daterangepicker_end_input .input-mini";
    private static final String DATEPICKER_SAVE_BUTTON_SELECTOR = ".daterangepicker .dialogButton.dbSaveClose";

    private WebDriver driver;
    private ApplicationManager applicationManager;

    public CampaignManager(WebDriver driver) {
        this.driver = driver;
        applicationManager = new ApplicationManager(driver);
    }

    public void open() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        applicationManager.openLeftMenu();
        applicationManager.selectMenuItem(CAMPAIGN_MANAGER_MENU_ITEM_CSS_SELECTOR);

        logger.info("Wait for campaign manager modal to be visible");
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("campaignManager")));
        waitSpinnerCM();
    }

    public void cutButton() {
        driver.findElement(By.cssSelector(CUT_BUTTON)).click();
        waitSpinnerCM();
    }

    public void productDropdown() {
        driver.findElement(By.cssSelector(PRODUCT_DROPDOWN)).click();
    }

    public void productOption1() {
        driver.findElement(By.cssSelector(PRODUCT_OPTION1)).click();
    }

    public void productOption2() {
        driver.findElement(By.cssSelector(PRODUCT_OPTION2)).click();
    }

    public void productOption3() {
        driver.findElement(By.cssSelector(PRODUCT_OPTION3)).click();
    }

    public void save() {
        driver.findElement(By.cssSelector(SAVE)).click();
        waitSpinnerCM();
    }

    public void close() {
        logger.info("Close campaign manager");
        WebDriverWait webDriverWait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(CLOSE)))
                .click();

        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("campaignManager")));
        logger.info("Campaign manager closed successfully");
    }

    public void shiftButton() {
        driver.findElement(By.cssSelector(SHIFT_BUTTON)).click();
        waitSpinnerCM();
    }

    public void editButton() {
        driver.findElement(By.cssSelector(EDIT_BUTTON)).click();
        waitSpinnerCM();
    }

    public void campaignButton() {
        driver.findElement(By.cssSelector(CAMPAIGN_BUTTON)).click();
    }

    public void goToNowButton() {
        driver.findElement(By.cssSelector(GOTONOW_BUTTON)).click();
    }

    public void last8HButton() {
        driver.findElement(By.cssSelector(LAST8H_BUTTON)).click();
    }

    public void last24HButton() {
        driver.findElement(By.cssSelector(LAST24H_BUTTON)).click();
    }

    public void setDatePickerCM(String startDate,
                                String endDate) {
        /** Default date picker format is dd.MM.yy  HH:mm */
        String datePickerDefaultFormat = DateUtils.getNewDatepickerDateTimeFormatShort(DateFormatShortOption.OPTION_WITH_POINT,
                TimeFormatShortOption.OPTION_WITH_COLON);

        String convertedStartDate = DateUtils.date(startDate, datePickerDefaultFormat);
        String convertedEndDate = DateUtils.date(endDate, datePickerDefaultFormat);

        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        WebElement datePickerLeft = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(CM_LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR)));
        datePickerLeft.click();

        //wait for date picker loading to finish
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(DATEPICKER_SAVE_BUTTON_SELECTOR)));

        WebElement startDateInput = driver.findElement(By.cssSelector(DATE_PICKER_START_INPUT_SELECTOR));
        applicationManager.sendKeysScript(startDateInput, convertedStartDate);
        logger.debug("Start date in date picker changed to {}", convertedStartDate);

        WebElement endDateInput = driver.findElement(By.cssSelector(DATE_PICKER_END_INPUT_SELECTOR));
        applicationManager.sendKeysScript(endDateInput, convertedEndDate);
        logger.debug("End date in date picker changed to {}", convertedEndDate);

        WebElement save = driver.findElement(By.cssSelector(DATEPICKER_SAVE_BUTTON_SELECTOR));
        save.click();

        waitSpinnerCM();
    }

    public void checkCampaignStartEnd(String name, String start, String end, String duration) {
        WebLocator tooltipTitle = new WebLocator().setClasses("tooltipTitle");
        tooltipTitle.assertReady();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("treeTooltip")));

        WebElement campaignStartEndTime = driver.findElement(By.cssSelector(".treeTooltip"));
        Assert.assertEquals(campaignStartEndTime.getText(), name + "\n" +
                start + "\n" +
                end + duration);
    }

    public String getNoteTooltipCampaignManager() {
        WebElement noteInCampaignManager = driver.findElement(By.cssSelector(".campaignManager .note div"));
        MouseUtils.mouseOver(noteInCampaignManager, driver);

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("timelinePopover")));

        String tooltipText = driver.findElement(By.cssSelector(".timelinePopover")).getText();
        return tooltipText;
    }

    public void waitSpinnerCM() {
        Utils.sleep(2000);
        WebDriverWait webDriverWait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        ApplicationManager.waitForAngularLoad(webDriverWait);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(CONTENT_SPRINNET_CSS_SELECTOR)));
        logger.debug("Content spinner done");

        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TIMELINE_SPINNER_CSS_SELECTOR)));
        logger.debug("Timeline spinner done");


    }

}
