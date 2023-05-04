package scrapingFramework;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Web crawling and scraping class that scrapes poem data.
 * 
 * @author milanfilo
 *
 */
public class WebCrawler {

    public static void main(String[] args) {

        System.out.println("Scraping started...");

        // k represents the number of pages that we wish to scrape (exclusive)
        int k = 11;

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

            // Chrome options
            ChromeOptions options = new ChromeOptions();

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

//      String link = "http://www.famouspoetsandpoems.com/search/1/poems/";

            for (int i = 1; i < k; i++) {

                // Navigate to the website
                String url = "http://www.famouspoetsandpoems.com/search/" + Integer.toString(i) + "/poems/";
                driver.get(url);

                // Wait for the page to fully load

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        WebElement ad = driver.findElement(By.cssSelector(".g-xqpa52"));

                List<WebElement> poemLinks = driver.findElements(By.cssSelector("td td div a"));
                poemLinks.remove(0);

                ArrayList<String> targets = new ArrayList<String>();

                // collect targets which are href (HTML attributes) used to specify the URL of
                // the page that the link goes to
                // this is where we crawl
                for (WebElement link : poemLinks) {
                    targets.add(link.getAttribute("href"));
                }

                // do what is needed in the target i.e. scrape the data
                for (String target : targets) {
//                  System.out.println(target);
                    driver.get(target);

                    // get poem author
                    WebElement poemAuthor = driver.findElement(By.cssSelector("div[align='left'] span span"));
                    String poemAuthorString = poemAuthor.getText().replaceAll("by", "").trim();

                    // append poem's author to CSV file
                    csvWriter.append(poemAuthorString);

                    // new column
                    csvWriter.append(",");

                    // get poem title
                    WebElement poemTitle = driver.findElement(By.cssSelector("div > span"));
                    String[] poemTitleArray = poemTitle.getText().split(poemAuthor.getText());

                    // reformat poem title for csv file
                    String poemTitleString = poemTitleArray[0];
                    poemTitleString = "\"" + poemTitleString.replace("\"", "\"\"") + "\"";

                    // append poem title to CSV file
                    csvWriter.append(poemTitleString);

                    // new column
                    csvWriter.append(",");

//                    System.out.println(poemTitleString);

                    // poem poem text
                    WebElement poemContent = driver.findElement(By.cssSelector(
                            "body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > div:nth-child(5)"));
                    String poemContentString = poemContent.getText();
                    poemContentString = "\"" + poemContentString.replace("\"", "\"\"") + "\"\n";

                    // append poem's content to CSV file
                    csvWriter.append(poemContentString);

//                    System.out.println(poemContent.getText());
//
//                    System.out.println();
                }

                System.out.println(i + " page(s) out of " + k + " scraped...");
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

            System.out.println("Scraping complete!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
