package de.brueckner.fph.acceptance.contentRoll;


import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.LAYERS_TAB_TEXT;

public class RollLayersAcceptanceTest extends AbstractApplicationSettingsTest {
    /**
     * The name of the roll used in tests
     */
    static final String CONST_ROLL_NAME = "A 160413 1449";
    /**
     * The UID of the roll used in tests
     */
    static final String CONST_ROLL_UID = "roll:A160413064950:1460530190433:1460535116127";
    /**
     * The logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RollLayersAcceptanceTest.class);

    @Test
    public void rollLayersAcceptance() {
        logger.info("rollLayersAcceptance");
        //perform the login
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");
        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll(CONST_ROLL_UID);
        //test if the roll is selected
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".rollCampaignName"), CONST_ROLL_NAME));

        //click on the layers tab
        contentManager.openTab(LAYERS_TAB_TEXT);
        //wait to change the tab
        //new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".totalContainer"), "LABORATORY QUALITY"));
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalContainer")));

        //test the content
        // Top coextruder
        testExtruderInfo("Coextruder 2", "1.05");
        //-- first material row
        testMaterialInfo("Coextruder 2", "#T30(FD)", "264.7", "kg");
        //-- second material row
        testMaterialInfo("Coextruder 2", "NA", "4.6", "kg");

        //Main extruder
        testExtruderInfo("Main Extruder", "20.82");
        //-- first material row
        testMaterialInfo("Main Extruder", "*S1003(SH)", "3,069.8", "kg");
        //-- second material row
        testMaterialInfo("Main Extruder", "T38F(HD)", "2,046.5", "kg");
        //-- third material row
        testMaterialInfo("Main Extruder", "NA", "325.3", "kg");

        // Bottom coextruder
        testExtruderInfo("Coextruder 1", "1.03");
        //-- first material row
        testMaterialInfo("Coextruder 1", "#T30(FD)", "260.4", "kg");
        //-- second material row
        testMaterialInfo("Coextruder 1", "NA", "4.8", "kg");

        //Total - the following are tested:
        //-- the container containing the total info is there
        //-- the first section contains information related to thickness: value +  measurement unit
        //-- the second section contains information related to quantity: value + measurement unit
        String xPath = "//div[contains(@class,'totalContainer') and "
                + "./div[2]/div[1][contains(@style, 'inline-block') and ./span[@class='number' and text()='22.89'] and ./span[@class='um' and text()='µm']] "
                + "and ./div[2]/div[3][./span[@class='number' and text()='5,976.1'] and ./span[@class='um' and text()='kg']]]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

    }

    /**
     * Test the material information details per each layer.
     * <br/>
     * Per each layer, a list of materials is specified. The current method is testing the information for each material
     * in the layer that is peroduced by a certain extruder.
     *
     * @param extruderName    the extruder that produces teh material in the layer
     * @param materialName    the name of the material
     * @param quantity        the quantity of the material
     * @param measurementUnit the measurement unit used to express the quantity for the material
     */
    private void testMaterialInfo(String extruderName, String materialName, String quantity, String measurementUnit) {
        String xPath = "//div[@class='materialRow' and ./span[@class='materialQuantity' and contains(text(), '" + quantity
                + "') ]/span[@class='materialUnit' and contains(text(), '" + measurementUnit
                + "')] and ./span[@class='materialName' and contains(text(), '" + materialName
                + "')] and ../../div[contains(@class, 'coexDescription')]/div/span[contains(text(), '" + extruderName
                + "')] ]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }

    /**
     * Test the extruder information.
     * For the time being, the following are tested:
     *
     * @param name     the name of the extruder as is shown in the page
     * @param quantity the material per layer / extruder. This is considered as a <code>String</code> to avoid possible formatting issues.
     */
    private void testExtruderInfo(String name, String quantity) {
        String xPath = "//div[contains(@class, 'coexDescription') and ./div/span/text()='" + name + "' and ./div/div/span/text()='" + quantity + "']";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }
}