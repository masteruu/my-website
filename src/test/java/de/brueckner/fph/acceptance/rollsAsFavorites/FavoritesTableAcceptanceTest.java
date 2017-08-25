package de.brueckner.fph.acceptance.rollsAsFavorites;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.RollsAsFavoritesOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.ContentManager.*;

public class FavoritesTableAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(FavoritesTableAcceptanceTest.class);

    @Test
    public void favoritesTableContentAcceptanceTest() {
        logger.info("favoritesTableContentAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //rolls are predefined as favorite and benchmark in this period
        timelineManager.setNewDateForDatePicker("2016-04-14 22:00:00", "2016-04-15 22:00:00");

        //go to a selection where there is a chr_thread status
        timelineManager.clickRoll("roll:A160415010243:1460682163918:1460688316670");

        contentManager.waitContentSpinner();

        //open favorites table
        contentManager.openFavoriteTable();

        //check content of the favorite table rows
        //check table header
        WebElement tableHeader = getWebDriver().findElement(By.cssSelector(FAVORITES_TABLE_MODAL + " .ngHeaderContainer"));
        Assert.assertEquals(tableHeader.getText().trim(), "Roll\n" +
                "  Campaign\n" +
                "  Speed\n" +
                "  Output\n" +
                "  Thickness Average\n" +
                "  2 Sigma %\n" +
                "Rating");

        List<WebElement> tableRows = getWebDriver().findElements(By.cssSelector(FAVORITES_TABLE_ROW));

        //check that we have 4 rows in the table
        Assert.assertEquals(tableRows.size(), 4);

        Assert.assertEquals(tableRows.get(0).getText(), "  A 160415 0902\n" +
                "  160411_11T23u_400m_4.3t\n" +
                "  401.39\n" +
                "  4,347.29\n" +
                "  22.80\n" +
                "  0.47");
        Assert.assertEquals(tableRows.get(1).getText(), "  A 160415 1921\n" +
                "  160411_11p23u_400m_4.3t\n" +
                "  401.37\n" +
                "  4,338.99\n" +
                "  22.81\n" +
                "  0.56");
        Assert.assertEquals(tableRows.get(2).getText(), "  A 160415 2103\n" +
                "  160411_11p23u_400m_4.3t\n" +
                "  401.39\n" +
                "  4,332.22\n" +
                "  22.77\n" +
                "  0.55");
        Assert.assertEquals(tableRows.get(3).getText(), "  A 160415 2246\n" +
                "  160411_11p23u_400m_4.3t\n" +
                "  401.38\n" +
                "  4,337.01\n" +
                "  22.80\n" +
                "  0.57");

    }

    @Test
    public void favoritesTableChangeFavoritesAcceptanceTest() {
        logger.info("favoritesTableChangeFavoritesAcceptanceTest");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //rolls are predefined as favorite and benchmark in this period
        timelineManager.setNewDateForDatePicker("2016-04-14 22:00:00", "2016-04-15 22:00:00");

        //go to a selection where there is a chr_thread status
        timelineManager.clickRoll("roll:A160415010243:1460682163918:1460688316670");

        contentManager.waitContentSpinner();

        //open favorites table
        contentManager.openFavoriteTable();

        //change benchmark roll to favorite roll
        WebElement benchmarkRoll = getWebDriver().findElement(By.cssSelector("#favoriteTable .table-icon.BENCHMARK"));
        benchmarkRoll.click();

        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_FAVORITE.getOptionText());
        contentManager.closeFavoriteTable();

        //check if the roll changed to favorite
        treeManager.openTree();

        List<WebElement> treeItem = getWebDriver().findElements(By.cssSelector("li.k-item .k-image"));
        Assert.assertEquals(treeItem.get(6).getAttribute("src"), getApplicationURL() + "/resources/img/svg/star_black.svg");

        //change roll to normal roll from the table
        contentManager.openFavoriteTable();
        List<WebElement> favoriteRoll = getWebDriver().findElements(By.cssSelector(".table-icon.FAVORITE"));
        favoriteRoll.get(0).click();

        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_NORMAL.getOptionText());
        contentManager.closeFavoriteTable();

        //check that the roll was changed to normal
        treeItem = getWebDriver().findElements(By.cssSelector("li.k-item .k-image"));
        Assert.assertEquals(treeItem.get(6).getAttribute("src"), getApplicationURL() + "/resources/img/tree/statuses/notChecked.png");

        //check if the normal roll was deleted from the table
        contentManager.openFavoriteTable();

        List<WebElement> tableRows = getWebDriver().findElements(By.cssSelector(FAVORITES_TABLE_ROW));

        //check that we have 3 rows in the table
        Assert.assertEquals(tableRows.size(), 3);
        contentManager.closeFavoriteTable();


        //rollback (set the roll back to benchmark
        WebElement favoriteButton = getWebDriver().findElement(By.cssSelector(FAVORITES_BUTTON));
        favoriteButton.click();
        contentManager.rollsFavorite(RollsAsFavoritesOption.OPTION_BENCHMARK.getOptionText());

    }

    @Test
    public void accessRollFromTable() {

        logger.info("accessRollFromTable");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //rolls are predefined as favorite and benchmark in this period
        timelineManager.setNewDateForDatePicker("2016-04-14 22:00:00", "2016-04-15 22:00:00");

        //go to a selection where there is a chr_thread status
        timelineManager.clickRoll("roll:A160415010243:1460682163918:1460688316670");

        contentManager.waitContentSpinner();

        //open favorites table
        contentManager.openFavoriteTable();

        List<WebElement> rollClick = getWebDriver().findElements(By.cssSelector(".rollClick"));
        rollClick.get(1).click();
        contentManager.waitContentSpinner();

        //check that the clicked roll was selected
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160411_11p23u_400m_4.3t", "PRODUCTIVE", "A 160415 1921"));

    }

}

