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
import static de.brueckner.fph.managers.ContentManager.TABS_CONTAINER;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class SelectionStatusAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(SelectionStatusAcceptanceTest.class);

    @Test
    public void selectionStatusAcceptance() {
        logger.info("selectionStatusAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //select a period of one minute
        timelineManager.setNewDateForDatePicker("2016-05-08 22:00:00", "2016-05-11 10:00:00");

        //access a stabilizing status
        treeManager.openTree();
        treeManager.expandNode(0);

        List<WebElement> treeElement = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        treeElement.get(3).click();
        treeElement.get(3).click();
        contentManager.waitContentSpinner();

        treeElement = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        treeElement.get(4).click();
        contentManager.waitContentSpinner();

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "1605009_25u_450m_5.3t", "NON_PRODUCTIVE", "STABILIZING"));

        //check content
        WebElement contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "STABILIZING");

        contentManager.checkContentPeriod(DateUtils.date("2016-05-09 06:24:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-05-09 06:37:00", DEFAULT_SHORT_DATE_FORMAT));

        //check campaign tabs
        WebElement tabs = getWebDriver().findElement(By.cssSelector(TABS_CONTAINER));
        Assert.assertEquals(tabs.getText(), "OVERVIEW");

    }
}