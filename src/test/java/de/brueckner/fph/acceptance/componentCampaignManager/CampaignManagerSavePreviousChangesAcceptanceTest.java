package de.brueckner.fph.acceptance.componentCampaignManager;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.ContentManager.NOTIFICATION_CONTAINER;

public class CampaignManagerSavePreviousChangesAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerSavePreviousChangesAcceptanceTest.class);

    @Test
    public void campaignManagerSavePreviousChangesAcceptance() {

        logger.info("CampaignManagerSavePreviousChangesAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //set timeline datepicker to a certain period
        timelineManager.setNewDateForDatePicker("2016-04-11 06:00:00", "2016-04-11 15:00:00");

        campaignManager.open();

        //switch a campaign to the right
        campaignManager.shiftButton();
        campaignManager.waitSpinnerCM();

        List<WebElement> shift = getWebDriver().findElements(By.cssSelector(".shift"));
        MouseUtils.dragAndDrop(shift.get(5), 500, getWebDriver());

        //click on the edit button and check the info box popup
        campaignManager.editButton();

        WebElement notificationContainer = getWebDriver().findElement(By.cssSelector(NOTIFICATION_CONTAINER));
        Assert.assertEquals(notificationContainer.getText(), "Save changes\n" +
                "Do you want to save the previous changes?\n" +
                "YES\n" +
                "NO");

        WebLocator yes = new WebLocator().setText("Yes");
        yes.click();

        campaignManager.waitSpinnerCM();

        campaignManager.close();

        contentManager.waitContentSpinner();

        //check that the changes were saved
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160411_11P19u_400m_3.6t"));
    }
}
