package de.brueckner.fph.general.tests;

import de.brueckner.fph.general.AbstractGeneralTest;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.TimelineManager.TIMELINE_NEXT_ELEMENT;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_PREVIOUS_ELEMENT;

public class Timeline extends AbstractGeneralTest {
    private static final Logger logger = LoggerFactory.getLogger(Timeline.class);

    @Test
    public void timelineNextPreviousTest() {

        //check previous button
        WebElement previous = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        previous.click();
        contentManager.waitContentSpinner();
        logger.info("Previous element selection works in timeline");

        //check next button
        WebElement next = getWebDriver().findElement(By.cssSelector(TIMELINE_NEXT_ELEMENT));
        next.click();
        logger.info("Next element selection works in timeline");

    }

    @Test
    public void timelineDragAndDropMarkersAcceptanceTest() {

        //move the left marker 500 px to the right
        WebElement rightSelector = getWebDriver().findElement(By.cssSelector(".inverted.selectionDecorator"));
        MouseUtils.dragAndDrop(rightSelector, -500, getWebDriver());
        contentManager.waitContentSpinner();

        List<WebElement> leftSelector = getWebDriver().findElements(By.cssSelector(".selectionDecorator"));
        MouseUtils.dragAndDrop(leftSelector.get(0), 500, getWebDriver());
        contentManager.waitContentSpinner();

        logger.info("Left and Right markers on timeline are working with drag and drop");
    }

    @Test
    public void timelineScaleZoomAcceptanceTest() {

        //check if a zoom was performed without crashing
        WebElement scale = getWebDriver().findElement(By.cssSelector(".background"));
        MouseUtils.dragAndDrop(scale, -500, getWebDriver());
        contentManager.waitContentSpinner();

        logger.info("Timeline scale zoom works");

    }
}
