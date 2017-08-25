package de.brueckner.fph.acceptance.componentsTree;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.TreeManager.TREE_DATEPICKER_TEXT_SELECTOR;
import static de.brueckner.fph.util.DateUtils.DEFAULT_LONG_DATE_SHORT_TIME_FORMAT;

public class TreeDatepickerAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(TreeDatepickerAcceptanceTest.class);

    @Test
    public void treeDatepickerAcceptanceTest() {
        logger.info("TreeDatepickerAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        //set tree date picker to 2 months
        treeManager.openTree();

        WebElement treeDatepicker = getWebDriver().findElement(By.cssSelector(TREE_DATEPICKER_TEXT_SELECTOR));
        Assert.assertEquals(treeDatepicker.getText(), DateUtils.date("2016-04-12 22:00:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT)
                + "  -  " + DateUtils.date("2016-04-13 22:00:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));

        //check tree datepicker tooltip
        MouseUtils.mouseOver(treeDatepicker, getWebDriver());

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("tooltip-inner")));

        WebLocator tooltipProduct = new WebLocator().setClasses("noteTooltip").setText("OPEN DATEPICKER");
        tooltipProduct.assertReady();
    }
}