package de.brueckner.fph.acceptance.userInterface;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.*;

public class LoginFailedAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginFailedAcceptanceTest.class);

    @Test
    public void loginBadUserBadPasswordAcceptance() {
        logger.info("loginBadUserBadPasswordAcceptance");

        failedLogin("bad_username", "bad_password");

    }

    @Test
    public void loginBadUserGoodPassword() {
        logger.info("loginBadUserGoodPassword");

        failedLogin("bad_username", LoginCredentials.USER_DEV.getPassword());

    }

    @Test
    public void loginGoodUserBadPasswordAcceptance() {
        logger.info("loginGoodUserBadPasswordAcceptance");

        failedLogin(LoginCredentials.USER_DEV.getUsername(), "bad_password");

    }

    void failedLogin(String user, String pass) {
        logger.debug("Type username");
        WebElement username = getWebDriver().findElement(By.cssSelector(LOGIN_USERNAME_CSS_SELECTOR));
        username.sendKeys(user);

        logger.debug("Type password");
        WebElement password = getWebDriver().findElement(By.cssSelector(LOGIN_PASSWORD_CSS_SELECTOR));
        password.sendKeys(pass);

        logger.debug("Submit login");
        WebElement login = getWebDriver().findElement(By.cssSelector(LOGIN_BUTTON_CSS_SELECTOR));
        login.click();

        WebLocator loginBtn = new WebLocator().setTag("button").setText("LOGIN");
        loginBtn.click();

        WebElement errorContainer = new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(LOGIN_ERROR_MESSAGE_CONTAINER)));

        //check failed login message
        Assert.assertEquals(errorContainer.getText().trim(), "Username and/or password are wrong.\n" +
                "Please try again.");

        //check css values (red inputs when login failed)
        username = getWebDriver().findElement(By.cssSelector(LOGIN_USERNAME_CSS_SELECTOR));
        Assert.assertEquals(username.getCssValue("border-width"), "2px 1px 1px 2px");
        Assert.assertEquals(username.getCssValue("border-color"), "rgb(230, 0, 38)");

        password = getWebDriver().findElement(By.cssSelector(LOGIN_PASSWORD_CSS_SELECTOR));
        Assert.assertEquals(password.getCssValue("border-width"), "2px 1px 1px 2px");
        Assert.assertEquals(password.getCssValue("border-color"), "rgb(230, 0, 38)");
    }
}
