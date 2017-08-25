package de.brueckner.fph.managers;

import com.sdl.selenium.web.WebLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeManager {

    private static final Logger logger = LoggerFactory.getLogger(TreeManager.class);

    public static final String TREE_CLASS_SELECTOR = "treeButton";
    private static final String TREE_FILTER_SELECTOR = ".treeFilterContainer .treeFilterSelect";
    private static final String TREE_SPINNER_SELECTOR = ".treeContainer .tree-content-spinner";
    public static final String TREE_DATEPICKER_TEXT_SELECTOR = ".treeContainer #timePeriodInput";
    public static final String TREE_CONTAINER = "#tree";
    public static final String TREE_ROW = ".name.treeRow";
    public static final String TREE_FILTER_CONTAINER = ".treeFilterContainer";

    private WebDriver driver;

    public TreeManager(WebDriver driver) {
        this.driver = driver;
    }

    public void openTree() {
        logger.debug("Click open tree button");
        WebDriverWait webDriverWait = new WebDriverWait(driver, ApplicationManager.WAIT_TIME_OUT_IN_SECONDS);
        WebElement openTree = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className(TREE_CLASS_SELECTOR)));
        openTree.click();

        //wait for tree nodes to be clickable
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(TREE_FILTER_SELECTOR)));
        waitTreeSpinner();
        logger.info("Tree opened successful");
    }

    public void waitTreeSpinner() {
        logger.debug("Wait for tree spinner to be hidden");
        WebDriverWait webDriverWait = new WebDriverWait(driver, ApplicationManager.WAIT_TIME_OUT_IN_SECONDS);
        ApplicationManager.waitForAngularLoad(webDriverWait);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TREE_SPINNER_SELECTOR)));
    }

    public void closeTree() {
        WebLocator closeTree = new WebLocator().setClasses("closeTree");
        closeTree.click();
        new WebDriverWait(driver, ApplicationManager.WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TREE_CONTAINER)));
    }

    public String getTreeDatepickerText() {
        WebElement dateOnTreeDatepicker = driver.findElement(By.cssSelector(TREE_DATEPICKER_TEXT_SELECTOR));
        return dateOnTreeDatepicker.getText().trim();
    }

    public void expandNode(int index) {
        WebElement expandNode = driver.findElements(By.cssSelector(".k-icon.k-plus")).get(index);
        expandNode.click();
        waitTreeSpinner();
        new WebDriverWait(driver, ApplicationManager.WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.stalenessOf(expandNode));

    }
}
