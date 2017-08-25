package de.brueckner.fph.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

public class BreadcrumbManager {

    private static final Logger logger = LoggerFactory.getLogger(BreadcrumbManager.class);

    private WebDriver driver;

    public BreadcrumbManager(WebDriver driver) {
        this.driver = driver;
    }

    public void checkBreadcrumb(List<String> stringArray) {
        for (int i = 1; i <= stringArray.size(); i++) {
            WebElement breadCrumbContent = driver.findElement(By.cssSelector(".breadcrumb li:nth-child(" + i + ")"));
            Assert.assertEquals(breadCrumbContent.getText(), stringArray.get(i - 1));
        }
    }
}
