import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.HashSet;


public class all_test extends base {



    public void clickAtCoordinates(int x, int y) {
        Actions actions = new Actions(driver);
        actions.moveByOffset(x, y).click().perform();
    }

    @Test
    @DisplayName("Locations")
    public void all_test() throws IOException, InterruptedException {

        driver.get("https://www.playtechpeople.com"); // open Playtech website

        // handling the cookies bot
        try {
            driver.findElement(By.xpath("//*[@id=\"CybotCookiebotDialog\"]")).click();
            driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[1]/div/div[2]/button[4]")).click();
        } catch (Exception e) {
            System.out.println("Cookie popup not found or already handled.");
        }

        // Clicking on the "Locations"
        WebElement locations = driver.findElement(By.xpath("(//a[@href='#'][normalize-space()='Locations'])[1]"));



        locations.click();

        // Listing all locations
        WebElement locationsAll = driver.findElement(By.xpath("(//a[normalize-space()='View all locations'])[1]"));
        locationsAll.click();

        // Find all location elements
        List<WebElement> locationElements = driver.findElements(By.xpath("/html/body/div[1]/section/div[1]/div[2]//div/a/div[2]/div[1]/h4"));

        // Print the total count of locations
        System.out.println("Total locations found: " + locationElements.size());

        // Save the locations to a file
        try (FileWriter writer = new FileWriter("location.txt", true)) {
            writer.write("Total locations found: " + locationElements.size() + "\n\n");
            for (WebElement location : locationElements) {
                String locationName = location.getText().trim();
                System.out.println(locationName);
                writer.write(locationName + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOExceptions
        }


        // Clicking on "Life at Playtech"


        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[1]/div[1]/div[2]/nav[1]/ul[1]/li[2]/a[1]")).click();
        driver.findElement(By.xpath("(//li[@id='menu-item-47'])[1]")).click();
        Thread.sleep(3000);  // Wait for 3 seconds to allow page load

        // Scroll down to the casino suite
        WebElement casinoSuite = driver.findElement(By.xpath("(//div[@class='col-md-12'])[4]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", casinoSuite);

        // Wait for the casino suite to load (better than just using Thread.sleep)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(casinoSuite));

        // Extract the suite name
        WebElement casino = driver.findElement(By.xpath(".//div[2]/div[1]/div/h4"));
        String suiteName = casino.getText().trim();

        // Check if the suite name is "Casino"
        if (suiteName.equalsIgnoreCase("Casino")) {
            // Find the full description for the "casino" suite
            WebElement descriptionElement = driver.findElement(By.xpath("(//p[contains(text(),'The world’s largest and most diverse online casino')])[1]"));
            String description = descriptionElement.getText().trim();

            // Print the description to the console
            System.out.println("Suite Name: " + suiteName);
            System.out.println("Description: " + description);

            // Save the description to a text file
            try (FileWriter writer = new FileWriter("suite_description.txt")) {
                writer.write("Suite Name: " + suiteName + "\n");
                writer.write("Description: " + description + "\n");
            } catch (IOException e) {
                e.printStackTrace(); // Handle file writing exceptions
            }
        } else {
            System.out.println("The suite name is not 'casino'. No description found.");
        }


        //All jobs link printing


        driver.findElement(By.xpath("(//div[@class='right-header'])[1]")).click();
        WebElement select_team = driver.findElement(By.xpath("(//div[@class='search-column'])[1]"));
        select_team.click();
        Thread.sleep(3000);
        WebElement select_option = driver.findElement(By.xpath("(//div[@class='search-column__item teams-column__item'])[1]"));
        select_option.click(); // Selecting all team from dropdown
        Thread.sleep(3000);


        WebElement select_location = driver.findElement(By.xpath("(//div[@class='search-column'])[2]"));
        select_location.click();
        Thread.sleep(3000);
        WebElement select_estonia = driver.findElement(By.xpath("(//div[@class='search-column__item locations-column__item'])[6]"));
        select_estonia.click(); // Select Estonia from dropdown
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//input[@placeholder='Search'])[1]")).click();

        Thread.sleep(3000);

       List<WebElement> jobItems = driver.findElements(By.xpath("(//div[@class='container'])[5]//a[@class='job-item']"));

       driver.findElement(By.xpath("(//a[@class='job-item'])[24]")).click();

        String currentTab = driver.getWindowHandle();
                for (String windowHandle : driver.getWindowHandles()) {
                    if (!windowHandle.equals(currentTab)) {
                        driver.switchTo().window(windowHandle);
                        break;
                    }
                }


//        WebElement jobLocationElement = driver.findElement(By.cssSelector("c-spl-job-location__place"));
//                String jobLocation = jobLocationElement.getText().trim();
//        System.out.println("Job Location: " + jobLocation);

        String newTabUrl = driver.getCurrentUrl();
        System.out.println("URL of the new tab: " + newTabUrl);

        try (FileWriter writer = new FileWriter("job_urls.txt", true)) {
            writer.write(newTabUrl + "\n"); // Save the URL followed by a new line
        } catch (IOException e) {
            e.printStackTrace(); // Handle potential IOExceptions
        }

        driver.close();
        driver.switchTo().window(currentTab);


        driver.findElement(By.xpath("(//p[@class='arrow-link'][normalize-space()='Apply'])[25]")).click();

        String currentTab1 = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentTab)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }



        String newTabUrl1 = driver.getCurrentUrl();
        System.out.println("URL of the new tab: " + newTabUrl1);

        try (FileWriter writer = new FileWriter("job_urls.txt", true)) {
            writer.write(newTabUrl1 + "\n"); // Save the URL followed by a new line
        } catch (IOException e) {
            e.printStackTrace(); // Handle potential IOExceptions
        }

        driver.close();
        driver.switchTo().window(currentTab);






        // Set to track locations that we have already printed
//        Set<String> printedLocations = new HashSet<>();
//
//        // Iterate through each job item to extract the job link and location
//        for (WebElement jobItem : jobItems) {
//            try {
//                // Wait for the job link element to be visible
//                jobItem.click();
//                driver.findElement(By.className("arrow-link")).click();
//
//                // Switch to the new tab
//                String currentTab = driver.getWindowHandle();
//                for (String windowHandle : driver.getWindowHandles()) {
//                    if (!windowHandle.equals(currentTab)) {
//                        driver.switchTo().window(windowHandle);
//                        break;
//                    }
//                }
//
//                // Find the location within the new tab
//                WebElement jobLocationElement = driver.findElement(By.className("c-spl-job-location__place"));
//                String jobLocation = jobLocationElement.getText().trim();
//
//                // Check if the location is Tartu and if it has been printed already
//                if (jobLocation.equalsIgnoreCase("Tartu") && !printedLocations.contains(jobLocation)) {
//                  //  System.out.println("Job link for Tartu: " + jobUrl);
//                    printedLocations.add(jobLocation); // Mark Tartu as printed
//                }
//
//                // Similarly, check for Tallinn and print the link if not already printed
//                if (jobLocation.equalsIgnoreCase("Tallinn") && !printedLocations.contains(jobLocation)) {
//                    //System.out.println("Job link for Tallinn: " + jobUrl);
//                    printedLocations.add(jobLocation); // Mark Tallinn as printed
//                }
//
//                // Close the current tab and switch back to the original window
//                driver.close();
//                driver.switchTo().window(currentTab); // Switch back to the original tab
//
//            } catch (Exception e) {
//                System.out.println("Error extracting job details: " + e.getMessage());
//            }
//        }


    }
}
