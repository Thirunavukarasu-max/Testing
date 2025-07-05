package seleniumtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

@Epic("HerokuApp Alerts")
@Feature("JavaScript Alert Handling")
public class AppTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @Test(description = "Handle JavaScript alerts using XPath buttons")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Handle simple alert, confirm alert, and prompt alert using XPath button text")
    @Description("Tests all three JavaScript alerts on HerokuApp using XPath selectors and handles them properly.")
    public void handleJavaScriptAlerts() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // Simple Alert
        WebElement jsAlertBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Click for JS Alert']")));
        jsAlertBtn.click();
        Alert simpleAlert = driver.switchTo().alert();
        System.out.println("Simple Alert Text: " + simpleAlert.getText());
        Thread.sleep(1000);
        simpleAlert.accept();

        // Confirm Alert
        WebElement jsConfirmBtn = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        jsConfirmBtn.click();
        Alert confirmAlert = driver.switchTo().alert();
        System.out.println("Confirm Alert Text: " + confirmAlert.getText());
        Thread.sleep(1000);
        confirmAlert.accept(); // Or confirmAlert.dismiss();

        // Prompt Alert
        WebElement jsPromptBtn = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        jsPromptBtn.click();
        Alert promptAlert = driver.switchTo().alert();
        System.out.println("Prompt Alert Text: " + promptAlert.getText());
        Thread.sleep(1000);
        promptAlert.sendKeys("Hi I am Tester");
        Thread.sleep(1000);
        promptAlert.accept(); // Or promptAlert.dismiss();

        System.out.println("All alerts handled successfully.");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
