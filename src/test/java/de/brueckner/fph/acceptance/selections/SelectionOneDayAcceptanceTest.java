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
import static de.brueckner.fph.util.DateUtils.*;

public class SelectionOneDayAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(SelectionOneDayAcceptanceTest.class);

    @Test
    public void selectionOneDayAcceptance() {
        logger.info("selectionOneDayAcceptance");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //select a period of one day
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", DateUtils.date("2016-04-12 22:00:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "  -  " + DateUtils.date("2016-04-13 22:00:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE)));

        //check if one day selection is ok in content
        contentManager.checkContentPeriod(DateUtils.date("2016-04-12 22:00:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-13 22:00:00", DEFAULT_SHORT_DATE_FORMAT));

        //time in tree
        treeManager.openTree();
        WebElement timeInTree = getWebDriver().findElement(By.cssSelector(TREE_DATEPICKER_TEXT_SELECTOR));
        Assert.assertEquals(timeInTree.getText().trim(), DateUtils.date("2016-04-12 22:00:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT) + "  -  " + DateUtils.date("2016-04-13 22:00:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));

        //check tabs
        contentManager.checkTabs("PRODUCTS\n" +
                "EFFICIENCY\n" +
                "ROLLS\n" +
                "MATERIAL");
    }
}