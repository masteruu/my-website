package de.brueckner.fph.acceptance.applicationSettings;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.managers.ApplicationSettingsManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public abstract class AbstractApplicationSettingsTest extends AbstractAcceptanceTest {

    protected ApplicationSettingsManager applicationSettingsManager;

    @BeforeMethod
    public void initTest() {
        applicationSettingsManager = new ApplicationSettingsManager(getWebDriver());
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(GENERAL_SETTINGS_SCRIPT_NAME);
    }
}
