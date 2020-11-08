import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Selenium32 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("http://fakestore.testelka.pl/moje-konto/");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void existentUserNameCorrectPasswordTest() {
        String userName = "maciek_p@interia.pl";
        String password = "maciek_p@interia.pl";
        driver.findElement(By.cssSelector("input[name=username]")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=login]")).click();

        String userDisplayedName = "Maciek P";
        String myAccountContent = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).getText();
        Assertions.assertTrue(myAccountContent.contains(userDisplayedName),
                "My Account page doesn't contain name. Expected name: " + userDisplayedName + " was not found in a string: " + myAccountContent);
    }

    @Test
    public void existentUserNameIncorrectPasswordTest(){
        String userName = "maciek_p@interia.pl";
        String password = "blebleble";
        driver.findElement(By.cssSelector("input[name=username]")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=login]")).click();

        String errorMessageText = driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
        String expectedMessage = "BŁĄD: Dla adresu email maciek_p@interia.pl podano nieprawidłowe hasło. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct");
    }
}
