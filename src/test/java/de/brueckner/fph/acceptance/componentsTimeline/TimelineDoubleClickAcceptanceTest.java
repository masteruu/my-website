package de.brueckner.fph.acceptance.componentsTimeline;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.ROLL_OVERVIEW_NAME;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class TimelineDoubleClickAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineDoubleClickAcceptanceTest.class);

    @Test
    public void doubleClickElementSmallerThanSelection() {
        logger.info("doubleClickElementSmallerThanSelection");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        //double click the roll and check if it is fit to selection
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");

        contentManager.waitContentSpinner();

        //check fit to selection
        timelineManager.checkFitToSelection();

        WebElement rollId = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(rollId.getText(), "A 160413 1449");

        //check if the selection in the content was done ok
        contentManager.checkContentPeriod(DateUtils.date("2016-04-13 06:49:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-13 08:11:00", DEFAULT_SHORT_DATE_FORMAT));

    }

    @Test
    public void doubleClickElementLargerThanSelection() {
        logger.info("doubleClickElementLargerThanSelection");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-12 22:05:00");

        //double click the roll and check if it is fit to selection
        timelineManager.clickRoll("roll:A160412202216:1460492536851:1460498725459");

        contentManager.waitContentSpinner();

        //check fit to selection
        timelineManager.checkFitToSelection();

        WebElement rollId = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(rollId.getText(), "A 160413 0422");

        //check if the selection in the content was done ok
        contentManager.checkContentPeriod(DateUtils.date("2016-04-12 20:22:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-12 22:05:00", DEFAULT_SHORT_DATE_FORMAT));

    }

    @Test
    public void doubleClickElementPartiallyVisibleInSelection() {
        logger.info("doubleClickElementPartiallyVisibleInSelection");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:30:00", "2016-04-13 00:30:00");

        //double click the roll and check if it is fit to selection
        timelineManager.clickRoll("roll:A160412220525:1460498725459:1460504913271");
        timelineManager.clickRoll("roll:A160412220525:1460498725459:1460504913271");

        contentManager.waitContentSpinner();

        //check fit to selection
        timelineManager.checkFitToSelection();

        WebElement rollId = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(rollId.getText(), "A 160413 0605");

        //check if the selection in the content was done ok
        contentManager.checkContentPeriod(DateUtils.date("2016-04-12 22:05:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-12 23:48:00", DEFAULT_SHORT_DATE_FORMAT));

    }
}
