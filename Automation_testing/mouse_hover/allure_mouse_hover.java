package seleniumtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

import java.time.Duration;

@Epic("DemoQA Test Automation")
@Feature("Mouse Hover Menu")
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

    @Test(description = "Mouse hover over nested menu items using XPath")
    @Severity(SeverityLevel.NORMAL)
    @Story("Perform hover over multiple menu levels")
    @Description("Hovers over 'Main Item 2' > 'SUB SUB LIST »' > 'Sub Sub Item 2' on the DemoQA menu page")
    public void mouseHoverTest() throws InterruptedException {
        driver.get("https://demoqa.com/menu");

        // Hover on Main Item 2
        WebElement mainItem2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Main Item 2']")));
        js.executeScript("arguments[0].scrollIntoView(true);", mainItem2);
        actions.moveToElement(mainItem2).perform();
        Thread.sleep(1000);

        // Hover on SUB SUB LIST »
        WebElement subSubList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='SUB SUB LIST »']")));
        actions.moveToElement(subSubList).perform();
        Thread.sleep(1000);

        // Hover on Sub Sub Item 2
        WebElement subSubItem2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Sub Sub Item 2']")));
        actions.moveToElement(subSubItem2).perform();
        Thread.sleep(1000);

        System.out.println("Hovering completed successfully.");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
