import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Selenium47 {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://jsfiddle.net/nm134se7/");
        driver.switchTo().frame("result");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void acceptConfirmWindowTest() {
        driver.findElement(By.cssSelector("button[onclick='confirmFunction()']")).click();
        driver.switchTo().alert().accept();
        String message = driver.findElement(By.cssSelector("p#demo")).getText();
        Assertions.assertEquals("Wybrana opcja to OK!", message, "Message is not correct.");
    }

    @Test
    public void dismissConfirmWindowTest() {
        driver.findElement(By.cssSelector("button[onclick='confirmFunction()']")).click();
        driver.switchTo().alert().dismiss();
        String message = driver.findElement(By.cssSelector("p#demo")).getText();
        Assertions.assertEquals("Wybrana opcja to Cancel!", message, "Message is not correct.");
    }

    @Test
    public void acceptPromptWindowTest() {
        driver.findElement(By.cssSelector("button[onclick='promptFunction()']")).click();
        String textToSend = "SpongeBob";
        driver.switchTo().alert().sendKeys(textToSend);
        driver.switchTo().alert().accept();
        String message = driver.findElement(By.cssSelector("p#prompt-demo")).getText();
        Assertions.assertEquals("Cześć " + textToSend + "! Jak leci?", message, "Message is not correct.");
    }

    @Test
    public void dismissPromptWindowTest() {
        driver.findElement(By.cssSelector("button[onclick='promptFunction()']")).click();
        driver.switchTo().alert().dismiss();
        String message = driver.findElement(By.cssSelector("p#prompt-demo")).getText();
        Assertions.assertEquals("Użytkownik anulował akcję.", message, "Message is not correct.");
    }
}
