package de.brueckner.fph.general.tests;

import de.brueckner.fph.general.AbstractGeneralTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.TimelineManager.DATEPICKER_SAVE_BUTTON_SELECTOR;
import static de.brueckner.fph.managers.TimelineManager.LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR;

public class Roll extends AbstractGeneralTest {

    private static final Logger logger = LoggerFactory.getLogger(Roll.class);

    @Test
    public void roll() {

        WebElement datePickerLeft = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        datePickerLeft.click();

        //wait for date picker loading to finish
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(DATEPICKER_SAVE_BUTTON_SELECTOR)));

        timelineManager.selectMaxPeriod();
        logger.info("Datepicker is working, max period was selected");

        treeManager.openTree();

        //while until roll is found
        logger.info("Find a roll in the timeline");
        Boolean finishCond = true;
        do {

            try {
                List<WebElement> expandNode = getWebDriver().findElements(By.cssSelector(".k-icon.k-plus"));
                expandNode.get(0).click();
                contentManager.waitContentSpinner();

                WebElement myRoll = getWebDriver().findElement(By.cssSelector(".objectRating_NONE"));
                myRoll.click();
                contentManager.waitContentSpinner();
                finishCond = false;
            } catch (NoSuchElementException e) {
                List<WebElement> expandNode = getWebDriver().findElements(By.cssSelector(".k-icon.k-plus"));
                if (expandNode.size() == 1) {
                    finishCond = false;
                }
            }
        }
        while (finishCond);

        logger.info("Open the roll");
        List<WebElement> roll = getWebDriver().findElements(By.cssSelector(".objectRating_NONE"));
        roll.get(0).click();
        contentManager.waitContentSpinner();

        //check the roll overview tab

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toggleRollDetails")));

        logger.info("Roll Overview Tab Working");

        checkQualityTab();
        checkLayersTab();
        checkMaterialTab();
        //checkAlertTab();
        checkNotesTab();
    }

}
