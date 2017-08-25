package de.brueckner.fph.acceptance.componentsHeader;

import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class BreadcrumAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(BreadcrumAcceptanceTest.class);


    @Test
    public void breadcrumbAcceptance() {
        logger.info("BreadcrumAcceptance");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-08 22:00:00", "2016-05-11 10:00:00");

        treeManager.openTree();

        //click on campaign in tree
        clickOnElementInTree(1);

        //check if the selection is ok in breadcrum (campaign)
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "1605009_25u_450m_5.3t"));

        //click on productive in tree
        clickOnElementInTree(2);

        //check if the selection is ok in breadcrum (productive)
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "1605009_25u_450m_5.3t", "PRODUCTIVE"));

        //click on a roll in tree
        clickOnElementInTree(3);

        //check if the selection is ok in breadcrum (roll)
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "1605009_25u_450m_5.3t", "PRODUCTIVE", "A 160509 1437"));

        //click on a non productive in tree
        clickOnElementInTree(4);

        //check if the selection is ok in breadcrum (non productive)
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "1605009_25u_450m_5.3t", "NON_PRODUCTIVE"));

        //click on stabilizing state in tree
        clickOnElementInTree(5);

        //check if the selection is ok in breadcrum (stabilizing state)
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "1605009_25u_450m_5.3t", "NON_PRODUCTIVE", "STABILIZING"));

    }

    private void clickOnElementInTree(int index) {
        List<WebElement> treeElement = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        treeElement.get(index).click();
        contentManager.waitContentSpinner();
    }
}