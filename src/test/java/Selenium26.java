import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium26 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-atrybuty-w-xpath/");
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findElementsByXpath() {
        driver.findElement(By.xpath(".//*[text()='Button']"));
        driver.findElement(By.xpath(".//*[contains(@title, 'Button')]"));

        driver.findElement(By.xpath(".//*[contains(@style, 'background-color: #db456f')]"));

        driver.findElement(By.xpath(".//*[contains(@id, 'btn')]"));
        driver.findElement(By.xpath(".//*[contains(text(), 'Btn')]"));

        driver.findElement(By.xpath(".//*[contains(@id, 'button-')]"));

        driver.findElement(By.xpath(".//*[@class='button primary test']"));

        driver.findElement(By.xpath(".//*[contains(@class, 'accent')]"));

        driver.findElement(By.xpath(".//*[contains(@class, 'accent') and not(contains(@class, 'accent-test'))]"));
        driver.findElement(By.xpath(".//*[contains(@class, 'accent') and not(contains(@class, 'accent-test')) and not(contains(@id, 'button-button1'))]"));

        driver.findElement(By.xpath(".//*[text()='Button' and contains(@class, 'button accent')]"));
    }
}
