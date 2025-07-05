//Drag and Drop

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

      driver.get("https://jqueryui.com/droppable/");
      driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
      WebElement source = driver.findElement(By.id("draggable"));
      WebElement target = driver.findElement(By.id("droppable"));
      Actions actions = new Actions(driver);
      actions.dragAndDrop(source,target).perform();
      Thread.sleep(2);
       String droppedText = target.getText();
        if (droppedText.contains("Dropped")) {
            System.out.println("Drag and drop successful");
        } else {
            System.out.println("Drag and drop failed");
        }
      driver.quit();
             }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}