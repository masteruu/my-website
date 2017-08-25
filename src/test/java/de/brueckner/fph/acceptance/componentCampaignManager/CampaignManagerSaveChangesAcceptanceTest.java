package de.brueckner.fph.acceptance.componentCampaignManager;

import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class CampaignManagerSaveChangesAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerSaveChangesAcceptanceTest.class);

    @Test
    public void campaignManagerSaveChangesAcceptanceTest() {

        logger.info("CampaignManagerSaveChangesAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        campaignManager.open();

        campaignManager.setDatePickerCM("2016-04-11 06:00:00", "2016-04-11 15:00:00");

        //switch a campaign to the right
        campaignManager.shiftButton();

        List<WebElement> shift = getWebDriver().findElements(By.cssSelector(".shift"));
        MouseUtils.dragAndDrop(shift.get(5), 500, getWebDriver());

        campaignManager.shiftButton();

        campaignManager.save();
        campaignManager.close();

        contentManager.waitContentSpinner();

        //check if the selection is ok in breadcrum (campaign)
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160411_11P19u_400m_3.6t"));

    }
}
