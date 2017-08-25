package de.brueckner.fph.managers;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.RollsAsFavoritesOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;

/**
 * Utility class that handles different actions that need to be performed on the content section
 */
public class ContentManager {

    private static final Logger logger = LoggerFactory.getLogger(ContentManager.class);

    public static final String CONTENT_SPINNER_SELECTOR = ".mainContent .contentSpinner";
    public static final String SHOW_DETAILS_MATERIAL_TABLE = ".mainContent .tab-content .viewMoreButton";
    private static final String TABLE_GROUPS_REMOVE_BUTTON_SELECTOR = ".mainContent #dynamicTable .ngHeaderContainer .ngRemoveGroup";
    public static final String CONTENT_START_DATE = ".contentStartPeriodContainer .contentPeriod.contentTitleDate";
    public static final String CONTENT_END_DATE = ".contentEndPeriodContainer .contentPeriod.contentTitleDate";
    public static final String CONTENT_START_PERIOD = ".contentStartPeriodContainer";
    public static final String CONTENT_END_PERIOD = ".contentEndPeriodContainer";
    public static final String CONTENT_NAME = ".contentName";
    public static final String ROLL_OVERVIEW_NAME = ".rollCampaignName";

    /**
     * Tabs selector
     */
    public static final String ROLLS_TAB_TEXT = "Rolls";
    public static final String MATERIAL_TAB_TEXT = "Material";
    public static final String ALERT_TAB_TEXT = "Alert";
    public static final String THICKNESS_TAB_TEXT = "Thickness";
    public static final String QUALITY_TAB_TEXT = "Quality";
    public static final String OVERVIEW_TAB_TEXT = "Overview";
    public static final String EFFICIENCY_TAB_TEXT = "Efficiency";
    public static final String LAYERS_TAB_TEXT = "Layers";
    public static final String NOTES_TAB_TEXT = "Notes";
    public static final String TABS_CONTAINER = ".mainContentTabs";

    /**
     * Header buttons selector
     */
    public static final String PRODUCTION_TREND_HEADER_BUTTON = ".toggleTrendTimelineButton";
    public static final String ADD_NOTE_HEADER_BUTTON = ".notes";
    public static final String BACK_TO_LAST_VIEW_HEADER_BUTTON = ".historyBack";
    public static final String RELOAD_HEADER_BUTTON = ".reloadData";
    public static final String CAMPAIGN_BUTTON = ".lastCampaignClick";
    public static final String FIT_TO_SELECTION_HEADER_BUTTON = ".fitToSelection";
    public static final String GO_TO_NOW_HEADER_BUTTON = ".goToCurrent";
    public static final String LAST_8H_HEADER_BUTTON = ".last8Hours";
    public static final String LAST_24H_HEADER_BUTTON = ".last24Hours";
    public static final String STATE_REPORT_HEADER_BUTTON = ".stateReportButton";

    /**
     * Roll weight
     */
    public static final String ROLL_WEIGHT_EDIT_BUTTON_COMPACTED = ".leftRollContainer > div.cols.leftColumn > div.pullDown > div:nth-child(5) > div.weight > span.dButtonWrapper.pull-right > span";
    public static final String ROLL_WEIGHT_EDIT_BUTTON_COMPACTED_DISABLED = ".leftRollContainer > div.cols.leftColumn > div.pullDown > div:nth-child(5) > div.weight > span.dButtonWrapper.pull-right.disabled";
    public static final String ROLL_WEIGHT_EDIT_INPUT_COMPACTED = ".leftRollContainer div:nth-child(5) div.weight span.editInPlace span.inputCover number-input input";
    public static final String ROLL_WEIGHT_SAVE_COMPACTED = "#rollProtocol > div > div.leftRollContainer > div.cols.leftColumn > div.pullDown > div:nth-child(5) > div.weight > span.editInPlace > span:nth-child(3) > span";
    public static final String ROLL_WEIGHT_CANCEL_COMPACTED = "#rollProtocol > div > div.leftRollContainer > div.cols.leftColumn > div.pullDown > div:nth-child(5) > div.weight > span.editInPlace > span:nth-child(2) > span";
    public static final String ROLL_WEIGHT_VALUE = "#rollProtocol div.leftRollContainer > div.cols.leftColumn > div.pullDown > div:nth-child(5) div.weight";
    public static final String ROLL_NOTES_TEXT = "#rollNoteText";
    public static final String ROLL_NOTES_SAVE_BUTTON = "#rollComments .dbSaveDiskWhiteClose";

    /**
     * Thickness tab selectors
     */
    public static final String THICKNESS_TREND_PADDING_BAR = ".thicknessContainer .paddingElementContainer";
    public static final int THICKNESS_TAB_INFO_EXPAND_ANIMATION = 1000;
    public static final String THICKNESS_TAB_SETTINGS = ".thicknessTop .icon.gearTimeline";
    public static final String THICKNESS_TAB_SETTINGS_SAVE = ".thicknessTrendSettingsModal .dialogButton.dbSaveClose";
    public static final String THICKNESS_MODAL = "#thicknessTrendSettingsModal";

    /**
     * Campaign overview selectors
     */
    public static final String CAMPAIGN_OVERVIEW_NAME = ".campaignName";
    public static final String CAMPAIGN_OVERVIEW_PRODUCT = ".productName";
    public static final String CAMPAIGN_OVERVIEW_EXPAND_INFO_BUTTON = ".campaignOverview .expandGraph span:nth-child(1)";
    public static final int CAMPAIGN_OVERVIEW_EXPAND_INFO_ANIMATION_TIME = 2000;
    public static final String CAMPAIGN_OVERVIEW_OUTPUT_INFO = ".outputInfo";
    public static final String CAMPAIGN_YIELD = ".overviewTabs li:nth-child(1)";
    public static final String CAMPAIGN_UPTIME = ".overviewTabs li:nth-child(2)";
    public static final String CAMPAIGN_CAPACITY = ".overviewTabs li:nth-child(3)";
    public static final int CAMPAIGN_OVERVIEW_TABS_ANIMATION = 1000;

    /**
     * Automatic refresh
     */
    public static final String AUTOMATIC_REFRESH_MESSAGE = "Application reload\n" +
            "Changes from campaign manager triggered the application reload.";

    /**
     * Notification container
     */
    public static final String NOTIFICATION_CONTAINER = ".notificationContainer";

    /**
     * Rolls as favorites selectors
     **/
    public static final String FAVORITES_BUTTON = ".favoriteButton";
    public static final String FAVORITES_TABLE_MODAL = "#favoriteTable";
    public static final String FAVORITES_TABLE_OPTION = "br-link";
    public static final String FAVORITES_TABLE_ROW = "#favoriteTable .ngRow";
    public static final String FAVORITES_TABLE_CLOSE = ".inModal .dialogButton.dbCancelClose";


    public static final String NOTE_USER = ".noteFormBody #user";
    public static final String NOTE_BODY = ".noteFormBody #user";

    public static final String MATERIAL_TABLE_MENU = "#dynamicTable div.ngGridMenuContainer .showColumnMenu > div.brMenuSwitch > div";

    private WebDriver driver;

    public ContentManager(WebDriver driver) {
        this.driver = driver;
    }

    public void rollsFavorite(String favorite) {

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".br-select-row")));

        List<WebElement> radioButtons = driver.findElements(By.cssSelector("br-radio"));
        if (favorite == RollsAsFavoritesOption.OPTION_NORMAL.getOptionText()) {
            radioButtons.get(0).click();
        }
        if (favorite == RollsAsFavoritesOption.OPTION_FAVORITE.getOptionText()) {
            radioButtons.get(1).click();
        }
        if (favorite == RollsAsFavoritesOption.OPTION_BENCHMARK.getOptionText()) {
            radioButtons.get(2).click();
        }

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".br-select-row")));

        waitContentSpinner();
    }

    public void openTab(String tabToOpenSelector) {
        WebLocator tabContainer = new WebLocator().setClasses("mainContentTabs");
        WebLocator tab = new WebLocator(tabContainer).setTag("a").setText(tabToOpenSelector);
        tab.click();
        waitContentSpinner();
    }

    public void openDetailedMaterialTabUngrouped() {
        openTab(MATERIAL_TAB_TEXT);

        logger.debug("Wait for show details button to be clickable");
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        WebElement showDetails = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SHOW_DETAILS_MATERIAL_TABLE)));
        showDetails.click();
        waitContentSpinner();

        logger.debug("Wait for close group buttons to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(TABLE_GROUPS_REMOVE_BUTTON_SELECTOR)));

        logger.debug("Close all groups");
        List<WebElement> closeGroupButtons = driver.findElements(By.cssSelector(TABLE_GROUPS_REMOVE_BUTTON_SELECTOR));
        for (WebElement closeGroupButton : closeGroupButtons) {
            closeGroupButton.click();
        }
    }

    public void changeRollWeightCompacted(String weight) {
        WebElement rollWeightEditButton = driver.findElement(By.cssSelector(ROLL_WEIGHT_EDIT_BUTTON_COMPACTED));
        rollWeightEditButton.click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ROLL_WEIGHT_EDIT_INPUT_COMPACTED)));
        WebElement rollWeightEditInput = driver.findElement(By.cssSelector(ROLL_WEIGHT_EDIT_INPUT_COMPACTED));
        rollWeightEditInput.clear();
        rollWeightEditInput.sendKeys(weight);
        WebElement saveWeight = driver.findElement(By.cssSelector(ROLL_WEIGHT_SAVE_COMPACTED));
        saveWeight.click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(ROLL_WEIGHT_EDIT_INPUT_COMPACTED)));
        waitContentSpinner();
    }

    public void waitContentSpinner() {
        Utils.sleep(2000);
        logger.debug("Wait for content spinner to be hidden");
        WebDriverWait webDriverWait = new WebDriverWait(driver, ApplicationManager.WAIT_TIME_OUT_IN_SECONDS);
        ApplicationManager.waitForAngularLoad(webDriverWait);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(CONTENT_SPINNER_SELECTOR)));
    }

    public void checkContentPeriod(String startPeriod, String endPeriod) {
        //check period in content
        WebElement contentStartPeriod = driver.findElement(By.cssSelector(CONTENT_START_PERIOD));
        String dateStart = contentStartPeriod.getText().trim();
        WebElement contentEndPeriod = driver.findElement(By.cssSelector(CONTENT_END_PERIOD));
        String dateEnd = contentEndPeriod.getText().trim();
        DateUtils.compareDates(startPeriod, dateStart);
        DateUtils.compareDates(endPeriod, dateEnd);
    }

    public void checkCampaignNameProduct(String name, String product) {
        WebElement campaignName = driver.findElement(By.cssSelector(CAMPAIGN_OVERVIEW_NAME));
        Assert.assertEquals(campaignName.getText().trim(), name);
        WebElement productName = driver.findElement(By.cssSelector(CAMPAIGN_OVERVIEW_PRODUCT));
        Assert.assertEquals(productName.getText().trim(), product);
    }

    public void rollsAsFavorites(String favorite) {
        WebElement favoriteButton = driver.findElement(By.cssSelector(".favoriteButton"));
        favoriteButton.click();
        Utils.sleep(500);
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("br-radio"));
        if (favorite == "normal") {
            radioButtons.get(0).click();
        }
        if (favorite == "favorite") {
            radioButtons.get(1).click();
        }
        if (favorite == "benchmark") {
            radioButtons.get(2).click();
        }
    }

    public void openFavoriteTable() {
        WebElement favoriteButton = driver.findElement(By.cssSelector(FAVORITES_BUTTON));
        favoriteButton.click();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".br-select-row")));

        WebElement openFavoritesTable = driver.findElement(By.cssSelector(FAVORITES_TABLE_OPTION));
        openFavoritesTable.click();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(FAVORITES_TABLE_MODAL)));
    }

    public void closeFavoriteTable() {
        WebElement closeFavoriteTable = driver.findElement(By.cssSelector(FAVORITES_TABLE_CLOSE));
        closeFavoriteTable.click();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(FAVORITES_TABLE_MODAL)));

        waitContentSpinner();
    }

    public void checkTabs(String allTabs) {
        WebElement tabs = driver.findElement(By.cssSelector(TABS_CONTAINER));
        Assert.assertEquals(tabs.getText(), allTabs);
    }

    public void materialTableFilters() {
        materialTableOpenMenu();

        List<WebElement> showFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(1)"));
        showFilters.get(3).click();
    }

    public void materialTableOpenMenu() {
        WebElement materialTableMenu = driver.findElement(By.cssSelector(MATERIAL_TABLE_MENU));
        materialTableMenu.click();
    }
}
