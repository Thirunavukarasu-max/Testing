package seleniumtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

@Epic("Techlistic Practice Form")
@Feature("Text Input via XPath")
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

    @Test(description = "Enter first and last name using XPath")
    @Severity(SeverityLevel.NORMAL)
    @Story("Enter text into input fields using XPath type='text'")
    @Description("Navigates to Techlistic Practice Form and fills in first and last name using XPath with input type.")
    public void fillNameFieldsUsingXPath() throws InterruptedException {
        driver.get("https://www.techlistic.com/p/selenium-practice-form.html");

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[1]")));
        firstName.clear();
        firstName.sendKeys("I am");
        Thread.sleep(1000);

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@type='text'])[2]")));
        lastName.clear();
        lastName.sendKeys("Batman");
        Thread.sleep(1000);

        System.out.println("First and Last Name entered successfully.");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
