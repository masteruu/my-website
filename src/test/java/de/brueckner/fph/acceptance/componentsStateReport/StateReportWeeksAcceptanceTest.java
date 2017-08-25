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

import static de.brueckner.fph.managers.StateReportManager.STATE_REPORT_DATEPICKER;

public class StateReportWeeksAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(StateReportWeeksAcceptanceTest.class);

    @Test
    public void stateReportWeeksAcceptance() {
        logger.info("stateReportWeeksAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        stateReportManager.open();

        //press week 13 and check if the state report have moved to that week
        WebElement week13 = getWebDriver().findElement(By.cssSelector(".weeks div:nth-child(2)"));

        //check week13 text
        Assert.assertEquals(week13.getText(), "13");
        week13.click();
        stateReportManager.waitStateReportSpinner();

        //check initial date on datepicker
        WebElement stateReportDatepicker = getWebDriver().findElement(By.cssSelector(STATE_REPORT_DATEPICKER));
        Assert.assertEquals(stateReportDatepicker.getText(), "CW 13\n" +
                DateUtils.date("2016-03-28 04:14:28", DateFormatShortOption.OPTION_WITH_POINT.getOptionText()) + " - " + DateUtils.date("2016-04-03 04:14:28", DateFormatShortOption.OPTION_WITH_POINT.getOptionText()));

    }
}