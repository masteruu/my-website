package de.brueckner.fph.acceptance.componentsStateReport;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.NoteCausedByOption;
import de.brueckner.fph.util.NoteMachinePartOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.StateReportManager.STATE_REPORT_NOTE_BODY;
import static de.brueckner.fph.managers.StateReportManager.STATE_REPORT_NOTE_EDIT_TEXT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_LONG_DATE_SHORT_TIME_FORMAT;

public class StateReportNoteEditAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(StateReportNoteEditAcceptanceTest.class);

    @Test
    public void stateReportNoteEditAcceptance() {
        logger.info("stateReportNoteEditAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-01 22:00:00", "2016-05-07 22:00:00");
        contentManager.waitContentSpinner();

        //add a note and set in to state report
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.noteStateReportCheckbox();
        noteManager.save();

        stateReportManager.open();

        stateReportManager.editStateReportNote();

        //edit note text
        WebElement noteText = getWebDriver().findElement(By.cssSelector(STATE_REPORT_NOTE_EDIT_TEXT));
        noteText.sendKeys(" EDIT");

        //check all machine part options
        for (NoteMachinePartOption options : NoteMachinePartOption.values()) {
            checkNoteMachinePart(options);
        }

        //check all caused by options
        for (NoteCausedByOption options : NoteCausedByOption.values()) {
            checkNoteCausedBy(options);
        }

        stateReportManager.noteStateReportSaveEdit();

        //check full body of edited note
        WebElement noteBody = getWebDriver().findElement(By.cssSelector(STATE_REPORT_NOTE_BODY));
        Assert.assertEquals(noteBody.getText().trim(), DateUtils.date("2016-05-04 21:57:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT) + "\n" +
                "MACHINE PART:\n" +
                "MDO\n" +
                "CAUSED BY:\n" +
                "Other\n" +
                "Other\n" +
                "USER:\n" +
                "Gr_dev_fph_test\n" +
                "Text to be deleted EDIT");
        stateReportManager.close();

        //delete note
        noteManager.openNote();
        noteManager.deleteNote();

    }

    private void checkNoteCausedBy(NoteCausedByOption option) {
        noteManager.setMachinePartCausedByOption(option.getOptionText(), option.getDropdownIndex(), ".note.noteFormBody.editing .select2-container");

        List<WebElement> causedBy = getWebDriver().findElements(By.cssSelector(".note.noteFormBody.editing .select2-container"));
        Assert.assertEquals(causedBy.get(option.getDropdownIndex()).getText().trim(), option.getOptionText());
    }

    private void checkNoteMachinePart(NoteMachinePartOption option) {
        noteManager.setMachinePartCausedByOption(option.getOptionText(), 0, ".note.noteFormBody.editing .select2-container");

        //check text option in dropdown
        List<WebElement> machinePart = getWebDriver().findElements(By.cssSelector(".note.noteFormBody.editing .select2-container"));
        Assert.assertEquals(machinePart.get(0).getText(), option.getOptionText());

        stateReportManager.noteStateReportSaveEdit();

        //check option after edit save
        WebElement noteMachinePartText = getWebDriver().findElement(By.cssSelector(".stateReportContainer > div.stateReportBody.slideFormBody > div.box.col-xs-4.fullHeight.scrollbar > div.note.noteFormBody > div.formBody > div.col-lg-6.dataPart > div:nth-child(2) > span"));
        Assert.assertEquals(noteMachinePartText.getText(), option.getOptionText());

        stateReportManager.close();

        //check option in tooltip
        Assert.assertEquals(noteManager.getNoteMachinePartTooltip(), "MACHINE PART" + option.getOptionText());

        stateReportManager.open();
        stateReportManager.editStateReportNote();
    }
}