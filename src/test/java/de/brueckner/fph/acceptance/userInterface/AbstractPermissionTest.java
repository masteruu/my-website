package de.brueckner.fph.acceptance.userInterface;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.managers.ApplicationSettingsManager;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractPermissionTest extends AbstractAcceptanceTest {

    protected ApplicationSettingsManager applicationSettingsManager;

    @BeforeMethod
    public void initTest() {
        applicationSettingsManager = new ApplicationSettingsManager(getWebDriver());
    }
}
