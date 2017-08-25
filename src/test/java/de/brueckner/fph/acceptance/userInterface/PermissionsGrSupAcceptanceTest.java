package de.brueckner.fph.acceptance.userInterface;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.QualityOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class PermissionsGrSupAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(PermissionsGrSupAcceptanceTest.class);

    @Test
    public void cantAccessApplicationSettingsLowSettings() {
        logger.info(LoginCredentials.USER_SUP.getUsername() + " can't access application settings low");

        applicationManager.loginPage(LoginCredentials.USER_SUP.getUsername(), LoginCredentials.USER_SUP.getPassword());

        permissionsManager.cantAccessApplicationSettingsLowSettings();
    }

    @Test
    public void cantAccessApplicationSettingsHighSettings() {
        logger.info(LoginCredentials.USER_SUP.getUsername() + " can't access application settings high");

        applicationManager.loginPage(LoginCredentials.USER_SUP.getUsername(), LoginCredentials.USER_SUP.getPassword());

        permissionsManager.cantAccessApplicationSettingsHighSettings();
    }

    @Test
    public void cantAccessStateReport() {
        logger.info(LoginCredentials.USER_SUP.getUsername() + " can access state report");

        applicationManager.loginPage(LoginCredentials.USER_SUP.getUsername(), LoginCredentials.USER_SUP.getPassword());

        permissionsManager.cantAccessStateReport();
    }

    @Test
    public void canCreateEditNote() {

        logger.info(LoginCredentials.USER_SUP.getUsername() + " can create/edit note");

        applicationManager.loginPage(LoginCredentials.USER_SUP.getUsername(), LoginCredentials.USER_SUP.getPassword());

        permissionsManager.canCreateEditNote();
    }

    @Test
    public void canChangeRollWeight() {

        logger.info(LoginCredentials.USER_SUP.getUsername() + " can change roll weight");

        applicationManager.loginPage(LoginCredentials.USER_SUP.getUsername(), LoginCredentials.USER_SUP.getPassword());

        permissionsManager.canChangeRollWeight();
    }

    @Test
    public void canAddRollNote() {

        logger.info(LoginCredentials.USER_SUP.getUsername() + " can add roll note");

        applicationManager.loginPage(LoginCredentials.USER_SUP.getUsername(), LoginCredentials.USER_SUP.getPassword());

        permissionsManager.canAddRollNote();
    }

    @Test
    public void canChangeVisualQuality() {
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        applicationSettingsManager.setRollQuality(QualityOption.OPTION_VISUAL.getOptionText(), 1);
        applicationManager.logout();

        logger.info(LoginCredentials.USER_SUP.getUsername() + " can change visual quality");

        applicationManager.loginPage(LoginCredentials.USER_SUP.getUsername(), LoginCredentials.USER_SUP.getPassword());

        permissionsManager.canChangeVisualQuality();
    }

    @Test
    public void cantChangeLaboratoryQuality() {

        logger.info(LoginCredentials.USER_SUP.getUsername() + " can't change laboratory quality");

        applicationManager.loginPage(LoginCredentials.USER_SUP.getUsername(), LoginCredentials.USER_SUP.getPassword());

        permissionsManager.cantChangeLaboratoryQuality();
    }

    @Test
    public void canAccessCampaignManager() {

        logger.info(LoginCredentials.USER_SUP.getUsername() + " can access campaign manager");

        applicationManager.loginPage(LoginCredentials.USER_SUP.getUsername(), LoginCredentials.USER_SUP.getPassword());

        permissionsManager.canAccessCampaignManager();
    }

    @Test
    public void cantAddPrivateNote() {

        logger.info(LoginCredentials.USER_SUP.getUsername() + " can't add private note");

        applicationManager.loginPage(LoginCredentials.USER_SUP.getUsername(), LoginCredentials.USER_SUP.getPassword());

        permissionsManager.cantAddPrivateNote();
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(GENERAL_SETTINGS_SCRIPT_NAME);
    }
}