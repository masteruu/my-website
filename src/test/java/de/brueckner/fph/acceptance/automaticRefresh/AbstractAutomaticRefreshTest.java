package de.brueckner.fph.acceptance.automaticRefresh;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.managers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.AUTOMATIC_REFRESH_MESSAGE;
import static de.brueckner.fph.managers.ContentManager.NOTIFICATION_CONTAINER;

public class AbstractAutomaticRefreshTest extends AbstractAcceptanceTest {
    protected CampaignManager campaignManager;
    protected WebDriver secondDriver;
    protected ApplicationManager secondApplicationManager;
    protected BreadcrumbManager secondBreadcrumbManager;
    protected TimelineManager secondTimelineManager;
    protected ContentManager secondContentManager;

    @BeforeMethod
    public void setUpTest() {
        campaignManager = new CampaignManager(getWebDriver());
        secondDriver = createSecondWebDriver();
        secondApplicationManager = new ApplicationManager(secondDriver);
        secondBreadcrumbManager = new BreadcrumbManager(secondDriver);
        secondTimelineManager = new TimelineManager(secondDriver);
        secondContentManager = new ContentManager(secondDriver);

    }

    protected void checkAutomaticRefreshNotification() {
        new WebDriverWait(secondDriver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTIFICATION_CONTAINER)));

        WebElement applicationReload = secondDriver.findElement(By.cssSelector(NOTIFICATION_CONTAINER));
        Assert.assertEquals(applicationReload.getText(), AUTOMATIC_REFRESH_MESSAGE, "The automatic refresh notification is not present");

        WebElement dismiss = secondDriver.findElement(By.cssSelector(".dismiss"));
        dismiss.click();

        new WebDriverWait(secondDriver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTIFICATION_CONTAINER)));
    }

    @AfterMethod
    public void restoreCampaigns() throws IOException {
        secondDriver.quit();
        getMongoUtils().executeScript(CAMPAIGNS_SCRIPT_NAME);
    }
}
