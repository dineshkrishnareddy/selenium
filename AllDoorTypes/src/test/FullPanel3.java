package test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FullPanel3 {
	WebDriver driver;
	String message,project_path = System.getProperty("user.dir");
	@BeforeMethod
	@Parameters("url")
	public void beforeTest(String url) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver", project_path+"/libs/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("test-type");
	    capabilities.setCapability("chrome.binary",project_path+"/libs/chromedriver.exe");
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	    driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(10000);
		if(driver.findElement(By.xpath("//*[@id='SubscriptionModal']")).isDisplayed()){
			driver.findElement(By.id("emailIdInModal")).clear();
			driver.findElement(By.id("emailIdInModal")).sendKeys("test@gmail.com");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='button postfix redbutton']")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='SubscribeModal']/a/img")));
			driver.findElement(By.xpath("//*[@id='SubscribeModal']/a/img")).click();
		}
	}
	
    @Test
    public void fullPanelDoors() throws InterruptedException {
    	Thread.sleep(2000);
		driver.findElement(By.linkText("WARDROBES")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='kmBody']/div[9]/a/ul")).click();

		Thread.sleep(1000);
		for (int width = 93 ; width < 121 ; width++){
			driver.findElement(By.xpath("//*[@id='myText']")).clear();
			driver.findElement(By.xpath("//*[@id='myText']")).sendKeys(String.valueOf(width));
			for (int height = 72 ; height < 85 ; height++){
				try{
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id='heightOfCloset']")).clear();
					driver.findElement(By.xpath("//*[@id='heightOfCloset']")).sendKeys(String.valueOf(height));
					Thread.sleep(1000);
					driver.findElement(By.linkText("Proceed to select door type")).click();
					Thread.sleep(2000);
					String path = "//*[@id='kmBody']/div[5]/div[2]/img";
					new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
					driver.findElement(By.xpath(path)).click();
					Thread.sleep(10000);
					new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to door design")));
					if(!driver.findElement(By.linkText("Proceed to door design")).isDisplayed()){
						message = message + "\nNot loading interiors for "+width+" " + height + " in full panel";
					}
					else {
						driver.findElement(By.linkText("Proceed to door design")).click();
						Thread.sleep(10000);
						new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to select accessories")));
						if(!driver.findElement(By.linkText("Proceed to select accessories")).isDisplayed()){
							message = message + "\nNot loading exteriors for "+width+" " + height + " in full panel";
						}
					}
					driver.findElement(By.linkText("DIMENSIONS")).click();
					try{  
						driver.switchTo().alert().accept(); 
					}catch (Exception Ex){}
					Thread.sleep(2000);
				}
				catch (Exception e) {
					message = message + "\nException in full panel for "+width+" " + height;
				}
			}
		}
    }
        
    @AfterMethod
    public void afterTest() throws FileNotFoundException, UnsupportedEncodingException{
    	PrintWriter writer = new PrintWriter(project_path + "/XSLT_Reports/output/errors/FullPanel3.txt", "UTF-8");
    	writer.println(message);
    	writer.close();
  		driver.close();
    }
}
