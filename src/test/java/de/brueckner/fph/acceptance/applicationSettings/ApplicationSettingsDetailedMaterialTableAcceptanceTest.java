package de.brueckner.fph.acceptance.applicationSettings;

import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.MATERIAL_TAB_TEXT;
import static de.brueckner.fph.managers.ContentManager.SHOW_DETAILS_MATERIAL_TABLE;

public class ApplicationSettingsDetailedMaterialTableAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsDetailedMaterialTableAcceptanceTest.class);

    @Test  //detailed material table set to 7 days
    public void applicationSettingsDetailedMaterialTableAcceptanceTest() {

        logger.info("applicationSettingsDetailedMaterialTableAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-19 21:59:00");
        contentManager.waitContentSpinner();
        contentManager.openTab(MATERIAL_TAB_TEXT);

        //check if the button is available for 7 days or less

        getWebDriver().findElement(By.cssSelector(SHOW_DETAILS_MATERIAL_TABLE));

        //set more then 7 days and check if the button is present
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-19 22:01:00");
        contentManager.waitContentSpinner();

        //check if the Show Details button is hidden
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(SHOW_DETAILS_MATERIAL_TABLE)));

        //check detailed material table by changing the application settings
        applicationSettingsManager.openSettings();

        //set detailed material table to 8 days
        applicationSettingsManager.detailedMaterialTableDays("8");

        applicationSettingsManager.saveSettings();

        //check if the Show Details button is hidden
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SHOW_DETAILS_MATERIAL_TABLE)));


    }

}