package de.brueckner.fph.acceptance.componentsTimeline;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TimelineScaleAlgorithmSubdivisionsAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineScaleAlgorithmSubdivisionsAcceptanceTest.class);

    @Test
    public void timelineScaleAlgorithmSubdivisionsAcceptance() {
        logger.info("TimelineScaleAlgorithmSubdivisionsAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //Division	Subdivision	Subdivision number	Division label	    Label format	           Milliseconds
        //minute	10 sec	    6	                second in minute	shortDate + shortTime
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-12 22:10:00");
        checkNumberOfThicks(5, 6, 6);

        //Division	Subdivision	Subdivision number	Division label	    Label format	           Milliseconds
        //half hour	1 min	    30	                hour in day	        shortDate + shortTime
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 00:00:00");
        checkNumberOfThicks(5, 6, 30);

        //Division	Subdivision	Subdivision number	Division label	    Label format	           Milliseconds
        //hour	    5 min	    12	                hour in day	        shortDate + shortTime
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 08:00:00");
        checkNumberOfThicks(5, 6, 12);

        //Division	Subdivision	Subdivision number	Division label	    Label format	           Milliseconds
        //hour	    15 min	    4	                hour in day	        shortDate + shortTime
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-15 22:00:00");
        checkNumberOfThicks(57, 58, 4);

        //Division	Subdivision	Subdivision number	Division label	        Label format	           Milliseconds
        //day	    1h	        24	                the day in the month	shortDate + shortTime
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-16 22:00:00");
        checkNumberOfThicks(3, 4, 24);

        //Division	Subdivision	Subdivision number	Division label	        Label format	           Milliseconds
        //day	    2h	        12	                the day in the month	shortDate + shortTime
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-28 22:00:00");
        checkNumberOfThicks(13, 14, 12);

        //Division	Subdivision	Subdivision number	Division label	        Label format	           Milliseconds
        //day	    3h	        8	                the day in the month	shortDate + shortTime
        timelineManager.setNewDateForDatePicker("2016-03-28 22:00:00", "2016-04-29 22:00:00");
        checkNumberOfThicks(20, 21, 8);

        //Division	Subdivision	Subdivision number	Division label	        Label format	           Milliseconds
        //day     	6h	        4	                the day in the month	shortDate + shortTime
        timelineManager.setNewDateForDatePicker("2016-03-28 22:00:00", "2016-05-27 22:00:00");
        checkNumberOfThicks(54, 55, 4);

        //Division	Subdivision	Subdivision number	Division label	        Label format	           Milliseconds
        //day	    8h	        3                  	the day in the month	shortDate + shortTime
        timelineManager.setNewDateForDatePicker("2016-03-28 22:00:00", "2016-06-03 22:00:00");
        checkNumberOfThicks(54, 55, 3);

        //Division	Subdivision	Subdivision number	Division label	        Label format	           Milliseconds
        //month	    3 days	    ~10	                the month short	        shortDate
        timelineManager.setNewDateForDatePicker("2016-03-28 22:00:00", "2016-08-15 22:00:00");
        checkNumberOfThicks(3, 4, 10);

        //Division	Subdivision	Subdivision number	Division label	        Label format	           Milliseconds
        //month   	5 days	    ~6	                the month short	        shortDate
        timelineManager.setNewDateForDatePicker("2016-03-28 22:00:00", "2019-06-21 22:00:00");
        checkNumberOfThicks(32, 33, 6);

        //Division	Subdivision	Subdivision number	Division label	        Label format	           Milliseconds
        //month     week	    ~4	                the month short	        None
        timelineManager.setNewDateForDatePicker("2016-03-28 22:00:00", "2021-05-21 22:00:00");
        checkNumberOfThicks(32, 33, 4);

        //Division	Subdivision	Subdivision number	Division label	        Label format	           Milliseconds
        //year	    month	    12	                the year	            None
        timelineManager.setNewDateForDatePicker("2016-03-28 22:00:00", "2023-08-30 22:00:00");
        checkNumberOfThicks(4, 5, 12);

        //Division	Subdivision	Subdivision number	Division label	        Label format	           Milliseconds
        //year	    year	    10	                ten years	            None
        timelineManager.setNewDateForDatePicker("2016-03-28 22:00:00", "2049-02-11 22:00:00");
        checkNumberOfThicks(4, 5, 10);

    }

    public void checkNumberOfThicks(int thickFirst, int thickLast, int number) {

        WebElement thicks = getWebDriver().findElement(By.cssSelector(".x.axis.timelineAxisTranslate .temporaryTick"));
        String attribute = thicks.getAttribute("d");
        String[] thicksMinor = attribute.split("M");

        List<WebElement> thickMajor = getWebDriver().findElements(By.cssSelector(".tick"));

        String majorThickStart = thickMajor.get(thickFirst).getAttribute("transform").substring(10, 15);
        String majorThickEnd = thickMajor.get(thickLast).getAttribute("transform").substring(10, 15);
        int start = 0;
        int end = 0;
        for (int i = 0; i < thicksMinor.length; i++) {
            if (thicksMinor[i].contains(majorThickStart)) {
                start = i;

            }
            if (thicksMinor[i].contains(majorThickEnd)) {
                end = i;
            }
        }

        if (end - start != number) {
            Assert.fail();
        }
    }

}
