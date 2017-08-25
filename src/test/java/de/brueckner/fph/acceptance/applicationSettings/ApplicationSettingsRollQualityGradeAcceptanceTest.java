package de.brueckner.fph.acceptance.applicationSettings;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.QualityOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class ApplicationSettingsRollQualityGradeAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsRollQualityGradeAcceptanceTest.class);

    /* 4 rolls are defined:
    uid=roll:A160505100120:1462442480777:1462447685051 VISUAL: Good Roll, LABORATORY: Recyclable
    uit=roll:A160505112805:1462447685051:1462452889644 VISUAL: Non-Recyclable, LABORATORY: Pending
    uid=roll:A160505125449:1462452889644:1462458082834 VISUAL: Pending, LABORATORY: Non-Recyclable
    uid=roll:A160505142122:1462458082834:1462463279563 VISUAL: Recyclable, LABORATORY: Good Roll */
    private final List<Roll> rolls = Arrays.asList(
            new Roll("roll:A160505100120:1462442480777:1462447685051", "GOOD_ROLL", "RECYCLABLE"),
            new Roll("roll:A160505112805:1462447685051:1462452889644", "NON_RECYCLABLE", "timeBasedPossition"),
            new Roll("roll:A160505125449:1462452889644:1462458082834", "timeBasedPossition", "NON_RECYCLABLE"),
            new Roll("roll:A160505142122:1462458082834:1462463279563", "RECYCLABLE", "GOOD_ROLL")
    );

    @Test  //check the roll display when changing options to laboratory quality
    public void applicationSettingsRollLaboratoryQualityAcceptanceTest() {
        logger.info("applicationSettingsRollLaboratoryQualityAcceptanceTest");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        applicationSettingsManager.setRollQuality(QualityOption.OPTION_LABORATORY.getOptionText(), 1);

        timelineManager.setNewDateForDatePicker("2016-05-05 10:00:00", "2016-05-05 16:00:00");

        for (Roll currentRoll : rolls) {
            checkRollQuality(currentRoll.getId(), currentRoll.getLaboratoryQuality());
        }

    }

    @Test  //check the roll display when changing options to laboratory quality
    public void applicationSettingsRollVisualQualityAcceptanceTest() {
        logger.info("applicationSettingsRollVisualQualityAcceptanceTest");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        applicationSettingsManager.setRollQuality(QualityOption.OPTION_VISUAL.getOptionText(), 1);

        timelineManager.setNewDateForDatePicker("2016-05-05 10:00:00", "2016-05-05 16:00:00");

        for (Roll currentRoll : rolls) {
            checkRollQuality(currentRoll.getId(), currentRoll.getVisualQuality());
        }

    }

    private void checkRollQuality(String rollID, String quality) {
        WebLocator myRoll = new WebLocator().setAttribute("uid", rollID);
        WebLocator rollQuality = new WebLocator(myRoll).setClasses(quality);
        rollQuality.assertExists();
    }

    class Roll {
        private final String id;
        private final String visualQuality;
        private final String laboratoryQuality;

        public Roll(String id, String visualQuality, String laboratoryQuality) {
            this.id = id;
            this.visualQuality = visualQuality;
            this.laboratoryQuality = laboratoryQuality;
        }

        public String getId() {
            return id;
        }

        public String getVisualQuality() {
            return visualQuality;
        }

        public String getLaboratoryQuality() {
            return laboratoryQuality;
        }
    }
}