import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Selenium14 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("http://wikipedia.pl");
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findElementByLinkText() {
        driver.findElement(By.linkText("Wikisłownik"));
        driver.findElement(By.partialLinkText("redagować"));
    }
}
