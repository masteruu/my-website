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
import static de.brueckner.fph.managers.ContentManager.NOTIFICATION_CONTAINER;
import static de.brueckner.fph.managers.NoteManager.*;

public class NoteSaveCancelAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteSaveCancelAcceptanceTest.class);

    @Test
    public void noteSaveCancelAcceptance() {
        logger.info("noteSaveCancelAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //add a note
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.save();

        //edit the note but don't save changes
        noteManager.openNote();
        WebElement noteText = getWebDriver().findElement(By.cssSelector(NOTE_TEXT_FIELD));
        Assert.assertEquals(noteText.getAttribute("value"), "Text to be deleted");
        noteText = getWebDriver().findElement(By.cssSelector(NOTE_TEXT_FIELD));
        noteText.sendKeys(" XXX");
        WebElement cancel = getWebDriver().findElement(By.cssSelector(NOTE_CLOSE));
        cancel.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));

        //check that note was not changed
        noteManager.openNote();
        noteText = getWebDriver().findElement(By.cssSelector(NOTE_TEXT_FIELD));
        Assert.assertEquals(noteText.getAttribute("value"), "Text to be deleted");

        //check if error message for empty note is present
        noteText = getWebDriver().findElement(By.cssSelector(NOTE_TEXT_FIELD));
        noteText.clear();
        getWebDriver().findElement(By.cssSelector(NOTE_SAVE)).click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTIFICATION_CONTAINER)));

        WebElement messageError = getWebDriver().findElement(By.cssSelector(NOTIFICATION_CONTAINER));
        Assert.assertEquals(messageError.getText(), "Can not save\n" +
                "The form still contains errors");
        System.out.println("TEST1");
        WebElement dismiss = getWebDriver().findElement(By.cssSelector(".dismiss"));
        dismiss.click();
        System.out.println("TEST2");
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTIFICATION_CONTAINER)));

        WebElement tooltip = getWebDriver().findElement(By.cssSelector(".tooltip-inner"));
        Assert.assertEquals(tooltip.getText(), "This field cannot be empty");
        noteManager.close();

        //delete the note
        noteManager.openNote();
        noteManager.deleteNote();
    }
}