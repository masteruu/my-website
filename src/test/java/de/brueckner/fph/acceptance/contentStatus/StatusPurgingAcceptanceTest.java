package de.brueckner.fph.acceptance.contentStatus;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
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

import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_LONG_TIME_FORMAT;

public class StatusPurgingAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(StatusPurgingAcceptanceTest.class);

    @Test
    public void statusPurgingAcceptance() {
        logger.info("statusPurgingAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-08 22:00:00", "2016-05-11 10:00:00");

        treeManager.openTree();
        treeManager.expandNode(0);
        treeManager.expandNode(1);

        List<WebElement> treeElement = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        treeElement.get(14).click();
        treeElement.get(14).click();
        contentManager.waitContentSpinner();

        //check status overview
        WebElement overview = getWebDriver().findElement(By.cssSelector(".tabList_tab"));
        Assert.assertEquals(overview.getText().trim(), "OVERVIEW");

        WebElement info = getWebDriver().findElement(By.cssSelector(".sectionTitle"));
        Assert.assertEquals(info.getText().trim(), "INFO");

        //check duration
        WebElement time = getWebDriver().findElement(By.cssSelector(".rollDuration"));
        Assert.assertEquals(time.getText().trim(), "09.05.16 07:53:01\n" +
                "Start\n" +
                "01s\n" +
                "Duration\n" +
                DateUtils.date("2016-05-09 05:53:03", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "End");

        //check reason operator
        WebElement operator = getWebDriver().findElement(By.cssSelector(".reasonValue"));
        Assert.assertEquals(operator.getText().trim(), "Operator");

        //check status icon in content
        WebElement iconContent = getWebDriver().findElement(By.cssSelector(".doubleArrowContent.titleType_purging"));
        Assert.assertEquals(iconContent.getCssValue("background-image"), "url(\"" + getApplicationURL() + "/resources/img/tree/statuses/purging.png\")");

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "1605009_25u_450m_5.3t", "NON_PRODUCTIVE", "PURGING"));

        //timeline tooltip
        Assert.assertEquals(applicationManager.getTooltipContainerText("*[uid='purging:2A160509055301808:1462773181808:1462773183325']"), "PURGING\n" +
                "2A16050905530180800 min");

    }
}