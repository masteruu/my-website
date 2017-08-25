package de.brueckner.fph.acceptance.rollsAsFavorites;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.RollsAsFavoritesOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.FAVORITES_BUTTON;
import static de.brueckner.fph.managers.ContentManager.NOTIFICATION_CONTAINER;

public class OverwriteBenchmarkAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(OverwriteBenchmarkAcceptanceTest.class);

    @Test
    public void overwriteBenchmarkAcceptance() {
        logger.info("OverwriteBenchmarkAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //rolls are predefined as favorite and benchmark in this period
        timelineManager.setNewDateForDatePicker("2016-04-14 22:00:00", "2016-04-15 22:00:00");

        timelineManager.clickRoll("roll:A160415043053:1460694653876:1460700806478");

        contentManager.waitContentSpinner();

        WebElement favoriteButton = getWebDriver().findElement(By.cssSelector(FAVORITES_BUTTON));
        favoriteButton.click();

        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_BENCHMARK.getOptionText());

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTIFICATION_CONTAINER)));

        WebElement notificationPanel = getWebDriver().findElement(By.cssSelector(NOTIFICATION_CONTAINER));
        Assert.assertEquals(notificationPanel.getText().trim(), "Overwrite benchmark\n" +
                "Product already has a benchmark. Please confirm replacement.\n" +
                "YES\n" +
                "NO");

        List<WebElement> yesButton = getWebDriver().findElements(By.cssSelector(".actionButtons button"));
        yesButton.get(0).click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTIFICATION_CONTAINER)));

        contentManager.waitContentSpinner();

        favoriteButton = getWebDriver().findElement(By.cssSelector(FAVORITES_BUTTON));
        favoriteButton.click();
        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_NORMAL.getOptionText());

        //check if the old benchmark roll turned favorite
        treeManager.openTree();

        timelineManager.clickRoll("roll:A160415010243:1460682163918:1460688316670");

        contentManager.waitContentSpinner();

        List<WebElement> treeItem = getWebDriver().findElements(By.cssSelector("li.k-item .k-image"));
        Assert.assertEquals(treeItem.get(15).getAttribute("src"), getApplicationURL() + "/resources/img/svg/star_black.svg");

        //rollback(set the roll back to benchmark
        favoriteButton = getWebDriver().findElement(By.cssSelector(FAVORITES_BUTTON));
        favoriteButton.click();
        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_BENCHMARK.getOptionText());

    }

    @Test
    public void notOverwriteBenchmarkAcceptance() {
        logger.info("notOverwriteBenchmarkAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //rolls are predefined as favorite and benchmark in this period
        timelineManager.setNewDateForDatePicker("2016-04-14 22:00:00", "2016-04-15 22:00:00");

        timelineManager.clickRoll("roll:A160415043053:1460694653876:1460700806478");

        contentManager.waitContentSpinner();

        WebElement favoriteButton = getWebDriver().findElement(By.cssSelector(FAVORITES_BUTTON));
        favoriteButton.click();

        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_BENCHMARK.getOptionText());

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTIFICATION_CONTAINER)));

        //don't overwrite the benchmark roll
        WebElement notificationPanel = getWebDriver().findElement(By.cssSelector(NOTIFICATION_CONTAINER));
        Assert.assertEquals(notificationPanel.getText().trim(), "Overwrite benchmark\n" +
                "Product already has a benchmark. Please confirm replacement.\n" +
                "YES\n" +
                "NO");

        List<WebElement> noButton = getWebDriver().findElements(By.cssSelector(".actionButtons button"));
        noButton.get(1).click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTIFICATION_CONTAINER)));

        contentManager.waitContentSpinner();

        treeManager.openTree();

        timelineManager.clickRoll("roll:A160415010243:1460682163918:1460688316670");

        contentManager.waitContentSpinner();

        //check if the benchmark roll is unchanged
        List<WebElement> treeItem = getWebDriver().findElements(By.cssSelector("li.k-item .k-image"));
        Assert.assertEquals(treeItem.get(15).getAttribute("src"), getApplicationURL() + "/resources/img/svg/star_gold.svg");

    }
}

