package de.brueckner.fph.acceptance.applicationSettings;

import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ApplicationSettingsManager.SAVE;

public class ApplicationSettingsSaveWithErrorsAcceptanceTest extends AbstractApplicationSettingsTest {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsSaveWithErrorsAcceptanceTest.class);

    @Test
    public void applicationSettingsSaveWithErrorsAcceptanceTest() {
        logger.info("ApplicationSettingsSaveWithErrorsAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        applicationSettingsManager.openSettings();

        List<WebElement> inputs = getWebDriver().findElements(By.cssSelector("#settingsModal .base-number-input"));
        inputs.get(0).sendKeys("test");

        //check the application settings save button
        WebElement saveAppSettings = getWebDriver().findElement(By.cssSelector(SAVE));
        saveAppSettings.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".error")));

        //check if the error message is present
        WebElement errorMessage = getWebDriver().findElement(By.cssSelector(".error"));
        Assert.assertEquals(errorMessage.getText().trim(), "Can not save\n" +
                "The form still contains errors", "The application settings error message wasx not present when the form has errors");

    }
}
