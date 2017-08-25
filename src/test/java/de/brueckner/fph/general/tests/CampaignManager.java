package de.brueckner.fph.general.tests;

import de.brueckner.fph.general.AbstractGeneralTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class CampaignManager extends AbstractGeneralTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManager.class);

    @Test
    public void campaignManagerTest() {

        //open Campaign manager
        campaignManager.open();
        logger.info("Campaign manager opens");

        //close campaign manager
        campaignManager.close();
        logger.info("Campaign manager closes");

        logger.info("Campaign manager working");

    }
}