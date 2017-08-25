package de.brueckner.fph.acceptance.componentCampaignManager;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.managers.CampaignManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class AbstractCampaignManagerTest extends AbstractAcceptanceTest {
    protected CampaignManager campaignManager;

    @BeforeMethod
    public void setUpTest() {
        campaignManager = new CampaignManager(getWebDriver());
    }

    @AfterMethod
    public void restoreCampaigns() throws IOException {
        getMongoUtils().executeScript(CAMPAIGNS_SCRIPT_NAME);
    }
}
