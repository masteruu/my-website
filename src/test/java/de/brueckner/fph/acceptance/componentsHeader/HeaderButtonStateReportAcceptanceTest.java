package de.brueckner.fph.acceptance.componentsHeader;


import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.STATE_REPORT_HEADER_BUTTON;

public class HeaderButtonStateReportAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(HeaderButtonStateReportAcceptanceTest.class);

    @Test
    public void headerButtonStateReportAcceptance() {
        logger.info("headerButtonStateReportAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //check tooltip
        WebElement stateReport = getWebDriver().findElement(By.cssSelector(STATE_REPORT_HEADER_BUTTON));
        Assert.assertEquals(applicationManager.getTooltipTextForHeaderButtons(stateReport), "STATE REPORT", "Text from state report button tooltip is not correct");

        stateReport.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".stateReportContainer")));
    }
}