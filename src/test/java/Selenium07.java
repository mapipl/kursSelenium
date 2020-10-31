import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium07 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void checkTitle() {
        String expectedPolishTitle = "Wikipedia, wolna encyklopedia";
        driver.navigate().to("http://wikipedia.pl");
        Assertions.assertEquals(expectedPolishTitle, driver.getTitle(), "Page title is not: " + expectedPolishTitle);
        Assertions.assertTrue(driver.getTitle().contains("wolna encyklopedia"), "Page title is not: " + expectedPolishTitle);
    }

    @Test
    public void checkURL() {
        String expectedPolishUrl = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
        driver.navigate().to("http://wikipedia.pl");
        Assertions.assertEquals(expectedPolishUrl, driver.getCurrentUrl(), "Current URL is not: " + expectedPolishUrl);
        Assertions.assertTrue(driver.getCurrentUrl().contains("Wikipedia:Strona_g%C5%82%C3%B3wna"), "Current URL is not: " + expectedPolishUrl);
    }

    @Test
    public void checkPageSource() {
        driver.navigate().to("http://wikipedia.pl");
        Assertions.assertTrue(driver.getPageSource().contains("lang=\"pl\""), "Page source does not contains lang=\"pl\" ");
    }

    @Test
    public void checkTitleSpanish() {
        driver.navigate().to("http://wikipedia.pl");
        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();
        String expectedSpanishTitle = "Wikipedia, la enciclopedia libre";
        Assertions.assertEquals(expectedSpanishTitle, driver.getTitle(), "Page title is not: " + expectedSpanishTitle);
        Assertions.assertTrue(driver.getTitle().contains("la enciclopedia"), "Page title is not: " + expectedSpanishTitle);
    }
}
