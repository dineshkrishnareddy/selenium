package test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SignUp {
	WebDriver driver;
	static String message = "";
	String project_path=System.getProperty("user.dir");
	static BufferedWriter writer;
	
	@BeforeClass
	public void beforeClass() throws IOException{
		writer = Files.newBufferedWriter(Paths.get(project_path + "/XSLT_Reports/output/signup1.txt"),StandardCharsets.UTF_8);
	}
	
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
			driver.findElement(By.id("emailIdInModal")).sendKeys("test_user@gmail.com");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@class='button postfix redbutton']")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='SubscribeModal']/a/img")));
			driver.findElement(By.xpath("//*[@id='SubscribeModal']/a/img")).click();
		}
	}
    
	@AfterMethod
    public void afterTest(){
  		driver.quit();
    }
	
	@AfterClass
	public void afterClass() throws IOException{
		writer.close();
	}

    @Test
	@Parameters("url")
    public void Subscribe(String url) throws InterruptedException, IOException {   
		subscribeBottom(driver,"test_user@gmail.com",url);
		forgotPassword(driver,"test_user@gmail.com",url);
    }
    
    @Test
	  @Parameters("url")
    public void LandingPage(String url) throws InterruptedException, IOException {
    	navigateLandingPage(driver);
    	manualSignup(driver,"test_user","LandingPage");
    	manualSignin(driver, "test_user","LandingPage",url);
		driver.get(url);
    	navigateLandingPage(driver);
    	signinFB(driver,"LandingPage",url);
		driver.get(url);
    	navigateLandingPage(driver);
    	signinGoogle(driver,"LandingPage",url);
		driver.get(url);
    }
    
    @Test
	  @Parameters("url")
    public void TemplatesPage(String url) throws InterruptedException, IOException {
    	navigateTemplatesPage(driver);
    	manualSignup(driver,"test_user","TemplatesPage");
    	manualSignin(driver, "test_user","TemplatesPage",url);
		driver.get(url);
    	navigateTemplatesPage(driver);
    	signinFB(driver,"TemplatesPage",url);
		driver.get(url);
    	navigateTemplatesPage(driver);
    	signinGoogle(driver,"TemplatesPage",url);
		driver.get(url);
    }
    
    @Test
	  @Parameters("url")
    public void DimensionsPage(String url) throws InterruptedException, IOException {
    	navigateDimensionsPage(driver);
    	manualSignup(driver,"test_user","DimensionsPage");
    	manualSignin(driver, "test_user","DimensionsPage",url);
		driver.get(url);
    	navigateDimensionsPage(driver);
    	signinFB(driver,"DimensionsPage",url);
		driver.get(url);
    	navigateDimensionsPage(driver);
    	signinGoogle(driver,"DimensionsPage",url);
		driver.get(url);
    }
    
    @Test
	  @Parameters("url")
    public void DoorTypePage(String url) throws InterruptedException, IOException {
    	navigateDoorTypePage(driver);
    	manualSignup(driver,"test_user","DoorTypePage");
    	manualSignin(driver, "test_user","DoorTypePage",url);
    	driver.get(url);
    	navigateDoorTypePage(driver);		
    	signinFB(driver,"DoorTypePage",url);
    	driver.get(url);
    	navigateDoorTypePage(driver);
    	signinGoogle(driver,"DoorTypePage",url);
    	driver.get(url);
    }
    
    @Test
	  @Parameters("url")
    public void InteriorsPage(String url) throws InterruptedException, IOException {
    	navigateInteriorsPage(driver);
    	manualSignup(driver,"test_user","InteriorsPage");
    	manualSignin(driver, "test_user","InteriorsPage",url);
    	try{
    		driver.switchTo().alert().accept();
    	}catch (Exception e) {}
    	navigateInteriorsPage(driver);		
    	signinFB(driver,"InteriorsPage",url);
    	try{
    		driver.switchTo().alert().accept();
    	}catch (Exception e) {}
    	navigateInteriorsPage(driver);
    	signinGoogle(driver,"InteriorsPage",url);
    	try{
    		driver.switchTo().alert().accept();
    	}catch (Exception e) {}
    }
    
    @Test
	  @Parameters("url")
    public void ExteriorsPage(String url) throws InterruptedException, IOException {
    	navigateExteriorsPage(driver);
    	manualSignup(driver,"test_user","ExteriorsPage");
    	manualSignin(driver, "test_user","ExteriorsPage",url);
    	try{
    		driver.switchTo().alert().accept();
    	}catch (Exception e) {}
    	navigateExteriorsPage(driver);		
    	signinFB(driver,"ExteriorsPage",url);
    	try{
    		driver.switchTo().alert().accept();
    	}catch (Exception e) {}
    	navigateExteriorsPage(driver);
    	signinGoogle(driver,"ExteriorsPage",url);
    	try{
    		driver.switchTo().alert().accept();
    	}catch (Exception e) {}
    }
    
    @Test
	  @Parameters("url")
    public void AccessoriesPage(String url) throws InterruptedException, IOException {
    	navigateAccessoriesPage(driver);
    	manualSignup(driver,"test_user","AccessoriesPage");
    	manualSignin(driver, "test_user","AccessoriesPage",url);
    	driver.get(url);
    	navigateAccessoriesPage(driver);		
    	signinFB(driver,"AccessoriesPage",url);
    	driver.get(url);
    	navigateAccessoriesPage(driver);
    	signinGoogle(driver,"AccessoriesPage",url);
    	driver.get(url);
    }
    
    @Test
	  @Parameters("url")
    public void OrderPage(String url) throws InterruptedException, IOException {
    	navigateOrderPage(driver);
    	manualSignup(driver,"test_user","OrdersPage");
    	manualSignin(driver, "test_user","OrdersPage",url);
    	driver.get(url);
    	navigateOrderPage(driver);		
    	signinFB(driver,"OrdersPage",url);
    	driver.get(url);
    	navigateOrderPage(driver);
    	signinGoogle(driver,"OrdersPage",url);
    	driver.get(url);
    }
    
    @Test
	  @Parameters("url")
    public void CartPage(String url) throws InterruptedException, IOException {
    	navigateCartPage(driver);
    	manualSignup(driver,"test_user","CartPage");
    	manualSignin(driver, "test_user","CartPage",url);
    	Thread.sleep(2000);
    	if(driver.findElement(By.id("cartModal")).isDisplayed()){
    		driver.findElement(By.xpath("//*[@id='cartModal']/div[2]/button")).click();
    		Thread.sleep(1000);
    	}
		signOut(driver, url,"CartPage");
    	driver.get(url);
    	navigateCartPage(driver);		
    	signinFB(driver,"CartPage",url);
    	Thread.sleep(2000);
    	if(driver.findElement(By.id("cartModal")).isDisplayed()){
    		driver.findElement(By.xpath("//*[@id='cartModal']/div[2]/button")).click();
    		Thread.sleep(1000);
    	}
		signOut(driver, url,"CartPage");
    	driver.get(url);
    	navigateCartPage(driver);
    	signinGoogle(driver,"CartPage",url);
    	Thread.sleep(2000);
    	if(driver.findElement(By.id("cartModal")).isDisplayed()){
    		driver.findElement(By.xpath("//*[@id='cartModal']/div[2]/button")).click();
    		Thread.sleep(1000);
    	}
		signOut(driver, url,"CartPage");
    	driver.get(url);
    }
    
	public static void subscribeBottom(WebDriver driver,String name,String url) throws InterruptedException, IOException{
		try{
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id='emailId']")));
			driver.findElement(By.xpath("//*[@id='emailId']")).clear();
			driver.findElement(By.xpath("//*[@id='emailId']")).sendKeys(name);
			Thread.sleep(1000);
			driver.findElement(By.linkText("Sign up")).click();
			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='SubscribeModal']/a/img")));
			driver.findElement(By.xpath("//*[@id='SubscribeModal']/a/img")).click();
		}catch (Exception e) {
			writer.write("Exception in Bottom Subscription");
			writer.newLine();
			driver.get(url);
		}	
	}
	
	public static void forgotPassword(WebDriver driver,String name,String url) throws InterruptedException, IOException{
		try{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='loginUser']/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='AccountModel']/div[3]/div[1]/div/ul[2]/li[1]/a")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='email']")).clear();
			driver.findElement(By.xpath("//*[@id='email']")).sendKeys(name);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='emailSubmit']")).click();
			Thread.sleep(5000);
			org.testng.Assert.assertEquals(driver.findElement(By.xpath("//*[@id='cs']")).getText(),"Email Sent. Login to your mail to reset your password","Forgot password not displayed");
		}catch (Exception e) {
			writer.write("Exception in Forgot Password");
			writer.newLine();
			driver.get(url);
		}
	}
    
    public static void navigateLandingPage(WebDriver driver) throws InterruptedException, IOException{
    	try{
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//*[@id='loginUser']/span")).click();
    	}catch (Exception e) {
			writer.write("Exception while navigating to landing page");
			writer.newLine();
		}
    }
    
    public static void navigateTemplatesPage(WebDriver driver) throws InterruptedException, IOException{
    	try{
    		driver.findElement(By.linkText("WARDROBES")).click();
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//*[@id='loginUser']/span")).click();
    	}catch (Exception e) {
			writer.write("Exception while navigating to templates page");
			writer.newLine();
		}
    }
    
    public static void navigateDimensionsPage(WebDriver driver) throws InterruptedException, IOException{
    	try{
    		Thread.sleep(1000);
    		driver.findElement(By.linkText("WARDROBES")).click();
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//*[@id='kmBody']/div[9]/a/ul")).click();
    		signinClick(driver, "Dimensions page");
    	}catch (Exception e) {
			writer.write("Exception while navigating to dimensions page");
			writer.newLine();
		}
    }
    
    public static void navigateDoorTypePage(WebDriver driver) throws InterruptedException, IOException{
    	try{
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
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		signinClick(driver, "Door types page");
    	}catch (Exception e) {
			writer.write("Exception while navigating to door type page");
			writer.newLine();
		}
    }
    
    public static void navigateInteriorsPage(WebDriver driver) throws InterruptedException, IOException{
    	try{
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
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		WebDriverWait wait1 = new WebDriverWait(driver, 30);
    		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='step2DoorTypeTraditional']")));
    		driver.findElement(By.xpath("//*[@id='step2DoorTypeTraditional']")).click();
    		signinClick(driver, "interiors page");
    	}catch (Exception e) {
			writer.write("Exception while navigating to interiors page");
			writer.newLine();
		}
    }
    
    public static void navigateExteriorsPage(WebDriver driver) throws InterruptedException, IOException{
    	try{
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
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='step2DoorTypeTraditional']")));
    		driver.findElement(By.xpath("//*[@id='step2DoorTypeTraditional']")).click();
    		Thread.sleep(10000);
    		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed.right")));
    		driver.findElement(By.cssSelector("a.button.Proceed.right")).click();
    		Thread.sleep(10000);
    		signinClick(driver, "exteriors page");
    	}catch (Exception e) {
			writer.write("Exception while navigating to exteriors page");
			writer.newLine();
		}
    }
    
    public static void navigateAccessoriesPage(WebDriver driver) throws InterruptedException, IOException{
    	try{
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
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='step2DoorTypeTraditional']")));
    		driver.findElement(By.xpath("//*[@id='step2DoorTypeTraditional']")).click();
    		Thread.sleep(10000);
    		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed.right")));
    		driver.findElement(By.cssSelector("a.button.Proceed.right")).click();
    		Thread.sleep(10000);
    		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed")));
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		Thread.sleep(10000);
    		signinClick(driver, "accessories page");
    	}catch (Exception e) {
			writer.write("Exception while navigating to accessories page");
			writer.newLine();
		}
    }
    
    public static void navigateOrderPage(WebDriver driver) throws InterruptedException, IOException{
    	try{
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
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='step2DoorTypeTraditional']")));
    		driver.findElement(By.xpath("//*[@id='step2DoorTypeTraditional']")).click();
    		Thread.sleep(10000);
    		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed.right")));
    		driver.findElement(By.cssSelector("a.button.Proceed.right")).click();
    		Thread.sleep(10000);
    		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed")));
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		Thread.sleep(10000);
    		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed")));
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		Thread.sleep(2000);
    		signinClick(driver, "orders page");
    	}catch (Exception e) {
			writer.write("Exception while navigating to order page");
			writer.newLine();
		}
    }
    
    public static void navigateCartPage(WebDriver driver) throws InterruptedException, IOException{
    	try{
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
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='step2DoorTypeTraditional']")));
    		driver.findElement(By.xpath("//*[@id='step2DoorTypeTraditional']")).click();
    		Thread.sleep(10000);
    		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed.right")));
    		driver.findElement(By.cssSelector("a.button.Proceed.right")).click();
    		Thread.sleep(10000);
    		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed")));
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		Thread.sleep(10000);
    		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.Proceed")));
    		driver.findElement(By.cssSelector("a.button.Proceed")).click();
    		Thread.sleep(2000);
    		driver.findElement(By.linkText("Add to Cart")).click();
    		Thread.sleep(2000);
    		driver.findElement(By.linkText("Sign in")).click();
    	}catch (Exception e) {
			writer.write("Exception while navigating to cart page");
			writer.newLine();
		}
    }
    
    public static void manualSignup(WebDriver driver,String name,String page) throws InterruptedException, IOException{
    	try{
    		Thread.sleep(2000);
    		driver.findElement(By.xpath("//*[@id='signup-name']")).clear();
    		driver.findElement(By.xpath("//*[@id='signup-name']")).sendKeys(name);
    		driver.findElement(By.xpath("//*[@id='signup-email']")).clear();
    		driver.findElement(By.xpath("//*[@id='signup-email']")).sendKeys(name + "@gmail.com");
    		driver.findElement(By.xpath("//*[@id='signup-phoneNumber']")).clear();
    		driver.findElement(By.xpath("//*[@id='signup-phoneNumber']")).sendKeys("9999999999");
    		driver.findElement(By.xpath("//*[@id='signup-password']")).clear();
    		driver.findElement(By.xpath("//*[@id='signup-password']")).sendKeys(name);
    		driver.findElement(By.xpath("//*[@id='signup']/span/img")).click();
    		Thread.sleep(1000);
    	}catch (Exception e) {
			writer.write("Exception in Manual Signup at " + page);
			writer.newLine();
		}
	}
	
	public static void manualSignin(WebDriver driver,String name,String page,String url) throws InterruptedException, IOException{
		try{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='login-email']")).clear();
			driver.findElement(By.xpath("//*[@id='login-email']")).sendKeys("test_user@gmail.com");
			driver.findElement(By.xpath("//*[@id='login-password']")).clear();
			driver.findElement(By.xpath("//*[@id='login-password']")).sendKeys("test_user");
			driver.findElement(By.xpath("//*[@id='login']/span/img")).click();
			if(!page.equals("CartPage"))
			{
				signOut(driver, url,page);
			}
		}catch (Exception e) {
			writer.write("Exception in Manual Signin at " + page);
			writer.newLine();
		}
	}
	
	public static void signOut(WebDriver driver,String url,String page) throws InterruptedException, IOException{
		try{
			Thread.sleep(2000);
			if(page.equals("CartPage") || page.equals("LandingPage") || page.equals("TemplatesPage")){
				hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/ul");
			}else{
				hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/div/ul[1]");	
			}
			if(!url.equals(driver.getCurrentUrl())){
				writer.write("Not going to sign out at " + page);
				writer.newLine();
			}
		}catch (Exception e) {
			writer.write("Exception in sign out at " + page);
			writer.newLine();
		}
		driver.get(url);
	}
	
	public static void signinFB(WebDriver driver,String page,String url) throws InterruptedException, IOException{
		Thread.sleep(2000);
		String winHandleBefore = driver.getWindowHandle();
		try{
			driver.findElement(By.xpath("//*[@id='connectToFB']/a/img")).click();
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='email']")).clear();
			driver.findElement(By.xpath("//*[@id='email']")).sendKeys("10pa1a0590@gmail.com");
			driver.findElement(By.xpath("//*[@id='pass']")).clear();
			driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("10pa1a0590");
			driver.findElement(By.xpath("//*[@id='loginbutton']")).click();
			driver.switchTo().window(winHandleBefore);
			Thread.sleep(2000);
			if(!page.equals("CartPage"))
			{
				signOut(driver, url,page);
			}
		}catch (Exception e) {
			writer.write("Exception in FB login at " + page);
			writer.newLine();
			driver.close();
			Thread.sleep(5000);
			System.out.println(driver.findElement(By.xpath("//*[@id='closeIcon']")).isDisplayed());
			driver.findElement(By.xpath("//*[@id='closeIcon']")).click();
			Thread.sleep(5000);
			driver.switchTo().window(winHandleBefore);
		}
	}
	
	public static void signinGoogle(WebDriver driver,String page,String url) throws InterruptedException, IOException {
		Thread.sleep(2000);
		String winHandleBefore = driver.getWindowHandle();
		try{
			driver.findElement(By.xpath("//*[@id='AccountModel']/div[3]/div[1]/div/ul[1]/li[2]/a/img")).click();
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='Email']")).clear();
			driver.findElement(By.xpath("//*[@id='Email']")).sendKeys("10pa1a0590@gmail.com");
			driver.findElement(By.xpath("//*[@id='Passwd']")).clear();
			driver.findElement(By.xpath("//*[@id='Passwd']")).sendKeys("10pa1a05900");
			driver.findElement(By.xpath("//*[@id='signIn']")).click();
			driver.switchTo().window(winHandleBefore);
			Thread.sleep(2000);
			if(!page.equals("CartPage"))
			{
				signOut(driver, url,page);
			}
		}catch (Exception e) {
			writer.write("Exception in Google login at " + page);
			writer.newLine();
			driver.close();
			driver.findElement(By.id("closeIcon")).click();
			driver.switchTo().window(winHandleBefore);
		}
	}
	
	public static void hoverClick(WebDriver driver,String hover) throws InterruptedException{
		Thread.sleep(2000);
		Actions builder = new Actions(driver); 
		Actions hoverOverRegistrar = builder.moveToElement(driver.findElement(By.xpath(hover)));
		hoverOverRegistrar.perform();
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(1000);
	}
	
	public static void signinClick(WebDriver driver,String page) throws IOException{
		try{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='loginUser']/span")).click();
		}catch (Exception e) {
			writer.write("Exception in signin click at " + page);
			writer.newLine();
		}
	}
  
}
