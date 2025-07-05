package seleniumtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

import java.time.Duration;

@Epic("Practice Test Automation")
@Feature("Menu Navigation")
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(description = "Verify redirection from menu items")
    @Severity(SeverityLevel.NORMAL)
    @Story("Navigation through menu links")
    @Description("Click menu items and verify correct page redirection based on URL")
    public void pageRedirectionTest() {
        String baseUrl = "https://practicetestautomation.com/";

        String[][] menuItems = {
            {"Blog", "a[href*='blog']", "blog"},
            {"Contact", "a[href*='contact']", "contact"}
        };

        for (String[] item : menuItems) {
            String name = item[0];
            String cssSelector = item[1];
            String expectedUrlPart = item[2];

            driver.get(baseUrl);

            WebElement menuElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
            menuElement.click();

            boolean redirected = wait.until(ExpectedConditions.urlContains(expectedUrlPart));

            Assert.assertTrue(redirected,
                "Redirection failed for menu item: " + name + 
                ". Current URL: " + driver.getCurrentUrl());

            System.out.println("Redirected correctly after clicking " + name);
        }
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
