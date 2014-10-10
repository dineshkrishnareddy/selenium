package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Subscribe {
	WebDriver driver;
	static String message = "";
	String project_path = System.getProperty("user.dir");

	@BeforeMethod
	@Parameters("url")
	public void beforeTest(String url) throws InterruptedException {
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

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

	@Test
	@Parameters("url")
	public void subscribe(String url) throws InterruptedException {
		subscribeBottom(driver, "test_user@gmail.com", url);
		forgotPassword(driver, "test_user@gmail.com", url);
	}

	public static void subscribeBottom(WebDriver driver, String name, String url)
			throws InterruptedException {
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//*[@id='emailId']")));
		driver.findElement(By.xpath("//*[@id='emailId']")).clear();
		driver.findElement(By.xpath("//*[@id='emailId']")).sendKeys(name);
		Thread.sleep(1000);
		driver.findElement(By.linkText("Sign up")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.elementToBeClickable(By
						.xpath("//*[@id='SubscribeModal']/a/img")));
		driver.findElement(By.xpath("//*[@id='SubscribeModal']/a/img")).click();
	}

	public static void forgotPassword(WebDriver driver, String name, String url)
			throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='loginUser']/span")).click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//*[@id='AccountModel']/div[3]/div[1]/div/ul[2]/li[1]/a"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='email']")).clear();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(name);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='emailSubmit']")).click();
		Thread.sleep(5000);
		org.testng.Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id='cs']")).getText(),
				"Email Sent. Login to your mail to reset your password",
				"Forgot password not displayed");
	}

}
