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
import static de.brueckner.fph.managers.TimelineManager.UNDER_1MONTH_DROPDOWN;

public class TimelineRenderingUnder1MonthAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineRenderingUnder1MonthAcceptanceTest.class);

    @Test
    public void timelineRenderingUnder1MonthAcceptanceTest() {
        logger.info("TimelineRenderingUnder1MonthAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-19 22:00:00", "2016-04-29 22:00:00");

        //check position 1 to be a month
        WebLocator pos1 = new WebLocator().setText("April").setRenderMillis(10000);
        pos1.assertReady();

        //check position 2 to be a week
        WebLocator pos2 = new WebLocator().setText("CW 17").setRenderMillis(10000);
        pos2.assertReady();

        //check position 3 to be a campaign
        WebLocator pos3 = new WebLocator().setText("160423_25u_400m_5T").setRenderMillis(10000);
        pos3.assertReady();
        Utils.sleep(1000);

        //change from campaign to prod/non-prod
        timelineManager.timelineSettingsSetDropdownOption(UNDER_1MONTH_DROPDOWN, PROD_NONPROD_DROPDOWN_OPTION);

        //check position 1 to be a month
        pos1 = new WebLocator().setText("April").setRenderMillis(10000);
        pos1.assertReady();

        //check position 2 to be a campaign
        pos2 = new WebLocator().setText("160423_25u_400m_5T").setRenderMillis(10000);
        pos2.assertReady();

        //check position 3 to be a prod/non-prod
        pos3 = new WebLocator().setText("15").setRenderMillis(10000);
        pos3.assertReady();
        WebLocator rollIcon = new WebLocator().setClasses("rollIcon").setRenderMillis(10000);
        rollIcon.assertReady();
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}