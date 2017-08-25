package de.brueckner.fph.acceptance.componentsStateReport;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateFormatShortOption;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.StateReportManager.*;

public class StateReportDatepickerAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(StateReportDatepickerAcceptanceTest.class);

    @Test
    public void stateReportDatepickerAcceptance() {
        logger.info("stateReportDatepickerAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //check datepicker from state report (move from current week to another week)
        stateReportManager.open();

        //check initial date on datepicker
        WebElement stateReportDatepicker = getWebDriver().findElement(By.cssSelector(STATE_REPORT_DATEPICKER));
        Assert.assertEquals(stateReportDatepicker.getText(), "CW 18\n" +
                DateUtils.date("2016-05-02 04:14:28", DateFormatShortOption.OPTION_WITH_POINT.getOptionText()) + " - " + DateUtils.date("2016-05-08 04:14:28", DateFormatShortOption.OPTION_WITH_POINT.getOptionText()));

        //pick another week in state report datepicker
        stateReportManager.openDatepicker();
        WebElement previousMonth = getWebDriver().findElement(By.cssSelector(STATE_REPORT_DATEPICKER_PREVIOUS_MONTH));
        previousMonth.click();

        //check new date in the state report
        WebElement monthName = getWebDriver().findElement(By.cssSelector(STATE_REPORT_DATEPICKER_MONTH_NAME));
        Assert.assertEquals(monthName.getText(), "APRIL 2016");

    }
}