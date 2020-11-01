import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium25 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://www.amazon.com/");
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findElementsByXpath() {
        driver.findElement(By.xpath(".//img[@class='landscape-image']"));
        driver.findElement(By.xpath(".//*[@class='landscape-image']"));
        driver.findElement(By.xpath(".//img[contains(@class, 'scape-ima')]"));
        driver.findElement(By.xpath(".//img[starts-with(@class, 'landscape-im')]"));

        driver.findElement(By.xpath(".//h2[@class='a-color-base headline truncate-2line' and text()='AmazonBasics']"));
        driver.findElement(By.xpath(".//h2[contains(@class, 'truncate-2line')]"));
        driver.findElement(By.xpath(".//h2[contains(text(), 'AmazonBasics')]"));
    }
}
