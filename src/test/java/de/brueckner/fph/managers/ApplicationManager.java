package de.brueckner.fph.managers;


import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ApplicationManager {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    private static final int MENU_ANIMATION_DURATION_MILLISECONDS = 200;
    public static final int WAIT_TIME_OUT_IN_SECONDS = 120;
    public static final String LOGO_OVERLAY_CLASS_SELECTOR = "brandingOverlay";
    private static final String TOOLTIP_CONTENT_CSS_CLASS = "tooltipContent";
    private static final String LEFT_MENU_CSS_SELECTOR = ".headerMenu.leftHeaderMenu";
    public static final String LOGIN_USERNAME_CSS_SELECTOR = "#username";
    public static final String LOGIN_PASSWORD_CSS_SELECTOR = "#userpassword";
    public static final String LOGIN_BUTTON_CSS_SELECTOR = ".login-footer button";
    public static final String LOGIN_ERROR_MESSAGE_CONTAINER = ".login-error";

    private static final String TOOLTIP_HEADER_BUTTONS_CSS_CLASS = "tooltip";
    private static final String WHOLE_APP_SPINNER_SELECTOR = "body > .contentSpinner";

    private WebDriver driver;

    public ApplicationManager(WebDriver driver) {
        this.driver = driver;
    }

    public void selectMenuItem(String menuItemSelector) {
        logger.info("Click menu item {}", menuItemSelector);
        WebElement settings = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.cssSelector(menuItemSelector)));
        settings.click();
    }

    public void openLeftMenu() {
        logger.info("Click left menu button");
        WebElement leftMenu = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.cssSelector(LEFT_MENU_CSS_SELECTOR)));
        leftMenu.click();

        //have to wait for the menu animation to finish
        Utils.sleep(MENU_ANIMATION_DURATION_MILLISECONDS);
    }

    public void pressEnter() {
        logger.info("Press enter key");
        Keyboard Enter = ((RemoteWebDriver) driver).getKeyboard();
        Enter.pressKey(Keys.ENTER);
    }

    public void waitLogo() {
        logger.info("Wait for logo to be shown");
        WebDriverWait webDriverWait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(LOGO_OVERLAY_CLASS_SELECTOR)));

        logger.info("Wait for logo to be hidden");
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(LOGO_OVERLAY_CLASS_SELECTOR)));
    }

    public void waitSpinner() {
        logger.info("Wait for spinner to be hidden");
        WebDriverWait webDriverWait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        waitForAngularLoad(webDriverWait);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(WHOLE_APP_SPINNER_SELECTOR)));
    }

    public void loginPage(String usernameText, String passwordText) {
        logger.info("Login with user {} and password {}", usernameText, passwordText);

        logger.debug("Type username");
        WebElement username = driver.findElement(By.cssSelector(LOGIN_USERNAME_CSS_SELECTOR));
        username.sendKeys(usernameText);

        logger.debug("Type password");
        WebElement password = driver.findElement(By.cssSelector(LOGIN_PASSWORD_CSS_SELECTOR));
        password.sendKeys(passwordText);

        logger.debug("Submit login");
        WebElement login = driver.findElement(By.cssSelector(LOGIN_BUTTON_CSS_SELECTOR));
        login.click();
        waitLogo();
    }

    public void logout() {
        WebElement userbutton = driver.findElement(By.cssSelector("#userbutton"));
        userbutton.click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("username-logout")));
        WebElement logout = driver.findElement(By.cssSelector(".username-logout"));
        logout.click();
        WebLocator assertLogout = new WebLocator().setClasses("login-container").setRenderMillis(10000);
        assertLogout.assertReady();
        waitLogo();
    }

    public void sendKeysScript(WebElement webElementToModify, String text) {
        JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
        myExecutor.executeScript("arguments[0].value='" + text + "';$(arguments[0]).trigger(\"change\");$(arguments[0]).trigger(\"focus\");angular.element(arguments[0]).blur();", webElementToModify);
    }

    public String getTooltipSpecificTextForElement(WebElement element, String childSelector) {
        logger.debug("Element to hover: {}", element.getText());
        MouseUtils.mouseOver(element, driver);

        WebElement tooltipText = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className(childSelector)));

        String text = tooltipText.getText();

        logger.debug("Tooltip text: {}", text);

        return text;
    }

    public String getTooltipTextForHeaderButtons(WebElement element) {
        return getTooltipSpecificTextForElement(element, TOOLTIP_HEADER_BUTTONS_CSS_CLASS);
    }

    public String getTooltipContentText(String elementSelector, int index) {
        WebElement element = driver.findElements(By.cssSelector(elementSelector)).get(index);
        return getTooltipSpecificTextForElement(element, TOOLTIP_CONTENT_CSS_CLASS);
    }


    public String getTooltipContentText(String elementSelector) {
        WebElement element = driver.findElement(By.cssSelector(elementSelector));
        return getTooltipSpecificTextForElement(element, TOOLTIP_CONTENT_CSS_CLASS);
    }

    public String getTimelineTooltipTitleText(String elementSelector) {
        WebElement element = driver.findElement(By.cssSelector(elementSelector));
        MouseUtils.mouseOver(element, driver);

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".genericTooltipContainer")));

        WebElement tooltipTitle = driver.findElement(By.cssSelector(".genericTooltipContainer .tooltipTitle"));
        return tooltipTitle.getText();
    }

    public String getTreeTooltipTitle(int index) {
        List<WebElement> myRollInTree = driver.findElements(By.cssSelector(".name.treeRow"));
        MouseUtils.mouseOver(myRollInTree.get(index), driver);

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".genericTooltipContainer")));

        WebElement tooltipTitle = driver.findElement(By.cssSelector(".genericTooltipContainer .tooltipTitle"));
        return tooltipTitle.getText();

    }

    public String getTooltipContainerText(String elementSelector) {
        WebElement element = driver.findElement(By.cssSelector(elementSelector));
        return getTooltipSpecificTextForElement(element, "genericTooltipContainer");
    }

    //Wait for Angular Load
    public static void waitForAngularLoad(WebDriverWait wait) {
        String angularReadyScript = "return angular.element(document).injector().get(\"$http\").pendingRequests.length === 0";

        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
                .executeScript(angularReadyScript).toString());
        wait.until(angularLoad);
        logger.debug("Angular load done");
    }
}