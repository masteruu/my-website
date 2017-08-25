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
import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;


/**
 * Test used to cover the &quot;normal&quot; scenario on the overview tab.
 */
public class RollOverviewAcceptanceTest extends AbstractApplicationSettingsTest {
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
    private static final Logger logger = LoggerFactory.getLogger(RollOverviewAcceptanceTest.class);

    /**
     * Check the roll display when changing options to laboratory quality.
     *
     * @throws IOException
     */
    @Test
    public void rollOverviewAcceptance() throws IOException {
        logger.info("rollOverviewAcceptance");
        //perform the login
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check
        timelineManager.clickRoll(CONST_ROLL_UID);
        contentManager.waitContentSpinner();

        //check if the right roll was selected
        WebElement rollId = getWebDriver().findElement(By.cssSelector(".rollCampaignName"));
        Assert.assertEquals(rollId.getText(), CONST_ROLL_NAME);

        //check the text displayed on (i) button by the thickness profile graph title
        String tooltipText = applicationManager.getTooltipContainerText(".infoThicknessProfile");
        Assert.assertEquals(tooltipText, "Calculation limits: ,\n" +
                "The entered limits are set to the inside 25 mm bucket borders");

        //check the text displayed on (i) button by the thickness profile graph title
        tooltipText = applicationManager.getTooltipContainerText(".infoThicknessTrend");
        Assert.assertEquals(tooltipText, "Calculation limits: ,\n" +
                "Each scan has been calculated with the limits valuable for that time.\n" +
                "The entered limits are set to the inside 25 mm bucket borders");

        //test the visible section titles
        List<WebElement> text = getWebDriver().findElements(By.cssSelector(".titleHeader"));
        Assert.assertEquals(text.get(1).getText(), "THICKNESS");
        Assert.assertEquals(text.get(2).getText(), "TREATMENT");

        //expand the section containing the details
        WebElement rollDetails = getWebDriver().findElement(By.cssSelector(".toggleRollDetails"));
        rollDetails.click();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.attributeToBe(By.cssSelector(".leftRollContainer"), "min-width", "550px"));

        //product name
        WebElement rollProductName = getWebDriver().findElement(By.cssSelector(".rollProductName"));
        Assert.assertEquals(rollProductName.getText(), "23um Plain");

        //test the present properties
        List<WebElement> legend = getWebDriver().findElements(By.cssSelector(".legend"));
        Assert.assertEquals(legend.get(0).getText(), "Roll");
        Assert.assertEquals(legend.get(1).getText(), "Product");
        Assert.assertEquals(legend.get(2).getText(), "Campaign");
        Assert.assertEquals(legend.get(3).getText(), "Recipe");
        Assert.assertEquals(legend.get(4).getText(), "VISUAL QUALITY");
        Assert.assertEquals(legend.get(5).getText(), "LAB QUALITY");
        Assert.assertEquals(legend.get(6).getText(), "Start");
        Assert.assertEquals(legend.get(7).getText(), "Duration");
        Assert.assertEquals(legend.get(8).getText(), "End");
        Assert.assertEquals(legend.get(19).getText(), "Outside");
        Assert.assertEquals(legend.get(20).getText(), "m/min Line Speed");
        Assert.assertEquals(legend.get(21).getText(), "kg/h Output on winder");
        Assert.assertEquals(legend.get(22).getText(), "mm Width");
        Assert.assertEquals(legend.get(23).getText(), "m Length");
        Assert.assertEquals(legend.get(24).getText(), "kg Weight");
        Assert.assertEquals(legend.get(25).getText(), "mm Diameter");
        Assert.assertEquals(legend.get(26).getText(), "% Density");

    }

    /**
     * Check the roll display when changing options to visual quality
     */
    @Test
    public void visualQuality() {
        logger.info("visualQuality");
        //perform the login
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        applicationSettingsManager.setRollQuality("Visual", 1);

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll(CONST_ROLL_UID);
        contentManager.waitContentSpinner();

        //get the campaign name
        WebElement rollId = getWebDriver().findElement(By.cssSelector(".rollCampaignName"));
        Assert.assertEquals(rollId.getText(), CONST_ROLL_NAME);

        //open the tree
        treeManager.openTree();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'treeRow') and text()='" + CONST_ROLL_NAME + "']")));

        selectQualityOption(QualityOption.OPTION_GOOD_ROLL, "GOOD_ROLL", "productive_roll.png");
        selectQualityOption(QualityOption.OPTION_NON_RECYCLABLE, "NON_RECYCLABLE", "notReciclable.png");
        selectQualityOption(QualityOption.OPTION_PENDING, "active", "notChecked.png");
        selectQualityOption(QualityOption.OPTION_RECYCLABLE, "RECYCLABLE", "notReciclable.png");//-- yep, same icon as for non-recyclable
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
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".visualQualityValue")));
        //click teh combo
        WebElement qualityCombo = getWebDriver().findElement(By.cssSelector(".visualQualityValue"));
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
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(GENERAL_SETTINGS_SCRIPT_NAME);
    }
}