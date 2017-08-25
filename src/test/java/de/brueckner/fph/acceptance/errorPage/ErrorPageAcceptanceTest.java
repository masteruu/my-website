package de.brueckner.fph.acceptance.errorPage;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorPageAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(ErrorPageAcceptanceTest.class);

    @Test
    public void errorPageAcceptanceTest() {
        logger.info("errorPageAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //open the error page
        getWebDriver().get(getApplicationErrorPage());

        //check error page content
        WebLocator fatalError = new WebLocator().setText("Fatal Error");
        fatalError.assertReady();
        WebLocator appTerminated = new WebLocator().setText("The application needs to be terminated");
        appTerminated.assertReady();
        WebLocator fph = new WebLocator().setClasses("productTitle");
        fph.assertReady();

        //refresh the application by clicking the logo
        WebElement logo = getWebDriver().findElement(By.cssSelector(".logo"));
        logo.click();
        applicationManager.waitLogo();

        //check if the application has reloaded
        WebElement productName = getWebDriver().findElement(By.cssSelector(".productName"));
        Assert.assertEquals(productName.getText(), "25um Plain");

        //open the error page
        getWebDriver().get(getApplicationErrorPage());

        //refresh the browser
        getWebDriver().navigate().refresh();
        applicationManager.waitLogo();

        //check if the application has reloaded
        productName = getWebDriver().findElement(By.cssSelector(".productName "));
        Assert.assertEquals(productName.getText(), "25um Plain");

    }
}