package de.brueckner.fph.acceptance.userInterface;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class PermissionsGrViewAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(PermissionsGrViewAcceptanceTest.class);

    //gr_view

        /*Change Application Settings High
        Change Application Settings Low
        State Report
        Table Hide col
        Create/edit Note
        Edit Roll Weight
        Change Visual Quality
        Change Laboratory Quality
        Campaign Manager*/

    @Test
    public void cantAccessApplicationSettingsLowSettings() {
        logger.info(LoginCredentials.USER_VIEW.getUsername() + " can't access application settings low");

        applicationManager.loginPage(LoginCredentials.USER_VIEW.getUsername(), LoginCredentials.USER_ET.getPassword());

        permissionsManager.cantAccessApplicationSettingsLowSettings();
    }

    @Test
    public void cantAccessApplicationSettingsHighSettings() {
        logger.info(LoginCredentials.USER_VIEW.getUsername() + " can't access application settings high");

        applicationManager.loginPage(LoginCredentials.USER_VIEW.getUsername(), LoginCredentials.USER_VIEW.getPassword());

        permissionsManager.cantAccessApplicationSettingsHighSettings();
    }

    @Test
    public void cantCreateEditNote() {
        logger.info(LoginCredentials.USER_VIEW.getUsername() + " can't create/edit note");

        applicationManager.loginPage(LoginCredentials.USER_VIEW.getUsername(), LoginCredentials.USER_VIEW.getPassword());

        permissionsManager.cantCreateEditNote();
    }

    @Test
    public void cantAccessStateReport() {
        logger.info(LoginCredentials.USER_VIEW.getUsername() + " can't access state report");

        applicationManager.loginPage(LoginCredentials.USER_VIEW.getUsername(), LoginCredentials.USER_VIEW.getPassword());

        permissionsManager.cantAccessStateReport();
    }

    @Test
    public void cantChangeRollWeight() {
        logger.info(LoginCredentials.USER_VIEW.getUsername() + " can access application settings low");

        applicationManager.loginPage(LoginCredentials.USER_VIEW.getUsername(), LoginCredentials.USER_VIEW.getPassword());

        permissionsManager.cantChangeRollWeight();
    }

    @Test
    public void cantChangeVisualQuality() {

        logger.info(LoginCredentials.USER_VIEW.getUsername() + " can' change visual quality");

        applicationManager.loginPage(LoginCredentials.USER_VIEW.getUsername(), LoginCredentials.USER_VIEW.getPassword());

        permissionsManager.cantChangeVisualQuality();
    }

    @Test
    public void cantChangeLaboratoryQuality() {
        logger.info(LoginCredentials.USER_VIEW.getUsername() + " can't change laboratory quality");

        applicationManager.loginPage(LoginCredentials.USER_VIEW.getUsername(), LoginCredentials.USER_VIEW.getPassword());

        permissionsManager.cantChangeLaboratoryQuality();
    }

    @Test
    public void cantAddRollNote() {

        logger.info(LoginCredentials.USER_VIEW.getUsername() + " can't add roll note");

        applicationManager.loginPage(LoginCredentials.USER_VIEW.getUsername(), LoginCredentials.USER_VIEW.getPassword());

        permissionsManager.cantAddRollNote();
    }

    @Test
    public void cantAccessCampaignManager() {

        logger.info(LoginCredentials.USER_VIEW.getUsername() + " can't access campaign manager");

        applicationManager.loginPage(LoginCredentials.USER_VIEW.getUsername(), LoginCredentials.USER_VIEW.getPassword());

        permissionsManager.cantAccessCampaignManager();
    }
}