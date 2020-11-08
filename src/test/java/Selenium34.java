import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class Selenium34 {

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
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("input[id='first-name']"))).sendKeys(firstName).perform();
        actions.moveToElement(driver.findElement(By.cssSelector("input[id='last-name']"))).sendKeys(lastName).perform();
        actions.moveToElement(driver.findElement(By.cssSelector("input[id='country']"))).sendKeys(country).perform();
        actions.moveToElement(driver.findElement(By.cssSelector("button[name='register']"))).click().perform();
    }

    private String getAlertText() {
        return driver.findElement(By.cssSelector("id='alert'")).getText();
    }
}
