package de.brueckner.fph.acceptance.automaticRefresh;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
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
import java.util.Set;

import static de.brueckner.fph.managers.CampaignManager.CAMPAIGN_NAME_INPUT;
import static de.brueckner.fph.managers.CampaignManager.CUT_ANIMATION_DURATION_MILLISECONDS;
import static de.brueckner.fph.managers.ContentManager.*;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_NEXT_ELEMENT;

public class AutomaticRefreshHardAcceptanceTest extends AbstractAutomaticRefreshTest {

    private static final Logger logger = LoggerFactory.getLogger(AutomaticRefreshHardAcceptanceTest.class);

    /*Hard
    The main components of the application are the three navigation tools: tree, timeline and breadcrumb.
    These components are displaying hierarchical data and the only reliable way to identify and match elements is by start-end time stamp pair.
    For a pair of start-end time stamp, on each level of the hierarchy, an unique element should exist.
    If this condition is not met the components cannot be displayed and an error will be thrown.
    The problem related to modifying data is when the start or the end time stamp of an element is changed after it was loaded.
    If this happens any interaction in the application, with that element, will lead into a critical error.
    Application behavior:
    reload all components;
    delete cached data;
    clear history of previous states (back functionality);
    change selection to the previous visible period in timeline;
    Events that will trigger this type of refresh:
    shifting start/end of the campaign using campaign management shift tool;
    deleting a campaign using campaign management shift tool;
    inserting a new campaign using campaign management cut tool;*/

    String startDate = "2016-04-11 06:00:00";
    String endDate = "2016-04-11 15:00:00";

    @Test
    public void shiftCampaign() {

        logger.info("shiftCampaign");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_EE.getUsername(), LoginCredentials.USER_EE.getPassword());
        }
        timelineManager.setNewDateForDatePicker(startDate, endDate);

        secondTimelineManager.setNewDateForDatePicker(startDate, endDate);

        campaignManager.open();

        //switch a campaign to the right
        campaignManager.shiftButton();

        List<WebElement> shift = getWebDriver().findElements(By.cssSelector(".shift"));
        MouseUtils.dragAndDrop(shift.get(5), 300, getWebDriver());

        campaignManager.shiftButton();
        campaignManager.save();
        campaignManager.close();

        checkAutomaticRefreshNotification();

        String tooltipText = secondApplicationManager.getTooltipContainerText("*[uid='campaign:160411062629088:1460355989088:1460376335896']");
        Assert.assertEquals(tooltipText, "160410_11P18u_400m_3.4t\n" +
                "11.04.16  08:26:29\n" +
                "11.04.16  14:05:355 h 39 min");

        WebElement inactiveBack = secondDriver.findElement(By.cssSelector(BACK_TO_LAST_VIEW_HEADER_BUTTON + ".inactive"));

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160411_11P19u_400m_3.6t"));

    }

    @Test
    public void deleteCampaign() {

        logger.info("deleteCampaign");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_EE.getUsername(), LoginCredentials.USER_EE.getPassword());
        }

        timelineManager.setNewDateForDatePicker(startDate, endDate);

        secondTimelineManager.setNewDateForDatePicker(startDate, endDate);

        campaignManager.open();

        //switch a campaign max to the right so it will be deleted
        campaignManager.shiftButton();

        campaignManager.shiftButton();

        List<WebElement> shift = getWebDriver().findElements(By.cssSelector(".shift"));
        MouseUtils.dragAndDrop(shift.get(5), 700, getWebDriver());

        campaignManager.shiftButton();
        campaignManager.save();
        campaignManager.close();

        checkAutomaticRefreshNotification();

        String tooltipText = secondApplicationManager.getTooltipContainerText("*[uid='campaign:160411062629088:1460355989088:1460383722345']");
        Assert.assertEquals(tooltipText, "160410_11P18u_400m_3.4t\n" +
                "11.04.16  08:26:29\n" +
                "11.04.16  16:08:427 h 42 min");

        WebElement inactiveBack = secondDriver.findElement(By.cssSelector(BACK_TO_LAST_VIEW_HEADER_BUTTON + ".inactive"));

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160410_11P18u_400m_3.4t"));

    }

    @Test
    public void cutCampaign() {

        logger.info("cutCampaign");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_EE.getUsername(), LoginCredentials.USER_EE.getPassword());
        }

        timelineManager.setNewDateForDatePicker(startDate, endDate);

        secondTimelineManager.setNewDateForDatePicker(startDate, endDate);

        campaignManager.open();
        campaignManager.cutButton();

        WebLocator myCut = new WebLocator().setClasses("possibleCut").setAttribute("transform", "translate( 1239.294869876543 0)");
        myCut.click();

        Utils.sleep(CUT_ANIMATION_DURATION_MILLISECONDS);

        campaignManager.productDropdown();
        campaignManager.productOption1();

        WebElement name = getWebDriver().findElement(By.cssSelector(CAMPAIGN_NAME_INPUT));
        applicationManager.sendKeysScript(name, "TEST");

        campaignManager.save();
        campaignManager.close();

        checkAutomaticRefreshNotification();

        WebElement inactiveBack = secondDriver.findElement(By.cssSelector(BACK_TO_LAST_VIEW_HEADER_BUTTON + ".inactive"));

        WebElement campaign = secondDriver.findElement(By.cssSelector("*[uid='campaign:160411100229908:1460368949908:1460376335896']"));
        campaign.click();
        secondTimelineManager.waitTimelineSpinner();
        secondContentManager.waitContentSpinner();

        WebElement timeArrowRight = secondDriver.findElement(By.cssSelector(TIMELINE_NEXT_ELEMENT));
        timeArrowRight.click();

        secondTimelineManager.waitTimelineSpinner();
        secondContentManager.waitContentSpinner();

        WebElement campaignName = secondDriver.findElement(By.cssSelector(CAMPAIGN_OVERVIEW_NAME));
        Assert.assertEquals(campaignName.getText().trim(), "TEST");
        WebElement productName = secondDriver.findElement(By.cssSelector(CAMPAIGN_OVERVIEW_PRODUCT));
        Assert.assertEquals(productName.getText().trim(), "19um Plain");

        //check if the selection is ok in breadcrumb
        secondBreadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "TEST"));

    }

    @Test
    public void deleteSelectedCampaign() {

        logger.info("deleteCampaign");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        Set<String> allWindowHandles = secondDriver.getWindowHandles();

        for (String ignored : allWindowHandles) {
            secondApplicationManager.loginPage(LoginCredentials.USER_EE.getUsername(), LoginCredentials.USER_EE.getPassword());
        }

        timelineManager.setNewDateForDatePicker(startDate, endDate);

        secondTimelineManager.setNewDateForDatePicker(startDate, endDate);
        secondContentManager.waitContentSpinner();

        WebElement campaignClick = secondDriver.findElement(By.cssSelector("*[uid='campaign:160411100229908:1460368949908:1460383722345']"));
        campaignClick.click();
        secondTimelineManager.waitTimelineSpinner();
        secondContentManager.waitContentSpinner();

        campaignManager.open();

        //switch a campaign max to the right so it will be deleted
        campaignManager.shiftButton();

        List<WebElement> shift = getWebDriver().findElements(By.cssSelector(".shift"));
        MouseUtils.dragAndDrop(shift.get(5), 700, getWebDriver());

        campaignManager.shiftButton();
        campaignManager.save();
        campaignManager.close();

        checkAutomaticRefreshNotification();

        String tooltipText = secondApplicationManager.getTooltipContainerText("*[uid='campaign:160411062629088:1460355989088:1460383722345']");
        Assert.assertEquals(tooltipText, "160410_11P18u_400m_3.4t\n" +
                "11.04.16  08:26:29\n" +
                "11.04.16  16:08:427 h 42 min");

        WebElement inactiveBack = secondDriver.findElement(By.cssSelector(BACK_TO_LAST_VIEW_HEADER_BUTTON + ".inactive"));

        //check if the selection is ok in breadcrum
        secondBreadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160410_11P18u_400m_3.4t"));

    }
}
