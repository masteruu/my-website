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

import static de.brueckner.fph.managers.ContentManager.ROLLS_TAB_TEXT;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_NEXT_ELEMENT;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_PREVIOUS_ELEMENT;

public class CampaignManagerShiftAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerShiftAcceptanceTest.class);

    String startTime = "2016-04-11 06:00:00";
    String endTime = "2016-04-11 15:00:00";

    @Test
    public void campaignManagerShiftRight() {

        logger.info("CampaignManagerShiftRightAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //set timeline datepicker to a certain period
        timelineManager.setNewDateForDatePicker(startTime, endTime);
        contentManager.waitContentSpinner();

        //check initial start end and duration of two campaigns
        WebElement campaigns = getWebDriver().findElement(By.cssSelector("*[uid='campaign:160411062629088:1460355989088:1460368949908']"));
        MouseUtils.mouseOver(campaigns, getWebDriver());
        campaignManager.checkCampaignStartEnd("160410_11P18u_400m_3.4t", "11.04.16  08:26:29", "11.04.16  12:02:29", "3 h 36 min");
        campaigns = getWebDriver().findElement(By.cssSelector("*[uid='campaign:160411100229908:1460368949908:1460383722345']"));
        MouseUtils.mouseOver(campaigns, getWebDriver());
        campaignManager.checkCampaignStartEnd("160411_11P19u_400m_3.6t", "11.04.16  12:02:29", "11.04.16  16:08:42", "4 h 06 min");

        campaignManager.open();
        campaignManager.waitSpinnerCM();

        //switch a campaign to the right
        campaignManager.shiftButton();

        List<WebElement> shift = getWebDriver().findElements(By.cssSelector(".shift"));
        MouseUtils.dragAndDrop(shift.get(5), 300, getWebDriver());

        campaignManager.shiftButton();

        campaignManager.save();
        campaignManager.close();

        contentManager.waitContentSpinner();

        //check start, end and duration of the campaign after the shift was done
        campaigns = getWebDriver().findElement(By.cssSelector(".rollDurationTimes"));
        Assert.assertEquals(campaigns.getText().trim(), "11.04.16 14:05:35\n" +
                "Start\n" +
                "02h 03min 06s\n" +
                "Duration\n" +
                "11.04.16 16:08:42\n" +
                "End");

        WebElement timeArrowLeft = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        timeArrowLeft.click();
        timelineManager.waitTimelineSpinner();

        campaigns = getWebDriver().findElement(By.cssSelector(".rollDurationTimes"));
        Assert.assertEquals(campaigns.getText().trim(), "11.04.16 08:26:29\n" +
                "Start\n" +
                "05h 39min 06s\n" +
                "Duration\n" +
                "11.04.16 14:05:35\n" +
                "End");

        //check rolls in table
        contentManager.openTab(ROLLS_TAB_TEXT);

        List<WebElement> roll = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(roll.get(2).getText(), "  A 160411 1802");
        Assert.assertEquals(roll.get(3).getText(), "  A 160411 1629");
        Assert.assertEquals(roll.get(4).getText(), "  A 160411 1426");

        WebElement timeArrowRight = getWebDriver().findElement(By.cssSelector(TIMELINE_NEXT_ELEMENT));
        timeArrowRight.click();
        timelineManager.waitTimelineSpinner();

        roll = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(roll.get(2).getText(), "  A 160411 2005");

    }

    @Test
    public void campaignManagerShiftLeft() {

        logger.info("CampaignManagerShiftLeftAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker(startTime, endTime);
        contentManager.waitContentSpinner();

        //check initial start end and duration of two campaigns
        WebElement campaigns = getWebDriver().findElement(By.cssSelector("*[uid='campaign:160411062629088:1460355989088:1460368949908']"));
        MouseUtils.mouseOver(campaigns, getWebDriver());
        campaignManager.checkCampaignStartEnd("160410_11P18u_400m_3.4t", "11.04.16  08:26:29", "11.04.16  12:02:29", "3 h 36 min");

        campaigns = getWebDriver().findElement(By.cssSelector("*[uid='campaign:160411100229908:1460368949908:1460383722345']"));
        MouseUtils.mouseOver(campaigns, getWebDriver());
        campaignManager.checkCampaignStartEnd("160411_11P19u_400m_3.6t", "11.04.16  12:02:29", "11.04.16  16:08:42", "4 h 06 min");

        campaignManager.open();
        campaignManager.waitSpinnerCM();

        //switch a campaign to the left
        campaignManager.shiftButton();

        List<WebElement> shift = getWebDriver().findElements(By.cssSelector(".shift"));
        MouseUtils.dragAndDrop(shift.get(5), -300, getWebDriver());

        campaignManager.shiftButton();

        campaignManager.save();
        campaignManager.close();

        contentManager.waitContentSpinner();

        //check start, end and duration of the campaign after the shift was done
        campaigns = getWebDriver().findElement(By.cssSelector(".rollDurationTimes"));
        Assert.assertEquals(campaigns.getText().trim(), "11.04.16 10:29:31\n" +
                "Start\n" +
                "05h 39min 11s\n" +
                "Duration\n" +
                "11.04.16 16:08:42\n" +
                "End");

        WebElement timeArrowLeft = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        timeArrowLeft.click();
        timelineManager.waitTimelineSpinner();

        campaigns = getWebDriver().findElement(By.cssSelector(".rollDurationTimes"));
        Assert.assertEquals(campaigns.getText().trim(), "11.04.16 08:26:29\n" +
                "Start\n" +
                "02h 03min 02s\n" +
                "Duration\n" +
                "11.04.16 10:29:31\n" +
                "End");

        contentManager.openTab(ROLLS_TAB_TEXT);

        List<WebElement> roll = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(roll.get(2).getText(), "  A 160411 1426");

        WebElement timeArrowRight = getWebDriver().findElement(By.cssSelector(TIMELINE_NEXT_ELEMENT));
        timeArrowRight.click();
        timelineManager.waitTimelineSpinner();

        roll = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(roll.get(2).getText(), "  A 160411 2005");
        Assert.assertEquals(roll.get(3).getText(), "  A 160411 1802");
        Assert.assertEquals(roll.get(4).getText(), "  A 160411 1629");
    }

    @Test
    public void campaignManagerDeleteCampaign() {

        logger.info("CampaignManagerShiftDeleteCampaign");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker(startTime, endTime);
        contentManager.waitContentSpinner();

        //check initial start end and duration of two campaigns
        WebElement campaigns = getWebDriver().findElement(By.cssSelector("*[uid='campaign:160411062629088:1460355989088:1460368949908']"));
        MouseUtils.mouseOver(campaigns, getWebDriver());
        campaignManager.checkCampaignStartEnd("160410_11P18u_400m_3.4t", "11.04.16  08:26:29", "11.04.16  12:02:29", "3 h 36 min");

        campaigns = getWebDriver().findElement(By.cssSelector("*[uid='campaign:160411100229908:1460368949908:1460383722345']"));
        MouseUtils.mouseOver(campaigns, getWebDriver());
        campaignManager.checkCampaignStartEnd("160411_11P19u_400m_3.6t", "11.04.16  12:02:29", "11.04.16  16:08:42", "4 h 06 min");

        campaignManager.open();
        campaignManager.waitSpinnerCM();

        campaignManager.shiftButton();
        //switch a campaign max to the right so it will be deleted

        List<WebElement> shift = getWebDriver().findElements(By.cssSelector(".shift"));
        MouseUtils.dragAndDrop(shift.get(5), 700, getWebDriver());

        campaignManager.shiftButton();
        campaignManager.save();
        campaignManager.close();

        contentManager.waitContentSpinner();

        //check start, end and duration of the campaign after the shift was done
        campaigns = getWebDriver().findElement(By.cssSelector(".rollDurationTimes"));
        Assert.assertEquals(campaigns.getText().trim(), "11.04.16 08:26:29\n" +
                "Start\n" +
                "07h 42min 13s\n" +
                "Duration\n" +
                "11.04.16 16:08:42\n" +
                "End");

        contentManager.openTab(ROLLS_TAB_TEXT);

        List<WebElement> roll = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(roll.get(2).getText(), "  A 160411 2005");
        Assert.assertEquals(roll.get(3).getText(), "  A 160411 1802");
        Assert.assertEquals(roll.get(4).getText(), "  A 160411 1629");
        Assert.assertEquals(roll.get(5).getText(), "  A 160411 1426");

    }
}