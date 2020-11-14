import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium55 {
    WebDriver driver;
    Actions actions;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions = new Actions(driver);
        driver.navigate().to("https://fakestore.testelka.pl/actions/");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void goToCartTest() {
        WebElement menu = driver.findElement(By.cssSelector("#menu-link"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menu);
        actions.contextClick(menu).build().perform();
        WebElement cart = driver.findElement(By.cssSelector(".menu-cart"));
        actions.click(cart).build().perform();
        Assertions.assertEquals("https://fakestore.testelka.pl/koszyk/", driver.getCurrentUrl(), "URL is not correct and you are not  at Cart page");
    }

    @Test
    public void colorChangeTest() {
        WebElement colorBox = driver.findElement(By.cssSelector("#double-click"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", colorBox);
        actions.doubleClick(colorBox).build().perform();
        Assertions.assertEquals("rgba(245, 93, 122, 1)", colorBox.getCssValue("background-color"), "Color of the box is not correct");
    }

    @Test
    public void textInputTest() {
        WebElement input = driver.findElement(By.cssSelector("#input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", input);
        String text = "MAHNAMAHNA";
        actions.sendKeys(input, text).build().perform();
        WebElement button = driver.findElement(By.cssSelector("[onclick='zatwierdzTekst()']"));
        actions.click(button).build().perform();
        WebElement output = driver.findElement(By.cssSelector("#output"));
        Assertions.assertEquals("Wprowadzony tekst: " + text, output.getText(), "Output text is not correct.");
    }

    @Test
    public void selectTest() {
        List<WebElement> items = driver.findElements(By.cssSelector(".ui-selectee"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", items.get(0));
        actions.keyDown(Keys.CONTROL).click(items.get(3)).click(items.get(5)).click(items.get(4)).keyUp(Keys.CONTROL).build().perform();
        Assertions.assertAll(
                () -> Assertions.assertTrue(items.get(3).getAttribute("class").contains("ui-selected"), "Item with id=3 was not selected"),
                () -> Assertions.assertTrue(items.get(5).getAttribute("class").contains("ui-selected"), "Item with id=5 was not selected"),
                () -> Assertions.assertTrue(items.get(4).getAttribute("class").contains("ui-selected"), "Item with id=4 was not selected")
        );
    }
}
