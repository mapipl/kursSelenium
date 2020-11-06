import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Selenium31 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.manage().window().setPosition(new Point(8, 30));
        driver.navigate().to("https://gofile.io/uploadFiles");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void fileUploadTest() {
        WebElement uploadFileInput = driver.findElement(By.cssSelector("input[type='file']"));
        String expectedFileName = "ij_image.png";
        String patch = "C:\\testing\\example_patch\\" + expectedFileName;
        uploadFileInput.sendKeys(patch);

        String actualFileName = driver.findElement(By.cssSelector("table[id='uploadFiles-datatable'] tr td")).getText();

        Assertions.assertEquals(expectedFileName, actualFileName, "Name of uploaded file is different than expected one.");
    }
}
