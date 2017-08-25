package de.brueckner.fph.acceptance.componentsNote;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.StateReportManager.STATE_REPORT_NOTES_CONTAINER;
import static de.brueckner.fph.managers.StateReportManager.STATE_REPORT_NOTE_BODY;

public class NoteInStateReportAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteInStateReportAcceptanceTest.class);

    @Test
    public void noteInStateReportAcceptance() {
        logger.info("noteInStateReportAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-01 22:00:00", "2016-05-07 22:00:00");

        //check that state report has no notes
        stateReportManager.open();
        WebElement notesInStateReport = getWebDriver().findElement(By.cssSelector(STATE_REPORT_NOTES_CONTAINER));
        Assert.assertEquals(notesInStateReport.getText(), "No available notes for this report");

        stateReportManager.close();

        //add note and set it to appear in state report
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.noteStateReportCheckbox();
        noteManager.save();
        stateReportManager.open();

        //check that the note is present in state report
        notesInStateReport = getWebDriver().findElement(By.cssSelector(STATE_REPORT_NOTE_BODY));
        Assert.assertEquals(notesInStateReport.getText(), DateUtils.date("2016-05-04 21:57:00", "dd.MM.YYYYHH:mm") + "\n" +
                "Text to be deleted");

        stateReportManager.close();

        //delete the note
        noteManager.openNote();
        noteManager.deleteNote();

    }
}