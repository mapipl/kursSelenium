import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Selenium49 {
    WebDriver driver;
    WebDriverWait wait;
    By cookieAccept = By.cssSelector("a[class*='dismiss-link']");
    By pilatesGroup = By.cssSelector("a[href*='pilates']");
    By product = By.cssSelector("li.post-61");

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/");
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cookieAccept));
        driver.findElement(cookieAccept).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieAccept));
        driver.findElement(pilatesGroup).click();
        driver.findElement(product).click();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void removeFromWishListTesr() {
        By addToWishList = By.cssSelector(".add_to_wishlist");
        driver.findElement(addToWishList).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(addToWishList));
        By wishlistLink = By.cssSelector("#menu-item-248");
        driver.findElement(wishlistLink).click();
        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        windows.remove(parentWindow);
        String wishlistWindow = windows.iterator().next();
        driver.switchTo().window(wishlistWindow);
        By removeFromWishlist = By.cssSelector(".remove_from_wishlist");
        driver.findElement(removeFromWishlist).click();
        By emptyWishlist = By.cssSelector("td.wishlist-empty");
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(emptyWishlist));
        Assertions.assertDoesNotThrow(
                () -> wait.until(ExpectedConditions.invisibilityOfElementLocated(emptyWishlist)), "Wishlist is not empty."
        );
    }
}
