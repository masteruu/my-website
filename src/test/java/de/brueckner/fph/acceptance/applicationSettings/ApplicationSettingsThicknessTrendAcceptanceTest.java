package de.brueckner.fph.acceptance.applicationSettings;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationSettingsManager.THICKNESS_TREND_INPUT;
import static de.brueckner.fph.managers.ContentManager.THICKNESS_TAB_TEXT;
import static de.brueckner.fph.managers.ContentManager.THICKNESS_TREND_PADDING_BAR;

public class ApplicationSettingsThicknessTrendAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsThicknessTrendAcceptanceTest.class);

    @Test ////thickness trend is set to 48 hours
    public void applicationSettingsThicknessTrendAcceptanceTest() {
        logger.info("applicationSettingsThicknessTrendAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-11 22:00:00", "2016-04-14 22:00:00");

        timelineManager.clickCampaign("campaign:160412114726310:1460461646310:1460511701759");

        contentManager.waitContentSpinner();

        contentManager.openTab(THICKNESS_TAB_TEXT);

        //check the thickness trend displayed rolls
        WebLocator checkRoll = new WebLocator().setTag("span").setText("A 160412 1947");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160412 1947");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160412 2130");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160412 2313");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160413 0056");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160413 0239");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160413 0422");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160413 0605");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160413 0748");
        checkRoll.assertReady();

        //check the panning bar to have a full width
        WebElement panningBar = getWebDriver().findElement(By.cssSelector(THICKNESS_TREND_PADDING_BAR));
        Assert.assertEquals(panningBar.getAttribute("width"), "1813");

        //change the thickness trend period to 10 hours
        applicationSettingsManager.openSettings();
        WebElement thickTrendDown = getWebDriver().findElement(By.cssSelector(THICKNESS_TREND_INPUT));

        thickTrendDown.clear();
        thickTrendDown.sendKeys("10");

        applicationManager.pressEnter();
        applicationSettingsManager.saveSettingsByPressingEnter();

        checkRoll = new WebLocator().setTag("span").setText("A 160412 1947");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160412 1947");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160412 2130");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160412 2313");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160413 0056");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160413 0239");
        checkRoll.assertReady();
        checkRoll = new WebLocator().setTag("span").setText("A 160413 0422");
        checkRoll.assertReady();

        //check the panning bar width to be smaller, because of the thickness trend hours
        panningBar = getWebDriver().findElement(By.cssSelector(THICKNESS_TREND_PADDING_BAR));
        Assert.assertEquals(panningBar.getAttribute("width"), "1303.9139854683951");

    }
}