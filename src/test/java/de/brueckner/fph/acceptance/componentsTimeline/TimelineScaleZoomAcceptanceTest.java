package de.brueckner.fph.acceptance.componentsTimeline;


import com.sdl.selenium.web.WebLocator;
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

import static de.brueckner.fph.managers.TimelineManager.TIMELINE_SCALE;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_TICKS_VALUES;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR;

public class TimelineScaleZoomAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineScaleZoomAcceptanceTest.class);

    @Test
    public void TimelineScaleZoomAcceptance() {
        logger.info("TimelineScaleZoomAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        //zoom in 6 times until the info message "Your time selection must be more than 1 minute!" appears
        WebElement scale = getWebDriver().findElement(By.cssSelector(TIMELINE_SCALE));
        MouseUtils.dragAndDrop(scale, -500, getWebDriver());
        timelineManager.waitTimelineSpinner();

        WebElement checkScale = getWebDriver().findElement(By.cssSelector(TIMELINE_TICKS_VALUES));
        Assert.assertEquals(checkScale.getText(), DateUtils.date("2016-04-13 04:00:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:00:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 06:00:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 07:00:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 08:00:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 09:00:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 10:00:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR));
        scale = getWebDriver().findElement(By.cssSelector(TIMELINE_SCALE));
        MouseUtils.dragAndDrop(scale, -500, getWebDriver());
        timelineManager.waitTimelineSpinner();

        checkScale = getWebDriver().findElement(By.cssSelector(TIMELINE_TICKS_VALUES));
        Assert.assertEquals(checkScale.getText(), DateUtils.date("2016-04-13 05:30:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 06:00:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 06:30:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR));

        scale = getWebDriver().findElement(By.cssSelector(TIMELINE_SCALE));
        MouseUtils.dragAndDrop(scale, -500, getWebDriver());
        timelineManager.waitTimelineSpinner();

        checkScale = getWebDriver().findElement(By.cssSelector(TIMELINE_TICKS_VALUES));
        Assert.assertEquals(checkScale.getText(), DateUtils.date("2016-04-13 05:28:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:29:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:30:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:31:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:32:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:33:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:34:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:35:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:36:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:37:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:38:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:39:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:40:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:41:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:42:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:43:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:44:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:45:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:46:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:47:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:48:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:49:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:50:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:51:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:52:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:53:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:54:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:55:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:56:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR));

        scale = getWebDriver().findElement(By.cssSelector(TIMELINE_SCALE));
        MouseUtils.dragAndDrop(scale, -500, getWebDriver());
        timelineManager.waitTimelineSpinner();

        checkScale = getWebDriver().findElement(By.cssSelector(TIMELINE_TICKS_VALUES));
        Assert.assertEquals(checkScale.getText(), DateUtils.date("2016-04-13 05:34:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:35:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:36:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:37:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:38:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:39:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:40:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:41:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR));

        scale = getWebDriver().findElement(By.cssSelector(TIMELINE_SCALE));
        MouseUtils.dragAndDrop(scale, -500, getWebDriver());
        timelineManager.waitTimelineSpinner();

        checkScale = getWebDriver().findElement(By.cssSelector(TIMELINE_TICKS_VALUES));
        Assert.assertEquals(checkScale.getText(), DateUtils.date("2016-04-13 05:36:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "\n" +
                DateUtils.date("2016-04-13 05:37:16", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR));

        scale = getWebDriver().findElement(By.cssSelector(TIMELINE_SCALE));
        MouseUtils.dragAndDrop(scale, -500, getWebDriver());
        timelineManager.waitTimelineSpinner();

        //check info message "Your time selection must be more than 1 minute!"
        WebLocator messageBox = new WebLocator().setText("Your time selection must be more than 1 minute!");
        messageBox.assertReady();
        WebElement closeMessage = getWebDriver().findElement(By.cssSelector(".dismiss"));
        closeMessage.click();
    }
}