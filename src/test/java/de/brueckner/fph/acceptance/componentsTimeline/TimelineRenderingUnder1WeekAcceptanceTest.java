package de.brueckner.fph.acceptance.componentsTimeline;


import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static de.brueckner.fph.managers.TimelineManager.PROD_NONPROD_DROPDOWN_OPTION;
import static de.brueckner.fph.managers.TimelineManager.UNDER_1WEEK_DROPDOWN;

public class TimelineRenderingUnder1WeekAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineRenderingUnder1WeekAcceptanceTest.class);

    @Test
    public void timelineRenderingUnder1WeekAcceptanceTest() {
        logger.info("TimelineRenderingUnder1WeekAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-17 22:00:00");

        //check position 1 to be a week
        WebLocator pos1 = new WebLocator().setText("CW 15").setRenderMillis(10000);
        pos1.assertReady();

        //check position 2 to be an event
        WebLocator pos2 = new WebLocator().setClasses("tdo").setRenderMillis(10000);
        pos2.assertReady();

        //check position 3 to be a campaign
        WebLocator pos3 = new WebLocator().setText("160415_11p25u_400m_4.8t").setRenderMillis(10000);
        pos3.assertReady();
        Utils.sleep(1000);

        //change from campaign to prod/non-prod
        timelineManager.timelineSettingsSetDropdownOption(UNDER_1WEEK_DROPDOWN, PROD_NONPROD_DROPDOWN_OPTION);

        //check position 1 to be a campaign
        pos1 = new WebLocator().setText("160415_11p25u_400m_4.8t").setRenderMillis(10000);
        pos1.assertReady();

        //check position 2 to be an event
        pos2 = new WebLocator().setClasses("tdo").setRenderMillis(10000);
        pos2.assertReady();

        //check position 3 to be a prod/non-prod
        pos3 = new WebLocator().setText("25").setRenderMillis(10000);
        pos3.assertReady();
        WebLocator rollIcon = new WebLocator().setClasses("rollIcon").setRenderMillis(10000);
        rollIcon.assertReady();
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}