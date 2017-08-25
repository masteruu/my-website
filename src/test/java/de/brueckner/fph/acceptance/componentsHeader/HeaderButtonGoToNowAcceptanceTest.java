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

import static de.brueckner.fph.managers.ContentManager.GO_TO_NOW_HEADER_BUTTON;
import static de.brueckner.fph.managers.TimelineManager.LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class HeaderButtonGoToNowAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(HeaderButtonGoToNowAcceptanceTest.class);


    @Test
    public void headerButtonGoToNowAcceptance() {
        logger.info("headerButtonGoToNowAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //go to a period
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");
        contentManager.waitContentSpinner();

        //check tooltip
        WebElement goToCurrent = getWebDriver().findElement(By.cssSelector(GO_TO_NOW_HEADER_BUTTON));
        Assert.assertEquals(applicationManager.getTooltipTextForHeaderButtons(goToCurrent), "GO TO NOW", "Text from go to now button tooltip is not correct");
        goToCurrent.click();
        contentManager.waitContentSpinner();

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", DateUtils.date("2016-05-10 10:00:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "  -  ..."));

        //check if the selection is ok in content
        contentManager.checkContentPeriod(DateUtils.date("2016-05-10 10:00:00", DEFAULT_SHORT_DATE_FORMAT), "...");

        //check if the selection is ok in content
        WebElement datePleft = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateLeft = datePleft.getText().replace("  ", " ");

        Assert.assertEquals(datePickerDateLeft, DateUtils.date("2016-04-12 22:00:00", DEFAULT_SHORT_DATE_FORMAT));

        //check if the selection is ok in tree
        treeManager.openTree();
        List<WebElement> checkRoll = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        Assert.assertEquals(checkRoll.get(3).getText(), "A 160509 1437", "Roll in tree was not found");

    }
}