import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.jvm.hotspot.utilities.Assert;

import java.util.concurrent.TimeUnit;

public class Selenium33 {

    String firstName = "Maciek";
    String lastName = "P";
    String country;

    WebDriver driver;

    @Test
    public void registerUserPolandTests() {
        country = "Poland";
        registerUser(firstName, lastName, country);
        Assertions.assertEquals("Polish user registered", getAlertText());
    }

    @Test
    public void registerUserPortugalTests() {
        country = "Portugal";
        registerUser(firstName, lastName, country);
        Assertions.assertEquals("Polish user registered", getAlertText());
    }

    private void registerUser(String firstName, String lastName, String country) {
        driver.findElement(By.cssSelector("input[id='first-name']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[id='last-name']")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input[id='country']")).sendKeys(country);
        driver.findElement(By.cssSelector("button[name='register']")).click();
    }

    private String getAlertText() {
        return driver.findElement(By.cssSelector("id='alert'")).getText();
    }
}
