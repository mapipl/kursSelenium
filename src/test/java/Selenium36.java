import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Selenium36 {

    WebDriver driver;
    String password = "m^&vApNUQ#WjuQWUFnj)8G22";
    String userFullName = "Geralt z Rivii";
    String myAccountContent;
    String errorMessageText;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @DisplayName("Successful login")
    @ParameterizedTest(name = "User: \"{0}\"")
    @CsvSource({"TestowyUser",
            "test@testelka.pl"})
    void successfulLogin(String userName) {
        logIn(userName, password);
        myAccountContent = getDisplayedName();
        Assertions.assertTrue(myAccountContent.contains(userFullName),
                "My Account page does not contain correct name. Expected name: " + userFullName + " was not found in a string: " + myAccountContent);
    }

    @DisplayName("Unsuccessful login")
    @ParameterizedTest(name = "User: \"{0}\" with password: {1}")
    @CsvSource({"TestowyUser, wrong, BŁĄD: Wprowadzone hasło dla nazwy użytkownika TestowyUser nie jest poprawne. Nie pamiętasz hasła?",
            "NonexistentUser, wrong, BŁĄD: Nieprawidłowa nazwa użytkownika. Nie pamiętasz hasła?",
            "test@testelka.pl, wrong, BŁĄD: Dla adresu e-mail test@testelka.pl podano nieprawidłowe hasło. Nie pamiętasz hasła?",
            "NonexistentEmail@test.pl, wrong, BŁĄD: Nieprawidłowy adres e-mail. Nie pamiętasz hasła?",
            "test@testelka.pl, '', BŁĄD: Pole „Hasło” jest puste.",
            "'', wrong, Błąd: Nazwa użytkownika jest wymagana."})
    void unsuccessfulLogin(String userName, String password, String expectedMessage) {
        logIn(userName, password);
        errorMessageText = getErrorMessage();
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }

    private void logIn(String userName, String password) {
        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    private String getDisplayedName() {
        return driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).getText();
    }

    private String getErrorMessage() {
        return driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
    }
}