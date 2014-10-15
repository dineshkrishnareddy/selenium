package Signup;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LandingPage {
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
	public void Landingpage(String url) throws InterruptedException,
			IOException {
		navigateLandingPage(driver);
		manualSignup(driver, "test_user", "LandingPage");
		manualSignin(driver, "test_user", "LandingPage", url);
		signOut(driver, url, "LandingPage");
		driver.get(url);
		navigateLandingPage(driver);
		signinFB(driver, "LandingPage", url);
		signOut(driver, url, "LandingPage");
		driver.get(url);
		navigateLandingPage(driver);
		signinGoogle(driver, "LandingPage", url);
		signOut(driver, url, "LandingPage");
		driver.get(url);
	}

	public static void navigateLandingPage(WebDriver driver)
			throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='loginUser']/span")).click();
	}

	public static void manualSignup(WebDriver driver, String name, String page)
			throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='signup-name']")).clear();
		driver.findElement(By.xpath("//*[@id='signup-name']")).sendKeys(name);
		driver.findElement(By.xpath("//*[@id='signup-email']")).clear();
		driver.findElement(By.xpath("//*[@id='signup-email']")).sendKeys(
				name + "@gmail.com");
		driver.findElement(By.xpath("//*[@id='signup-phoneNumber']")).clear();
		driver.findElement(By.xpath("//*[@id='signup-phoneNumber']")).sendKeys(
				"9999999999");
		driver.findElement(By.xpath("//*[@id='signup-password']")).clear();
		driver.findElement(By.xpath("//*[@id='signup-password']")).sendKeys(
				name);
		driver.findElement(By.xpath("//*[@id='signup']/span/img")).click();
		Thread.sleep(1000);
	}

	public static void manualSignin(WebDriver driver, String name, String page,
			String url) throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='login-email']")).clear();
		driver.findElement(By.xpath("//*[@id='login-email']")).sendKeys(
				"test_user@gmail.com");
		driver.findElement(By.xpath("//*[@id='login-password']")).clear();
		driver.findElement(By.xpath("//*[@id='login-password']")).sendKeys(
				"test_user");
		driver.findElement(By.xpath("//*[@id='login']/span/img")).click();
		signOut(driver, url, page);
	}

	public static void signOut(WebDriver driver, String url, String page)
			throws InterruptedException{
		Thread.sleep(2000);
		hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/ul");
		driver.get(url);
	}

	public static void signinFB(WebDriver driver, String page, String url)
			throws InterruptedException{
		Thread.sleep(2000);
		String winHandleBefore = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@id='connectToFB']/a/img")).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='email']")).clear();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(
				"10pa1a0590@gmail.com");
		driver.findElement(By.xpath("//*[@id='pass']")).clear();
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("10pa1a0590");
		driver.findElement(By.xpath("//*[@id='loginbutton']")).click();
		driver.switchTo().window(winHandleBefore);
		Thread.sleep(2000);
		if (!page.equals("CartPage")) {
			signOut(driver, url, page);
		}
	}

	public static void signinGoogle(WebDriver driver, String page, String url)
			throws InterruptedException{
		Thread.sleep(2000);
		String winHandleBefore = driver.getWindowHandle();
		driver.findElement(
				By.xpath("//*[@id='AccountModel']/div[3]/div[1]/div/ul[1]/li[2]/a/img"))
				.click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='Email']")).clear();
		driver.findElement(By.xpath("//*[@id='Email']")).sendKeys(
				"10pa1a0590@gmail.com");
		driver.findElement(By.xpath("//*[@id='Passwd']")).clear();
		driver.findElement(By.xpath("//*[@id='Passwd']")).sendKeys(
				"10pa1a05900");
		driver.findElement(By.xpath("//*[@id='signIn']")).click();
		driver.switchTo().window(winHandleBefore);
		Thread.sleep(2000);
		if (!page.equals("CartPage")) {
			signOut(driver, url, page);
		}
	}

	public static void hoverClick(WebDriver driver, String hover)
			throws InterruptedException {
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		Actions hoverOverRegistrar = builder.moveToElement(driver
				.findElement(By.xpath(hover)));
		hoverOverRegistrar.perform();
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(1000);
	}

}
