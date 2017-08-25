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
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;

public class RollTrendCalculationLimitsAcceptanceTest extends AbstractApplicationSettingsTest {

    private static final Logger logger = LoggerFactory.getLogger(RollTrendCalculationLimitsAcceptanceTest.class);

    static final String CONST_ROLL_UID = "roll:A160505190019:1462474819743:1462480010011";

    @Test
    public void rollTrendCalculationLimitsAcceptanceTest() throws IOException {
        logger.info("RollTrendCalculationLimitsAcceptanceTest");

        //perform the login
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific interval
        timelineManager.setNewDateForDatePicker("2016-05-05 19:00:00", "2016-05-05 20:26:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll(CONST_ROLL_UID);

        contentManager.waitContentSpinner();

        WebElement infoThicknessProfile = getWebDriver().findElement(By.cssSelector(".infoThicknessProfile"));
        MouseUtils.mouseOver(infoThicknessProfile, getWebDriver());

        //wait until the tooltip is populated with teh right text
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".genericTooltipContainer")));
        WebElement tooltip = getWebDriver().findElement(By.cssSelector(".genericTooltipContainer"));
        Assert.assertNotEquals(tooltip.getText(), "");

        List<WebElement> calculationLimits = getWebDriver().findElements(By.cssSelector(".calculationLimits"));
        Assert.assertNotEquals(calculationLimits.get(1).getAttribute("d"), "");
        Assert.assertNotEquals(calculationLimits.get(2).getAttribute("d"), "");

    }
}
