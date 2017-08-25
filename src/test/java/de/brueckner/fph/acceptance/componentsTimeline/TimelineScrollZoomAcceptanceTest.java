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

import static de.brueckner.fph.managers.TimelineManager.TIMELINE_TICKS_VALUES;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR;

public class TimelineScrollZoomAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineScrollZoomAcceptanceTest.class);

    @Test
    public void timelineScrollZoomAcceptanceTest() {
        logger.info("TimelineScrollZoomAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //scroll in 10 times
        MouseUtils.mouseScroll("2016-05-10T11:00:00.219+0200", 10, getWebDriver());
        timelineManager.waitTimelineSpinner();

        //check if the zoom was made ok
        WebElement checkScale = getWebDriver().findElement(By.cssSelector(TIMELINE_TICKS_VALUES));
        Assert.assertEquals(checkScale.getText(), DateUtils.date("2016-05-10 09:00:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR));

        //scroll out 100 times
        MouseUtils.mouseScroll("2016-05-10T11:00:00.219+0200", -100, getWebDriver());
        timelineManager.waitTimelineSpinner();

        //check if the zoom was made ok
        checkScale = getWebDriver().findElement(By.cssSelector(TIMELINE_TICKS_VALUES));
        Assert.assertEquals(checkScale.getText(), DateUtils.date("2016-05-10 08:55:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 08:56:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 08:57:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 08:58:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 08:59:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 09:00:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 09:01:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 09:02:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 09:03:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 09:04:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 09:05:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-05-10 09:06:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR));

    }
}