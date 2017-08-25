package de.brueckner.fph.acceptance.rollsAsFavorites;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import de.brueckner.fph.util.RollsAsFavoritesOption;
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

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.FAVORITES_BUTTON;

public class FavoritePopupOptionsAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(FavoritePopupOptionsAcceptanceTest.class);

    @Test
    public void favoritePopupOptionsAcceptanceTest() {
        logger.info("FavoritePopupOptionsAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //go to a selection where there is a mdo_thread status
        timelineManager.setNewDateForDatePicker("2016-04-19 16:00:00", "2016-04-19 19:00:00");

        timelineManager.clickRoll("roll:A160419162124:1461082884864:1461088754186");

        contentManager.waitContentSpinner();

        //favorite roll
        WebElement favoriteButton = getWebDriver().findElement(By.cssSelector(FAVORITES_BUTTON));
        favoriteButton.click();

        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_FAVORITE.getOptionText());
        WebLocator starInTimeline = new WebLocator().setClasses("star_FAVORITE", "isVisible");
        starInTimeline.assertReady();
        treeManager.openTree();

        List<WebElement> treeItem = getWebDriver().findElements(By.cssSelector("li.k-item .k-image"));
        Assert.assertEquals(treeItem.get(4).getAttribute("src"), getApplicationURL() + "/resources/img/svg/star_black.svg");

        //benchmark roll
        favoriteButton = getWebDriver().findElement(By.cssSelector(FAVORITES_BUTTON));
        favoriteButton.click();

        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_BENCHMARK.getOptionText());
        starInTimeline = new WebLocator().setClasses("star_BENCHMARK", "isVisible");
        starInTimeline.assertReady();
        treeItem = getWebDriver().findElements(By.cssSelector("li.k-item .k-image"));
        Assert.assertEquals(treeItem.get(4).getAttribute("src"), getApplicationURL() + "/resources/img/svg/star_gold.svg");

        //normal roll
        favoriteButton = getWebDriver().findElement(By.cssSelector(FAVORITES_BUTTON));
        favoriteButton.click();

        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_NORMAL.getOptionText());
        treeItem = getWebDriver().findElements(By.cssSelector("li.k-item .k-image"));
        Assert.assertEquals(treeItem.get(4).getAttribute("src"), getApplicationURL() + "/resources/img/tree/statuses/notChecked.png");

    }

    @Test
    public void shortcutKeysTooltipFavoritesAcceptanceTest() {
        logger.info("shortcutKeysTooltipFavoritesAcceptanceTest");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-19 16:00:00", "2016-04-19 19:00:00");

        timelineManager.clickRoll("roll:A160419162124:1461082884864:1461088754186");
        contentManager.waitContentSpinner();

        //check tooltip
        WebElement favoriteButton = getWebDriver().findElement(By.cssSelector(FAVORITES_BUTTON));
        MouseUtils.mouseOver(favoriteButton, getWebDriver());

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tooltip")));

        WebElement tooltipText = getWebDriver().findElement(By.cssSelector(".tooltip"));
        Assert.assertEquals(tooltipText.getText(), "FAVORITE F");

        //open favorites modal with shortcut keys
        Keyboard note = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        note.pressKey(Keys.SHIFT + "f");
        note.releaseKey(Keys.SHIFT + "f");

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".br-select-row")));

        //close favorites modal with shortcut keys

        note = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        note.pressKey(Keys.SHIFT + "f");
        note.releaseKey(Keys.SHIFT + "f");

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".br-select-row")));

    }
}