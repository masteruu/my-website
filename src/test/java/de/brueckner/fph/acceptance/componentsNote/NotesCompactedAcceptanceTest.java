package de.brueckner.fph.acceptance.componentsNote;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.NoteManager.NOTE_DELETE;
import static de.brueckner.fph.managers.NoteManager.NOTE_MODAL;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_LONG_TIME_FORMAT_DOUBLE_SPACE;

public class NotesCompactedAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(NotesCompactedAcceptanceTest.class);

    @Test
    public void notesCompactedAcceptance() {
        logger.info("NotesCompactedAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //add 2 notes
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.save();
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.save();

        WebElement aggregate = getWebDriver().findElement(By.cssSelector(".aggregate"));
        MouseUtils.mouseOver(aggregate, getWebDriver());

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("timelineAggregatedEvents")));

        //check compacted tooltip
        WebElement popover = getWebDriver().findElement(By.cssSelector(".popover"));
        Assert.assertEquals(popover.getText(), DateUtils.date("2016-05-10 09:59:13", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT_DOUBLE_SPACE) + "\n" +
                "Text to be deleted\n" +
                DateUtils.date("2016-05-10 09:59:13", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT_DOUBLE_SPACE) + "\n" +
                "Text to be deleted");

        //delete the 2 notes
        List<WebElement> group = getWebDriver().findElements(By.cssSelector(".group"));
        group.get(0).click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));

        WebElement deleteNote = getWebDriver().findElement(By.cssSelector(NOTE_DELETE));
        deleteNote.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));

        noteManager.openNote();
        noteManager.deleteNote();
    }
}
