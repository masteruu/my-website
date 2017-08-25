package de.brueckner.fph.acceptance.componentsNote;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class NoteOldTimeDurableAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(NoteOldTimeDurableAcceptanceTest.class);

    //check a note does not deteriorate in time
    @Test
    public void noteOldTimeDurableAcceptanceTest() {
        logger.info("NoteOldTimeDurableAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-07 00:00:00", "2016-04-07 02:00:00");
        contentManager.waitContentSpinner();

        Assert.assertEquals(noteManager.getNoteTooltipInTimeline(), DateUtils.date("2016-04-07 01:00:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "\n" +
                "MACHINE PART\n" +
                "Don't delete this note ! (part of NoteOldTimeDurableAcceptanceTest)");
    }
}
