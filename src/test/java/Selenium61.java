import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Selenium61 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.navigate().to("https://www.zooniverse.org");
        wait = new WebDriverWait(driver, 5);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void screenshotExample() throws IOException {
        driver.findElement(By.cssSelector("button[value='sign-in']")).click();
        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("malaMi");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("hasłotestowe");
        driver.findElement(By.cssSelector("form")).submit();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span[class='login-bar']")));
        WebElement userName = driver.findElement(By.cssSelector("span[class='account-bar'] strong"));

//        File userNameScreenshot = userName.getScreenshotAs(OutputType.FILE);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File("c:\\TEMP\\test.jpg"));
//        FileUtils.copyFile(screenshot, new File("c:\\TEMP\\test.jpg"));
//        FileHandler.copy(userNameScreenshot, new File("c:\\TEMP\\test.jpg"));

        Assertions.assertEquals("MALAMI", userName.getText(), "Username displayed on header is not correct. The user was probably not logged in.");
    }
}
