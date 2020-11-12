import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Selenium46 {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    By cookieConsentBar = By.cssSelector("a[class*='dismiss-link']");

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
//        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-ramek/");
//        driver.findElement(cookieConsentBar).click();
        wait = new WebDriverWait(driver, 10);
        js = (JavascriptExecutor) driver;
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void promptBoxTest() {
        String javascript = "prompt('Możesz tutaj coś wpisać:')";
        js.executeScript(javascript);
        wait.until(ExpectedConditions.alertIsPresent());
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");
        driver.switchTo().alert().accept();
        js.executeScript(javascript);
        driver.switchTo().alert().dismiss();
    }
}
