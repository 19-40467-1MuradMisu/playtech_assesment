import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Needed for @BeforeAll and @AfterAll to work with non-static methods
public class base{
    protected WebDriver driver;

    @BeforeAll
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

//    @Test
//    public void test1() {
//        driver.get("https://www.google.com");
//        //Assertions.assertEquals("Google", driver.getTitle(), "Page title does not match");
//    }

    @AfterAll
    public void teardown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(10000); // Wait for debugging (Optional)
            driver.quit();
        }
    }
}
