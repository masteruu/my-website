package de.brueckner.fph.acceptance.componentsNote;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.NoteManager.*;

public class NoteAttachementsAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteAttachementsAcceptanceTest.class);

    @Value("${attachment.small.source.path}")
    private String attachmentSmallToLoad;

    @Value("${attachment.big.source.path}")
    private String attachmentBigToLoad;

    public NoteAttachementsAcceptanceTest() {
    }


    @Test
    public void noteAddAttachmentAcceptanceTest() {
        logger.info("noteAddAttachmentsAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //add a note attachment
        addAttachment(attachmentSmallToLoad);

        noteManager.openNote();

        //check if the attachment is present
        WebElement attachmentContent = getWebDriver().findElement(By.cssSelector(".noteUploaderUpload"));
        Assert.assertEquals(attachmentContent.getText().trim(), "rollsAsFavorites.png", "The attachment is not present");

        //delete note
        noteManager.deleteNote();

    }

    @Test
    public void noteDeleteAttachmentAcceptanceTest() {
        logger.info("noteDeleteAttachmentAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //add a note
        addAttachment(attachmentSmallToLoad);

        noteManager.openNote();
        WebElement deleteAttachment = getWebDriver().findElement(By.cssSelector(NOTE_ATTACHMENT_DELETE_BUTTON));
        deleteAttachment.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_ATTACHMENT_DELETE_NOTIFICATION)));

        //check delete confirmation message
        WebElement deleteConfirmation = getWebDriver().findElement(By.cssSelector(NOTE_ATTACHMENT_DELETE_NOTIFICATION));
        Assert.assertEquals(deleteConfirmation.getText().trim(), "Are you sure you want to delete 'rollsAsFavorites.png'?");

        //don't delete the attachment
        WebLocator noButton = new WebLocator().setTag("button").setText("No");
        noButton.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_ATTACHMENT_DELETE_NOTIFICATION)));

        //check that the attachment was note deleted
        WebElement attachmentContent = getWebDriver().findElement(By.cssSelector(NOTE_ATTACHMENT_NAME));
        Assert.assertEquals(attachmentContent.getText().trim(), "rollsAsFavorites.png", "The attachment is not present");

        deleteAttachment = getWebDriver().findElement(By.cssSelector(NOTE_ATTACHMENT_DELETE_BUTTON));
        deleteAttachment.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_ATTACHMENT_DELETE_NOTIFICATION)));

        //delete the attachment
        WebLocator yesButton = new WebLocator().setTag("button").setText("Yes");
        yesButton.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_ATTACHMENT_DELETE_NOTIFICATION)));

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_ATTACHMENT_DELETE_BUTTON)));

        //check that the attachment was deleted
        try {
            WebElement noteAttachment = getWebDriver().findElement(By.cssSelector(NOTE_ATTACHMENT_NAME));
            Assert.fail();
            logger.info("The attachment is still present");
        } catch (NoSuchElementException e) {
            logger.info
                    ("The attachment was deleted");
        }

        noteManager.deleteNote();
    }

    @Test
    public void noteAddBigAttachmentAcceptanceTest() {
        logger.info("noteAddBigAttachmentAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //add a note
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");

        WebElement filesUploadNote = getWebDriver().findElement(By.cssSelector(NOTE_ATTACHEMENT_INPUT));
        filesUploadNote.sendKeys(attachmentBigToLoad);

        //check the information message
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_TOO_BIG_INFORMATION)));

        WebElement notificationBody = getWebDriver().findElement(By.cssSelector(NOTE_TOO_BIG_INFORMATION));
        Assert.assertEquals(notificationBody.getText(), "The selected file has more then the max file size( 7,000,000 bytes)");

    }

    private void addAttachment(String attPath) {
        
        //add a note
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");

        WebElement filesUploadNote = getWebDriver().findElement(By.cssSelector(NOTE_ATTACHEMENT_INPUT));
        filesUploadNote.sendKeys(attPath);

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_ATTACHMENT_DELETE_BUTTON)));
        noteManager.save();
    }
}