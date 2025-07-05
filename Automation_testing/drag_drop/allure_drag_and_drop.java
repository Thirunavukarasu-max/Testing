package seleniumtest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

@Epic("Practice Test Automation")
@Feature("UI Interactions")
public class AppTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(description = "Drag and Drop Test")
    @Severity(SeverityLevel.NORMAL)
    @Story("Perform drag and drop interaction")
    @Description("Drag element from source and drop it into target. Verify successful drop.")
    public void testDragAndDrop() throws InterruptedException {
        driver.get("https://jqueryui.com/droppable/");

        // Switch to the iframe containing draggable elements
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

        Thread.sleep(1000); // brief pause to ensure action is complete

        String droppedText = target.getText();
        assert droppedText.contains("Dropped!") : "Drag and drop failed";
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
