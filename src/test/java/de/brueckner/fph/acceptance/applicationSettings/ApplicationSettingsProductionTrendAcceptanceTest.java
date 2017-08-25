package de.brueckner.fph.acceptance.applicationSettings;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.PRODUCTION_TREND_HEADER_BUTTON;

public class ApplicationSettingsProductionTrendAcceptanceTest extends AbstractApplicationSettingsTest {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsProductionTrendAcceptanceTest.class);

    @Test //production trend is set to 2 days
    public void applicationSettingsProductionTrendAcceptanceTest() {
        logger.info("applicationSettingsProductionTrendAcceptanceTest");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-14 22:01:00");

        //check is the production trend does not appear for more than 2 days
        WebElement prodTrend = getWebDriver().findElement(By.cssSelector(PRODUCTION_TREND_HEADER_BUTTON));
        prodTrend.click();

        WebLocator emptyContainer = new WebLocator().setText("time range too big for trend").setRenderMillis(10000);
        emptyContainer.assertReady();

        //change the selection to less than 2 days and check if the trend appears
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-14 22:00:00");

        WebLocator load = new WebLocator().setClasses("stateClass_PERIOD_PRODUCT_LIST").setRenderMillis(15000);
        load.assertReady();

        WebLocator graph = new WebLocator().setClasses("THICKNESSContainer").setRenderMillis(7000);
        graph.assertReady();

    }
}