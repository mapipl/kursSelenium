//1. Znajdź pole do wpisania imienia w pierwszym formularzu.
//2. Znajdź pole do wpisania adresu email w trzecim formularzu.
//3. Znajdź pole do wpisania adresu email w trzecim formularzu używając selektorów pseudoklas.
//4. Znajdź przycisk „Subscribe” w piątym formularzu.
//5. Znajdź przycisk „Subscribe” w piątym formularzu używając selektorów pseudoklas.
//6. Znajdź przycisk „Subscribe” w drugim formularzu.
//7. Znajdź przycisk „Subscribe” w drugim formularzu używając selektorów pseudoklas.
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium23 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://fakestore.testelka.pl/wyszukiwanie-elementow-poprzez-relacje/");
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findElementsByCssSelector() {
        driver.findElement(By.cssSelector("dd#name-element>input#name"));
        driver.findElement(By.cssSelector("div.second-div>input#email"));

        driver.findElement(By.cssSelector("div.second-div>input:first-of-type"));
        driver.findElement(By.cssSelector("div.second-div>input:nth-of-type(1)"));
        driver.findElement(By.cssSelector("div.second-div>input:nth-child(3)"));

        driver.findElement(By.cssSelector("div.second-div input#submit"));
        driver.findElement(By.cssSelector("div.second-div>div.div>input#submit"));

        driver.findElement(By.cssSelector("div.second-div input:last-child"));
        driver.findElement(By.cssSelector("div.second-div input:nth-of-type(3)"));

        driver.findElement(By.cssSelector("div:not([class])>button#submit")); // DIV BEZ KLASY
        driver.findElement(By.cssSelector("div:not(.second-div)>button#submit"));
    }
}
