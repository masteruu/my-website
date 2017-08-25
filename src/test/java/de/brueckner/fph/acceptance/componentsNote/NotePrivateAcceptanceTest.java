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

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.NoteManager.NOTE_OPEN;

public class NotePrivateAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(NotePrivateAcceptanceTest.class);
    //note can be added only by users gr_et_fph_test, gr_ee_fph_test, gr_dev_fph_test

    @Test
    public void notePrivateAcceptanceTest() {
        logger.info("NotePrivateAcceptanceTest");

        List<LoginCredentials> listUsersAddNote = Arrays.asList(LoginCredentials.USER_ET, LoginCredentials.USER_EE, LoginCredentials.USER_DEV);
        List<LoginCredentials> listUsersCheckNote = Arrays.asList(LoginCredentials.USER_VIEW, LoginCredentials.USER_OP, LoginCredentials.USER_SUP, LoginCredentials.USER_CT, LoginCredentials.USER_CE);

        for (LoginCredentials users : listUsersAddNote) {
            addNoteByUser(users);
        }

        for (LoginCredentials users : listUsersCheckNote) {
            checkNotePresent(users);
        }

        for (LoginCredentials users : listUsersAddNote) {
            deleteNoteByUser(users);
        }

    }

    void addNoteByUser(LoginCredentials credentials) {
        applicationManager.loginPage(credentials.getUsername(), credentials.getPassword());
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Note By " + credentials.getUsername());
        noteManager.privateCheckbox();
        noteManager.save();
        applicationManager.logout();
    }

    void deleteNoteByUser(LoginCredentials credentials) {
        applicationManager.loginPage(credentials.getUsername(), credentials.getPassword());
        noteManager.openNote();
        noteManager.deleteNote();
        applicationManager.logout();
    }

    public void checkNotePresent(LoginCredentials credentials) {
        applicationManager.loginPage(credentials.getUsername(), credentials.getPassword());
        try {
            WebElement note = getWebDriver().findElement(By.cssSelector(NOTE_OPEN));
            Assert.fail();
            logger.info("Note was found");
        } catch (NoSuchElementException e) {

        }
        applicationManager.logout();
    }

}
