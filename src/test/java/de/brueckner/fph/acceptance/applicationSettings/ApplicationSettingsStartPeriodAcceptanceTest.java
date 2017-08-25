package de.brueckner.fph.acceptance.applicationSettings;


import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;

import static de.brueckner.fph.managers.ApplicationSettingsManager.START_PERIOD_INPUT;

public class ApplicationSettingsStartPeriodAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsStartPeriodAcceptanceTest.class);

    @Test
    public void applicationSettingsStartPeriodAcceptanceTest() {
        logger.info("applicationSettingsStartPeriodAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //change the start period and check if the campaign overview is displayed
        applicationSettingsManager.openSettings();
        WebElement startPeriod = getWebDriver().findElement(By.cssSelector(START_PERIOD_INPUT));
        startPeriod.clear();
        startPeriod.sendKeys("3");
        applicationManager.pressEnter();
        applicationSettingsManager.saveSettingsByPressingEnter();

        //click the logo to refresh the application
        WebElement logo = getWebDriver().findElement(By.cssSelector(".logo"));
        logo.click();
        applicationManager.waitLogo();

        //check that the campaign is displayed in the breadcrumb
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "1605009_25u_450m_5.3t"));

    }

}