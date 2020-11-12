import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium45 {
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
        driver.navigate().to("https://fakestore.testelka.pl/product/zmien-swoja-sylwetke-yoga-na-malcie/");
        driver.findElement(cookieConsentBar).click();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void headerCardTest() {
        WebElement description = driver.findElement(By.cssSelector("div#tab-description"));
        ((JavascriptExecutor) driver).executeScript("argumants[0].scrollIntoView()", description);
        List<WebElement> headerCard = driver.findElements(By.cssSelector("section.storefront-sticky-add-to-cart--slideInDown"));
        Assertions.assertTrue(headerCard.size() == 1, "Header is not displayed after scrolling to description.");
    }

    @Test
    public void headerCardTest2() {
        WebElement description = driver.findElement(By.cssSelector("div#tab-description"));

        ((JavascriptExecutor) driver).executeScript("argumants[0].scrollIntoView()", description);

        WebElement headerCard = driver.findElement(By.cssSelector("section.storefront-sticky-add-to-cart"));
        Assertions.assertTrue(headerCard.isDisplayed(), "Header is not displayed after scrolling to description.");
    }
}
