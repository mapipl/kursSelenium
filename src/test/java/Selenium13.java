import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Selenium13 {
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
    public void findElementById() {
        driver.findElement(By.id("searchInput"));
        driver.findElement(By.name("search"));
        driver.findElement(By.className("searchButton"));
//        driver.findElement(By.className("header nomobile")); DWIE RÓŻNE KLASY

        List<WebElement> nomobileClassElements = driver.findElements(By.className("nomobile"));

        WebElement elementWithTwoClasses = null;

        for (WebElement iteratorClassElement : nomobileClassElements) {
            String elementClass = iteratorClassElement.getAttribute("class");
            if (elementClass.equals("header nomobile")) {
                elementWithTwoClasses = iteratorClassElement;
            }
        }
        Assertions.assertTrue(elementWithTwoClasses != null, "Element was not found");

        int numberOfImages = driver.findElements(By.tagName("img")).size();
    }
}
