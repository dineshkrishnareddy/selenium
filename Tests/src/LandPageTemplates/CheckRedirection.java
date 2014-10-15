package LandPageTemplates;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckRedirection {
	WebDriver driver;
	String project_path = System.getProperty("user.dir");
	static BufferedWriter writer;

	@BeforeMethod
	@Parameters("url")
	public void beforeTest(String url) throws InterruptedException, IOException {		
		writer = Files.newBufferedWriter(Paths.get(project_path + "/XSLT_Reports/output/checktemplates.txt"),StandardCharsets.UTF_8);
		System.setProperty("webdriver.chrome.driver", project_path
				+ "/libs/chromedriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		capabilities.setCapability("chrome.binary", project_path
				+ "/libs/chromedriver.exe");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(10000);
		if (driver.findElement(By.xpath("//*[@id='SubscriptionModal']"))
				.isDisplayed()) {
			driver.findElement(By.id("emailIdInModal")).clear();
			driver.findElement(By.id("emailIdInModal")).sendKeys(
					"test_user@gmail.com");
			Thread.sleep(1000);
			driver.findElement(
					By.xpath("//*[@class='button postfix redbutton']")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By
							.xpath("//*[@id='SubscribeModal']/a/img")));
			driver.findElement(By.xpath("//*[@id='SubscribeModal']/a/img"))
					.click();
		}
	}
	
	@Test
	public void Checkredirection() throws InterruptedException, IOException {
		List<WebElement> activeListElements = driver.findElements(By.className("gridImageHeight"));
		
		for(int i=1;i<=activeListElements.size();i++){
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,900)", ""); 
            Thread.sleep(2000);
            String path = "//*[@id='kmBody']/div[10]/div/ul/li["+i+"]/div/section[1]/a[1]/img";
			driver.findElement(By.xpath(path)).click();
			String title = driver.getCurrentUrl();
			if(!title.contains("http://kustommade.tk:9980/templates/?")){
				writer.write("breaking at " + i);
				writer.newLine();
			}
			Thread.sleep(2000);
			driver.navigate().back();
			Thread.sleep(2000);
		}
	}

	@AfterMethod
	public void afterMethod() throws IOException {
		driver.quit();
		writer.close();
	}

}
