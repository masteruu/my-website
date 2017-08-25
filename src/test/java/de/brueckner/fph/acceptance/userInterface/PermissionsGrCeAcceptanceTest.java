package de.brueckner.fph.acceptance.userInterface;

import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.QualityOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class PermissionsGrCeAcceptanceTest extends AbstractPermissionTest {
    private static final Logger logger = LoggerFactory.getLogger(PermissionsGrCeAcceptanceTest.class);

    //gr_ce

        /*Change Application Settings High
        Change Application Settings Low   X
        State Report
        Table Hide col   X
        Create/edit Note   X
        Edit Roll Weight   X
        Change Visual Quality   X
        Change Laboratory Quality
        Campaign Manager   X
        */

    @Test
    public void canAccessApplicationSettingsLowSettings() {
        logger.info(LoginCredentials.USER_CE.getUsername() + " can access application settings low");

        applicationManager.loginPage(LoginCredentials.USER_CE.getUsername(), LoginCredentials.USER_CE.getPassword());

        permissionsManager.canAccessApplicationSettingsLowSettings();
    }

    @Test
    public void cantAccessApplicationSettingsHighSettings() {
        logger.info(LoginCredentials.USER_CE.getUsername() + " can't access application settings high");

        applicationManager.loginPage(LoginCredentials.USER_CE.getUsername(), LoginCredentials.USER_CE.getPassword());

        permissionsManager.cantAccessApplicationSettingsHighSettings();
    }

    @Test
    public void cantAccessStateReport() {
        logger.info(LoginCredentials.USER_CE.getUsername() + " can't access state report");

        applicationManager.loginPage(LoginCredentials.USER_CE.getUsername(), LoginCredentials.USER_CE.getPassword());

        permissionsManager.cantAccessStateReport();
    }

    @Test
    public void canCreateEditNote() {

        logger.info(LoginCredentials.USER_CE.getUsername() + " can create/edit note");

        applicationManager.loginPage(LoginCredentials.USER_CE.getUsername(), LoginCredentials.USER_CE.getPassword());

        permissionsManager.canCreateEditNote();
    }

    @Test
    public void canChangeRollWeight() {

        logger.info(LoginCredentials.USER_CE.getUsername() + " can change roll weight");

        applicationManager.loginPage(LoginCredentials.USER_CE.getUsername(), LoginCredentials.USER_CE.getPassword());

        permissionsManager.canChangeRollWeight();
    }

    @Test
    public void canAddRollNote() {

        logger.info(LoginCredentials.USER_CT.getUsername() + " can add roll note");

        applicationManager.loginPage(LoginCredentials.USER_CE.getUsername(), LoginCredentials.USER_CE.getPassword());

        permissionsManager.canAddRollNote();
    }

    @Test
    public void canChangeVisualQuality() {

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        applicationSettingsManager.setRollQuality(QualityOption.OPTION_VISUAL.getOptionText(), 1);
        applicationManager.logout();

        logger.info(LoginCredentials.USER_CE.getUsername() + " can change visual quality");

        applicationManager.loginPage(LoginCredentials.USER_CE.getUsername(), LoginCredentials.USER_CE.getPassword());

        permissionsManager.canChangeVisualQuality();
    }

    @Test
    public void cantChangeLaboratoryQuality() {

        logger.info(LoginCredentials.USER_CE.getUsername() + " can't change laboratory quality");

        applicationManager.loginPage(LoginCredentials.USER_CE.getUsername(), LoginCredentials.USER_CE.getPassword());

        permissionsManager.cantChangeLaboratoryQuality();
    }

    @Test
    public void canAccessCampaignManager() {

        logger.info(LoginCredentials.USER_CE.getUsername() + " can access campaign manager");

        applicationManager.loginPage(LoginCredentials.USER_CE.getUsername(), LoginCredentials.USER_CE.getPassword());

        permissionsManager.canAccessCampaignManager();
    }

    @Test
    public void cantAddPrivateNote() {

        logger.info(LoginCredentials.USER_CE.getUsername() + " can't add private note");

        applicationManager.loginPage(LoginCredentials.USER_CE.getUsername(), LoginCredentials.USER_CE.getPassword());

        permissionsManager.cantAddPrivateNote();
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(GENERAL_SETTINGS_SCRIPT_NAME);

    }
}