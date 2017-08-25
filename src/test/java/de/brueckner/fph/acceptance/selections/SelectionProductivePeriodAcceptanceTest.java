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
import java.util.List;

import static de.brueckner.fph.managers.ContentManager.CONTENT_NAME;
import static de.brueckner.fph.managers.TreeManager.TREE_DATEPICKER_TEXT_SELECTOR;
import static de.brueckner.fph.util.DateUtils.DEFAULT_LONG_DATE_SHORT_TIME_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class SelectionProductivePeriodAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(SelectionProductivePeriodAcceptanceTest.class);

    @Test
    public void selectionProductivePeriodAcceptance() {
        logger.info("selectionProductivePeriodAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //select a period of one minute
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-12 23:00:00");

        contentManager.waitContentSpinner();
        treeManager.openTree();

        List<WebElement> productiveInTree = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        productiveInTree.get(2).click();

        contentManager.waitContentSpinner();

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160411_11T25u_400m_4.1t", "PRODUCTIVE"));

        //check the content
        WebElement contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "PRODUCTIVE");

        contentManager.checkContentPeriod(DateUtils.date("2016-04-12 11:47:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-13 01:41:00", DEFAULT_SHORT_DATE_FORMAT));

        //time in tree
        WebElement timeInTree = getWebDriver().findElement(By.cssSelector(TREE_DATEPICKER_TEXT_SELECTOR));
        Assert.assertEquals(timeInTree.getText().trim(), DateUtils.date("2016-04-12 11:47:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT) + "  -  " + DateUtils.date("2016-04-13 01:41:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));

        //check tabs
        contentManager.checkTabs("OVERVIEW\n" +
                "MATERIAL\n" +
                "THICKNESS");

    }
}
