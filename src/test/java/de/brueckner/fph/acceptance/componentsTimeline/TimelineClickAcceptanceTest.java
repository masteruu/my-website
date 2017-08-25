package de.brueckner.fph.acceptance.componentsTimeline;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.ContentManager.ROLL_OVERVIEW_NAME;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;


public class TimelineClickAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(TimelineClickAcceptanceTest.class);

    @Test
    public void clickCurrentElement() {
        logger.info("clickCurrentElement");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        contentManager.waitContentSpinner();

        WebElement rollId = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(rollId.getText(), "A 160413 1449");
    }

    @Test
    public void clickElementPartiallyVisible() {
        logger.info("clickElementPartiallyVisible");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:30:00", "2016-04-13 01:30:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160412234833:1460504913271:1460511701759");
        contentManager.waitContentSpinner();

        WebElement rollId = getWebDriver().findElement(By.cssSelector(ROLL_OVERVIEW_NAME));
        Assert.assertEquals(rollId.getText(), "A 160413 0748");

        //check if the selection in the content was done ok
        contentManager.checkContentPeriod(DateUtils.date("2016-04-12 23:48:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-13 01:41:00", DEFAULT_SHORT_DATE_FORMAT));

    }

    @Test
    public void clickElementNotVisible() {
        logger.info("clickElementNotVisible");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //open the tree and check if the roll is visible in tree after clicking on it and everything is ok
        treeManager.openTree();

        timelineManager.clickRoll("roll:A160413050715:1460524035427:1460530190433");
        contentManager.waitContentSpinner();

        List<WebElement> treeRollsExpanded = getWebDriver().findElements(By.cssSelector(".name.treeRow"));

        Assert.assertEquals(treeRollsExpanded.get(9).getText(), "A 160413 0941");
        Assert.assertEquals(treeRollsExpanded.get(8).getText(), "A 160413 1124");
        Assert.assertEquals(treeRollsExpanded.get(7).getText(), "A 160413 1307");
        Assert.assertEquals(treeRollsExpanded.get(6).getText(), "A 160413 1449");
        Assert.assertEquals(treeRollsExpanded.get(5).getText(), "A 160413 1611");
    }
}