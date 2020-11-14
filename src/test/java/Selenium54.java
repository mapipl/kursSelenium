import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Selenium54 {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        actions = new Actions(driver);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void sendKeysExample() {
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
        WebElement login = driver.findElement(By.cssSelector("#username"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", login);

//        actions.sendKeys(login, "testowy user").build().perform();
//        actions.sendKeys(login, Keys.SHIFT, "testowy user").build().perform();
        actions.click(login).sendKeys(Keys.SHIFT, "testowy user").build().perform();
    }

    @Test
    public void pressingKeysExample() {
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
        WebElement login = driver.findElement(By.cssSelector("#username"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", login);

        actions.keyDown(Keys.SHIFT).sendKeys(login, "testowy user").keyUp(Keys.SHIFT).build().perform();
    }

    @Test
    public void pressingKeysExample2() {
        driver.navigate().to("https://jqueryui.com/selectable/#default");
        driver.switchTo().frame(0);

        List<WebElement> listItems = driver.findElements(By.cssSelector("li.ui-selectee"));
        actions.keyDown(Keys.CONTROL)
                .click(listItems.get(0))
                .click(listItems.get(1))
                .click(listItems.get(2))
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
    }

    @Test
    public void pressingKeysExample3() {
        driver.navigate().to("https://jqueryui.com/selectable/#default");
        driver.switchTo().frame(0);

        List<WebElement> listItems = driver.findElements(By.cssSelector("li.ui-selectee"));
        actions.clickAndHold(listItems.get(0))
                .clickAndHold(listItems.get(1))
                .clickAndHold(listItems.get(2))
                .release()
                .build()
                .perform();
    }

    @Test
    public void pressingKeysExample4() {
        driver.navigate().to("https://jqueryui.com/selectable/#default");
        driver.switchTo().frame(0);

        List<WebElement> listItems = driver.findElements(By.cssSelector("li.ui-selectee"));
        actions.keyDown(Keys.CONTROL)
                .click(listItems.get(0))
                .click(listItems.get(1))
                .keyUp(Keys.CONTROL)
                .click(listItems.get(2))
                .build()
                .perform();
    }
}
