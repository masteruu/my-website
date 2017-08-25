package de.brueckner.fph.acceptance.componentsNote;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.NoteCausedByOption;
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
import static de.brueckner.fph.managers.NoteManager.NOTE_DELETE;
import static de.brueckner.fph.managers.NoteManager.NOTE_MODAL;

public class NoteCausedByAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteCausedByAcceptanceTest.class);

    @Test
    public void notesCausedByAcceptance() {
        logger.info("notesCausedByAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //add a note
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.save();
        noteManager.openNote();

        //check all caused by options
        for (NoteCausedByOption options : NoteCausedByOption.values()) {
            checkNoteCausedBy(options);
        }

        //delete note on event
        WebElement delete = getWebDriver().findElement(By.cssSelector(NOTE_DELETE));
        delete.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));
    }

    private void checkNoteCausedBy(NoteCausedByOption option) {
        noteManager.setMachinePartCausedByOption(option.getOptionText(), option.getDropdownIndex(), ".noteFormContainer .noteSection .select2-container");
        noteManager.save();
        noteManager.openNote();

        List<WebElement> causedBy = getWebDriver().findElements(By.cssSelector(".noteFormContainer .noteSection .select2-container"));
        Assert.assertEquals(causedBy.get(option.getDropdownIndex()).getText().trim(), option.getOptionText());
    }

}
