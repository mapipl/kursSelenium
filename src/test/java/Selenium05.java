//Otworzy stronę główną Wikipedii.
//Następnie otworzy stronę główną Nasa.
//Cofnie się do strony Wikipedii (używając nawigacji wstecz).
//Potwierdź, że driver jest na stronie Wikipedii: porównaj (Assertions.assertEquals()) tytuł strony z oczekiwanym.
//Przejdź do strony Nasa (używając nawigacji naprzód).
//Potwierdź, że driver jest na stronie Nasa: porównaj tytuł strony z oczekiwanym.
//Zamknij okno przeglądarki.
//Zamknij sesję.

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium05 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void navigationTest() {
        driver.get("http://wikipedia.pl");
        driver.navigate().to("https://nasa.gov");
        driver.navigate().back();
        String wikiTitle = "Wikipedia, wolna encyklopedia";
        Assertions.assertEquals(wikiTitle, driver.getTitle(), "The title of the page is not: " + wikiTitle);
        driver.navigate().forward();
        String nasaTitle = "NASA";
        Assertions.assertEquals(nasaTitle, driver.getTitle(), "The title of the page is not: " + nasaTitle);
    }
}
