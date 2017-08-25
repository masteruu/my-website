package de.brueckner.fph.general;

import com.sdl.selenium.utils.config.WebDriverConfig;
import de.brueckner.fph.general.tests.ApplicationSettings;
import de.brueckner.fph.managers.*;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.*;

@ContextConfiguration(locations = {"classpath:spring/applicationContext-acceptance-test.xml"})
public class AbstractGeneralTest extends AbstractTestNGSpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettings.class);

    protected TimelineManager timelineManager;
    protected ApplicationManager applicationManager;
    protected TreeManager treeManager;
    protected ContentManager contentManager;
    protected BreadcrumbManager breadcrumbManager;
    protected ApplicationSettingsManager applicationSettingsManager;
    protected CampaignManager campaignManager;

    @Value("${chrome.driver.location}")
    private String chromeDriverLocation;

    @Value("${ilm.url}")
    private String applicationURL;

    private static WebDriver driver;

    @BeforeClass
    public void setup() {
        setupChromeDriver();
    }

    @BeforeMethod
    @Parameters({"appUrl", "user", "password"})
    public void openApplication(@Optional String appUrl, @Optional String user, @Optional String password) {
        logger.info("------------------------ Test start ------------------------");
        createWebDriver();

        //init testy
        WebDriverConfig.init(driver);

        applicationManager = new ApplicationManager(driver);
        timelineManager = new TimelineManager(driver);
        treeManager = new TreeManager(driver);
        contentManager = new ContentManager(driver);
        breadcrumbManager = new BreadcrumbManager(driver);
        campaignManager = new CampaignManager(driver);
        applicationSettingsManager = new ApplicationSettingsManager(driver);

        openAppAndLogin(appUrl, user, password);
    }

    private void openAppAndLogin(@Optional String appUrl, @Optional String user, @Optional String password) {
        String urlToUse = appUrl != null ? appUrl : applicationURL;
        logger.info("Opening application " + urlToUse);
        driver.get(urlToUse);
        applicationManager.waitLogo();

        //login
        String usernameToUse = user != null ? user : LoginCredentials.USER_DEV.getUsername();
        String passwordToUse = password != null ? password : LoginCredentials.USER_DEV.getPassword();
        applicationManager.loginPage(usernameToUse, passwordToUse);
    }

    @AfterMethod
    public void destroy() {
        quitDriver();
        logger.info("------------------------ Test finish ------------------------");
    }


    /**
     * Set chrome driver location as system property.
     */
    private void setupChromeDriver() {
        logger.debug("Retrieving chrome driver from location: " + chromeDriverLocation);

        String chromeDriverPath = getChromeDriverPath();

        if (chromeDriverPath != null) {
            setChromeDriverPath(chromeDriverPath);
        } else {
            String errorMessage = "Chrome driver not found";
            logger.error(errorMessage);

            throw new RuntimeException(errorMessage);
        }
    }

    /**
     * Check if the configured chrome driver path is relative or absolute and return the absolute path.
     *
     * @return absolute path of chrome driver
     */
    private String getChromeDriverPath() {
        String chromeDriverPath = null;

        Path path = Paths.get(chromeDriverLocation);
        if (Files.exists(path)) {
            chromeDriverPath = chromeDriverLocation;
        } else {
            URL chromeDriver = AbstractGeneralTest.class.getResource(chromeDriverLocation);
            if (chromeDriver != null) {
                chromeDriverPath = chromeDriver.getPath();
            }
        }

        return chromeDriverPath;
    }

    /**
     * Sets Chrome driver path system property.
     *
     * @param chromeDriverPath chrome driver executable path
     */
    private void setChromeDriverPath(String chromeDriverPath) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    private void createWebDriver() {
        logger.debug("Initalizing chrome getWebDriver()..");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        driver = new ChromeDriver(chromeOptions);
    }


    protected static WebDriver getWebDriver() {
        return driver;
    }

    private void quitDriver() {
        if (driver != null) {
            getWebDriver().quit();

            logger.debug("Chrome driver has quit.");
        }
    }

    public void checkRollsTab() {
        //open rolls tab
        contentManager.openTab(ROLLS_TAB_TEXT);

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ngGroupPanelDescription")));
        logger.info("Current Campaign Rolls tab is working");
    }

    public void checkThicknessTab() {
        //thickness tab
        contentManager.openTab(THICKNESS_TAB_TEXT);

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".thicknessChartContainer")));

        logger.info("Campaign Thickness tab opened successful");
    }

    public void checkAlertTab() {
        //open alert tab
        contentManager.openTab(ALERT_TAB_TEXT);

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alarmButtons")));

        logger.info("Current Campaign Alarm tab is working");
    }

    public void checkMaterialTab() {
        //open material tab
        contentManager.openTab(MATERIAL_TAB_TEXT);

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".materialBarChartTitle")));

        logger.info("Current Campaign Material tab is working");
    }

    public void checkQualityTab() {
        //Quality tab
        contentManager.openTab(QUALITY_TAB_TEXT);
        contentManager.waitContentSpinner();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".labQualityValue .select2-chosen")));

        logger.info("Roll Quality Tab Working");
    }

    public void checkLayersTab() {
        contentManager.openTab(LAYERS_TAB_TEXT);

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".chillRollSide")));

        logger.info("Roll Layers Tab Working");
    }

    public void checkNotesTab() {
        contentManager.openTab(NOTES_TAB_TEXT);

        contentManager.waitContentSpinner();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rollNoteText")));

        logger.info("Roll notes tab working");
    }


}
