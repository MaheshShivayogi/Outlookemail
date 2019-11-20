package stepDefinition;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefinition {

	WebDriver driver;

	@Given("^User alreday present in the home page$")
	public void user_alreday_present_in_the_home_page() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://outlook.live.com/owa/");
		String actualtitle = driver.getTitle();
		Assert.assertEquals("Outlook – free personal email and calendar from Microsoft", actualtitle);

	}

	@When("^User clicks on Sign in button and lands on login page$")
	public void user_clicks_on_Sign_in_button_and_lands_on_login_page() throws Throwable {
		driver.findElement(By.xpath("(//a[text()='Sign in'])[1]")).click();
		;
		WebElement actualUsername = driver.findElement(By.xpath("//input[@name='loginfmt']"));
		Thread.sleep(1000);
		boolean status = actualUsername.isDisplayed();
		Assert.assertTrue(status);

	}

	@Then("^User enters username and click on Next button$")
	public void user_enters_username_and_click_on_Next_button() throws Throwable {
		driver.findElement(By.xpath("//input[@name='loginfmt']")).sendKeys("cernertest2018@outlook.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Next']")).click();
		Thread.sleep(3000);
		WebElement txtPassword = driver.findElement(By.xpath("//input[@name='passwd']"));
		boolean status = txtPassword.isDisplayed();
		Assert.assertTrue(status);
	}

	@Then("^enters password and clicks on Sign in button$")
	public void enters_password_and_clicks_on_Sign_in_button() throws Throwable {
		driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("Welcome@#123");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Sign in']")).click();
		Thread.sleep(5000);
		takeScreenshot();
	}

	@Then("^User lands on outlook homepage and logs out from the session$")
	public void user_lands_on_outlook_homepage() throws Throwable {
		Thread.sleep(5000);
		WebElement txtOutlook = driver.findElement(By.xpath("//span[text()='Outlook']"));
		boolean status = txtOutlook.isDisplayed();
		Assert.assertTrue(status);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div/img[@alt='CT']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		Thread.sleep(5000);
		driver.close();

	}

	// take screenshot
	public void takeScreenshot() {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		File destination = new File("C:/OutlookTestNGProj/Screenshots/Email.png");

		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
