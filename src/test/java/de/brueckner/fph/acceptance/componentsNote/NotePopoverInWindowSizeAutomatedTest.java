package de.brueckner.fph.acceptance.componentsNote;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.CAMPAIGN_BUTTON;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_SCALE;

public class NotePopoverInWindowSizeAutomatedTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(NotePopoverInWindowSizeAutomatedTest.class);

    @Test
    public void notePopoverInWindowSizeAutomated() {
        logger.info("NotePopoverInWindowSizeAutomatedTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.save();

        WebElement scale = getWebDriver().findElement(By.cssSelector(TIMELINE_SCALE));
        MouseUtils.dragAndDrop(scale, -600, getWebDriver());

        //check note visible at the edge of the timeline
        Object scrollBarPresent = ((JavascriptExecutor) getWebDriver()).executeScript("return document.documentElement.scrollWidth>document.documentElement.clientWidth;");
        Assert.assertEquals(scrollBarPresent, false);

        WebElement lastCampaignClick = getWebDriver().findElement(By.cssSelector(CAMPAIGN_BUTTON));
        lastCampaignClick.click();
        contentManager.waitContentSpinner();

        //delete note
        noteManager.openNote();
        noteManager.deleteNote();
    }
}
