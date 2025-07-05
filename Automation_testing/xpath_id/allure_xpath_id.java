package seleniumtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

@Epic("DemoQA Practice Form")
@Feature("Form Input via XPath")
public class AppTest {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @Test(description = "Form Fill Using XPath Selectors")
    @Severity(SeverityLevel.NORMAL)
    @Story("Filling form fields using XPath locators")
    @Description("Enter first name, last name, and select gender using XPath-based element location on the DemoQA practice form.")
    public void formInputTest() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstName']")));
        firstName.clear();
        firstName.sendKeys("I am");
        System.out.println("First name entered successfully");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[2]")));
        lastName.clear();
        lastName.sendKeys("Batman");
        System.out.println("Last name entered successfully");

        WebElement gender = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Male']")));
        gender.click();
        System.out.println("Gender selected successfully");

        Thread.sleep(2000); // Optional visual wait
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
