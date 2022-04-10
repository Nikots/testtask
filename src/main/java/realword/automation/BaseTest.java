package realword.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(new ChromeOptions());
    }
}
