package de.brueckner.fph.acceptance.componentsNote;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.NOTE_USER;

public class NoteByUserAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteByUserAcceptanceTest.class);

    @Test
    public void noteByUserAcceptance() {
        logger.info("noteByUserAcceptance");

        for (LoginCredentials credentials : LoginCredentials.values()) {
            if (!credentials.equals(LoginCredentials.USER_VIEW)) checkNoteForUser(credentials);
        }
    }

    private void checkNoteForUser(LoginCredentials credential) {
        applicationManager.loginPage(credential.getUsername(), credential.getPassword());
        noteManager.noteButton();
        noteManager.addNote();

        WebElement userNote = getWebDriver().findElement(By.cssSelector(NOTE_USER));
        Assert.assertEquals(userNote.getText(), credential.getUsername(), "Note for user " + credential.getUsername() + " is not correct");
        noteManager.writeText("Text to be deleted");
        noteManager.save();
        noteManager.openNote();

        userNote = getWebDriver().findElement(By.cssSelector(NOTE_USER));
        Assert.assertEquals(userNote.getText(), credential.getUsername(), "Note for user " + credential.getUsername() + " is not correct");
        noteManager.deleteNote();
        applicationManager.logout();
    }
}