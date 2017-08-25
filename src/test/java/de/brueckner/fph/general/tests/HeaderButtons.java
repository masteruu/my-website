package de.brueckner.fph.general.tests;

import de.brueckner.fph.general.AbstractGeneralTest;
import de.brueckner.fph.managers.NoteManager;
import de.brueckner.fph.managers.StateReportManager;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.*;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_PREVIOUS_ELEMENT;

public class HeaderButtons extends AbstractGeneralTest {

    private static final Logger logger = LoggerFactory.getLogger(HeaderButtons.class);

    @Test
    public void backToLastViewButton() {

        //get the breadcrumb text
        WebElement breadCrumbContent = getWebDriver().findElement(By.cssSelector(".breadcrumb li:nth-child(2)"));
        String breadcrumbText = breadCrumbContent.getText();

        //move in the timeline to a different selection
        WebElement previous = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        previous.click();
        contentManager.waitContentSpinner();

        //click back to last view button
        logger.info("Click on the back to last view button");
        WebElement historyBack = getWebDriver().findElement(By.cssSelector(BACK_TO_LAST_VIEW_HEADER_BUTTON));
        historyBack.click();
        contentManager.waitContentSpinner();

        //check that the previous selection is selected
        breadCrumbContent = getWebDriver().findElement(By.cssSelector(".breadcrumb li:nth-child(2)"));
        Assert.assertEquals(breadCrumbContent.getText(), breadcrumbText);
        logger.info("Back To Last View Button Works");

    }

    @Test
    public void reloadButton() {

        logger.info("Click on the reload button");
        WebElement reloadBtn = getWebDriver().findElement(By.cssSelector(RELOAD_HEADER_BUTTON));
        reloadBtn.click();

        applicationManager.waitLogo();

        logger.info("Reload Button Works");

    }

    @Test
    public void fitToSelectionButton() {

        //move selection to the left
        WebElement rightSelector = getWebDriver().findElement(By.cssSelector(".inverted.selectionDecorator"));
        MouseUtils.dragAndDrop(rightSelector, -500, getWebDriver());
        contentManager.waitContentSpinner();

        logger.info("Click on the fit to selection button");
        WebElement fitToSelection = getWebDriver().findElement(By.cssSelector(FIT_TO_SELECTION_HEADER_BUTTON));
        fitToSelection.click();
        contentManager.waitContentSpinner();

        logger.info("Check that the selection was fitted");
        timelineManager.checkFitToSelection();

        logger.info("Fit To Selection Button Works");

    }

    @Test
    public void addNoteButton() {
        NoteManager noteManager = new NoteManager(getWebDriver());

        //go to last 8H so a note can be added there
        WebElement last8H = getWebDriver().findElement(By.cssSelector(LAST_8H_HEADER_BUTTON));
        last8H.click();
        contentManager.waitContentSpinner();

        //add a note
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.save();
        logger.info("A note was added");

        timelineManager.waitTimelineSpinner();

        //open the note and delete it
        noteManager.openNote();
        noteManager.deleteNote();
        logger.info("The note was deleted");

        logger.info("Note Button Works");

    }

    @Test
    public void productionTrendButton() {

        WebElement last8H = getWebDriver().findElement(By.cssSelector(LAST_8H_HEADER_BUTTON));
        last8H.click();
        contentManager.waitContentSpinner();

        logger.info("click on the production trend button");
        WebElement productionTrend = getWebDriver().findElement(By.cssSelector(PRODUCTION_TREND_HEADER_BUTTON));
        productionTrend.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).
                until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".THICKNESSContainer")));

        logger.info("Production trend button is working");

    }

    @Test
    public void stateReportButton() {
        StateReportManager stateReportManager = new StateReportManager(getWebDriver());

        //open state report
        logger.info("Open the state report");
        stateReportManager.open();


        logger.info("State Report opens");

    }
}
