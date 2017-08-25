package de.brueckner.fph.acceptance.componentsNote;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
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

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.NoteManager.NOTE_MODAL;

public class NoteOnCompactedEventsAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteOnCompactedEventsAcceptanceTest.class);

    @Test
    public void noteOnCompactedEventsAcceptanceTest() {
        logger.info("NoteOnCompactedEventsAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-01 22:00:00", "2016-05-03 22:00:00");

        //add a note on a compacted event
        noteManager.noteButton();

        //add note on the first compacted event
        openCompactedEvents();

        noteManager.writeText("Text to be deleted");
        noteManager.save();

        openCompactedEvents();

        //check event name in note modal header
        WebElement eventName = getWebDriver().findElement(By.cssSelector(".noteFormContainer .modal-header"));
        Assert.assertEquals(eventName.getText().trim(), "Note for TDO_BREAK");

        //delete note
        noteManager.deleteNote();

    }

    void openCompactedEvents() {
        WebElement aggregate = getWebDriver().findElement(By.cssSelector(".eventLaneElement .aggregate"));
        MouseUtils.mouseOver(aggregate, getWebDriver());
        List<WebElement> group = getWebDriver().findElements(By.cssSelector(".group"));
        group.get(0).click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));
    }
}
