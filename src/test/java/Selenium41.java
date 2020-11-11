import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium41 {
    WebDriver driver;
    WebDriverWait wait;

    By cookieConsentBar = By.cssSelector("a[class*='dismiss-link']");

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/metody-na-elementach/");
        driver.findElement(cookieConsentBar).click();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void checkElementStateTest() {
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        WebElement nonVisibleButton = driver.findElement(By.cssSelector("[name='sailing']"));
        List<WebElement> yellowButtons = driver.findElements(By.cssSelector("a.button"));
        WebElement selectedCheckbox = driver.findElement(By.cssSelector("input[name='selected-checkbox']"));
        WebElement notSelectedCheckbox = driver.findElement(By.cssSelector("input[name='not-selected-checkbox']"));
        WebElement selectedRadiobutton = driver.findElement(By.cssSelector("input[name='selected-radio']"));
        WebElement notselectedRadiobutton = driver.findElement(By.cssSelector("input[name='not-selected-radio']"));
        List<WebElement> elementsWitchButtonClass = driver.findElements(By.cssSelector(".button"));

        Assertions.assertAll("Checking properties of elements",
                () -> Assertions.assertFalse(mainPageButton.isEnabled(), "Main page button is not disable"),
                () -> Assertions.assertFalse(nonVisibleButton.isDisplayed(), "Sail button is probably displayed"),
                () -> assertThatButtonsAreYellow(yellowButtons),
                () -> Assertions.assertTrue(selectedCheckbox.isSelected(), "Checkbox is not selected"),
                () -> Assertions.assertTrue(selectedRadiobutton.isSelected(), "Radiobutton is probably selected"),
                () -> Assertions.assertFalse(notSelectedCheckbox.isSelected(), "Checkbox is not selected"),
                () -> Assertions.assertFalse(notselectedRadiobutton.isSelected(), "Radiobutton is probably selected"),
                () -> assertElementsHaveCorrectTag(elementsWitchButtonClass)
        );
    }

    public void assertThatButtonsAreYellow(List<WebElement> buttons) {
        for (WebElement button : buttons) {
            String color = button.getCssValue("background-color");
            Assertions.assertEquals("rgba(245, 233, 101, 1)", color, "Button color is not what expected");
        }
    }

    public void assertElementsHaveCorrectTag(List<WebElement> elements) {
        for (WebElement element : elements) {
            Assertions.assertEquals("a", element.getTagName(), "Element's tag is not 'a'.");
        }
    }
}
