package de.brueckner.fph.acceptance.componentsTimeline;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
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

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.ContentManager.ROLL_OVERVIEW_NAME;


public class TimelineNextPreviousRollShortcutAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineNextPreviousRollShortcutAcceptanceTest.class);

    @Test
    public void timelineNextPreviousRollShortcutAcceptance() {
        logger.info("timelineNextPreviousRollShortcutAcceptance");

        List listKeys = Arrays.asList(Keys.ARROW_UP, Keys.ARROW_DOWN, Keys.ARROW_LEFT, Keys.ARROW_RIGHT);
        List listRolls = Arrays.asList("A 160413 1611", "A 160413 1449", "A 160413 1307", "A 160413 1449");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        contentManager.waitContentSpinner();

        WebElement rollName = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(rollName.getText(), "A 160413 1449");

        for (int i = 0; i < 4; i++) {
            checkTimelineShortcut((Keys) listKeys.get(i), (String) listRolls.get(i));
        }

    }

    void checkTimelineShortcut(Keys myKey, String roll) {
        Keyboard shortcut = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        shortcut.pressKey(myKey);
        contentManager.waitContentSpinner();

        WebElement rollName = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(rollName.getText(), roll);
    }
}
