//Automation Testing Login to Practice Test Automation

package seleniumtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.*;

public class AppTest {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void loginFromCSV() throws IOException, InterruptedException {
     driver.get("https://practicetestautomation.com/practice-test-login/");
      WebElement username=driver.findElement(By.id("username"));
       username.sendKeys("student");
       WebElement password=driver.findElement(By.id("password"));
       password.sendKeys("Password123");
       WebElement submit=driver.findElement(By.id("submit"));
       submit.click();

       Thread.sleep(3000);
       WebElement heading=driver.findElement(By.tagName("h1"));
       String msg=heading.getText();
       Assert.assertTrue(msg.contains("Logged In Successfully"),"Login failed");
         
}

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
