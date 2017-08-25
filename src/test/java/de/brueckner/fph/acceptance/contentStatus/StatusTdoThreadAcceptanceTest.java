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

public class StatusTdoThreadAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(StatusTdoThreadAcceptanceTest.class);


    @Test
    public void statusTdoThreadAcceptance() {
        logger.info("statusTdoThreadAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-08 22:00:00", "2016-05-11 10:00:00");

        treeManager.openTree();
        treeManager.expandNode(0);
        treeManager.expandNode(1);

        List<WebElement> treeElement = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        treeElement.get(11).click();
        treeElement.get(11).click();
        contentManager.waitContentSpinner();

        //check duration
        WebElement time = getWebDriver().findElement(By.cssSelector(".rollDuration"));
        Assert.assertEquals(time.getText().trim(), "09.05.16 08:05:50\n" +
                "Start\n" +
                "03min 22s\n" +
                "Duration\n" +
                DateUtils.date("2016-05-09 06:09:13", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "End");

        WebElement operator = getWebDriver().findElement(By.cssSelector(".reasonValue"));
        Assert.assertEquals(operator.getText().trim(), "Operator");

        //check status icon in content
        WebElement iconContent = getWebDriver().findElement(By.cssSelector(".doubleArrowContent.titleType_threading"));
        Assert.assertEquals(iconContent.getCssValue("background-image"), "url(\"" + getApplicationURL() + "/resources/img/tree/statuses/threading.png\")");

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "1605009_25u_450m_5.3t", "NON_PRODUCTIVE", "TDO_THREAD"));

        //timeline tooltip
        Assert.assertEquals(applicationManager.getTooltipContainerText("*[uid='threading:3A160509055303325:1462773950349:1462774153034']"), "TDO_THREAD\n" +
                "3A1605090553033253 min");

    }
}