import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Selenium57 {
    //    WebDriver driver;
    ChromeDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.navigate().to("https://fakestore.testelka.pl/");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void localStorageExample() {
        driver.navigate().to("https://airly.eu/map/pl/#54.5146313198,18.5495910009");
//        LocalStorage local = driver.getLocalStorage();
//        String value = local.getItem("persist:map");
//        int size = local.size();
//        Set<String> keys = local.keySet();
//        String removedValue = local.removeItem("persist:map");
//        local.setItem("spell", "Alohomora");
//        local.clear();

        String key = "persist:map";

        String valuelocal = (String) ((JavascriptExecutor) driver).executeScript("return localStorage.getItem(arguments[0]);", key);
        ((JavascriptExecutor) driver).executeScript("localStorage.setItem(arguments[0], arguments[1]);", "spell", "Alohomora!");
        ((JavascriptExecutor) driver).executeScript("localStorage.removeItem(arguments[0]);", key);
        String indexValueLocal = (String) ((JavascriptExecutor) driver).executeScript("return localStorage.key(arguments[0]);", 2);
        long sizeLocal = (long) ((JavascriptExecutor) driver).executeScript("return localStorage.length;");
        ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
    }

    @Test
    public void sesionStorageExample() {
        String key = "persist:map";

        driver.navigate().to("https://www.youtube.com/watch?v=DPfHHls50-w");
        String valueSessiom = (String) ((JavascriptExecutor) driver).executeScript("return sessionStorage.getItem(arguments[0]);", key);
        ((JavascriptExecutor) driver).executeScript("sessionStorage.setItem(arguments[0], arguments[1]);", "spell", "Alohomora!");
        ((JavascriptExecutor) driver).executeScript("sessionStorage.removeItem(arguments[0]);", key);
        String indexValueSession = (String) ((JavascriptExecutor) driver).executeScript("return sessionStorage.key(arguments[0]);", 2);
        long sizeSession = (long) ((JavascriptExecutor) driver).executeScript("return sessionStorage.length;");
        ((JavascriptExecutor) driver).executeScript("sessionStorage.clear();");
    }
}
