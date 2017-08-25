package de.brueckner.fph.acceptance.userInterface;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageElementsAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginPageElementsAcceptanceTest.class);

    @Test
    public void loginPageElementsAcceptance() {
        logger.info("loginPageElementsAcceptance");

        //check logo presence and app refresh on logo click
        WebElement logo = getWebDriver().findElement(By.cssSelector(".logo"));
        logo.click();
        applicationManager.waitLogo();

        //check login page header and clock
        WebElement text = getWebDriver().findElement(By.cssSelector(".app-header .productTitle"));
        Assert.assertEquals(text.getText(), "FILM PRODUCTION HISTORIAN");
        WebElement headerClock = getWebDriver().findElement(By.cssSelector(".app-header .headerClock"));

        //check login container
        WebElement login = getWebDriver().findElement(By.cssSelector("#login"));
        Assert.assertEquals(login.getText(), "Login\n" +
                "USERNAME\n" +
                "PASSWORD\n" +
                "LOGIN");
    }
}