import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ByVariablesTest {

    public static void main(String[] args) {

        By className = new By.ByClassName("gNO89b");
        By cssSelector = new By.ByCssSelector("body > div.L3eUgb > div.o3j99.LLD4me.yr19Zb.LS8OJ > div > img");
        By id = new By.ById("tophf");
        By name = new By.ByName("q");
        By linkText = new By.ByLinkText("русский");
        By partialLinkText = new By.ByPartialLinkText("ish");
        By tagName = new By.ByTagName("div");
        By xpath = new By.ByXPath("//*[@id=\"gb\"]/div/div[1]/div/div[1]/a");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");


        WebElement tag = driver.findElement(tagName);
        System.out.println(tag.getSize());

        WebElement classname = driver.findElement(className);
        System.out.println(classname.getTagName());

        WebElement css = driver.findElement(cssSelector);
        System.out.println(css.getTagName());

        WebElement name1 = driver.findElement(name);
        System.out.println(name1.getTagName());

        WebElement byID = driver.findElement(id);
        System.out.println(byID.getTagName() + byID.getText());

        WebElement linkText2 = driver.findElement(linkText);
        System.out.println(linkText2.getTagName() + linkText2.getText());

        WebElement partialLinkText1 = driver.findElement(partialLinkText);
        System.out.println(partialLinkText1.getTagName() +" " + partialLinkText1.getText());

        WebElement xpath1 = driver.findElement(xpath);
        System.out.println(xpath1.getTagName());

        driver.close();
    }
}
