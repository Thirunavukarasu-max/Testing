package seleniumtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

@Epic("DemoQA Test Automation")
@Feature("Login with CSV Data")
public class AppTest {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    @Test(description = "Login to DemoQA using data from CSV")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Attempt login with multiple credentials from CSV")
    @Description("Reads a CSV file containing multiple username/password combinations and attempts to log in for each")
    public void loginFromCSV() throws IOException, InterruptedException {
        driver.get("https://demoqa.com/login");

        try (BufferedReader reader = new BufferedReader(new FileReader("data.csv"))) {
            reader.readLine(); // Skip header
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String username = data[0];
                String password = data[1];

                WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
                js.executeScript("arguments[0].scrollIntoView(true);", userInput);
                actions.moveToElement(userInput).click().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE).sendKeys(username).perform();

                WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
                js.executeScript("arguments[0].scrollIntoView(true);", passInput);
                actions.moveToElement(passInput).click().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE).sendKeys(password).perform();

                WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
                js.executeScript("arguments[0].click();", loginButton);

                Thread.sleep(2000); // For demo purposes only (replace with better verification if needed)

                driver.get("https://demoqa.com/login"); // Reset state before next loop
            }
        }
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
