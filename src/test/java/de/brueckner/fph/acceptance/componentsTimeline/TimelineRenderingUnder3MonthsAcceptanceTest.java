package de.brueckner.fph.acceptance.componentsTimeline;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class TimelineRenderingUnder3MonthsAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineRenderingUnder3DaysAcceptanceTest.class);

    @Test
    public void timelineRenderingUnder3MonthsAcceptance() {
        logger.info("TimelineRenderingUnder3MonthsAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //set the selection to under 3 months
        timelineManager.setNewDateForDatePicker("2016-04-10 11:50:00", "2016-06-10 10:00:00");

        //check position 1 to be a month
        WebLocator pos1 = new WebLocator().setText("April").setRenderMillis(10000);
        pos1.assertReady();

        //check position 2 to be a week
        WebLocator pos2 = new WebLocator().setText("CW 18").setRenderMillis(10000);
        pos2.assertReady();

        //check position 3 to be a prod/non-prod
        WebLocator pos3 = new WebLocator().setText("ILM Init").setRenderMillis(10000);
        pos3.assertReady();

    }
}