package test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class SignUp {
	WebDriver driver;
	static String message;
	String project_path=System.getProperty("user.dir");
	
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
    public void afterTest() throws FileNotFoundException, UnsupportedEncodingException{
    	PrintWriter writer = new PrintWriter(project_path + "/XSLT_Reports/output/errors/endtoend.txt", "UTF-8");
    	writer.println(message);
    	writer.close();
  		driver.close();
    }

    @Test
	@Parameters("url")
    public void Subscribe(String url) throws InterruptedException {   
		subscribeBottom(driver,"test_user@gmail.com",url);
		forgotPassword(driver,"test_user@gmail.com",url);
    }
    
    @Test
	  @Parameters("url")
    public void LandingPage(String url) throws InterruptedException {
		try{
    		navigateLandingPage(driver);
    		manualSignup(driver,"test_user","LandingPage");
    		manualSignin(driver, "test_user","LandingPage");
    		signOut(driver, url,"LandingPage");
    		navigateLandingPage(driver);
    		signinFB(driver,"LandingPage");
    		signOut(driver, url,"LandingPage");
    		navigateLandingPage(driver);
    		signinGoogle(driver,"LandingPage");
    		signOut(driver, url,"LandingPage");
		}catch (Exception e) {
			message = message + "\nException in Landing Page";
			driver.get(url);
		}
    }
    
    @Test
	  @Parameters("url")
    public void TemplatesPage(String url) throws InterruptedException {
		try{
    		navigateTemplatesPage(driver);
    		manualSignup(driver,"test_user","TemplatesPage");
    		manualSignin(driver, "test_user","TemplatesPage");
    		signOut(driver, url,"TemplatesPage");
    		navigateTemplatesPage(driver);
    		signinFB(driver,"TemplatesPage");
    		signOut(driver, url,"TemplatesPage");
    		navigateTemplatesPage(driver);
    		signinGoogle(driver,"TemplatesPage");
    		signOut(driver, url,"TemplatesPage");
		}catch (Exception e) {
			message = message + "\nException in Templates Page";
			driver.get(url);
		}
    }
    
    @Test
	  @Parameters("url")
    public void DimensionsPage(String url) throws InterruptedException {
		try{
    		navigateDimensionsPage(driver);
    		manualSignup(driver,"test_user","DimensionsPage");
    		manualSignin(driver, "test_user","DimensionsPage");
    		signOutStage2(driver, url,"DimensionsPage");
    		navigateDimensionsPage(driver);		
    		signinFB(driver,"DimensionsPage");
    		signOutStage2(driver, url,"DimensionsPage");
    		navigateDimensionsPage(driver);
    		signinGoogle(driver,"DimensionsPage");
    		signOutStage2(driver, url,"DimensionsPage");
		}catch (Exception e) {
			message = message + "\nException in Dimensions Page";
			driver.get(url);
		}
    }
    
    @Test
	  @Parameters("url")
    public void DoorTypePage(String url) throws InterruptedException {
		try{
    		navigateDoorTypePage(driver);
    		manualSignup(driver,"test_user","DoorTypePage");
    		manualSignin(driver, "test_user","DoorTypePage");
    		signOutStage2(driver, url,"DoorTypePage");
    		navigateDoorTypePage(driver);		
    		signinFB(driver,"DoorTypePage");
    		signOutStage2(driver, url,"DoorTypePage");
    		navigateDoorTypePage(driver);
    		signinGoogle(driver,"DoorTypePage");
    		signOutStage2(driver, url,"DoorTypePage");
		}catch (Exception e) {
			message = message + "\nException in Door Type Page";
			driver.get(url);
		}
    }
    
    @Test
	  @Parameters("url")
    public void InteriorsPage(String url) throws InterruptedException {
    	try{
    		navigateInteriorsPage(driver);
    		manualSignup(driver,"test_user","InteriorsPage");
    		manualSignin(driver, "test_user","InteriorsPage");
    		signOutStage2(driver, url,"InteriorsPage");
    		navigateInteriorsPage(driver);		
    		signinFB(driver,"InteriorsPage");
    		signOutStage2(driver, url,"InteriorsPage");
    		navigateInteriorsPage(driver);
    		signinGoogle(driver,"InteriorsPage");
    		signOutStage2(driver, url,"InteriorsPage");
		}catch (Exception e) {
			message = message + "\nException in Interiors Page";
			driver.get(url);
		}
    }
    
    @Test
	  @Parameters("url")
    public void ExteriorsPage(String url) throws InterruptedException {
    	try{
    		navigateExteriorsPage(driver);
    		manualSignup(driver,"test_user","ExteriorsPage");
    		manualSignin(driver, "test_user","ExteriorsPage");
    		signOutStage2(driver, url,"ExteriorsPage");
    		navigateExteriorsPage(driver);		
    		signinFB(driver,"ExteriorsPage");
    		signOutStage2(driver, url,"ExteriorsPage");
    		navigateExteriorsPage(driver);
    		signinGoogle(driver,"ExteriorsPage");
    		signOutStage2(driver, url,"ExteriorsPage");
		}catch (Exception e) {
			message = message + "\nException in Exteriors Page";
			driver.get(url);
		}
    }
    
    @Test
	  @Parameters("url")
    public void AccessoriesPage(String url) throws InterruptedException {
    	try{
    		navigateAccessoriesPage(driver);
    		manualSignup(driver,"test_user","AccessoriesPage");
    		manualSignin(driver, "test_user","AccessoriesPage");
    		signOutStage2(driver, url,"AccessoriesPage");
    		navigateAccessoriesPage(driver);		
    		signinFB(driver,"AccessoriesPage");
    		signOutStage2(driver, url,"AccessoriesPage");
    		navigateAccessoriesPage(driver);
    		signinGoogle(driver,"AccessoriesPage");
    		signOutStage2(driver, url,"AccessoriesPage");
		}catch (Exception e) {
			message = message + "\nException in Accessories Page";
			driver.get(url);
		}
    }
    
    @Test
	  @Parameters("url")
    public void OrderPage(String url) throws InterruptedException {
    	try{
    		navigateOrderPage(driver);
    		manualSignup(driver,"test_user","OrdersPage");
    		manualSignin(driver, "test_user","OrdersPage");
    		signOutStage2(driver, url,"OrdersPage");
    		navigateOrderPage(driver);		
    		signinFB(driver,"OrdersPage");
    		signOutStage2(driver, url,"OrdersPage");
    		navigateOrderPage(driver);
    		signinGoogle(driver,"OrdersPage");
    		signOutStage2(driver, url,"OrdersPage");
		}catch (Exception e) {
			message = message + "\nException in Order Page";
			driver.get(url);
		}
    }
    
    @Test
	  @Parameters("url")
    public void CartPage(String url) throws InterruptedException {
    	try{
    		navigateCartPage(driver);
    		manualSignup(driver,"test_user","CartPage");
    		manualSignin(driver, "test_user","CartPage");
    		Thread.sleep(2000);
    		if(driver.findElement(By.id("cartModal")).isDisplayed()){
    			driver.findElement(By.xpath("//*[@id='cartModal']/div[2]/button")).click();
    			Thread.sleep(1000);
    		}
    		signOut(driver, url,"CartPage");
    		navigateCartPage(driver);		
    		signinFB(driver,"CartPage");
    		Thread.sleep(2000);
    		if(driver.findElement(By.id("cartModal")).isDisplayed()){
    			driver.findElement(By.xpath("//*[@id='cartModal']/div[2]/button")).click();
    			Thread.sleep(1000);
    		}
    		signOut(driver, url,"CartPage");
    		navigateCartPage(driver);
    		signinGoogle(driver,"CartPage");
    		Thread.sleep(2000);
    		if(driver.findElement(By.id("cartModal")).isDisplayed()){
    			driver.findElement(By.xpath("//*[@id='cartModal']/div[2]/button")).click();
    			Thread.sleep(1000);
    		}
    		signOut(driver, url,"CartPage");
		}catch (Exception e) {
			message = message + "\nException in Cart Page";
			driver.get(url);
		}
    }
    
	public static void subscribeBottom(WebDriver driver,String name,String url) throws InterruptedException{
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
			message = message + "\nException in Subscription";
			driver.get(url);
		}	
	}
	
	public static void forgotPassword(WebDriver driver,String name,String url) throws InterruptedException{
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
			message = message + "\nException in Forgot Password";
			driver.get(url);
		}
	}
    
    public static void navigateLandingPage(WebDriver driver) throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='loginUser']/span")).click();    	
    }
    
    public static void navigateTemplatesPage(WebDriver driver) throws InterruptedException{
    	driver.findElement(By.linkText("WARDROBES")).click();
    	Thread.sleep(2000);
    	driver.findElement(By.xpath("//*[@id='loginUser']/span")).click();
    }
    
    public static void navigateDimensionsPage(WebDriver driver) throws InterruptedException{
    	Thread.sleep(1000);
    	driver.findElement(By.linkText("WARDROBES")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='kmBody']/div[9]/a/ul")).click();
    	hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/div/ul[1]/li[2]", "//*[@id='user-options-design-pages']/li[1]/a/span");
    }
    
    public static void navigateDoorTypePage(WebDriver driver) throws InterruptedException{
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
		driver.findElement(By.linkText("Proceed to select door type")).click();
    	hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/div/ul[1]/li[2]", "//*[@id='user-options-design-pages']/li[1]/a/span");
    }
    
    public static void navigateInteriorsPage(WebDriver driver) throws InterruptedException{
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
		driver.findElement(By.linkText("Proceed to select door type")).click();
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")));
		driver.findElement(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")).click();
    	hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/div/ul[1]/li[2]", "//*[@id='user-options-design-pages']/li[1]/a/span");
    }
    
    public static void navigateExteriorsPage(WebDriver driver) throws InterruptedException{
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
		driver.findElement(By.linkText("Proceed to select door type")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")));
		driver.findElement(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to door design")));
		driver.findElement(By.linkText("Proceed to door design")).click();
		Thread.sleep(10000);
    	hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/div/ul[1]/li[2]", "//*[@id='user-options-design-pages']/li[1]/a/span");
    }
    
    public static void navigateAccessoriesPage(WebDriver driver) throws InterruptedException{
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
		driver.findElement(By.linkText("Proceed to select door type")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")));
		driver.findElement(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to door design")));
		driver.findElement(By.linkText("Proceed to door design")).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to select accessories")));
		driver.findElement(By.linkText("Proceed to select accessories")).click();
		Thread.sleep(10000);	
    	hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/div/ul[1]/li[2]", "//*[@id='user-options-design-pages']/li[1]/a/span");
    }
    
    public static void navigateOrderPage(WebDriver driver) throws InterruptedException{
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
		driver.findElement(By.linkText("Proceed to select door type")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")));
		driver.findElement(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to door design")));
		driver.findElement(By.linkText("Proceed to door design")).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to select accessories")));
		driver.findElement(By.linkText("Proceed to select accessories")).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to Order")));
		driver.findElement(By.linkText("Proceed to Order")).click();
		Thread.sleep(2000);
    	hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/div/ul[1]/li[2]", "//*[@id='user-options-design-pages']/li[1]/a/span");
    }
    
    public static void navigateCartPage(WebDriver driver) throws InterruptedException{
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
		driver.findElement(By.linkText("Proceed to select door type")).click();
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")));
		driver.findElement(By.xpath("//*[@id='kmBody']/div[5]/div[2]/img")).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to door design")));
		driver.findElement(By.linkText("Proceed to door design")).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to select accessories")));
		driver.findElement(By.linkText("Proceed to select accessories")).click();
		Thread.sleep(10000);
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to Order")));
		driver.findElement(By.linkText("Proceed to Order")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Add to Cart")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign in")).click();
    }
    
    public static void manualSignup(WebDriver driver,String name,String page) throws InterruptedException{
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
	}
	
	public static void manualSignin(WebDriver driver,String name,String page) throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='login-email']")).clear();
		driver.findElement(By.xpath("//*[@id='login-email']")).sendKeys("test_user@gmail.com");
		driver.findElement(By.xpath("//*[@id='login-password']")).clear();
		driver.findElement(By.xpath("//*[@id='login-password']")).sendKeys("test_user");
		driver.findElement(By.xpath("//*[@id='login']/span/img")).click();
	}
	
	public static void signOut(WebDriver driver,String url,String page) throws InterruptedException{
		try{
			hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/ul", "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/ul/li[1]/ul/li[4]/a");
			org.testng.Assert.assertEquals(driver.getCurrentUrl(),url,"Not going to signout page");
		}catch (Exception e) {
			message = message + "\nException in sign out at " + page;
		}
		driver.get(url);
	}

	public static void signOutStage2(WebDriver driver,String url,String page) throws InterruptedException{
		try{
			hoverClick(driver, "//*[@id='kmBody']/div[2]/div[1]/div/div/div[2]/div/ul[1]/li[2]", "//*[@id='user-options-design-pages']/li[5]/a/span");
			try{  
				driver.switchTo().alert().accept(); 
			}catch (Exception Ex){}
			org.testng.Assert.assertEquals(driver.getCurrentUrl(),url,"Not going to signout page");
		}catch (Exception e) {
			message = message + "\nException in sign out at " + page;
		}
		driver.get(url);
	}	
	
	public static void signinFB(WebDriver driver,String page) throws InterruptedException{
		Thread.sleep(2000);
		String winHandleBefore = driver.getWindowHandle();
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
	}
	
	public static void signinGoogle(WebDriver driver,String page) throws InterruptedException {
		Thread.sleep(2000);
		String winHandleBefore = driver.getWindowHandle();
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
	}
	
	public static void hoverClick(WebDriver driver,String hover, String click) throws InterruptedException{
		Thread.sleep(2000);		
    	Actions actions = new Actions(driver);
    	WebElement menuHoverLink = driver.findElement(By.xpath(hover));
    	actions.moveToElement(menuHoverLink);
    	WebElement subLink = driver.findElement(By.xpath(click));
    	actions.moveToElement(subLink);
    	actions.click();
    	actions.perform();
		Thread.sleep(1000);
	}
  
}
