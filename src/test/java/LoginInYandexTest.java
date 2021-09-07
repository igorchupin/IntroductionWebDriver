import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class LoginInYandexTest {

    private final String username = "firstnamelastname1989";
    private final String password = "pTKJctHpbaj@t7M";
    private final String expectedURL = "https://mail.yandex.com/?uid=1459838850#tabs/relevant";
    private final String expectedName = "Inbox — Yandex.Mail";
    private WebDriver driver;

    @BeforeAll
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void afterTest() {
       driver.quit();
    }

    @Test
    @Tag("yandex_login_test")
    void yandexLoginTest() {
        driver.get("https://mail.yandex.com/");
        WebElement enterButton = driver.findElement
                (By.xpath("//*[@id=\"index-page-container\"]/div/div[2]/div/div/div[4]/a[2]"));
        enterButton.click();
        WebElement emailField = driver.findElement(By.id("passp-field-login"));
        emailField.sendKeys(username);
        WebElement loginButton = driver.findElement(By.id("passp:sign-in"));
        loginButton.click();
        WebElement passwordField = driver.findElement(By.id("passp-field-passwd"));
        passwordField.sendKeys(password);
        WebElement logInButton = driver.findElement(By.id("passp:sign-in"));
        logInButton.click();
        WebElement userIcon = driver.findElement(By.className("user-pic__image"));
        userIcon.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='legouser__menu-header']//span[@class='user-account__name']")));
        WebElement userAccountName = driver.findElement
                (By.xpath("//div[@class='legouser__menu-header']//span[@class='user-account__name']"));

        assertAll("Links",
                () -> assertTrue(driver.getTitle().contains(expectedName),
                        "Incorrect page was opened (Incorrect Title)"),
                () -> assertEquals(expectedURL, driver.getCurrentUrl(),
                        "Incorrect page was opened  (Incorrect URL)"),
                () -> assertTrue(driver.findElement(By.linkText("Change password")).isDisplayed(),
                        "Settings Button is not displayed"),
                () -> assertEquals(username + "\n" + username + "@yandex.com", userAccountName.getText(),
                        "Incorrect user account name is shown")
        );
    }
}

