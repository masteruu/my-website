package de.brueckner.fph.acceptance.componentCampaignManager;

import de.brueckner.fph.util.LoginCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;

public class CampaignManagerSaveNoChangesAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerSaveNoChangesAcceptanceTest.class);

    @Test
    public void campaignManagerSaveNoChangesAcceptanceTest() {

        logger.info("CampaignManagerSaveNoChangesAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //set timeline datepicker to a certain period
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        timelineManager.waitTimelineSpinner();
        contentManager.waitContentSpinner();

        campaignManager.open();

        //set campaign manager datepicker to a different period than the timeline
        campaignManager.setDatePickerCM("2016-04-11 06:00:00", "2016-04-11 15:00:00");

        campaignManager.close();
        timelineManager.waitTimelineSpinner();
        contentManager.waitContentSpinner();

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "11.04.16  08:00  -  11.04.16  17:00"));

    }
}
