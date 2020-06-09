package realword.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class HomePage {

    private WebDriver driver;

    protected String url;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.url = "https://demo.realworld.io";
    }

    public void go() {
        driver.get(url);
    }

    public List<WebElement> findTagInFiltering(String tagName) {
        return driver.findElements(By.xpath("//*[@ng-bind='tagName' and text()='" + tagName + "']"));
    }

    public void selectTag(String tagName) {
        if (findTagInFiltering(tagName).size() == 1) {
            findTagInFiltering(tagName).get(0).click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(
                    driver.findElement(By.xpath("//*[contains(text(),'Loading articles...')]"))));
        } else { Assert.fail("Tag " + tagName + "is absent in filtering."); }
    }

    public List<WebElement> getArticlesTagLists() {
        return driver.findElements(By.className("tag-list"));
    }

    public List<WebElement> getAllFoundTagsOnPage() {
        return driver.findElements(By.xpath("//*[@class='tag-default tag-pill tag-outline ng-binding ng-scope']"));
    }

    public void getArticlePreviews() {
        List<WebElement> previews = driver.findElements(By.className("article-preview"));
    }

    public List<WebElement> getTagsForFirstArticlePreview() {
        return driver.findElements(By.xpath("//article-preview[1]/descendant::*[@class='tag-default tag-pill tag-outline ng-binding ng-scope']"));
    }

}
