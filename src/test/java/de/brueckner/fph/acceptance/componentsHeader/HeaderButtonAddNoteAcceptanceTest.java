package de.brueckner.fph.acceptance.componentsHeader;


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

import static de.brueckner.fph.managers.ContentManager.ADD_NOTE_HEADER_BUTTON;

public class HeaderButtonAddNoteAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(HeaderButtonAddNoteAcceptanceTest.class);


    @Test
    public void headerButtonAddNoteAcceptanceTest() {
        logger.info("headerButtonAddNoteAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //check shortcut
        Keyboard note = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        note.pressKey(Keys.SHIFT + "n");

        WebElement closeNote = getWebDriver().findElement(By.cssSelector(ADD_NOTE_HEADER_BUTTON + ".active"));
        closeNote.click();

        //check tooltip
        WebElement notes = getWebDriver().findElement(By.cssSelector(ADD_NOTE_HEADER_BUTTON));
        Assert.assertEquals(applicationManager.getTooltipTextForHeaderButtons(notes), "ADD NOTE N");

    }
}