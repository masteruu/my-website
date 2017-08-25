package de.brueckner.fph.acceptance.config;

import com.sdl.selenium.utils.config.WebDriverConfig;
import de.brueckner.fph.acceptance.util.MongoUtils;
import de.brueckner.fph.managers.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ContextConfiguration(locations = {"classpath:spring/applicationContext-acceptance-test.xml"})
public class AbstractAcceptanceTest extends AbstractTestNGSpringContextTests {

    public static final String GENERAL_SETTINGS_SCRIPT_NAME = "default_general_settings";
    public static final String USER_SETTINGS_SCRIPT_NAME = "default_user_settings";
    public static final String CAMPAIGNS_SCRIPT_NAME = "campaigns";
    public static final String REDUCE_OVERPRODUCTION = "reduce_overproduction";
    public static final String RESTORE_OVERPRODUCTION = "restore_overproduction";
    public static final String INVALID_CAMPAIGN = "invalid-campaign";
    private static final Logger logger = LoggerFactory.getLogger(AbstractAcceptanceTest.class);
    private static WebDriver driver;
    protected TimelineManager timelineManager;
    protected ApplicationManager applicationManager;
    protected TreeManager treeManager;
    protected ContentManager contentManager;
    protected BreadcrumbManager breadcrumbManager;
    protected NoteManager noteManager;
    protected StateReportManager stateReportManager;
    protected CampaignManager campaignManager;
    protected PermissionsManager permissionsManager;
    protected ApplicationSettingsManager applicationSettingsManager;
    @Autowired
    private MongoUtils mongoUtils;
    @Value("${chrome.driver.location}")
    private String chromeDriverLocation;
    @Value("${ilm.url}")
    private String applicationURL;

    protected static WebDriver getWebDriver() {
        return driver;
    }

    @BeforeClass
    public void setup() {
        setupChromeDriver();
    }

    @BeforeMethod
    public void openApplication() {
        logger.info("------------------------ Test start ------------------------");
        createWebDriver();

        //init testy
        WebDriverConfig.init(driver);

        applicationManager = new ApplicationManager(driver);
        timelineManager = new TimelineManager(driver);
        treeManager = new TreeManager(driver);
        contentManager = new ContentManager(driver);
        breadcrumbManager = new BreadcrumbManager(driver);
        noteManager = new NoteManager(driver);
        stateReportManager = new StateReportManager(driver);
        campaignManager = new CampaignManager(driver);
        permissionsManager = new PermissionsManager(driver);
        applicationSettingsManager = new ApplicationSettingsManager(driver);

        logger.info("Opening application " + applicationURL);
        driver.get(applicationURL);
        applicationManager.waitLogo();
    }

    @AfterMethod
    public void destroy() {
        quitDriver();
        logger.info("------------------------ Test finish ------------------------");
    }

    public MongoUtils getMongoUtils() {
        return mongoUtils;
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
            URL chromeDriver = AbstractAcceptanceTest.class.getResource(chromeDriverLocation);
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

    private void quitDriver() {
        if (driver != null) {
            getWebDriver().quit();

            logger.debug("Chrome driver has quit.");
        }
    }

    public WebDriver createSecondWebDriver() {
        //open second browser in incognito
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driverNew = new ChromeDriver(capabilities);
        driverNew.get(applicationURL);
        return driverNew;
    }

    public String getApplicationErrorPage() {
        return applicationURL + "/error";
    }

    public String getApplicationURL() {
        return applicationURL;
    }
}
