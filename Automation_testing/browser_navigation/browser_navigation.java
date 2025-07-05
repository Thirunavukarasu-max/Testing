//Redirectio in dodo QuantumUnique

package seleniumtest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {

    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }
   @Test
    public void loginTest() throws InterruptedException {
      driver.get("https://dodo.quantumnique.tech/");
        Thread.sleep(2000);
        String[][] menuItems = {
            {"assessments", "a[href*='assessments']", "assessments"},
            {"courses", "a[href*='courses']", "courses"},
            {"practice", "a[href*='practice']", "practice"},
            {"lsrw", "a[href*='lsrw']", "lsrw"},
            {"blog", "a[href*='blog']", "blog"},
        };

        for (String[] item : menuItems) {
            String name = item[0];
            String cssSelector = item[1];
            String expectedUrlPart = item[2];

           
                driver.findElement(By.cssSelector(cssSelector)).click();
                Thread.sleep(2000);

                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.contains(expectedUrlPart)) {
                    System.out.println("redirects correctly");
                } else {
                    System.out.println("did NOT redirect correctly");
                }

                
                driver.get("https://dodo.quantumnique.tech/");
                Thread.sleep(2000);
            }


    }
    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}