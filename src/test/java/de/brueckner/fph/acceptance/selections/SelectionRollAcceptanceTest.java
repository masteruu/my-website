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

import static de.brueckner.fph.managers.ContentManager.CONTENT_NAME;
import static de.brueckner.fph.managers.TreeManager.TREE_DATEPICKER_TEXT_SELECTOR;
import static de.brueckner.fph.util.DateUtils.DEFAULT_LONG_DATE_SHORT_TIME_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;


public class SelectionRollAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(SelectionRollAcceptanceTest.class);

    @Test
    public void selectionRollAcceptanceTest() {
        logger.info("selectionRollAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //select a period of one minute
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");

        contentManager.waitContentSpinner();

        //check content
        WebElement contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "A 160413 1449");

        contentManager.checkContentPeriod(DateUtils.date("2016-04-13 06:49:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-13 08:11:00", DEFAULT_SHORT_DATE_FORMAT));

        //checking breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160411_11T23u_400m_4.3t", "PRODUCTIVE", "A 160413 1449"));

        //time in tree
        treeManager.openTree();
        WebElement timeInTree = getWebDriver().findElement(By.cssSelector(TREE_DATEPICKER_TEXT_SELECTOR));
        Assert.assertEquals(timeInTree.getText().trim(), DateUtils.date("2016-04-12 23:00:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT) + "  -  " + DateUtils.date("2016-04-13 22:00:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));

        //check tabs
        contentManager.checkTabs("OVERVIEW\n" +
                "QUALITY\n" +
                "LAYERS\n" +
                "MATERIAL\n" +
                "NOTES");

    }
}