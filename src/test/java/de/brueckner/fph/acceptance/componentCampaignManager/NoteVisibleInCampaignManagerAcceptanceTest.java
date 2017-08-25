package de.brueckner.fph.acceptance.componentCampaignManager;

import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class NoteVisibleInCampaignManagerAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(NoteVisibleInCampaignManagerAcceptanceTest.class);

    @Test
    public void noteInCampaignManagerAcceptance() {

        logger.info("NoteVisibleInCampaignManagerAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.save();

        campaignManager.open();

        Assert.assertEquals(campaignManager.getNoteTooltipCampaignManager(), DateUtils.date("2016-05-10 09:59:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "\n" +
                "MACHINE PART\n" +
                "Text to be deleted");

        campaignManager.close();
        contentManager.waitContentSpinner();

        //TO DO script for note delete
        noteManager.openNote();
        noteManager.deleteNote();

    }
}
