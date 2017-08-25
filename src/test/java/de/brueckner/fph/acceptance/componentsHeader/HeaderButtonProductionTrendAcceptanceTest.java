package de.brueckner.fph.acceptance.componentsHeader;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.PRODUCTION_TREND_HEADER_BUTTON;

public class HeaderButtonProductionTrendAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(HeaderButtonProductionTrendAcceptanceTest.class);

    @Test
    public void headerButtonProductionTrendAcceptance() {
        logger.info("headerButtonProductionTrendAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //check shortcut
        Keyboard shortcut = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        shortcut.pressKey(Keys.SHIFT + "p");
        WebLocator graph = new WebLocator().setClasses("THICKNESSContainer").setRenderMillis(15000);
        graph.assertReady();

        WebElement prodTrend = getWebDriver().findElement(By.cssSelector(PRODUCTION_TREND_HEADER_BUTTON + ".active"));
        prodTrend.click();

        //check tooltip
        WebElement goToCurrent = getWebDriver().findElement(By.cssSelector(PRODUCTION_TREND_HEADER_BUTTON));
        Assert.assertEquals(applicationManager.getTooltipTextForHeaderButtons(goToCurrent), "PRODUCTION TREND P", "Text from production trend button tooltip is not correct");

    }
}