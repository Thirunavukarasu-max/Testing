package seleniumtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

@Epic("Dodo QuantumUnique")
@Feature("Menu Redirection Validation")
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

    @Test(description = "Verify redirection of main menu items on Dodo QuantumUnique")
    @Severity(SeverityLevel.NORMAL)
    @Story("User clicks main menu items and verifies correct redirection")
    @Description("Checks each top navigation menu redirects to the appropriate URL segment")
    public void verifyMenuRedirections() {
        String baseUrl = "https://dodo.quantumnique.tech/";

        String[][] menuItems = {
            {"Assessments", "a[href*='assessments']", "assessments"},
            {"Courses", "a[href*='courses']", "courses"},
            {"Practice", "a[href*='practice']", "practice"},
            {"LSRW", "a[href*='lsrw']", "lsrw"},
            {"Blog", "a[href*='blog']", "blog"}
        };

        for (String[] item : menuItems) {
            String name = item[0];
            String cssSelector = item[1];
            String expectedUrlPart = item[2];

            driver.get(baseUrl);

            WebElement menuLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelector)));
            menuLink.click();

            boolean redirected = wait.until(ExpectedConditions.urlContains(expectedUrlPart));
            Assert.assertTrue(redirected, "Redirection failed for menu item: " + name);

            System.out.println("✅ " + name + " redirected correctly");
        }
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
