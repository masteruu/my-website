package de.brueckner.fph.acceptance.componentsTimeline;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class TimelineDragAndDropMarkersAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineDragAndDropMarkersAcceptanceTest.class);

    @Test
    public void timelineDragAndDropMarkersAcceptance() {
        logger.info("timelineDragAndDropMarkersAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");
        contentManager.waitContentSpinner();

        //move the right marker 500 px to the left
        WebElement rightSelector = getWebDriver().findElement(By.cssSelector(".inverted.selectionDecorator"));
        new Actions(getWebDriver()).dragAndDropBy(rightSelector, -500, 0).build().perform();
        contentManager.waitContentSpinner();

        //check selection
        contentManager.checkContentPeriod(DateUtils.date("2016-04-12 22:00:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-13 15:34:00", DEFAULT_SHORT_DATE_FORMAT));

        //move the left marker 500 px to the right
        List<WebElement> leftSelector = getWebDriver().findElements(By.cssSelector(".selectionDecorator"));
        new Actions(getWebDriver()).dragAndDropBy(leftSelector.get(0), 500, 0).build().perform();
        contentManager.waitContentSpinner();

        //check the selection
        contentManager.checkContentPeriod(DateUtils.date("2016-04-13 04:25:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-13 15:34:00", DEFAULT_SHORT_DATE_FORMAT));

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", DateUtils.date("2016-04-13 04:25:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "  -  " + DateUtils.date("2016-04-13 15:34:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE)));

    }
}