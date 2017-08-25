package de.brueckner.fph.acceptance.componentsTree;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
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

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.TreeManager.TREE_CLASS_SELECTOR;
import static de.brueckner.fph.managers.TreeManager.TREE_CONTAINER;

public class TreeOpenCloseAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TreeOpenCloseAcceptanceTest.class);

    @Test //for toolbar button
    public void treeOpenCloseAcceptanceTest() {
        logger.info("TreeOpenCloseAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //from the button
        treeManager.openTree();
        treeManager.closeTree();

        //from the shortcut keys TREE_CONTAINER
        Keyboard tree = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        tree.pressKey(Keys.SHIFT + "t");
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TREE_CONTAINER)));

        treeManager.closeTree();

        //check tree tooltip
        WebElement treeButton = getWebDriver().findElement(By.className(TREE_CLASS_SELECTOR));
        MouseUtils.mouseOver(treeButton, getWebDriver());
        WebElement noteTooltip = getWebDriver().findElement(By.cssSelector(".noteTooltip"));
        Assert.assertEquals(noteTooltip.getText(), "TREE T");
    }
}
