import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginInYandexTest {

    private WebDriver driver;
    private String username = "firstnamelastname1989";
    private String password = "pTKJctHpbaj@t7M";
    private String expectedURL = "https://mail.yandex.com/?uid=1459838850#tabs/relevant";
    private String expectedName = "Inbox — Yandex.Mail";


    @BeforeAll
    public static void beforeClass () {

        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void beforeTest () {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @AfterEach
    public void afterTest () {
        driver.quit();
    }


    @Test
    @Tag("yandex_login_test")

    void yandexLoginTest () {

        driver.get("https://mail.yandex.com/");
        WebElement enterButton = driver.findElement(By.xpath("//*[@id=\"index-page-container\"]/div/div[2]/div/div/div[4]/a[2]"));
        enterButton.click();

        WebElement emailField = driver.findElement(By.id("passp-field-login"));
        emailField.sendKeys(username);
        WebElement loginButton = driver.findElement(By.id("passp:sign-in"));
        loginButton.click();

        WebElement passwordField = driver.findElement(By.id("passp-field-passwd"));
        passwordField.sendKeys(password + "\n");

        WebElement userIcon = driver.findElement(By.className("user-pic__image"));
        userIcon.click();

        assertAll("Links",
                () ->assertTrue(driver.getTitle().contains(expectedName), "Incorrect page was opened (Incorrect Title)"),
                () -> assertEquals(expectedURL, driver.getCurrentUrl(), "Incorrect page was opened  (Incorrect URL)"),
                () -> assertTrue(driver.findElement(By.linkText("Change password")).isDisplayed(),
                        "Settings Button is not displayed")
        );
    }
}

