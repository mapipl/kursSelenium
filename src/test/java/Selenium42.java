import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium42 {
    WebDriver driver;
    WebDriverWait wait;

    By cookieConsentBar = By.cssSelector("a[class*='dismiss-link']");

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://nasa.gov");
        wait = new WebDriverWait(driver, 10);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void frameExamples() {
        driver.switchTo().frame("twitter-widget-0");
        driver.switchTo().frame(0);

        WebElement frame = driver.findElement(By.cssSelector("iframe#twitter-widget-0"));
        driver.switchTo().frame(frame);

        WebElement viewOnTwitter = driver.findElement((By.cssSelector("a[data-scribe*='twitter_url']")));

        driver.switchTo().defaultContent();
//        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("div.navbar-header>a.logo"));
    }
}
