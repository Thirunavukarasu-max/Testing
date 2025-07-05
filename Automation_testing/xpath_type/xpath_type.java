//Xpath type(text)

package seleniumtest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {

    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }
   @Test
    public void loginTest() throws InterruptedException {
       WebDriver driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.get("https://www.techlistic.com/p/selenium-practice-form.html");
        WebElement firstName=driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        firstName.clear();
        firstName.sendKeys("I am");
        Thread.sleep(2000);
        WebElement lastName=driver.findElement(By.xpath("(//input[@type='text'])[2]"));
        lastName.clear();
        lastName.sendKeys("Batman");
        Thread.sleep(2000);
       driver.quit();
      }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}