import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Selenium50 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.navigate().to("https://allegro.pl");
        wait = new WebDriverWait(driver, 5);
        driver.manage().addCookie(new Cookie("gdpr_permission_given", "1"));
        driver.navigate().refresh();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void selectElement() {
        WebElement productCategories = driver.findElement(By.cssSelector("[data-role='filters-dropdown-toggle']"));
        Select categoriesDropdown = new Select(productCategories);
        categoriesDropdown.selectByIndex(3);
        categoriesDropdown.selectByValue("/kategoria/kultura-i-rozrywka");
        categoriesDropdown.selectByVisibleText("Zdrowie");

//        Boolean isMultiple = categoriesDropdown.isMultiple();
//        categoriesDropdown.deselectByIndex(3);

        List<WebElement> options = categoriesDropdown.getOptions();
        List<WebElement> selectedOptions = categoriesDropdown.getAllSelectedOptions();
        WebElement firstSelectedOptions = categoriesDropdown.getFirstSelectedOption();
    }

    @Test
    public void selectMultiElement() {
        WebElement dropdownElement = driver.findElement(By.cssSelector("select"));
        Select dropdown = new Select(dropdownElement);

        boolean isMultiple = dropdown.isMultiple();

        dropdown.selectByIndex(1);
        dropdown.selectByIndex(3);

        dropdown.deselectByIndex(1);
        dropdown.deselectByValue("opcja_2");
        dropdown.deselectByVisibleText("Opcja druga");

        dropdown.deselectAll();
    }
}
