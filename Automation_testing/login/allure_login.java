package seleniumtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

@Epic("Practice Test Automation")
@Feature("Login Functionality")
public class AppTest {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    class LoginPage {
        WebDriver driver;

        By username = By.id("username");
        By password = By.id("password");
        By loginBtn = By.id("submit");

        public LoginPage(WebDriver driver) {
            this.driver = driver;
        }

        public void login(String user, String pass) {
            driver.findElement(username).sendKeys(user);
            driver.findElement(password).sendKeys(pass);
            driver.findElement(loginBtn).click();
        }
    }

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    @Test(description = "Login using hardcoded values (simulating CSV input)")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login using valid credentials")
    @Description("Performs login using hardcoded user data and verifies successful login message")
    public void loginFromCSV() throws InterruptedException {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("student", "Password123");

        Thread.sleep(2000); // optionally replace with wait

        WebElement heading = driver.findElement(By.tagName("h1"));
        String msg = heading.getText();
        Assert.assertTrue(msg.contains("Logged In Successfully"), "Login failed");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
