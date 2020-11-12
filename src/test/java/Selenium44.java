import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Selenium44 {
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
//        driver.navigate().to("https://fakestore.testelka.pl");
//        driver.findElement(cookieConsentBar).click();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().setScriptTimeout(1000, TimeUnit.MILLISECONDS);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void exampleTest() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("console.log('Właśnie coś wpisałeś w konsole);");
        String domainName = (String) js.executeScript("return document.domain");
    }

    @Test
    public void asyncTest() {
        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript(
                "windows.setTimeout(arguments[arguments.length -1], 500);");
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Elapsed time: " + elapsedTime);
    }

    @Test
    public void syncTest() {
        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeScript(
                "windows.setTimeout(arguments[arguments.length -1], 500);");
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Elapsed time: " + elapsedTime);
    }
}
