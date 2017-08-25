package de.brueckner.fph.acceptance.componentsHeader;


import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.ContentManager.*;
import static de.brueckner.fph.managers.TimelineManager.LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class HeaderButtonCampaignAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(HeaderButtonCampaignAcceptanceTest.class);

    @Test
    public void headerButtonCampaignAcceptance() {
        logger.info("headerButtonCampaignAcceptance");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        WebElement campaign = getWebDriver().findElement(By.cssSelector(CAMPAIGN_BUTTON));
        campaign.click();
        contentManager.waitContentSpinner();

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", DateUtils.date("2016-05-09 10:00:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "  -  ..."));

        WebElement datePleft = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateLeft = datePleft.getText().replace("  ", " ");

        //check date in content
        WebElement contentStartPeriod = getWebDriver().findElement(By.cssSelector(CONTENT_START_PERIOD));
        String dateStart = contentStartPeriod.getText().trim();
        WebElement contentEndPeriod = getWebDriver().findElement(By.cssSelector(CONTENT_END_PERIOD));
        String dateEnd = contentEndPeriod.getText().trim();

        DateUtils.compareDates(datePickerDateLeft, dateStart);
        DateUtils.compareDates("...", dateEnd);

        //check in tree
        treeManager.openTree();
        List<WebElement> checkRoll = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        Assert.assertEquals(checkRoll.get(1).getText(), "1605009_25u_450m_5.3t");

    }
}
