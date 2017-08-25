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

public class StatusShutdownAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(StatusShutdownAcceptanceTest.class);

    @Test
    public void statusShutdownAcceptance() {
        logger.info("statusShutdownAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-05-08 22:00:00", "2016-05-11 10:00:00");

        treeManager.openTree();
        treeManager.expandNode(0);
        treeManager.expandNode(1);

        List<WebElement> treeElement = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        treeElement.get(15).click();
        treeElement.get(15).click();
        contentManager.waitContentSpinner();

        //check duration
        WebElement time = getWebDriver().findElement(By.cssSelector(".rollDuration"));
        Assert.assertEquals(time.getText().trim(), "09.05.16 07:49:16\n" +
                "Start\n" +
                "03min 45s\n" +
                "Duration\n" +
                DateUtils.date("2016-05-09 05:53:01", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "End");

        WebElement operator = getWebDriver().findElement(By.cssSelector(".reasonValue"));
        Assert.assertEquals(operator.getText().trim(), "Operator");

        //check status icon in content
        WebElement iconContent = getWebDriver().findElement(By.cssSelector(".doubleArrowContent.titleType_shutdown"));
        Assert.assertEquals(iconContent.getCssValue("background-image"), "url(\"" + getApplicationURL() + "/resources/img/tree/statuses/shutdown.png\")");

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "1605009_25u_450m_5.3t", "NON_PRODUCTIVE", "SHUTDOWN"));

        //timeline tooltip
        Assert.assertEquals(applicationManager.getTooltipContainerText("*[uid='shutdown:1A160509054916631:1462772956631:1462773181808']"), "SHUTDOWN\n" +
                "1A1605090549166313 min");

    }
}
