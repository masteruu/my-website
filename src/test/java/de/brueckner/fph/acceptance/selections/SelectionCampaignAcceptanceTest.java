package de.brueckner.fph.acceptance.selections;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.CONTENT_NAME;
import static de.brueckner.fph.managers.ContentManager.RELOAD_HEADER_BUTTON;
import static de.brueckner.fph.managers.TreeManager.TREE_CONTAINER;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class SelectionCampaignAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(SelectionCampaignAcceptanceTest.class);


    @Test
    public void selectionCampaignAcceptance() {
        logger.info("selectionCampaignAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //select a campaign
        timelineManager.setNewDateForDatePicker("2016-04-12 11:00:00", "2016-04-13 01:00:00");

        timelineManager.clickCampaign("campaign:160412114726310:1460461646310:1460511701759");

        contentManager.waitContentSpinner();

        //reload application because of browser/selenium bug
        WebElement reload = getWebDriver().findElement(By.cssSelector(RELOAD_HEADER_BUTTON));
        reload.click();
        applicationManager.waitLogo();

        //check if the campaign was selected in breadcrumb
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160411_11T25u_400m_4.1t"));

        //check if the campaign was selected in content
        WebElement contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "160411_11T25u_400m_4.1t");
        contentManager.checkContentPeriod(DateUtils.date("2016-04-12 11:47:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-13 01:41:00", DEFAULT_SHORT_DATE_FORMAT));

        //check if the campaign was selected in tree
        Keyboard tree = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        tree.pressKey(Keys.SHIFT + "t");
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TREE_CONTAINER)));

        List<WebElement> treeElement = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        Assert.assertEquals(treeElement.get(1).getText(), "160411_11T25u_400m_4.1t");

        //check tabs
        contentManager.checkTabs("OVERVIEW\n" +
                "ROLLS\n" +
                "MATERIAL\n" +
                "THICKNESS");

    }

}