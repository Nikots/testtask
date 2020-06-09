package automation.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import realword.automation.BaseTest;
import realword.automation.HomePage;

public class TestFiltrationByTags extends BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeTest(description = "initialize driver")
    public void init() {
        this.driver = getDriver();
        this.homePage = new HomePage(driver);
        this.homePage.go();
    }

    @Test (description = "Check if any article review is found by tag filtering.")
    public void checkFirstArticlePreviewContainsSelectedTag() {
        String tagName = "as";
        homePage.selectTag(tagName);

        Assert.assertTrue(homePage.getTagsForFirstArticlePreview().stream()
                .filter(tagElement -> tagElement.getText().trim().equalsIgnoreCase(tagName)).count() == 1);
    }

    @Test(description = "Check all article reviews (10 per 1 page) contain selected tag.")
    public void checkSelectedTagAppears10Times() {
        String tagName = "butt";
        homePage.selectTag(tagName);

        Assert.assertTrue(homePage.getAllFoundTagsOnPage().stream()
                .filter(tagElement -> tagElement.getText().trim().equalsIgnoreCase(tagName)).count() == 10);
    }

    @Test (description = "Check tag with current name is absent in filtering.")
    public void checkTagIsAbsentInFiltering() {
        String tagName = "absenttag";

        Assert.assertTrue(homePage.findTagInFiltering(tagName).isEmpty());
    }

    @AfterTest(description = "close browser")
    public void tearDown() {
        if (null != this.driver) {
            this.driver.quit();
        }
    }
}
