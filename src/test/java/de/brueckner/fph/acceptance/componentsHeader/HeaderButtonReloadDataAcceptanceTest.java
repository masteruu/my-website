package de.brueckner.fph.acceptance.componentsHeader;

import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.managers.*;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.RELOAD_HEADER_BUTTON;
import static de.brueckner.fph.managers.ContentManager.ROLL_WEIGHT_VALUE;
import static de.brueckner.fph.managers.TimelineManager.PROD_NONPROD_DROPDOWN_OPTION;
import static de.brueckner.fph.managers.TimelineManager.UNDER_1MONTH_DROPDOWN;
import static de.brueckner.fph.util.DateFormatShortOption.OPTION_WITH_DASH;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class HeaderButtonReloadDataAcceptanceTest extends AbstractApplicationSettingsTest {

    private static final Logger logger = LoggerFactory.getLogger(HeaderButtonReloadDataAcceptanceTest.class);
    protected CampaignManager campaignManager;
    protected WebDriver secondDriver;
    protected ApplicationManager secondApplicationManager;
    protected BreadcrumbManager secondBreadcrumbManager;
    protected TimelineManager secondTimelineManager;
    protected ContentManager secondContentManager;
    protected TreeManager secondTreeManager;

    @BeforeMethod
    public void setUpTest() {
        campaignManager = new CampaignManager(getWebDriver());
        secondDriver = createSecondWebDriver();
        secondApplicationManager = new ApplicationManager(secondDriver);
        secondBreadcrumbManager = new BreadcrumbManager(secondDriver);
        secondTimelineManager = new TimelineManager(secondDriver);
        secondContentManager = new ContentManager(secondDriver);
        secondTreeManager = new TreeManager(secondDriver);

    }

    @AfterMethod
    public void destroySecondBrowserTest() {
        secondDriver.quit();
    }

    @Test
    public void headerButtonReloadDataShortcutKeyAcceptanceTest() {
        logger.info("HeaderButtonReloadDataShortcutKeyAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //check shortcut
        Keyboard reload = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        reload.pressKey(Keys.SHIFT + "R");
        applicationManager.waitLogo();

        //check button tooltip
        WebElement goToCurrent = getWebDriver().findElement(By.cssSelector(RELOAD_HEADER_BUTTON));
        Assert.assertEquals(applicationManager.getTooltipTextForHeaderButtons(goToCurrent), "RELOAD DATA R", "Text from reload button tooltip is not correct");

    }

    @Test
    public void headerButtonReloadDataApplicationSettingsAcceptanceTest() {
        logger.info("headerButtonReloadDataApplicationSettingsAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //open second browser in incognito
        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_EE.getUsername(), LoginCredentials.USER_EE.getPassword());
        }

        //check if the date has changed for the new user when the reload button was pressed
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", DateUtils.date("2016-05-09 10:00:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "  -  ..."));

        //change the date format short

        applicationSettingsManager.setNewDateOrTimeFormat(OPTION_WITH_DASH.getOptionText(), 1);

        WebElement reload = secondDriver.findElement(By.cssSelector(RELOAD_HEADER_BUTTON));
        reload.click();
        secondApplicationManager.waitLogo();

        //check if the date has changed for the new user when the reload button was pressed
        secondBreadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", DateUtils.date("2016-05-09 10:00:00", "YY-MM-dd  HH:mm") + "  -  ..."));
    }


    @Test
    public void headerButtonReloadDataUserSettingsDifferentUsersAcceptanceTest() {
        logger.info("headerButtonReloadDataUserSettingsDifferentUsersAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //open second browser in incognito
        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_EE.getUsername(), LoginCredentials.USER_EE.getPassword());
        }

        //changing user settings with dev
        timelineManager.timelineSettingsSetDropdownOption(UNDER_1MONTH_DROPDOWN, PROD_NONPROD_DROPDOWN_OPTION);

        WebElement reload = secondDriver.findElement(By.cssSelector(RELOAD_HEADER_BUTTON));
        reload.click();
        secondApplicationManager.waitLogo();

        //check if user settings were not changed for ee user, after reload
        secondTimelineManager.openTimelineSettings();

        WebElement under1M = secondDriver.findElement(By.cssSelector(UNDER_1MONTH_DROPDOWN));
        Assert.assertEquals(under1M.getText(), "CAMPAIGN");

    }

    @Test
    public void headerButtonReloadDataUserSettingsSameUserAcceptanceTest() {
        logger.info("headerButtonReloadDataUserSettingsSameUserAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //open second browser in incognito
        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        }

        //changing user settings with dev
        timelineManager.timelineSettingsSetDropdownOption(UNDER_1MONTH_DROPDOWN, PROD_NONPROD_DROPDOWN_OPTION);

        WebElement reload = secondDriver.findElement(By.cssSelector(RELOAD_HEADER_BUTTON));
        reload.click();
        secondApplicationManager.waitLogo();

        //check if user settings were not changed for ee user, after reload
        secondTimelineManager.openTimelineSettings();

        WebElement under1M = secondDriver.findElement(By.cssSelector(UNDER_1MONTH_DROPDOWN));
        Assert.assertEquals(under1M.getText(), "PROD./NON-PROD");

    }

    @Test
    public void headerButtonReloadDataAcceptanceTest() {
        logger.info("headerButtonReloadDataAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //open second browser in incognito
        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_EE.getUsername(), LoginCredentials.USER_EE.getPassword());
        }

        treeManager.openTree();
        List<WebElement> currentRoll = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        currentRoll.get(3).click();
        treeManager.waitTreeSpinner();
        contentManager.waitContentSpinner();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ROLL_WEIGHT_VALUE)));

        contentManager.changeRollWeightCompacted("123");

        WebElement reload = secondDriver.findElement(By.cssSelector(RELOAD_HEADER_BUTTON));
        reload.click();
        secondApplicationManager.waitLogo();

        secondTreeManager.openTree();
        currentRoll = secondDriver.findElements(By.cssSelector(".name.treeRow"));
        currentRoll.get(3).click();
        secondTreeManager.waitTreeSpinner();
        secondContentManager.waitContentSpinner();

        new WebDriverWait(secondDriver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ROLL_WEIGHT_VALUE)));

        WebElement checkWeight = secondDriver.findElement(By.cssSelector(ROLL_WEIGHT_VALUE));
        Assert.assertEquals(checkWeight.getText().trim(), "123 kg Weight");

        contentManager.changeRollWeightCompacted("1");
        //TO DO ROLLBACK SCRIPT

    }
}
