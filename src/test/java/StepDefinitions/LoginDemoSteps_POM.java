package StepDefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.loginPage;

public class LoginDemoSteps_POM {
	
	WebDriver driver = null;
	loginPage login;
	TakesScreenshot ts = (TakesScreenshot)driver;
	
	
	@SuppressWarnings("deprecation")
	@Given("browser is open")
	public void browser_is_open() {
		
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is" + projectPath);
		
		System.setProperty("webdriver.gecko.driver", projectPath+"/src/test/resources/drivers/geckodriver");
		
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
	}
	
	@And("user is on login page")
	public void user_is_on_login_page() {
		
		driver.navigate().to("https://example.testproject.io/web/");
	}
	
	@When("^user enters (.*) and (.*)$")
	public void user_enters_username_and_password(String username, String password) throws InterruptedException, IOException{
		
		login = new loginPage(driver);
		
		login.enterUsername(username);
		login.enterPassword(password);
		Utils.CaptureScreenshot(driver, "print1.png");
		Thread.sleep(2000);
	}
	
	@And("user clicks on login")
	public void user_clicks_on_login() throws InterruptedException {
		
		
		login.clickLogin();
		Thread.sleep(3000);
		
		
	}
	
	@Then("user is navigated to the home page")
	public void user_is_navigated_to_the_home_page() throws InterruptedException, IOException{
	
		login.checkLogOutIsDisplayed();
		driver.findElement(By.id("logout")).isDisplayed();
		Utils.CaptureScreenshot(driver, "print2.png");
		Thread.sleep(3000);
		driver.close();
	//	driver.quit();
		
	}

}
