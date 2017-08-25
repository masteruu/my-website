package de.brueckner.fph.acceptance.selections;

import com.sdl.selenium.web.WebLocator;
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

import static de.brueckner.fph.managers.ContentManager.RELOAD_HEADER_BUTTON;
import static de.brueckner.fph.managers.TreeManager.TREE_DATEPICKER_TEXT_SELECTOR;
import static de.brueckner.fph.util.DateUtils.DEFAULT_LONG_DATE_SHORT_TIME_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class SelectionOneMonthAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(SelectionOneMonthAcceptanceTest.class);

    @Test
    public void selectionOneMonthAcceptanceTest() {
        logger.info("selectionOneMonthAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //select a period of one minute
        timelineManager.setNewDateForDatePicker("2016-04-01 02:00:00", "2016-04-24 22:00:00");

        timelineManager.clickMonth("month:1459461600000:1462053599999");

        contentManager.waitContentSpinner();

        //reload application because of browser/selenium bug
        WebElement reload = getWebDriver().findElement(By.cssSelector(RELOAD_HEADER_BUTTON));
        reload.click();
        applicationManager.waitLogo();

        //check month in timeline
        WebLocator april = new WebLocator().setClasses("contentName").setText("April").setRenderMillis(15000);
        april.assertReady();

        //time in tree
        treeManager.openTree();
        WebElement timeInTree = getWebDriver().findElement(By.cssSelector(TREE_DATEPICKER_TEXT_SELECTOR));
        Assert.assertEquals(timeInTree.getText().trim(), DateUtils.date("2016-03-31 22:00:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT) + "  -  " + DateUtils.date("2016-04-30 21:59:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));

        //check breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "2016", "April"));

        //check period in content
        contentManager.checkContentPeriod(DateUtils.date("2016-03-31 22:00:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-30 21:59:00", DEFAULT_SHORT_DATE_FORMAT));

        //check tabs
        contentManager.checkTabs("PRODUCTS\n" +
                "EFFICIENCY\n" +
                "MATERIAL");

    }
}