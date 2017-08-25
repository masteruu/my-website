package de.brueckner.fph.acceptance.componentsNote;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.NoteManager.NOTE_BUTTON;
import static de.brueckner.fph.managers.NoteManager.NOTE_MODAL;

public class NoteOnEventAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteOnEventAcceptanceTest.class);

    @Test
    public void noteDeleteOnEventAcceptance() {
        logger.info("noteDeleteOnEventAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-09 06:00:00", "2016-05-09 06:30:00");
        contentManager.waitContentSpinner();

        //add note to event
        WebElement notes = getWebDriver().findElement(By.cssSelector(NOTE_BUTTON));
        notes.click();

        WebElement event = getWebDriver().findElement(By.cssSelector(".eventLaneElement"));
        event.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));

        noteManager.writeText("Text to be deleted");
        noteManager.save();

        //delete note on event
        event = getWebDriver().findElement(By.cssSelector(".eventLaneElement"));
        event.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));

        noteManager.deleteNote();
    }
}