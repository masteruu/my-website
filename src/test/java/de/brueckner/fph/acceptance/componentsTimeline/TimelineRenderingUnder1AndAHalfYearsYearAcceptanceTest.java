package de.brueckner.fph.acceptance.componentsTimeline;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TimelineRenderingUnder1AndAHalfYearsYearAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineRenderingUnder1AndAHalfYearsYearAcceptanceTest.class);

    @Test
    public void TimelineRenderingUnder1AndAHalfYearsYearAcceptance() {
        logger.info("TimelineRenderingUnder1AndAHalfYearsYearAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-03-10 22:00:00", "2017-05-10 22:00:00");


        //check position 1 to be a year
        WebLocator pos1 = new WebLocator().setText("2016").setRenderMillis(10000);
        pos1.assertReady();

        //check position 2 to be a month
        WebLocator pos2 = new WebLocator().setText("July").setRenderMillis(10000);
        pos2.assertReady();

    }
}