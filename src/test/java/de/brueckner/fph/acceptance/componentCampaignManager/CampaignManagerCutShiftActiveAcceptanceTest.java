package de.brueckner.fph.acceptance.componentCampaignManager;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.NOTIFICATION_CONTAINER;

public class CampaignManagerCutShiftActiveAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerCutShiftActiveAcceptanceTest.class);

    @Test
    public void campaignManagerCutShiftActiveAcceptanceTest() {

        logger.info("CampaignManagerCutShiftActiveAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        campaignManager.open();

        WebElement checkProductive = getWebDriver().findElement(By.cssSelector(".campaignManager .selectionToolAwareText.isVisible"));
        Assert.assertEquals(checkProductive.getText(), "1");

        //check cut message
        campaignManager.cutButton();

        checkNotificationContainer();

        //check shift message
        campaignManager.shiftButton();

        checkNotificationContainer();

        WebElement scale = getWebDriver().findElement(By.cssSelector(".campaignManager .background"));
        new Actions(getWebDriver()).dragAndDropBy(scale, -500, 0).build().perform();
        timelineManager.waitTimelineSpinner();

        WebLocator roll = new WebLocator().setText("A 160509 1437");
        roll.assertReady();

        //check that cut works
        campaignManager.cutButton();
        roll = new WebLocator().setText("A 160509 1437");
        roll.assertReady();

        //check that shift works
        campaignManager.shiftButton();
        roll = new WebLocator().setText("A 160509 1437");
        roll.assertReady();

    }

    private void checkNotificationContainer() {
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTIFICATION_CONTAINER)));

        WebElement applicationReload = getWebDriver().findElement(By.cssSelector(NOTIFICATION_CONTAINER));
        Assert.assertEquals(applicationReload.getText(), "Please zoom in\n" +
                "Shift and Cut can be operated only on zoom level of the timeline showing rolls and status.", "The automatic refresh notification is not present");

        WebElement dismiss = getWebDriver().findElement(By.cssSelector(".dismiss"));
        dismiss.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTIFICATION_CONTAINER)));
    }
}
