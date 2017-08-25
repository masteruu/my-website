package de.brueckner.fph.acceptance.componentsNote;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.NoteManager.*;

public class NotePrivateOnEventAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(NotePrivateOnEventAcceptanceTest.class);

    @Test
    public void notePrivateOnEventAcceptance() {
        logger.info("NotePrivateOnEventAcceptanceTest");

        //add a private note on event with USER_DEV
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
        noteManager.privateCheckbox();
        noteManager.save();
        applicationManager.logout();

        //log with a user without permission to see the private note
        applicationManager.loginPage(LoginCredentials.USER_CT.getUsername(), LoginCredentials.USER_CT.getPassword());
        timelineManager.setNewDateForDatePicker("2016-05-09 06:00:00", "2016-05-09 06:30:00");
        contentManager.waitContentSpinner();

        noteManager.noteButton();
        event = getWebDriver().findElement(By.cssSelector(".eventLaneElement"));
        event.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));

        WebElement noteText = getWebDriver().findElement(By.cssSelector(NOTE_TEXT_FIELD));
        Assert.assertEquals(noteText.getAttribute("value"), "");
        noteManager.close();
        applicationManager.logout();

        //delete previous note
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-09 06:00:00", "2016-05-09 06:30:00");
        contentManager.waitContentSpinner();

        event = getWebDriver().findElement(By.cssSelector(".eventLaneElement"));
        event.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));

        noteManager.deleteNote();

    }
}
