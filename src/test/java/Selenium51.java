import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Selenium51 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/");
        wait = new WebDriverWait(driver, 5);
        driver.navigate().refresh();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void priceOrderAscTest() {
        driver.findElement(By.cssSelector("a[href*='windsurfing']")).click();
        WebElement orderBy = driver.findElements(By.cssSelector("select[name='orderby']")).get(0);
        Select orderByDropdown = new Select(orderBy);
        orderByDropdown.selectByValue("price");
        WebElement firstPriceElement = driver.findElements(By.cssSelector("span.price")).get(0);
        Assertions.assertEquals("2 900,00 zł", firstPriceElement.getText(), "First price is not the lowest price.");
    }

    @Test
    public void priceOrderDscTest() {
        driver.findElement(By.cssSelector("a[href*='windsurfing']")).click();
        WebElement orderBy = driver.findElements(By.cssSelector("select[name='orderby']")).get(0);
        Select orderByDropdown = new Select(orderBy);
        orderByDropdown.selectByValue("price-desc");
        WebElement firstPriceElement = driver.findElements(By.cssSelector("span.price")).get(0);
        Assertions.assertEquals("5 399,00 zł", firstPriceElement.getText(), "First price is not the highest price.");
    }
}
