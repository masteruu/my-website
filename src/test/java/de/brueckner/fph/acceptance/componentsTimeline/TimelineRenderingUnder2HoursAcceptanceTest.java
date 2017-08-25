package de.brueckner.fph.acceptance.componentsTimeline;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static de.brueckner.fph.managers.TimelineManager.PROD_NONPROD_DROPDOWN_OPTION;
import static de.brueckner.fph.managers.TimelineManager.UNDER_2H_DROPDOWN;

public class TimelineRenderingUnder2HoursAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineRenderingUnder2HoursAcceptanceTest.class);

    @Test
    public void timelineRenderingUnder2HoursAcceptanceTest() {
        logger.info("TimelineRenderingUnder2HoursAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //set the selection to under 3 months
        timelineManager.setNewDateForDatePicker("2016-04-10 10:00:00", "2016-04-10 11:00:00");

        //check position 1 to be a campaign
        WebLocator pos1 = new WebLocator().setText("160410_11P23u_400m_4.3t").setRenderMillis(10000);
        pos1.assertReady();

        //check position 3 to be a roll
        WebLocator pos3 = new WebLocator().setText("A 160410 1644").setRenderMillis(10000);
        pos3.assertReady();

        //change from campaign to prod/non-prod
        timelineManager.timelineSettingsSetDropdownOption(UNDER_2H_DROPDOWN, PROD_NONPROD_DROPDOWN_OPTION);

        //check position 1 to be a campaign
        pos1 = new WebLocator().setText("160410_11P23u_400m_4.3t").setRenderMillis(10000);
        pos1.assertReady();

        //check position 3 to be a prod/non-prod
        pos3 = new WebLocator().setText("12").setRenderMillis(10000);
        pos3.assertReady();
        WebLocator rollIcon = new WebLocator().setClasses("rollIcon").setRenderMillis(10000);
        rollIcon.assertReady();

    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}