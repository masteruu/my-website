package de.brueckner.fph.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public final class MouseUtils {

    public MouseUtils() {

    }

    public static void mouseOver(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public static void mouseScroll_CM(String dateOfScroll, Integer nrOfThicks, WebDriver driver) {

        //date format "2016-05-09T12:44:41.219+0200"
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("$('campaign-manager-component #timeline').scope().Timeline.callEventMethod(\"blockedZoom\", { \"point\":\"" + dateOfScroll + "\", \"scrolls\":" + nrOfThicks + "}); ");
    }

    public static void mouseScroll(String dateOfScroll, Integer nrOfThicks, WebDriver driver) {

        //date format "2016-05-09T12:44:41.219+0200"
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("$('#middleContainer #timeline').scope().Timeline.callEventMethod(\"blockedZoom\", { \"point\":\"" + dateOfScroll + "\", \"scrolls\":" + nrOfThicks + "}); ");
    }

    public static void dragAndDrop(WebElement element, Integer pixels, WebDriver driver) {
        new Actions(driver).dragAndDropBy(element, pixels, 0).build().perform();
    }

    //double click is not working
    public static void doubleClick(WebElement element, WebDriver driver) {
        Actions action = new Actions(driver);
        action.doubleClick(element).build().perform();
    }

}