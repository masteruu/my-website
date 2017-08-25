package de.brueckner.fph.acceptance.componentCampaignManager;

import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.CampaignManager.CM_LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR;
import static de.brueckner.fph.managers.CampaignManager.CM_RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR;

public class CampaignManagerShiftLoadingPeriodAcceptanceTest extends AbstractCampaignManagerTest {
    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerShiftLoadingPeriodAcceptanceTest.class);

    @Test
    public void campaignManagerShiftLoadingPeriodAcceptanceTest() {

        logger.info("CampaignManagerShiftLoadingPeriodAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-09 00:00:00", "2016-05-09 02:00:00");

        campaignManager.open();
        campaignManager.shiftButton();

        //max zoom out
        MouseUtils.mouseScroll_CM("2016-05-09T02:50:00.219+0200", -20, getWebDriver());
        timelineManager.waitTimelineSpinner();
        MouseUtils.mouseScroll_CM("2016-05-09T02:50:00.219+0200", -20, getWebDriver());
        timelineManager.waitTimelineSpinner();
        MouseUtils.mouseScroll_CM("2016-05-09T02:50:00.219+0200", -20, getWebDriver());
        timelineManager.waitTimelineSpinner();
        MouseUtils.mouseScroll_CM("2016-05-09T02:50:00.219+0200", -20, getWebDriver());
        timelineManager.waitTimelineSpinner();

        //check datepicker
        WebElement datepickerCMLeft = getWebDriver().findElement(By.cssSelector(CM_LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        Assert.assertEquals(datepickerCMLeft.getText(), "04.05.16  13:37", "Date and time in the left datepicker from campaign manager is not ok");
        WebElement datepickerCMRight = getWebDriver().findElement(By.cssSelector(CM_RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        Assert.assertEquals(datepickerCMRight.getText(), "10.05.16  19:45", "Date and time in the right datepicker from campaign manager is not ok");

        //check first and last element in selection
        List<WebElement> firstRoll = getWebDriver().findElements(By.cssSelector(".campaignManager .rollStatusLaneElement"));
        Assert.assertEquals(firstRoll.get(0).getAttribute("uid"), "roll:A160504112147:1462360907018:1462366105242");

        List<WebElement> lastRoll = getWebDriver().findElements(By.cssSelector(".campaignManager .rollStatusLaneElement"));
        Assert.assertEquals(lastRoll.get(95).getAttribute("uid"), "roll:A160509063725:1462775845822:null");

        //check that padding is not possible
        WebElement padding = getWebDriver().findElement(By.cssSelector(".campaignManager .paddingElementContainer"));
        MouseUtils.dragAndDrop(padding, 300, getWebDriver());
        campaignManager.waitSpinnerCM();

        firstRoll = getWebDriver().findElements(By.cssSelector(".campaignManager .rollStatusLaneElement"));
        Assert.assertEquals(firstRoll.get(0).getAttribute("uid"), "roll:A160504112147:1462360907018:1462366105242");

        lastRoll = getWebDriver().findElements(By.cssSelector(".campaignManager .rollStatusLaneElement"));
        Assert.assertEquals(lastRoll.get(95).getAttribute("uid"), "roll:A160509063725:1462775845822:null");

    }

    @Test
    public void campaignManagerShiftLoadingPeriodPartiallyVisibleAcceptanceTest() {

        logger.info("CampaignManagerShiftLoadingPeriodAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-08 22:00:00", "2016-05-09 02:00:00");
        campaignManager.open();

        campaignManager.shiftButton();

        //max zoom out
        MouseUtils.mouseScroll_CM("2016-05-09T02:50:00.219+0200", -20, getWebDriver());
        timelineManager.waitTimelineSpinner();
        MouseUtils.mouseScroll_CM("2016-05-09T02:50:00.219+0200", -20, getWebDriver());
        timelineManager.waitTimelineSpinner();
        MouseUtils.mouseScroll_CM("2016-05-09T02:50:00.219+0200", -20, getWebDriver());
        timelineManager.waitTimelineSpinner();
        MouseUtils.mouseScroll_CM("2016-05-09T02:50:00.219+0200", -20, getWebDriver());
        timelineManager.waitTimelineSpinner();

        //check datepicker
        WebElement datepickerCMLeft = getWebDriver().findElement(By.cssSelector(CM_LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        Assert.assertEquals(datepickerCMLeft.getText(), "04.05.16  13:37");
        WebElement datepickerCMRight = getWebDriver().findElement(By.cssSelector(CM_RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        Assert.assertEquals(datepickerCMRight.getText(), "10.05.16  19:45");

        //check first and last element in selection
        List<WebElement> firstRoll = getWebDriver().findElements(By.cssSelector(".campaignManager .rollStatusLaneElement"));
        Assert.assertEquals(firstRoll.get(0).getAttribute("uid"), "roll:A160504112147:1462360907018:1462366105242");

        List<WebElement> lastRoll = getWebDriver().findElements(By.cssSelector(".campaignManager .rollStatusLaneElement"));
        Assert.assertEquals(lastRoll.get(95).getAttribute("uid"), "roll:A160509063725:1462775845822:null");

        //check that padding is not possible
        WebElement padding = getWebDriver().findElement(By.cssSelector(".campaignManager .paddingElementContainer"));
        MouseUtils.dragAndDrop(padding, 300, getWebDriver());
        campaignManager.waitSpinnerCM();

        firstRoll = getWebDriver().findElements(By.cssSelector(".campaignManager .rollStatusLaneElement"));
        Assert.assertEquals(firstRoll.get(0).getAttribute("uid"), "roll:A160504112147:1462360907018:1462366105242");

        lastRoll = getWebDriver().findElements(By.cssSelector(".campaignManager .rollStatusLaneElement"));
        Assert.assertEquals(lastRoll.get(95).getAttribute("uid"), "roll:A160509063725:1462775845822:null");

    }
}
