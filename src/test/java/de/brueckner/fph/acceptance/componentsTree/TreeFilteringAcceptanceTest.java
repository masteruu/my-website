package de.brueckner.fph.acceptance.componentsTree;


import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.QualityOption;
import de.brueckner.fph.util.TreeFilterOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.QUALITY_TAB_TEXT;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_NEXT_ELEMENT;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_PREVIOUS_ELEMENT;

public class TreeFilteringAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TreeFilteringAcceptanceTest.class);

    @Test
    public void filterOptions() {
        logger.info("TreeFilteringAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-13 00:00:00", "2016-04-13 07:00:00");

        //set the scenarios, set 4 rolls to good, non-recyclable, pending and recyclable
        timelineManager.clickRoll("roll:A160412234833:1460504913271:1460511701759");
        contentManager.waitContentSpinner();
        contentManager.openTab(QUALITY_TAB_TEXT);

        //set good roll
        setLaboratoryQuality(QualityOption.OPTION_GOOD_ROLL.getOptionText());
        contentManager.waitContentSpinner();

        //set non-recyclable roll
        WebElement nextInTimeline = getWebDriver().findElement(By.cssSelector(TIMELINE_NEXT_ELEMENT));
        nextInTimeline.click();
        contentManager.waitContentSpinner();
        setLaboratoryQuality(QualityOption.OPTION_NON_RECYCLABLE.getOptionText());

        //set pending roll
        nextInTimeline = getWebDriver().findElement(By.cssSelector(TIMELINE_NEXT_ELEMENT));
        nextInTimeline.click();
        contentManager.waitContentSpinner();
        setLaboratoryQuality(QualityOption.OPTION_PENDING.getOptionText());

        //set recyclable roll
        nextInTimeline = getWebDriver().findElement(By.cssSelector(TIMELINE_NEXT_ELEMENT));
        nextInTimeline.click();
        contentManager.waitContentSpinner();
        setLaboratoryQuality(QualityOption.OPTION_RECYCLABLE.getOptionText());

        treeManager.openTree();

        //check all options in tree
        setFilterOption(TreeFilterOptions.OPTION_ALL_STATES.getOptionText());
        Assert.assertEquals(getTreeContentElements(), "Frd160411_11T23u_400m_4.3tPRODUCTIVEA 160413 1449A 160413 1307A 160413 1124A 160413 0941160411_11T25u_400m_4.1t");

        setFilterOption(TreeFilterOptions.OPTION_NON_PRODUCTIVE.getOptionText());
        Assert.assertEquals(getTreeContentElements(), "Frd160411_11T23u_400m_4.3t160411_11T25u_400m_4.1t");

        setFilterOption(TreeFilterOptions.OPTION_INVALID.getOptionText());
        Assert.assertEquals(getTreeContentElements(), "Frd160411_11T23u_400m_4.3t160411_11T25u_400m_4.1t");

        setFilterOption(TreeFilterOptions.OPTION_ALL_ROLLS.getOptionText());
        Assert.assertEquals(getTreeContentElements(), "Frd160411_11T23u_400m_4.3tPRODUCTIVEA 160413 1449A 160413 1307A 160413 1124A 160413 0941160411_11T25u_400m_4.1t");

        setFilterOption(TreeFilterOptions.OPTION_GOOD_ROLLS.getOptionText());
        Assert.assertEquals(getTreeContentElements(), "Frd160411_11T23u_400m_4.3tPRODUCTIVEA 160413 1449160411_11T25u_400m_4.1t");

        setFilterOption(TreeFilterOptions.OPTION_WITH_LAB_DATA.getOptionText());
        Assert.assertEquals(getTreeContentElements(), "Frd160411_11T23u_400m_4.3tPRODUCTIVEA 160413 1449A 160413 1307A 160413 0941160411_11T25u_400m_4.1t");

        setFilterOption(TreeFilterOptions.OPTION_ALL_BAD_ROLLS.getOptionText());
        Assert.assertEquals(getTreeContentElements(), "Frd160411_11T23u_400m_4.3tPRODUCTIVEA 160413 1307A 160413 0941160411_11T25u_400m_4.1t");

        setFilterOption(TreeFilterOptions.OPTION_RECYCLABLE_ROLLS.getOptionText());
        Assert.assertEquals(getTreeContentElements(), "Frd160411_11T23u_400m_4.3tPRODUCTIVEA 160413 1307160411_11T25u_400m_4.1t");

        setFilterOption(TreeFilterOptions.OPTION_NON_RECYCLABLE_ROLLS.getOptionText());
        Assert.assertEquals(getTreeContentElements(), "Frd160411_11T23u_400m_4.3tPRODUCTIVEA 160413 0941160411_11T25u_400m_4.1t");

        //rollback quality to all 4 rolls (SCRIPT NEEDED)
        setLaboratoryQuality(QualityOption.OPTION_GOOD_ROLL.getOptionText());
        contentManager.waitContentSpinner();
        WebElement previousInTimeline = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        previousInTimeline.click();
        contentManager.waitContentSpinner();
        setLaboratoryQuality(QualityOption.OPTION_GOOD_ROLL.getOptionText());
        contentManager.waitContentSpinner();
        previousInTimeline = getWebDriver().findElement(By.cssSelector(TIMELINE_PREVIOUS_ELEMENT));
        previousInTimeline.click();
        contentManager.waitContentSpinner();
        setLaboratoryQuality(QualityOption.OPTION_GOOD_ROLL.getOptionText());
        contentManager.waitContentSpinner();

    }

    String getTreeContentElements() {
        String content = "";
        List<WebElement> treeContent = getWebDriver().findElements(By.cssSelector("#tree .treeRow.name"));
        for (int i = 0; i < treeContent.size(); i++) {
            content += treeContent.get(i).getText().trim();
        }
        return content;
    }

    private void setLaboratoryQuality(String option) {
        WebElement labQuality = getWebDriver().findElement(By.cssSelector(".labQualityValue .select2-chosen"));
        labQuality.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select2-result-label")));

        WebLocator dropdownOption = new WebLocator().setClasses("select2-result-label").setText(option, SearchType.EQUALS);
        dropdownOption.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".select2-result-label")));

        contentManager.waitContentSpinner();
    }

    private void setFilterOption(String option) {
        WebElement labQuality = getWebDriver().findElement(By.cssSelector(".treeFilterSelect .select2-chosen"));
        labQuality.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".select2-result-label")));

        WebLocator dropdownOption = new WebLocator().setClasses("select2-result-label").setText(option, SearchType.EQUALS);
        dropdownOption.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".select2-result-label")));

        treeManager.waitTreeSpinner();
    }

    @AfterMethod
    public void restoreCampaigns() throws IOException {
        getMongoUtils().executeScript(CAMPAIGNS_SCRIPT_NAME);
    }
}