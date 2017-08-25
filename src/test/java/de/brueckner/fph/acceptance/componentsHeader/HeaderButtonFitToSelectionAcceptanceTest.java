package de.brueckner.fph.acceptance.componentsHeader;

import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.FIT_TO_SELECTION_HEADER_BUTTON;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class HeaderButtonFitToSelectionAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(HeaderButtonFitToSelectionAcceptanceTest.class);

    @Test
    public void selectionFitKeysShiftS() {

        logger.info("selectionFitKeysShiftS");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        WebElement rightSelector = getWebDriver().findElement(By.cssSelector(".inverted.selectionDecorator"));
        MouseUtils.dragAndDrop(rightSelector, -500, getWebDriver());

        //check shortcut
        Keyboard fitToSelection = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        fitToSelection.pressKey(Keys.SHIFT + "s");
        contentManager.waitContentSpinner();
        timelineManager.checkFitToSelection();
        contentManager.checkContentPeriod(DateUtils.date("2016-05-09 10:00:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-05-10 21:09:00", DEFAULT_SHORT_DATE_FORMAT));


    }

    @Test //for toolbar button
    public void selectionFitToolbarButton() {
        logger.info("selectionFitToolbarButton");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        WebElement rightSelector = getWebDriver().findElement(By.cssSelector(".inverted.selectionDecorator"));
        MouseUtils.dragAndDrop(rightSelector, -500, getWebDriver());

        //check tooltip
        WebElement fitToSelection = getWebDriver().findElement(By.cssSelector(FIT_TO_SELECTION_HEADER_BUTTON));
        Assert.assertEquals(applicationManager.getTooltipTextForHeaderButtons(fitToSelection), "FIT TO SELECTION S");
        fitToSelection.click();
        contentManager.waitContentSpinner();
        timelineManager.checkFitToSelection();
    }
}