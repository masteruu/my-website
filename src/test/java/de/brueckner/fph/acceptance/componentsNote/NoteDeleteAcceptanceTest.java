package de.brueckner.fph.acceptance.componentsNote;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NoteDeleteAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteDeleteAcceptanceTest.class);

    @Test
    public void noteDeleteAcceptance() {
        logger.info("noteDeleteAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //add a note
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.save();

        //delete the note
        noteManager.openNote();
        noteManager.deleteNote();

        //check if the note was deleted
        try {
            WebElement noteCheck = getWebDriver().findElement(By.cssSelector(".note"));
            noteCheck.click();
            Assert.fail();
        } catch (NoSuchElementException e) {
        }
    }
}