package SaveDesign;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SaveDesign {
	static WebDriver driver;
	String project_path = System.getProperty("user.dir");
	static BufferedWriter writer;
	
	@BeforeClass
    public void beforeClass() throws IOException {
		writer = Files.newBufferedWriter(Paths.get(project_path + "/XSLT_Reports/output/savedesign.txt"),StandardCharsets.UTF_8);
	}

	@BeforeMethod
	@Parameters("url")
	public void beforeTest(String url) throws InterruptedException, IOException {
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
		if (driver.findElement(By.xpath("//*[@id='SubscriptionModal']")).isDisplayed()) {
			driver.findElement(By.id("emailIdInModal")).clear();
			driver.findElement(By.id("emailIdInModal")).sendKeys("test_user@gmail.com");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='button postfix redbutton']")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='SubscribeModal']/a/img")));
			driver.findElement(By.xpath("//*[@id='SubscribeModal']/a/img")).click();
		}
	}
	
	@Test
	public void Interiors() throws InterruptedException, IOException {
		Navigate();
		AfterNavigation();
	}
	
	@Test
	public void Exteriors() throws InterruptedException, IOException {
		Navigate();
		Thread.sleep(10000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to door design")));
		driver.findElement(By.linkText("Proceed to door design")).click();
		Thread.sleep(10000);
		AfterNavigation();
	}
	
	public static void Navigate()
			throws InterruptedException, IOException {
		Thread.sleep(1000);
		driver.findElement(By.linkText("WARDROBES")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='kmBody']/div[9]/a/ul")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='myText']")).clear();
		driver.findElement(By.xpath("//*[@id='myText']")).sendKeys("100");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='heightOfCloset']")).clear();
		driver.findElement(By.xpath("//*[@id='heightOfCloset']")).sendKeys("80");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Enter dimensions to Proceed")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")));
		driver.findElement(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")).click();

		wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='kmBody']/div[11]/div[3]/div[2]/ul/li[2]/a/span")));
		
	}
	
	public static void AfterNavigation() throws InterruptedException, IOException{
		Thread.sleep(5000);
		driver.findElement(By.className("flaticon-diskette")).click();
		Signin();
		Savedesign();
		hoverClick("//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/div/ul[1]/li[3]");
		try{
    		driver.switchTo().alert().accept();
		}catch (Exception e) {}
		Thread.sleep(2000);
		String text = driver.findElement(By.className("myDesignName")).getText();
		if(!text.equals("test")){
			writer.write("Design is not saving");
			writer.newLine();
		}
		Thread.sleep(5000);
		driver.findElement(By.className("data_delete")).click();
		Thread.sleep(2000);
	}
	
	public static void Signin() throws InterruptedException, IOException{
		try{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='login-email-save']")).clear();
			driver.findElement(By.xpath("//*[@id='login-email-save']")).sendKeys("test_user@gmail.com");
			driver.findElement(By.xpath("//*[@id='login-password-save']")).clear();
			driver.findElement(By.xpath("//*[@id='login-password-save']")).sendKeys("test_user");
			driver.findElement(By.xpath("//*[@id='loginSave']/img")).click();
		}catch (Exception e) {
			writer.write("Exception in Signin");
			writer.newLine();
		}
	}
	
	public static void Savedesign() throws InterruptedException{
		Thread.sleep(7000);
		driver.findElement(By.xpath("//*[@id='designName']")).clear();
		driver.findElement(By.xpath("//*[@id='designName']")).sendKeys("test");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='saveInModal']/img")).click();
	}

	public static void hoverClick(String hover) throws InterruptedException{
		Thread.sleep(10000);
		Actions builder = new Actions(driver); 
		Actions hoverOverRegistrar = builder.moveToElement(driver.findElement(By.xpath(hover)));
		hoverOverRegistrar.perform();
		driver.findElement(By.linkText("My Designs")).click();
		Thread.sleep(1000);
	}
	
	@AfterMethod
	public void afterMethod() throws IOException {
		driver.quit();
		writer.close();
	}

}
