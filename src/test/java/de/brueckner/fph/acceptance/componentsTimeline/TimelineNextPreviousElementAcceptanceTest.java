package de.brueckner.fph.acceptance.componentsTimeline;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.CAMPAIGN_OVERVIEW_NAME;
import static de.brueckner.fph.managers.ContentManager.ROLL_OVERVIEW_NAME;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_NEXT_ELEMENT;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_PREVIOUS_ELEMENT;

public class TimelineNextPreviousElementAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineNextPreviousElementAcceptanceTest.class);

    @Test
    public void campaignType() {
        logger.info("campaignType");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-15 22:00:00");

        timelineManager.clickCampaign("campaign:160415032634472:1460690794472:1460734663379");
        contentManager.waitContentSpinner();

        WebElement campaignName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_NAME));
        Assert.assertEquals(campaignName.getText().trim(), "160411_11p23u_400m_ 4.3t");

        //left arrow test
        WebElement previousCampaign = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        previousCampaign.click();
        contentManager.waitContentSpinner();

        campaignName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_NAME));
        Assert.assertEquals(campaignName.getText().trim(), "160411_11T23u_400m_ 4.3t");

        //right arrow test
        WebElement nextCampaign = getWebDriver().findElement(By.cssSelector(TIMELINE_NEXT_ELEMENT));
        nextCampaign.click();
        contentManager.waitContentSpinner();

        campaignName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_NAME));
        Assert.assertEquals(campaignName.getText().trim(), "160411_11p23u_400m_ 4.3t");
    }

    @Test
    public void rollType() {
        logger.info("rollType");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        contentManager.waitContentSpinner();

        WebElement rollName = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(rollName.getText(), "A 160413 1449");

        //left arrow test
        WebElement previousRoll = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        previousRoll.click();
        contentManager.waitContentSpinner();

        rollName = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(rollName.getText(), "A 160413 1307");

        //right arrow test
        WebElement nextRoll = getWebDriver().findElement(By.cssSelector(TIMELINE_NEXT_ELEMENT));
        nextRoll.click();
        contentManager.waitContentSpinner();

        rollName = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(rollName.getText(), "A 160413 1449");

    }
}