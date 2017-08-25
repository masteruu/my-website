package de.brueckner.fph.acceptance.contentRoll;


import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;

/**
 * Test how the invalid rollis marked in the UI:
 * <ul>
 * <li>tree</li>
 * <li>timeline</li>
 * <li>content</li>
 * </ul>
 */
public class InvalidRollAcceptanceTest extends AbstractApplicationSettingsTest {
    /**
     * The name of the roll used in tests
     */
    static final String CONST_ROLL_NAME = "A 160505 2347";
    /**
     * The UID of the roll used in tests
     */
    static final String CONST_ROLL_UID = "roll:A160505154759:1462463279563:1462466122167";
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(InvalidRollAcceptanceTest.class);

    @Test
    public void invalidRollAcceptance() {
        logger.info("invalidRollAcceptance");
        //perform the login
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific interval
        timelineManager.setNewDateForDatePicker("2016-05-05 15:47:00", "2016-05-05 22:22:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll(CONST_ROLL_UID);

        //test if the roll is selected
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".rollCampaignName"), CONST_ROLL_NAME));

        //open the tree
        treeManager.openTree();
        //test the icon in the tree
        String xPath = "//img[contains(@src, 'invalid.png') and ../span/div/text()='" + CONST_ROLL_NAME + "']";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

        //test the element in the timeline
        xPath = "//*[@uid='" + CONST_ROLL_UID + "']/*[contains(@class, 'invalid')]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

        //test the content title
        xPath = "//div[contains(@class, 'contentSelector') and contains(@class, 'invalidTitle')]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }
}