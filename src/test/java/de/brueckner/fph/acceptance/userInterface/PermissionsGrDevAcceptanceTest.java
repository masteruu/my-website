package de.brueckner.fph.acceptance.userInterface;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.QualityOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class PermissionsGrDevAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(PermissionsGrDevAcceptanceTest.class);

    //gr_ct

        /*Change Application Settings High X
        Change Application Settings Low   X
        State Report X
        Table Hide col X
        Create/edit Note   X
        Edit Roll Weight   X
        Change Visual Quality   X
        Change Laboratory Quality   X
        Campaign Manager   X
        */

    @Test
    public void canAccessApplicationSettingsLowSettings() {
        logger.info(LoginCredentials.USER_DEV.getUsername() + " can access application settings low");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        permissionsManager.canAccessApplicationSettingsLowSettings();
    }

    @Test
    public void canAccessStateReport() {
        logger.info(LoginCredentials.USER_DEV.getUsername() + " can access state report");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        permissionsManager.canAccessStateReport();
    }

    @Test
    public void canCreateEditNote() {

        logger.info(LoginCredentials.USER_DEV.getUsername() + " can create/edit note");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        permissionsManager.canCreateEditNote();
    }

    @Test
    public void canChangeRollWeight() {

        logger.info(LoginCredentials.USER_DEV.getUsername() + " can change roll weight");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        permissionsManager.canChangeRollWeight();
    }

    @Test
    public void canAddRollNote() {

        logger.info(LoginCredentials.USER_DEV.getUsername() + " can add roll note");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        permissionsManager.canAddRollNote();
    }

    @Test
    public void canChangeVisualQuality() {
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        applicationSettingsManager.setRollQuality(QualityOption.OPTION_VISUAL.getOptionText(), 1);
        applicationManager.logout();

        logger.info(LoginCredentials.USER_DEV.getUsername() + " can change visual quality");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        permissionsManager.canChangeVisualQuality();
    }

    @Test
    public void canChangeLaboratoryQuality() {

        logger.info(LoginCredentials.USER_DEV.getUsername() + " can change laboratory quality");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        permissionsManager.canChangeLaboratoryQuality();
    }

    @Test
    public void canAccessCampaignManager() {

        logger.info(LoginCredentials.USER_DEV.getUsername() + " can access campaign manager");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        permissionsManager.canAccessCampaignManager();
    }

    @Test
    public void canAddPrivateNote() {

        logger.info(LoginCredentials.USER_DEV.getUsername() + " can add private note");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        permissionsManager.canAddPrivateNote();
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(GENERAL_SETTINGS_SCRIPT_NAME);

    }
}
