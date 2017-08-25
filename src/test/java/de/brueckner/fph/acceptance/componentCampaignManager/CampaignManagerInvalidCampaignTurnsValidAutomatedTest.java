package de.brueckner.fph.acceptance.componentCampaignManager;

import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.CampaignManager.CAMPAIGN_NAME_INPUT;
import static de.brueckner.fph.managers.CampaignManager.CUT_ANIMATION_DURATION_MILLISECONDS;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_PREVIOUS_ELEMENT;

public class CampaignManagerInvalidCampaignTurnsValidAutomatedTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerInvalidCampaignTurnsValidAutomatedTest.class);

    String startDate = "2016-04-06 00:00:00";
    String endDate = "2016-04-07 00:00:00";

    @Test
    public void campaignManagerInvalidCampaignEditProductName() {

        //edit an invalid campaign turns it to valid
        logger.info("campaignManagerInvalidCampaignEditProductName");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker(startDate, endDate);

        campaignManager.open();

        WebLocator campaignManagerContainer = new WebLocator().setClasses("campaignManager");
        WebLocator invalidCampaign = new WebLocator(campaignManagerContainer).setAttribute("uid", "campaign:160406101033315:1459928785622:1459950445061");
        invalidCampaign.click();
        campaignManager.waitSpinnerCM();

        //for Product name
        campaignManager.productDropdown();
        campaignManager.productOption3();

        //save changes
        campaignManager.save();
        campaignManager.close();

        checkSelectedCampaignValid();
    }

    @Test
    public void campaignManagerInvalidCampaignEditCampaignName() {

        //edit an invalid campaign turns it to valid
        logger.info("campaignManagerInvalidCampaignEditCampaignName");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker(startDate, endDate);

        campaignManager.open();
        WebLocator campaignManagerContainer = new WebLocator().setClasses("campaignManager");
        WebLocator invalidCampaign = new WebLocator(campaignManagerContainer).setAttribute("uid", "campaign:160406101033315:1459928785622:1459950445061");
        invalidCampaign.click();
        campaignManager.waitSpinnerCM();

        //for Product name
        campaignManager.productDropdown();
        campaignManager.productOption3();

        //for Campaign name
        WebElement name = getWebDriver().findElement(By.cssSelector(CAMPAIGN_NAME_INPUT));
        applicationManager.sendKeysScript(name, "TEST");

        campaignManager.save();
        campaignManager.close();

        checkSelectedCampaignValid();
    }

    @Test
    public void campaignManagerInvalidCampaignPlannedQuantity() {

        //edit an invalid campaign turns it to valid
        logger.info("campaignManagerInvalidCampaignPlannedQuantityAutomated");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker(startDate, endDate);

        campaignManager.open();
        WebLocator campaignManagerContainer = new WebLocator().setClasses("campaignManager");
        WebLocator invalidCampaign = new WebLocator(campaignManagerContainer).setAttribute("uid", "campaign:160406101033315:1459928785622:1459950445061");
        invalidCampaign.click();
        campaignManager.waitSpinnerCM();

        //for Product name
        campaignManager.productDropdown();
        campaignManager.productOption3();

        //for Planned quantity
        WebElement up = getWebDriver().findElement(By.cssSelector(".campaignManager .up"));
        up.click();
        campaignManager.save();
        campaignManager.close();

        checkSelectedCampaignValid();

    }

    @Test
    public void campaignManagerInvalidCampaignShift() {

        //shift an invalid campaign turns it to valid
        logger.info("campaignManagerInvalidCampaignShiftAutomated");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker(startDate, endDate);

        campaignManager.open();
        campaignManager.shiftButton();

        List<WebElement> shift = getWebDriver().findElements(By.cssSelector(".shift"));
        MouseUtils.dragAndDrop(shift.get(5), 200, getWebDriver());

        campaignManager.save();
        campaignManager.close();

        checkSelectedCampaignValid();

        WebElement timeArrowLeft = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        timeArrowLeft.click();
        timelineManager.waitTimelineSpinner();

        checkSelectedCampaignValid();
    }

    @Test
    public void campaignManagerInvalidCampaignCut() {

        //shift an invalid campaign turns it to valid
        logger.info("CampaignManagerInvalidCampaignCutAutomated");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker(startDate, endDate);

        campaignManager.open();
        campaignManager.cutButton();

        List<WebElement> possibleCut = getWebDriver().findElements(By.cssSelector(".possibleCut"));
        possibleCut.get(16).click();

        Utils.sleep(CUT_ANIMATION_DURATION_MILLISECONDS);
        //for Product name
        campaignManager.productDropdown();
        campaignManager.productOption3();

        WebElement keysScript = getWebDriver().findElement(By.cssSelector(CAMPAIGN_NAME_INPUT));
        applicationManager.sendKeysScript(keysScript, "TEST");

        //save changes
        campaignManager.save();
        campaignManager.close();

        checkSelectedCampaignValid();

        WebElement timeArrowLeft = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        timeArrowLeft.click();
        timelineManager.waitTimelineSpinner();

        checkSelectedCampaignValid();
    }

    private void checkSelectedCampaignValid() {
        try {
            WebElement selectedCampaign = getWebDriver().findElement(By.cssSelector(".campaign0.timeBasedPossition.invalid.active"));
            Assert.fail();
            logger.info("Campaign is valid");
        } catch (NoSuchElementException e) {
            logger.info
                    ("Campaign is not valid");
        }
    }
}

