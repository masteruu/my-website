package de.brueckner.fph.acceptance.applicationSettings;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.DateFormatShortOption;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ApplicationSettingsManager.*;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class ApplicationSettingsPerformSaveWithoutResetAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsPerformSaveWithoutResetAcceptanceTest.class);

    @Test
    public void applicationSettingsPerformSaveWithoutResetAcceptanceTest() {
        logger.info("applicationSettingsPerformSaveWithoutResetAcceptanceTest");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //set date format short yy-MM-dd but without saving
        applicationSettingsManager.openSettings();

        logger.debug("Open date format dropdown");

        List<WebElement> dateFormat = getWebDriver().findElements(By.cssSelector(DATE_FORMAT_LIST_SELECTOR));
        dateFormat.get(1).click();

        logger.debug("Select new value in date format dropdown");
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.className("select2-result-label")));
        WebLocator dateFormatLong = new WebLocator().setClasses("select2-result-label").setText(DateFormatShortOption.OPTION_WITH_DASH.getOptionText());
        dateFormatLong.click();

        //check that the change was not saved
        WebElement cancelSave = getWebDriver().findElement(By.cssSelector(CANCEL_SAVE));
        cancelSave.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(SETTINGS_MODAL_SELECTOR)));

        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", DateUtils.date("2016-05-09 10:00:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "  -  ..."));

    }
}

