import TestHelpers.TestStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Selenium62 {
    protected WebDriver driver;
    WebDriverWait wait;

    @RegisterExtension
    TestStatus status = new TestStatus();

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.zooniverse.org");
        wait = new WebDriverWait(driver, 5);
    }

    @AfterEach
    public void driverQuit(TestInfo info) throws IOException {
        if (status.isFailed) {
            System.out.println("Test screenshot is available at: " + takeScreenshots(info));
        }
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
        Assertions.assertEquals("MALAMI2", userName.getText(), "Username displayed on header is not correct. The user was probably not logged in.");
    }

//    private void takeScreenshots(TestInfo info) throws IOException {
//        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        LocalDateTime timeNow = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH-mm-ss");
//        FileHandler.copy(screenshot, new File("c:\\TEMP\\" + info.getDisplayName() + " " + formatter.format(timeNow) + " test.jpg"));
//    }

    private String takeScreenshots(TestInfo info) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH-mm-ss");
        String path = "c:\\TEMP\\" + info.getDisplayName() + " " + formatter.format(timeNow) + " test.jpg";
        FileHandler.copy(screenshot, new File(path));
        return path;
    }
}
