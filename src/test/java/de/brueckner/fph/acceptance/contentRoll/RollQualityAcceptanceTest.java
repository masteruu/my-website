package de.brueckner.fph.acceptance.contentRoll;

import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.QualityOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.QUALITY_TAB_TEXT;


public class RollQualityAcceptanceTest extends AbstractApplicationSettingsTest {
    /**
     * The name of the roll used in tests
     */
    static final String CONST_ROLL_NAME = "A 160413 1449";
    /**
     * The UID of the roll used in tests
     */
    static final String CONST_ROLL_UID = "roll:A160413064950:1460530190433:1460535116127";
    /**
     * The logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RollQualityAcceptanceTest.class);

    /**
     * Check the roll display when changing options to laboratory quality.
     */
    @Test
    public void laboratoryQuality() {
        logger.info("laboratoryQuality");
        //perform the login
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        //set the app settings to laboratory settings
        applicationSettingsManager.setRollQuality("Laboratory", 1);

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll(CONST_ROLL_UID);

        //test if the roll is selected
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".rollCampaignName"), CONST_ROLL_NAME));

        //click on the quality tab
        contentManager.openTab(QUALITY_TAB_TEXT);
        //wait to change the tab
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".titleHeader"), "LABORATORY QUALITY"));

        //check no lab module installed text
        WebElement titleHeader = getWebDriver().findElement(By.cssSelector(".titleHeader"));
        Assert.assertEquals(titleHeader.getText(), "LABORATORY QUALITY");
        WebElement placeholderText = getWebDriver().findElement(By.cssSelector(".placeholderText"));
        Assert.assertEquals(placeholderText.getText(), "no lab module installed");

        //open the tree
        treeManager.openTree();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'treeRow') and text()='" + CONST_ROLL_NAME + "']")));

        //test the selection of each option
        selectQualityOption(QualityOption.OPTION_NON_RECYCLABLE, "NON_RECYCLABLE", "notReciclable.png");
        selectQualityOption(QualityOption.OPTION_GOOD_ROLL, "GOOD_ROLL", "productive_roll.png");
        selectQualityOption(QualityOption.OPTION_PENDING, "active", "notChecked.png");
        selectQualityOption(QualityOption.OPTION_RECYCLABLE, "RECYCLABLE", "notReciclable.png");//-- yep, same icon as for non-recyclable

        //reset the value to good
        selectQualityOption(QualityOption.OPTION_GOOD_ROLL, "GOOD_ROLL", "productive_roll.png");

    }

    /**
     * The method is pserforming the specific test.
     * - select option based on name
     * - test the layout on the timeline
     * - test the representation in tree
     *
     * @param option
     * @param newOptionClass
     */
    private void selectQualityOption(QualityOption option, String newOptionClass, String treeIconFile) {
        //wait that the combo is clickable
        contentManager.waitContentSpinner();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".labQualityValue .select2-chosen")));
        //click teh combo
        WebElement qualityCombo = getWebDriver().findElement(By.cssSelector(".labQualityValue .select2-chosen"));
        qualityCombo.click();
        //select the specified option
        WebLocator qualityOptionElement = new WebLocator().setClasses("select2-result-label").setText(option.getOptionText(), SearchType.EQUALS);
        qualityOptionElement.click();

        //check the roll in timeline
        if (!QualityOption.OPTION_PENDING.equals(option)) {
            String xPath = "//*[@uid='" + CONST_ROLL_UID + "']/child::*[contains(@class, 'active')]";
            new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.attributeContains(By.xpath(xPath), "class", newOptionClass));
        }

        //check the roll in tree
        String treeXPath = "//img[contains(@src, '" + treeIconFile + "') and ../span/div/text()='" + CONST_ROLL_NAME + "']";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(treeXPath)));
    }

    @AfterMethod
    public void restoreCampaigns() throws IOException {
        getMongoUtils().executeScript(CAMPAIGNS_SCRIPT_NAME);
    }
}