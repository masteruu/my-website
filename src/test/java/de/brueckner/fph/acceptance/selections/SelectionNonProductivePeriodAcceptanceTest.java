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
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class SelectionNonProductivePeriodAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(SelectionNonProductivePeriodAcceptanceTest.class);

    @Test
    public void selectionNonProductivePeriodAcceptance() {
        logger.info("selectionNonProductivePeriodAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //select a campaign
        timelineManager.setNewDateForDatePicker("2016-04-11 14:00:00", "2016-04-12 11:00:00");

        treeManager.openTree();

        //click on a non productive in tree
        treeManager.expandNode(0);

        List<WebElement> nonProductiveInTree = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        Assert.assertEquals(nonProductiveInTree.get(3).getText(), "NON_PRODUCTIVE");
        nonProductiveInTree.get(3).click();
        contentManager.waitContentSpinner();

        //check the selection in tree
        treeManager.closeTree();

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160411_11P25u_400m_4.1t", "NON_PRODUCTIVE"));

        //check if the selection is ok in content
        WebElement contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "NON_PRODUCTIVE");

        //check if the selection is done in content
        contentManager.checkContentPeriod(DateUtils.date("2016-04-12 06:27:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-12 06:38:00", DEFAULT_SHORT_DATE_FORMAT));

        //check tabs
        contentManager.checkTabs("OVERVIEW\n" +
                "MATERIAL");

    }
}