package de.brueckner.fph.managers;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;

/**
 * Utility class that handles different actions that need to be performed on the application settings dialog
 */
public class ApplicationSettingsManager {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsManager.class);
    public static final String WEIGHT_SOURCE_LIST_SELECTOR = ".settingsList li:nth-child(1) .select2-chosen";
    public static final String DATE_FORMAT_LIST_SELECTOR = ".settingsList li:nth-child(2) .select2-chosen";
    public static final String QUALITY_FORMAT_LIST_SELECTOR = ".settingsList li:nth-child(4) .select2-chosen";
    public static final String ROLL_ID_LIST_SELECTOR = ".settingsList li:nth-child(5) .select2-chosen";
    public static final String DETAILED_MATERIAL_TABLE_INPUT = "#settingsModal li:nth-child(6) input";
    public static final String START_PERIOD_INPUT = "#settingsModal li:nth-child(7) input";
    public static final String PRODUCTION_TREND_INPUT = "#settingsModal li:nth-child(8) input";
    public static final String THICKNESS_TREND_INPUT = "#settingsModal li:nth-child(9) input";
    public static final String ROLL_MAX_INPUT = "#settingsModal li:nth-child(10) input";
    private static final String SETTINGS_MENU_ITEM_CSS_SELECTOR = ".leftMenuSlideContainer .globalSettings > ul > li:nth-child(3)";
    public static final String SETTINGS_MODAL_SELECTOR = ".settingsModal";
    public static final String CANCEL_SAVE = "#settingsModal .dialogButton.dbCancelClose";
    public static final String SAVE = "#settingsModal .dbSaveClose";

    private WebDriver driver;
    private ApplicationManager applicationManager;

    public ApplicationSettingsManager(WebDriver driver) {
        this.driver = driver;
        applicationManager = new ApplicationManager(driver);
    }

    public void openSettings() {
        applicationManager.openLeftMenu();
        applicationManager.selectMenuItem(SETTINGS_MENU_ITEM_CSS_SELECTOR);

        logger.info("Wait for setting modal to be visible");
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SETTINGS_MODAL_SELECTOR)));
    }


    public void setRollId(String text) {
        openSettings();
        WebElement rollId = driver.findElement(By.cssSelector(ROLL_ID_LIST_SELECTOR));
        rollId.click();

        logger.debug("Select new value in roll ID dropdown");
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.className("select2-result-label")));
        WebLocator iD = new WebLocator().setClasses("select2-result-label").setText(text);
        iD.click();
        saveSettingsByPressingEnter();
        applicationManager.waitSpinner();
    }

    public void setWeightSource(String text, int dropdownToSelect) {
        openSettings();
        List<WebElement> weightSourceLine = driver.findElements(By.cssSelector(WEIGHT_SOURCE_LIST_SELECTOR));
        weightSourceLine.get(dropdownToSelect).click();
        logger.debug("Select new value in weight source dropdown");

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.className("select2-result-label")));
        WebLocator weightSource = new WebLocator().setClasses("select2-result-label").setText(text);
        weightSource.click();

        saveSettingsByPressingEnter();
        applicationManager.waitSpinner();
    }

    public void setRollQuality(String quality, int dropdownToSelect) {

        openSettings();

        logger.debug("Open quality format dropdown");

        List<WebElement> dateFormat = driver.findElements(By.cssSelector(QUALITY_FORMAT_LIST_SELECTOR));
        dateFormat.get(dropdownToSelect).click();
        logger.debug("Select new value in date format dropdown");

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.className("select2-result-label")));
        WebLocator dateFormatLong = new WebLocator().setClasses("select2-result-label").setText(quality);
        dateFormatLong.click();

        saveSettingsByPressingEnter();
        applicationManager.waitSpinner();
    }

    public void setNewDateOrTimeFormat(String text, int dropdownToSelect) {
        openSettings();

        logger.debug("Open date format dropdown");

        List<WebElement> dateFormat = driver.findElements(By.cssSelector(DATE_FORMAT_LIST_SELECTOR));
        dateFormat.get(dropdownToSelect).click();

        logger.debug("Select new value in date format dropdown");
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.className("select2-result-label")));
        WebLocator dateFormatLong = new WebLocator().setClasses("select2-result-label").setText(text);
        dateFormatLong.click();

        saveSettingsByPressingEnter();
        applicationManager.waitSpinner();
    }

    public void setNewDateFormatShort(String text) {
        openSettings();
        List<WebElement> dateFormat = driver.findElements(By.cssSelector(".settingsList li:nth-child(2) .select2-chosen"));
        dateFormat.get(1).click();
        Utils.sleep(500);
        WebLocator dateFormatShort = new WebLocator().setClasses("select2-result-label").setText(text);
        dateFormatShort.click();
        saveSettingsByPressingEnter();
        applicationManager.waitSpinner();
    }

    public void detailedMaterialTableDays(String index) {
        WebElement detailedMaterialTableInput = driver.findElement(By.cssSelector("#settingsModal ul > li:nth-child(6) input"));
        detailedMaterialTableInput.clear();
        detailedMaterialTableInput.sendKeys(index);
    }

    public void rollMaxDays(String index) {
        WebElement rollMaxDays = driver.findElement(By.cssSelector("#settingsModal ul > li:nth-child(10) input"));
        rollMaxDays.clear();
        rollMaxDays.sendKeys(index);
    }

    public void saveSettingsByPressingEnter() {
        logger.debug("Sate settings by pressing enter key");
        Keyboard Enter = ((RemoteWebDriver) driver).getKeyboard();
        Enter.pressKey(Keys.ENTER);
        applicationManager.waitLogo();
    }

    public void saveSettings() {
        WebElement saveButton = driver.findElement(By.cssSelector("#settingsModal .dialogButton.dbSaveClose"));
        saveButton.click();
        applicationManager.waitLogo();
    }
}
