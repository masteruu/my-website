package de.brueckner.fph.general.tests;

import de.brueckner.fph.general.AbstractGeneralTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ApplicationSettings extends AbstractGeneralTest {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettings.class);

    @Test
    public void applicationSettingsTest() {

        //open the settings and save the settings to check that application is not crashing on settings save
        applicationSettingsManager.openSettings();
        logger.info("Application settings are opened");
        applicationSettingsManager.saveSettingsByPressingEnter();

        logger.info("Application settings were saved successfully");

    }

}