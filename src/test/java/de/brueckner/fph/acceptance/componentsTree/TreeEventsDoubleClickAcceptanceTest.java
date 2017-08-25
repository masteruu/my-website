package de.brueckner.fph.acceptance.componentsTree;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.ContentManager.CONTENT_NAME;
import static de.brueckner.fph.managers.TreeManager.TREE_ROW;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class TreeEventsDoubleClickAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TreeEventsDoubleClickAcceptanceTest.class);

    @Test
    public void treeEventsDoubleClickAcceptanceTest() {
        logger.info("TreeEventsDoubleClickAcceptanceTest");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //set the date to a specific date
        timelineManager.setNewDateForDatePicker("2016-05-08 22:00:00", "2016-05-11 10:00:00");
        treeManager.openTree();


        //double click a campaign in tree
        List<WebElement> treeElement = getWebDriver().findElements(By.cssSelector(TREE_ROW));
        treeElement.get(1).click();
        treeElement.get(1).click();
        treeManager.waitTreeSpinner();
        contentManager.waitContentSpinner();

        //check if the campaign was selected and fit to selection
        WebElement contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "1605009_25u_450m_5.3t");
        contentManager.checkContentPeriod(DateUtils.date("2016-05-09 04:13:00", DEFAULT_SHORT_DATE_FORMAT), "...");
        timelineManager.checkFitToSelection();

        //double click a productive in tree
        treeElement = getWebDriver().findElements(By.cssSelector(TREE_ROW));
        treeElement.get(2).click();
        treeElement.get(2).click();
        treeManager.waitTreeSpinner();
        contentManager.waitContentSpinner();

        //check if the productive was selected and fit to selection
        contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "PRODUCTIVE");
        contentManager.checkContentPeriod(DateUtils.date("2016-05-09 06:37:00", DEFAULT_SHORT_DATE_FORMAT), "...");
        timelineManager.checkFitToSelection();

        //double click a roll in tree
        treeElement = getWebDriver().findElements(By.cssSelector(TREE_ROW));
        treeElement.get(3).click();
        treeElement.get(3).click();
        treeManager.waitTreeSpinner();
        contentManager.waitContentSpinner();

        //check if the roll was cliked and fit to selection
        contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "A 160509 1437");
        contentManager.checkContentPeriod(DateUtils.date("2016-05-09 06:37:00", DEFAULT_SHORT_DATE_FORMAT), "...");
        timelineManager.checkFitToSelection();

        //double click a non productive
        treeElement = getWebDriver().findElements(By.cssSelector(TREE_ROW));
        treeElement.get(4).click();
        treeElement.get(4).click();
        treeManager.waitTreeSpinner();
        contentManager.waitContentSpinner();

        //check if the non productive was clicked and fit to selection
        contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "NON_PRODUCTIVE");
        contentManager.checkContentPeriod(DateUtils.date("2016-05-09 04:13:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-05-09 06:37:00", DEFAULT_SHORT_DATE_FORMAT));
        timelineManager.checkFitToSelection();

        //double click a status
        treeElement = getWebDriver().findElements(By.cssSelector(TREE_ROW));
        treeElement.get(5).click();
        treeElement.get(5).click();
        treeManager.waitTreeSpinner();
        contentManager.waitContentSpinner();

        //check if the status was clicked and fit to selection
        contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "STABILIZING");
        contentManager.checkContentPeriod(DateUtils.date("2016-05-09 06:24:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-05-09 06:37:00", DEFAULT_SHORT_DATE_FORMAT));
        timelineManager.checkFitToSelection();

    }
}
