import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Selenium56 {
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
        LocalStorage local = driver.getLocalStorage();
        String value = local.getItem("persist:map");
        int size = local.size();
        Set<String> keys = local.keySet();
        String removedValue = local.removeItem("persist:map");
        local.setItem("spell", "Alohomora");
        local.clear();
    }

    @Test
    public void sesionStorageExample() {
        driver.navigate().to("https://www.youtube.com/watch?v=DPfHHls50-w");
        SessionStorage session = driver.getSessionStorage();
        String value = session.getItem("yt-remote-session-app");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(a -> session.size() == 5);
        int size = session.size();
        Set<String> keys = session.keySet();
        String removedValue = session.removeItem("yt-remote-session-app");
        session.setItem("spell", "Alohomora");
        session.clear();
    }
}
