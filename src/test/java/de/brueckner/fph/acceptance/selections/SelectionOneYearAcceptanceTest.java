package de.brueckner.fph.acceptance.selections;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

import static de.brueckner.fph.managers.TreeManager.TREE_DATEPICKER_TEXT_SELECTOR;
import static de.brueckner.fph.util.DateUtils.DEFAULT_LONG_DATE_SHORT_TIME_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class SelectionOneYearAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(SelectionOneYearAcceptanceTest.class);


    @Test
    public void selectionOneYearAcceptanceTest() {
        logger.info("selectionOneYearAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //select a period of one minute
        timelineManager.setNewDateForDatePicker("2016-03-16 11:50:00", "2016-07-04 22:00:00");

        timelineManager.clickYear("year:1451602800000:1483225199999");


        contentManager.waitContentSpinner();

        //check if the selection is ok breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "2016"));

        //check selection in content
        contentManager.checkContentPeriod(DateUtils.date("2015-12-31 23:00:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-12-31 22:59:00", DEFAULT_SHORT_DATE_FORMAT));

        //time in tree
        treeManager.openTree();
        WebElement timeInTree = getWebDriver().findElement(By.cssSelector(TREE_DATEPICKER_TEXT_SELECTOR));
        Assert.assertEquals(timeInTree.getText().trim(), DateUtils.date("2015-12-31 23:00:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT) + "  -  " + DateUtils.date("2016-12-31 22:59:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));

        //check tabs
        contentManager.checkTabs("PRODUCTS\n" +
                "EFFICIENCY\n" +
                "MATERIAL");
    }
}