package test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.BeforeClass;
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

public class Sliding {
	WebDriver driver;
	String message, project_path = System.getProperty("user.dir");
	static BufferedWriter writer;

	@BeforeClass
	public void beforeclass() throws IOException {
		writer = Files.newBufferedWriter(
				Paths.get(project_path + "/XSLT_Reports/output/sliding1.txt"),
				StandardCharsets.UTF_8);
	}

	@BeforeMethod
	@Parameters("url")
	public void beforeTest(String url) throws InterruptedException, IOException {
		writer = Files.newBufferedWriter(
				Paths.get(project_path + "/XSLT_Reports/output/sliding.txt"),
				StandardCharsets.UTF_8);
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
					"test@gmail.com");
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
	public void slidingDoors() throws InterruptedException, IOException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("WARDROBES")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='kmBody']/div[9]/a/ul")).click();
		Thread.sleep(1000);
		for (int width = 40; width < 121; width++) {
			for (int height = 72; height < 85; height++) {
				try {
					driver.findElement(By.xpath("//*[@id='myText']")).clear();
					driver.findElement(By.xpath("//*[@id='myText']")).sendKeys(
							String.valueOf(width));
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[@id='heightOfCloset']"))
							.clear();
					driver.findElement(By.xpath("//*[@id='heightOfCloset']"))
							.sendKeys(String.valueOf(height));
					Thread.sleep(1000);
					driver.findElement(
							By.linkText("Enter dimensions to Proceed")).click();

					String path = "//*[@id='kmBody']/div[5]/div[3]/img";
					WebDriverWait wait1 = new WebDriverWait(driver, 30);
					wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
					driver.findElement(By.xpath(path)).click();
					Thread.sleep(10000);
					wait1 = new WebDriverWait(driver, 20);
					wait1.until(ExpectedConditions.elementToBeClickable(By.linkText("Proceed to door design")));
					if (!driver.findElement(By.linkText("Proceed to door design")).isDisplayed()) {
						writer.write("Not loading interiors for " + width + " "+ height + " in sliding doors");
						writer.newLine();
					} else {
						driver.findElement(
								By.linkText("Proceed to door design")).click();
						Thread.sleep(10000);
						wait1 = new WebDriverWait(driver, 20);
						wait1.until(ExpectedConditions.elementToBeClickable(By
								.linkText("Proceed to select accessories")));
						if (!driver.findElement(
								By.linkText("Proceed to select accessories"))
								.isDisplayed()) {
							writer.write("Not loading exteriors for " + width
									+ " " + height + " in sliding doors");
							writer.newLine();
						}
					}
				} catch (Exception e) {
					writer.write("Exception in Sliding doors for " + width
							+ " " + height);
					writer.newLine();
				}
				driver.findElement(By.linkText("DIMENSIONS")).click();
				try {
					driver.switchTo().alert().accept();
				} catch (Exception Ex) {
				}
				Thread.sleep(2000);
			}
		}
	}

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

	@AfterClass
	public void afterClass() throws IOException {
		writer.close();
	}
}
