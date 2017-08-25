package de.brueckner.fph.acceptance.componentsTimeline;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.PRODUCTION_TREND_HEADER_BUTTON;
import static de.brueckner.fph.managers.TimelineManager.*;

public class TimelineProductionTrendThicknessMinMaxAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineProductionTrendThicknessMinMaxAcceptanceTest.class);

    @Test
    public void timelineTrendThicknessMinMaxAcceptance() {
        logger.info("TimelineProductionTrendThicknessMinMaxAcceptanceTest");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        WebElement prodTrend = getWebDriver().findElement(By.cssSelector(PRODUCTION_TREND_HEADER_BUTTON));
        prodTrend.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TIMELINE_TREND_CONTAINER)));

        timelineManager.openTimelineSettings();
        WebElement trendSettings = getWebDriver().findElement(By.cssSelector(TIMELINE_SETTINGS_TREND));
        trendSettings.click();

        //check min and max thickness error message "Please use only numbers and the decimal Point."
        //insert min and max 100 and 1500

        //thickness min max
        checkMinMaxTrend(0, 1);

        //speed min max
        checkMinMaxTrend(2, 3);

        //output min max
        checkMinMaxTrend(4, 5);

        WebElement save = getWebDriver().findElement(By.cssSelector(TIMELINE_SETTINGS_SAVE));
        save.click();


        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TIMELINE_TREND_CONTAINER)));

        //check if the values on the markers are changed

        //TO DO FIND SOLUTION TO CHECK THE TRENDS AFTER SAVE

       /* WebElement checktrendBars = getWebDriver().findElement(By.cssSelector(".THICKNESS_axis"));
        Assert.assertEquals(checktrendBars.getText(), "500\n" +
                "1,000\n" +
                "1,500\n" +
                "2,000\n" +
                "2,500\n" +
                "µm THICKNESS");

        checktrendBars = getWebDriver().findElement(By.cssSelector(".SPEED_axis"));
        Assert.assertEquals(checktrendBars.getText(), "500\n" +
                "1,000\n" +
                "1,500\n" +
                "2,000\n" +
                "2,500\n" +
                "µm SPEED");

        checktrendBars = getWebDriver().findElement(By.cssSelector(".THROUGHPUT_axis"));
        Assert.assertEquals(checktrendBars.getText(), "500\n" +
                "1,000\n" +
                "1,500\n" +
                "2,000\n" +
                "2,500\n" +
                "µm OUTPUT");*/

    }

    void checkMinMaxTrend(int min, int max) {
        List<WebElement> minThickness = getWebDriver().findElements(By.cssSelector(".minMaxTrend"));
        minThickness.get(min).clear();
        minThickness.get(min).sendKeys("text");
        WebLocator tooltipError = new WebLocator().setClasses("tooltip-inner").setText("Please use only numbers and the decimal Point.");
        tooltipError.assertReady();
        minThickness.get(min).clear();
        minThickness.get(min).sendKeys("100");

        List<WebElement> maxThickness = getWebDriver().findElements(By.cssSelector(".minMaxTrend"));
        maxThickness.get(max).clear();
        maxThickness.get(max).sendKeys("text");
        tooltipError = new WebLocator().setClasses("tooltip-inner").setText("Please use only numbers and the decimal Point.");
        tooltipError.assertReady();
        minThickness.get(max).clear();
        minThickness.get(max).sendKeys("1500");
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}