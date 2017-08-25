package de.brueckner.fph.acceptance.componentsTimeline;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TimelineRenderingAbove1AndAHalfYearsYearAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineRenderingAbove1AndAHalfYearsYearAcceptanceTest.class);

    @Test
    public void timelineRenderingAbove1AndAHalfYearsYearAcceptanceTest() {
        logger.info("TimelineRenderingAbove1AndAHalfYearsYearAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //set the date to more than 1.5 years
        timelineManager.setNewDateForDatePicker("2016-03-10 22:00:00", "2017-10-10 22:00:00");

        //check if the year is displayed in the first and only position
        WebLocator pos1 = new WebLocator().setClasses().setText("2016").setRenderMillis(10000);
        pos1.assertReady();

    }
}