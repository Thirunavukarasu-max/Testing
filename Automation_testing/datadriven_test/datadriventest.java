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
        driver.get("https://demoqa.com/login");

        try (BufferedReader reader = new BufferedReader(new FileReader("data.csv"))) {
            String headerLine = reader.readLine(); // skip header
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String username = data[0];
                String password = data[1];

                WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
                js.executeScript("arguments[0].scrollIntoView(true);", userInput);
                actions.moveToElement(userInput).perform();
                userInput.clear();
                userInput.sendKeys(username);

                WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
                js.executeScript("arguments[0].scrollIntoView(true);", passInput);
                actions.moveToElement(passInput).perform();
                passInput.clear();
                passInput.sendKeys(password);

                WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
                js.executeScript("arguments[0].scrollIntoView(true);", loginButton);
                loginButton.click();

                Thread.sleep(2000);

                driver.get("https://demoqa.com/login");
            }
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
