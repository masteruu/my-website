package de.brueckner.fph.acceptance.componentsHeader;


import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.ContentManager.LAST_8H_HEADER_BUTTON;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class HeaderButtonLast8hAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(HeaderButtonLast8hAcceptanceTest.class);

    @Test
    public void headerButtonLast8hAcceptanceTest() {
        logger.info("headerButtonLast8hAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        WebElement last8H = getWebDriver().findElement(By.cssSelector(LAST_8H_HEADER_BUTTON));
        last8H.click();
        contentManager.waitContentSpinner();

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "Last 8h"));

        //check if the selection is ok in content
        contentManager.checkContentPeriod(DateUtils.date("2016-05-11 02:00:00", DEFAULT_SHORT_DATE_FORMAT), "...");

        //check if the selection is ok in tree
        treeManager.openTree();
        List<WebElement> checkRoll = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        Assert.assertEquals(checkRoll.get(3).getText(), "A 160509 1437", "Roll in tree was not found");

    }
}