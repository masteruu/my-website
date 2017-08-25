package de.brueckner.fph.acceptance.componentsNote;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.NoteMachinePartOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class NoteMachinePartAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteMachinePartAcceptanceTest.class);

    @Test
    public void noteMachinePartAcceptance() {
        logger.info("noteMachinePartAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //add a note
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");

        //check all machine part options
        for (NoteMachinePartOption options : NoteMachinePartOption.values()) {
            checkNoteMachinePart(options);
        }

        //delete the note
        noteManager.deleteNote();
    }

    private void checkNoteMachinePart(NoteMachinePartOption option) {
        noteManager.setMachinePartCausedByOption(option.getOptionText(), 0, ".noteFormContainer .noteSection .select2-container");
        noteManager.save();
        Assert.assertEquals(noteManager.getNoteMachinePartTooltip(), "MACHINE PART" + option.getOptionText());
        noteManager.openNote();
        List<WebElement> machinePart = getWebDriver().findElements(By.cssSelector(".noteFormContainer .noteSection .select2-container"));
        Assert.assertEquals(machinePart.get(0).getText(), option.getOptionText());
    }
}