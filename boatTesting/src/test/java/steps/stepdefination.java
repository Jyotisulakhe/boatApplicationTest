package steps;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class stepdefination {
static WebDriver driver;// This is global variable for driver

   @ParameterType("chrome|firefox")
   public String browser(String browser) {
	   return browser;
   }
   
   public static void capture(int number) throws IOException {
		 
		 TakesScreenshot ts=(TakesScreenshot)driver;
		 File file1=ts.getScreenshotAs(OutputType.FILE);

		 File file2=new File("C:\\Users\\Asus\\OneDrive\\Pictures\\Screenshotas"+number+".png");
		 FileUtils.copyFile(file1, file2);
		
	}
	@Given("user open browser and open boat website {browser}")
	public void user_open_browser_and_open_boat_website(String browser) throws InterruptedException {
		
		ChromeOptions chromeOptions = new ChromeOptions(); // I created ChromeOptions object for disabling notifications
     chromeOptions.addArguments("--disable-notifications");

		
		if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
     
	        
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
	        driver.manage().window().maximize();
	        driver.get("https://www.boat-lifestyle.com/");
	        
	   
	}

	@When("select True Wireless Earbuds from catagory")
	public void select_true_wireless_earbuds_from_catagory() {
		
		driver.findElement(By.linkText("Categories")).click();
		driver.findElement(By.xpath("(//img[@alt='Wireless Earbuds'])[1]")).click();

	}

	@When("click on filter, click on price, select minimun and maximum price")
	public void click_on_filter_click_on_price_select_minimun_and_maximum_price() throws InterruptedException, IOException {
		
		
		 
		driver.findElement(By.xpath("//button[@class='product-facet__meta-bar-item product-facet__meta-bar-item--filter hidden-pocket']")).click();// for filter

		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));// I am using Explicit wait for finding Price button
	 WebElement priceFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='facet-filters-form']/div/div[3]/button"))); 
		//try and catch block 
         try {
             // Attempt to click the price filter
             priceFilter.click();
           
     		
         } catch (ElementClickInterceptedException e) {
             // If intercepted, use JavaScript to click
        	 JavascriptExecutor js = (JavascriptExecutor) driver;
             js.executeScript("arguments[0].click();", priceFilter);
             
         	capture(1);//for capturing screenshot
         }
	
         driver.findElement(By.xpath("//input[@name='filter.v.price.gte']")).sendKeys("500");
			driver.findElement(By.xpath("//input[@name='filter.v.price.lte']")).sendKeys("1000");
		
	}

	
	@When("click on playback, select {int}-{int} hrs")
	public void click_on_playback_select_hrs(Integer int1, Integer int2) throws InterruptedException, IOException {
		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    boolean success = false;
	    int attempts = 0;
	    while (attempts < 3) {
	        try {//using try and catch block checking playback button is visible or not , if visible then click on it
	            WebElement playbackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Playback')]")));
	            playbackButton.click();
	            success = true;
	            System.out.println("Clicked on the 'Playback' button.");
	            break;
	        } catch (StaleElementReferenceException e) {
	            System.out.println("StaleElementReferenceException caught. Retrying...");
	            capture(1);//for capturing screenshot
	        } catch (ElementClickInterceptedException e) {
	            System.out.println("ElementClickInterceptedException caught. Using JavaScript to click.");
	            WebElement playbackButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Playback')]")));
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].click();", playbackButton);
	            success = true;
	            break;
	        }
	        attempts++;
	    }
	    if (!success) {
	        System.out.println("Failed to click on the 'Playback' button after multiple attempts.");
	    }

	driver.findElement(By.xpath("//*[@id=\"filter.p.m.custom.playback-2\"]")).click();
	}

	@Then("click on Apply filter, filter should apply")
	public void click_on_apply_filter_filter_should_apply() throws InterruptedException {
		
		driver.findElement(By.xpath( "//button[@class='button button--primary button--full']")).click();
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	
	//driver.close();
		
//		String palybactTest = driver.findElement(By.xpath("//*[@id=\"facet-main\"]/div[2]/div[2]")).getText();
//		
//		org.junit.assertEquals(palybactTest, "Playback: 50-75 Hrs");
//		Assert.assertEquals(palybactTest, "Playback: 50-75 Hrs");
		
	}


}
