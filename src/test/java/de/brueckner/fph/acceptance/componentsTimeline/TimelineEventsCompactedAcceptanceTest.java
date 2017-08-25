package de.brueckner.fph.acceptance.componentsTimeline;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_LONG_TIME_FORMAT_DOUBLE_SPACE;

public class TimelineEventsCompactedAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineEventsCompactedAcceptanceTest.class);

    @Test
    public void timelineEventsCompactedAcceptanceTest() {

        logger.info("TimelineEventsCompactedAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-10 22:00:00", "2016-04-11 22:00:00");
        contentManager.waitContentSpinner();

        WebElement aggregate = getWebDriver().findElement(By.cssSelector(".aggregate"));
        MouseUtils.mouseOver(aggregate, getWebDriver());
        WebElement popover = getWebDriver().findElement(By.cssSelector(".popover"));
        Assert.assertEquals(popover.getText(),
                DateUtils.date("2016-04-11 03:48:06", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT_DOUBLE_SPACE) + "\n" +
                        DateUtils.date("2016-04-11 03:55:16", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT_DOUBLE_SPACE) + "\n" +
                        DateUtils.date("2016-04-11 04:14:28", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT_DOUBLE_SPACE));

    }
}
