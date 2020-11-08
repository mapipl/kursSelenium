import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Selenium35 {

    WebDriver driver;
    String userName = "TestowyUser";
    String email = "test@testelka.pl";
    String password = "m^&vApNUQ#WjuQWUFnj)8G22";
    String wrongPassword = "wrong";
    String nonexistentUserName = "NonExistentUser";
    String nonexistentEmail = "notexistingemail@testelka.pl";
    String userFullName = "Geralt z Rivii";
    String myAccountContent;
    String errorMessageText;
    String expectedMessage;

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

    @Test
    public void existentUsernameCorrectPasswordTest() {
        logIn(userName, password);
        myAccountContent = getDisplayedName();
        Assertions.assertTrue(myAccountContent.contains(userFullName),
                "My Account page does not contain correct name. Expected name: " + userFullName + " was not found in a string: " + myAccountContent);
    }

    @Test
    public void existentEmailCorrectPasswordTest() {
        logIn(email, password);
        myAccountContent = getDisplayedName();
        Assertions.assertTrue(myAccountContent.contains(userFullName),
                "My Account page does not contain correct name. Expected name: " + userFullName + " was not found in a string: " + myAccountContent);
    }

    @Test
    public void existentUsernameIncorrectPasswordTest() {
        logIn(userName, wrongPassword);
        errorMessageText = getErrorMessage();
        expectedMessage = "BŁĄD: Wprowadzone hasło dla nazwy użytkownika " + userName + " nie jest poprawne. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }

    @Test
    public void nonexistentUsernameIncorrectPasswordTest() {
        logIn(nonexistentUserName, wrongPassword);
        errorMessageText = getErrorMessage();
        expectedMessage = "BŁĄD: Nieprawidłowa nazwa użytkownika. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }

    @Test
    public void existentEmailIncorrectPasswordTest() {
        logIn(email, wrongPassword);
        errorMessageText = getErrorMessage();
        expectedMessage = "BŁĄD: Dla adresu e-mail " + email + " podano nieprawidłowe hasło. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }

    @Test
    public void nonexistentEmailIncorrectPasswordTest() {
        logIn(nonexistentEmail, wrongPassword);
        errorMessageText = getErrorMessage();
        expectedMessage = "BŁĄD: Nieprawidłowy adres e-mail. Nie pamiętasz hasła?";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }

    @Test
    public void existentEmailNoPasswordTest() {
        logIn(email, "");
        errorMessageText = getErrorMessage();
        expectedMessage = "BŁĄD: Pole „Hasło” jest puste.";
        Assertions.assertEquals(expectedMessage, errorMessageText, "Error message is not correct.");
    }

    @Test
    public void noUsernameDummyPasswordTest() {
        logIn("", wrongPassword);
        errorMessageText = getErrorMessage();
        expectedMessage = "Błąd: Nazwa użytkownika jest wymagana.";
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
