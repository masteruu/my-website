package de.brueckner.fph.acceptance.applicationSettings;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.ROLLS_TAB_TEXT;

/**
 * Tests for changing the long date format from application settings
 * <p>
 * Created by andreeag on 7/13/2017.
 */
public class ApplicationSettingsDateFormatLongAcceptanceTest extends AbstractApplicationSettingsTest {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsDateFormatLongAcceptanceTest.class);

    @Test
    public void applicationSettings_dateFormatLong_dd_MM_yyyy() {
        //date format long dd.MM.yyyy
        testChangeDateFormatLong(DateFormatLongOption.OPTION_WITH_POINT);
    }

    @Test
    public void applicationSettings_dateFormatLong_yyyy_MM_dd() {
        //date format long yyyy-MM-dd
        testChangeDateFormatLong(DateFormatLongOption.OPTION_WITH_DASH);
    }

    @Test
    public void applicationSettings_dateFormatLong_MM_dd_yyyy() {
        //date format long MM/dd/yyyy
        testChangeDateFormatLong(DateFormatLongOption.OPTION_WITH_SLASH);
    }

    private void testChangeDateFormatLong(DateFormatLongOption formatToCheck) {
        logger.info("Test changing date format long to {} from application settings", formatToCheck.getOptionText());

        //period to test
        String startDateString = "2016-04-16 10:00:00";
        String endDateString = "2016-04-19 02:00:00";

        //date time formats
        String expectedContentDateFormat = DateUtils.getNewDateTimeFormatLong(formatToCheck,
                TimeFormatLongOption.OPTION_WITH_COLON);
        String expectedTreeDatepickerDateFormat = DateUtils.getNewDatepickerDateLongTimeShort(formatToCheck,
                TimeFormatShortOption.OPTION_WITH_COLON);

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker(startDateString, endDateString);

        applicationSettingsManager.setNewDateOrTimeFormat(formatToCheck.getOptionText(), 0);

        checkRollsTableDate(expectedContentDateFormat);
        checkMaterialTableDateFormat(expectedContentDateFormat);
        checkTreeDatepickerFormat(startDateString, endDateString, expectedTreeDatepickerDateFormat);
    }

    private void checkTreeDatepickerFormat(String startDateString, String endDateString, String expectedDateFormat) {
        logger.debug("Check date in tree datepicker");

        treeManager.openTree();

        //check date uses correct format
        String actualDateTreeText = treeManager.getTreeDatepickerText();
        String expectedDateTree = DateUtils.getTreeDatepickerDateString(startDateString, endDateString, expectedDateFormat);
        Assert.assertEquals(actualDateTreeText, expectedDateTree, "Date format in tree datepicker is not formatted correctly");
    }

    private void checkMaterialTableDateFormat(String newAppDateFormat) {
        logger.debug("Check date in material table");

        contentManager.openDetailedMaterialTabUngrouped();

        //check date uses correct format
        WebLocator checkDate = new WebLocator().setText(DateUtils.date("2016-04-17 20:26:29", newAppDateFormat)).setRenderMillis(7000);
        checkDate.assertReady();
    }

    private void checkRollsTableDate(String newAppDateFormat) {
        logger.debug("Check date in rolls table");

        contentManager.openTab(ROLLS_TAB_TEXT);

        //check date uses correct format
        WebLocator checkDate = new WebLocator().setText(DateUtils.date("2016-04-19 02:35:45", newAppDateFormat)).setRenderMillis(7000);
        checkDate.assertReady();

        checkDate = new WebLocator().setText(DateUtils.date("2016-04-19 01:54:23", newAppDateFormat)).setRenderMillis(7000);
        checkDate.assertReady();
    }

}
