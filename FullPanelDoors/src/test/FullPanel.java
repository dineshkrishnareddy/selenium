package test;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FullPanel {
	WebDriver driver;
	String message,project_path = System.getProperty("user.dir");
	static BufferedWriter writer;
	
	
	@BeforeMethod
	@Parameters("url")
	public void beforeTest(String url) throws InterruptedException, IOException{
		writer = Files.newBufferedWriter(Paths.get(project_path + "/XSLT_Reports/output/fullpanel.txt"),StandardCharsets.UTF_8);
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
    public void fullPanelDoors() throws InterruptedException, IOException {
    	Thread.sleep(2000);
		driver.findElement(By.linkText("WARDROBES")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='kmBody']/div[9]/a/ul")).click();

		Thread.sleep(1000);
		for (int width = 32 ; width < 121 ; width++){
			for (int height = 72 ; height < 85 ; height++){
				try{
					driver.findElement(By.xpath("//*[@id='myText']")).clear();
					driver.findElement(By.xpath("//*[@id='myText']")).sendKeys(String.valueOf(width));
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id='heightOfCloset']")).clear();
					driver.findElement(By.xpath("//*[@id='heightOfCloset']")).sendKeys(String.valueOf(height));
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("a.button.Proceed")).click();
					Thread.sleep(2000);
					String path = "//*[@id='step2DoorTypeTraditional']";
					new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
					driver.findElement(By.xpath(path)).click();
					Thread.sleep(10000);
					new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed.right")));
					if(!driver.findElement(By.cssSelector("a.button.Proceed.right")).isDisplayed()){
						writer.write("Not loading interiors for "+width+" " + height + " in full panel");
						writer.newLine();
					}
					else {
						driver.findElement(By.cssSelector("a.button.Proceed.right")).click();
						Thread.sleep(10000);
						new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed")));
						if(!driver.findElement(By.cssSelector("a.button.Proceed")).isDisplayed()){
							writer.write("Not loading exteriors for "+width+" " + height + " in full panel");
							writer.newLine();
						}
					}
				}
				catch (Exception e) {
					writer.write("Exception in full panel for "+width+" " + height);
					writer.newLine();
				}
				driver.findElement(By.linkText("DIMENSIONS")).click();
				try{  
					driver.switchTo().alert().accept(); 
				}catch (Exception Ex){}
				Thread.sleep(2000);
			}
		}
    }
        
    @AfterMethod
    public void afterTest() throws FileNotFoundException, UnsupportedEncodingException{
  		driver.quit();
    }
    
    @AfterClass
    public void afterClass() throws IOException{
    	writer.close();
    }
}
