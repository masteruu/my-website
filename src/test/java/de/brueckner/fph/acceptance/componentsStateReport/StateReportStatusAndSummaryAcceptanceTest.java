package de.brueckner.fph.acceptance.componentsStateReport;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.StateReportManager.STATE_REPORT_STATUS_PICKER;
import static de.brueckner.fph.managers.StateReportManager.STATE_REPORT_SUMMARY;

public class StateReportStatusAndSummaryAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(StateReportStatusAndSummaryAcceptanceTest.class);

    @Test
    public void stateReportStatusAcceptance() {
        logger.info("stateReportStatusAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        stateReportManager.open();

        //test for green
        testStatusAndSummary(1, "TEST GREEN", "background-color: rgb(0, 140, 0);");

        //test for yellow
        testStatusAndSummary(2, "TEST YELLOW", "background-color: rgb(219, 186, 1);");

        //test for red
        testStatusAndSummary(3, "TEST RED", "background-color: rgb(200, 0, 20);");

    }

    void testStatusAndSummary(int index, String summary, String color) {
        stateReportManager.selectStateReportStatus(index);
        stateReportManager.stateReportSummary(summary);
        stateReportManager.save();
        stateReportManager.close();
        stateReportManager.open();

        WebElement green = getWebDriver().findElement(By.cssSelector(STATE_REPORT_STATUS_PICKER));
        Assert.assertEquals(green.getAttribute("style"), color);
        WebElement stateSummary = getWebDriver().findElement(By.cssSelector(STATE_REPORT_SUMMARY));
        Assert.assertEquals(stateSummary.getAttribute("value"), summary);
    }

}