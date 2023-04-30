//Git commit

package scrapingFramework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebCrawler {

    public static void main(String[] args) {
        // csv
        String csvFilePath = "poem_data.csv";

        try {
            FileWriter csvWriter = new FileWriter(csvFilePath);
            csvWriter.append("Author");
            csvWriter.append(",");
            csvWriter.append("Title");
            csvWriter.append(",");
            csvWriter.append("Text");
            csvWriter.append("\n");

            // Set the path to the ChromeDriver executable
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

            ChromeOptions options = new ChromeOptions();
            
            
            //Chrome options

            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--disable-extensions");
//        options.addArguments("--disable-infobars");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("load-extension=/Users/milanfilo/Library/Application Support/Google/Chrome/Profile 2/ghbmnnjooekpmoecnnnilnnbdlolhkhi");
//        options.addArguments("start-maximized");

            // Launch Chrome browser

            WebDriver driver = new ChromeDriver(options);

            // defining browser and adding the “ — headless” argument

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//      String link = "http://www.famouspoetsandpoems.com/search/1/poems/";

            for (int i = 1; i < 10; i++) {

                // Navigate to the website

                String url = "http://www.famouspoetsandpoems.com/search/" + Integer.toString(i) + "/poems/";
                driver.get(url);

                // Wait for the page to fully load

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        WebElement ad = driver.findElement(By.cssSelector(".g-xqpa52"));

                // Find the dynamic table element

                // Find all the poem titles and URLs in the dynamic table

//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("td td div a")));

                List<WebElement> poemLinks = driver.findElements(By.cssSelector("td td div a"));
                poemLinks.remove(0);

                ArrayList<String> targets = new ArrayList<String>();
                // collect targets locations
                for (WebElement link : poemLinks) {
                    targets.add(link.getAttribute("href"));
                }

                for (String target : targets) {
                    System.out.println(target);
                }

                for (String target : targets) {
                    driver.get(target);
                    // do what is needed in the target

                    // get poem author
                    WebElement poemAuthor = driver.findElement(By.cssSelector("div[align='left'] span span"));

                    String poemAuthorString = poemAuthor.getText().replaceAll("by", "").trim();

                    // append to CSV file
                    csvWriter.append(poemAuthorString);
                    csvWriter.append(",");

                    // get poem title
                    WebElement poemTitle = driver.findElement(By.cssSelector("div > span"));
                    String[] poemTitleArray = poemTitle.getText().split(poemAuthor.getText());

                    String poemTitleString = poemTitleArray[0];
                    poemTitleString = "\"" + poemTitleString.replace("\"", "\"\"") + "\"";

                    // append to CSV file
                    csvWriter.append(poemTitleString);

                    csvWriter.append(",");

                    System.out.println(poemTitleString);

                    // poem poem text
                    WebElement poemContent = driver.findElement(By.cssSelector(
                            "body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > div:nth-child(5)"));
                    String poemContentString = poemContent.getText();
                    poemContentString = "\"" + poemContentString.replace("\"", "\"\"") + "\"\n";

                    // append to CSV file
                    csvWriter.append(poemContentString);

                    System.out.println(poemContent.getText());

                    System.out.println();
                }
            }

            // Loop through each poem and print its content
//        for (WebElement poemLink : poemLinks) {
//            String poemTitle = poemLink.getText();
//            
//            System.out.println(poemTitle);
//
//            // Click on the poem link to navigate to its page
//            poemLink.click();

//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("your-css-selector")));

            // Find the author
//            WebElement poemAuthor = driver.findElement(By.cssSelector("div td span"));
//            System.out.println(poemAuthor.getText());

            // Find the actual poem
//            WebElement poemContent = driver.findElement(By.cssSelector("div:nth-child(5)"));
//            System.out.println(poemContent.getText());

            // Navigate back to the poems page
//            driver.navigate().back();
//        }

//        driver.manage().window().maximize();

//
//        // Print out each title
//        for (WebElement title : titles) {
//            System.out.println(title.getText());
//        }
//
            // Close the browser
            driver.quit();

            csvWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // create CSV file

//        FileWriter csvWriter = new FileWriter(job_Search.replace(" ","")+".csv");
//        csvWriter.append("JobTitle");
//        csvWriter.append(",");
//        csvWriter.append("JobCompanyName");
//        csvWriter.append(",");
//        csvWriter.append("JobLocation");
//        csvWriter.append(",");
//        csvWriter.append(iJob.toString());
//        csvWriter.append("\n");

    }

}
