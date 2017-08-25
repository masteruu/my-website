package de.brueckner.fph.acceptance.contentPeriod;

import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.ContentManager.THICKNESS_TAB_INFO_EXPAND_ANIMATION;
import static de.brueckner.fph.managers.ContentManager.THICKNESS_TAB_TEXT;

public class ThicknessOverviewAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(ThicknessOverviewAcceptanceTest.class);

    @Test
    public void thicknessOverviewAcceptanceTest() {
        logger.info("thicknessOverviewAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-22 11:04:00", "2016-04-22 16:19:00");

        contentManager.openTab(THICKNESS_TAB_TEXT);

        //check mini timeline content
        WebElement minitimelineTrendContainer = getWebDriver().findElement(By.cssSelector(".minitimelineTrendContainer"));
        Assert.assertEquals(minitimelineTrendContainer.getText(), "A 160422 1823A 160422 1944A 160422 2031A 160422 2130A 160422 2204A 160422 2334");

        //check thickness info compacted
        WebElement thicknessInfo = getWebDriver().findElement(By.cssSelector(".thicknessInfo"));
        Assert.assertEquals(thicknessInfo.getText(), "29.59 µm Average\n" +
                "0.50 % 2 Sigma %\n" +
                "+");

        //check thickness info expanded
        WebElement plusMinusWebElement = getWebDriver().findElement(By.cssSelector(".plusMinus"));
        plusMinusWebElement.click();
        Utils.sleep(THICKNESS_TAB_INFO_EXPAND_ANIMATION);

        thicknessInfo = getWebDriver().findElement(By.cssSelector(".thicknessInfo"));
        Assert.assertEquals(thicknessInfo.getText(), "29.59 µm Average\n" +
                "0.50 % 2 Sigma %\n" +
                "0.15 µm 2 Sigma 29.31 µm Min 29.90 µm Max\n" +
                "-");

        //check thickness tooltip
        String tooltipText = applicationManager.getTooltipContainerText(".infoThicknessTrend.tabFormulaInfo");
        Assert.assertEquals(tooltipText, "Each scan has been calculated with the limits valuable for that time.\n" +
                "The entered limits are set to the inside 25 mm bucket borders");

        //checking mini time line roll click
        //access roll A 160422 1944 from mini timeline
        List<WebElement> roll = getWebDriver().findElements(By.cssSelector(".ROLL"));
        roll.get(1).click();
        contentManager.waitContentSpinner();

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160422_30u_356m_5.1T", "PRODUCTIVE", "A 160422 1944"));

    }
}