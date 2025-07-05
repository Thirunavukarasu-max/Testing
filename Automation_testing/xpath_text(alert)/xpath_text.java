//Xpath button text

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
       driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click(); 
        Alert simpleAlert=driver.switchTo().alert();
        System.out.println(simpleAlert.getText());
        Thread.sleep(2000);
        simpleAlert.accept();
         driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click(); 
        Alert confirmAlert=driver.switchTo().alert();
        System.out.println(confirmAlert.getText());
        Thread.sleep(2000);
        confirmAlert.accept();//or confirmAlert.dismiss();
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click(); 
        Alert nextAlert=driver.switchTo().alert();
        System.out.println(nextAlert.getText());
        Thread.sleep(2000);
        nextAlert.sendKeys("Hi I am Tester");
        Thread.sleep(2000);
        nextAlert.accept();//or nextAlert.dismiss();
        System.out.println("All Alerts run successfully");
        Thread.sleep(2000);
       driver.quit();
      }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}