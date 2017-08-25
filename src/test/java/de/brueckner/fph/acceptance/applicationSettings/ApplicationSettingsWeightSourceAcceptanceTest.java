package de.brueckner.fph.acceptance.applicationSettings;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.WeightSourceOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ApplicationSettingsWeightSourceAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsWeightSourceAcceptanceTest.class);

    @Test //source TCE setting
    public void applicationSettingsWeightSource_TCE_AcceptanceTest() {
        logger.info("applicationSettingsWeightSource_TCE_AcceptanceTest");

        checkWeightSource(WeightSourceOption.OPTION_TCE.getOptionText(), "TCE");
    }

    @Test //source Dosing setting
    public void applicationSettingsWeightSource_DOSING_AcceptanceTest() {
        logger.info("applicationSettingsWeightSource_DOSING_AcceptanceTest");
        checkWeightSource(WeightSourceOption.OPTION_DOSING.getOptionText(), "DOSING");
    }

    @Test //source Scale setting
    public void applicationSettingsWeightSource_SCALE_AcceptanceTest() {
        logger.info("applicationSettingsWeightSource_SCALE_AcceptanceTest");
        checkWeightSource(WeightSourceOption.OPTION_SCALE.getOptionText(), "MILLROLL_SCALE");

        WebLocator editButton = new WebLocator().setClasses("dButtonWrapper", "pull-right");
        editButton.assertExists();
    }

    private void checkWeightSource(String weightSource, String weightSourceClass) {
        String startDateString = "2016-04-12 22:00:00";
        String endDateString = "2016-04-13 03:00:00";

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker(startDateString, endDateString);

        applicationSettingsManager.setWeightSource(weightSource, 1);

        timelineManager.clickRoll("roll:A160412220525:1460498725459:1460504913271");

        WebLocator tce = new WebLocator().setClasses("rollWeightType", weightSourceClass).setRenderMillis(7000);
        tce.assertReady();
    }
}