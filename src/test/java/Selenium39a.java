import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Selenium39a {
    WebDriver driver;
    WebDriverWait wait;
    By cookieConsentBar = By.cssSelector("a[class*='dismiss-link']");
    By pilatesGroup = By.cssSelector("a[href*='pilates']");
    By product = By.cssSelector("li.post-61");
    By addToCartButton = By.cssSelector("button[name='add-to-cart']");
    By goToCartButton = By.cssSelector("a.cart-contents");
    String correctCoupon = "10procent";
    String incorrectCoupon = "test";

    By clicableEntry = By.cssSelector("input[name='coupon_code']");

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl");

        wait = new WebDriverWait(driver, 10);

        driver.findElement(cookieConsentBar).click();
        driver.findElement(pilatesGroup).click();
        driver.findElement(product).click();

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(goToCartButton));
        driver.findElement(goToCartButton).click();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @DisplayName("Simple coupon tests")
    @ParameterizedTest(name = "Coupon: \"{0}\"")
    @CsvSource({ "'', Proszę wpisać kod kuponu.",
            "test, Kupon \"test\" nie istnieje!",
            "10procent, Kupon został pomyślnie użyty."})
    void simpleAddingCouponTests(String coupon, String expectedAlert){
        applyCoupon(coupon);
        Assertions.assertEquals(expectedAlert, getAlertText(), "Alert message was not what expected.");
    }

    @Test
    public void emptyCouponTest() {
        applyCoupon("");
        Assertions.assertEquals("Proszę wpisać kod kuponu.", getAlertText(), "Alert message was not what expected.");
    }

    @Test
    public void incorectCouponTest() {
        applyCoupon(incorrectCoupon);
        Assertions.assertEquals("Kupon \"" + incorrectCoupon + "\" nie istnieje!", getAlertText(), "Alert message was not what expected.");
    }

    @Test
    public void correctCouponTest() {
        applyCoupon(correctCoupon);
        Assertions.assertEquals("Kupon został pomyślnie użyty.", getAlertText(), "Alert message was not what expected.");
    }

    @Test
    public void addingCouponWhenAlreadyAppliedTest() {
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        Assertions.assertEquals("Kupon został zastosowany!", getAlertText(), "Alert message was not what expected.");
    }

    @Test
    public void removingCouponTest() {
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        By removeLink = By.cssSelector("a.woocommerce-remove-coupon");
        wait.until(ExpectedConditions.elementToBeClickable(removeLink)).click();
        waitForProcessingEnd();
        Assertions.assertEquals("Kupon został usunięty.", getAlertText(), "Alert message was not what expected.");
    }

    private void applyCoupon(String coupon) {
        By couponCodeField = By.cssSelector("input[name='coupon_code']");
        By applyCouponButton = By.cssSelector("button[name='apply_coupon']");

        wait.until(ExpectedConditions.elementToBeClickable(clicableEntry));

        driver.findElement(couponCodeField).sendKeys(coupon);
        driver.findElement(applyCouponButton).click();
    }

    private void waitForProcessingEnd() {
        By blockedUI = By.cssSelector("div.blockUI");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(blockedUI, 0));
        wait.until(ExpectedConditions.numberOfElementsToBe(blockedUI, 0));
    }

    private String getAlertText() {
        By alert = By.cssSelector("[role='alert']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alert)).getText();
    }
}
