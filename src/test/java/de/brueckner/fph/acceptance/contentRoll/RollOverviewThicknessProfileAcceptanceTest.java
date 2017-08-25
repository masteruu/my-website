package de.brueckner.fph.acceptance.contentRoll;


import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
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

public class RollOverviewThicknessProfileAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(RollOverviewThicknessProfileAcceptanceTest.class);

    static final String CONST_ROLL_UID = "roll:A160413064950:1460530190433:1460535116127";
    static final String CONST_ROLL_NAME = "A 160413 1449";

    @Test  //check the roll display when changing options to laboratory quality
    public void rollOverviewThicknessProfileAcceptance() {
        logger.info("rollOverviewThicknessProfileAcceptance");
        //perform the login
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");
        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll(CONST_ROLL_UID);
        //test if the roll is selected
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".rollCampaignName"), CONST_ROLL_NAME));

        WebElement openThickProfile = getWebDriver().findElement(By.cssSelector(".thicknessTitle .gearTimeline"));
        openThickProfile.click();
        String xPath = "//div[@class='modal-dialog']";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

        //check bold
        xPath = "//div[contains(@class, 'boldCheckbox')]/label[contains(@class,'fakeCheckbox')]";
        List<WebElement> checkboxList = getWebDriver().findElements(By.xpath(xPath));
        for (WebElement checkbox : checkboxList) {
            checkbox.click();
        }

        applicationManager.pressEnter();
        contentManager.waitContentSpinner();

        //checking if the thickness profile tooltip works
        WebElement tooltip = getWebDriver().findElement(By.cssSelector(".contentBackground"));
        MouseUtils.mouseOver(tooltip, getWebDriver());
        WebElement thicknessTooltip = getWebDriver().findElement(By.cssSelector(".thicknessTooltip"));
        Assert.assertEquals(thicknessTooltip.getText().substring(0, 38), "22.83 µm22.37 Min23.13 Max0.38 2 Sigma");

        //check info box
        xPath = "//span[@class='plusMinusContainer' and ancestor::div[@class='thicknessTop']/div[contains(@class, 'thicknessTitle')]/text()[contains(., 'Profile')]]";
        WebElement expandInfo = getWebDriver().findElement(By.xpath(xPath));
        expandInfo.click();
        //wait intil the last element in the legend is displayed
        xPath = "//span[contains(@class, 'info') and ancestor::div[contains(@class, 'thicknessInfo') and contains(@class, 'expandedInfo')] and .//span[text()='Max']]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

        //
        xPath = "//span[contains(@class, 'info') and ancestor::div[contains(@class, 'thicknessInfo') and contains(@class, 'expandedInfo')]]";
        List<WebElement> checkText = getWebDriver().findElements(By.xpath(xPath));
        Assert.assertEquals(checkText.get(0).getText(), "22.80 µm Average");
        Assert.assertEquals(checkText.get(1).getText(), "0.50 % 2 Sigma %");
        Assert.assertEquals(checkText.get(2).getText(), "0.11 µm 2 Sigma");
        Assert.assertEquals(checkText.get(3).getText(), "22.58 µm Min");
        Assert.assertEquals(checkText.get(4).getText(), "23.15 µm Max");
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}
