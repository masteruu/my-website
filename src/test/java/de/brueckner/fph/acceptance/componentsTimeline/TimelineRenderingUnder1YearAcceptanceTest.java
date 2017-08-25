package de.brueckner.fph.acceptance.componentsTimeline;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TimelineRenderingUnder1YearAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineRenderingUnder1YearAcceptanceTest.class);

    @Test
    public void timelineRenderingUnder1YearAcceptance() {
        logger.info("TimelineRenderingUnder1YearAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //go to a selection smaller than a year
        timelineManager.setNewDateForDatePicker("2016-03-24 11:50:00", "2016-07-02 10:00:00");

        //check position 1 to be a year
        WebLocator pos1 = new WebLocator().setText("2016").setRenderMillis(10000);
        pos1.assertReady();

        //check position 2 to be a month
        WebLocator pos2 = new WebLocator().setText("May").setRenderMillis(10000);
        pos2.assertReady();

        //check position 3 to be a week
        WebLocator pos3 = new WebLocator().setText("CW 18").setRenderMillis(10000);
        pos3.assertReady();
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}