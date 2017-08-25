package de.brueckner.fph.acceptance.componentCampaignManager;

import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EventsVisibleInCampaignManagerAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(EventsVisibleInCampaignManagerAcceptanceTest.class);

    @Test
    public void eventsVisibleInCampaignManagerAcceptance() {

        logger.info("EventsVisibleInCampaignManagerAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        campaignManager.open();

        // get Tooltip Content Text
        String tooltipText = applicationManager.getTooltipContentText(".campaignManager .tdo div");
        Assert.assertEquals(tooltipText, "13.04.16  12:12:48");

    }
}
