package de.brueckner.fph.acceptance.componentsNote;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class NoteTooltipsAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteTooltipsAcceptanceTest.class);

    @Test
    public void noteTooltipsAcceptance() {
        logger.info("noteTooltipsAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //add a note
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.save();

        //check tooltip in timeline
        Assert.assertEquals(noteManager.getNoteTooltipInTimeline(), DateUtils.date("2016-05-10 9:59:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "\n" +
                "MACHINE PART\n" +
                "Text to be deleted");

        //check tooltip in tree
        treeManager.openTree();
        Assert.assertEquals(noteManager.getNoteTooltipInTree(), DateUtils.date("2016-05-10 9:59:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "\n" +
                "MACHINE PART\n" +
                "Text to be deleted");
        treeManager.closeTree();

        //delete the note
        noteManager.openNote();
        noteManager.deleteNote();
    }
}