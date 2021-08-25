package com.jupitercloud.planittesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTestClass {
	@Test(priority = 1)
	public void testCaseOne() {
// 		Create driver  
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver1 = new ChromeDriver();

// 		maximize browser window
		driver1.manage().window().maximize();

// 		open test page 
		String url = "http://jupiter.cloud.planittesting.com";
		driver1.get(url);
		System.out.println("Page is opened.");

//		navigate to contact page 
		WebElement contact = driver1.findElement(By.xpath("//li[@id='nav-contact']/a[@href='#/contact']"));
		contact.click();

// 		verify contact page
		String expectedUrl = "https://jupiter.cloud.planittesting.com/#/contact";
		String actualUrl = driver1.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual URL is not the same as expected ");

// 		click submit button

		WebDriverWait wait = new WebDriverWait(driver1, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//body[@class='ng-scope']/div[@class='container-fluid']//form[@name='form']//a")));
		// click on the button as soon as the "compose" button is visible
		driver1.findElement(By.xpath("//body[@class='ng-scope']/div[@class='container-fluid']//form[@name='form']//a"))
				.click();

//  	validating error 

		WebElement forName_check = driver1.findElement(By.xpath("/html//span[@id='forename-err']"));
		WebElement email_check = driver1.findElement(By.xpath("/html//span[@id='email-err']"));
		WebElement message_check = driver1.findElement(By.xpath("/html//span[@id='message-err']"));

		String forname_value = forName_check.getText().toString();
		String email_value = email_check.getText().toString();
		String message_value = message_check.getText().toString();

		if (forname_value == "Forename is required") {
			System.out.println("Forname is not found!");
		}
		if (email_value == "Email is required") {
			System.out.println("Email not found!");
		}

		if (message_value == "Message is required") {
			System.out.println("Message not found!");
		}

// 		Sending value to mandatory field 
		WebElement forname = driver1.findElement(By.xpath("/html//input[@id='forename']"));
		WebElement email = driver1.findElement(By.xpath("/html//input[@id='email']"));
		WebElement message = driver1.findElement(By.xpath("/html//textarea[@id='message']"));

		forname.sendKeys("john");
		email.sendKeys("john.example@planit.net.au");
		message.sendKeys("Tell us about it ");

//		validating if error is gone 
		boolean isName = driver1.findElements(By.xpath("/html//input[@id='forename']")).isEmpty();
		boolean isEmail = driver1.findElements(By.xpath("/html//input[@id='email']")).isEmpty();
		boolean isMessage = driver1.findElements(By.xpath("/html//textarea[@id='message']")).isEmpty();

		if (isName) {
			System.out.println("ForName Error is gone!");
		}
		if (isEmail) {
			System.out.println("Email Error is gone!");
		}
		if (isMessage) {
			System.out.println("Message Error is gone!");
		}

//		close browser 
		driver1.quit();

	}

	@Test(priority = 2)
	public void testCaseTwo() {
// 		Create driver  
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver2 = new ChromeDriver();

		WebDriverWait wait = new WebDriverWait(driver2, 30);

// 		maximize browser window
		driver2.manage().window().maximize();

// 		open test page 
		String url = "http://jupiter.cloud.planittesting.com";
		driver2.get(url);
		System.out.println("Page is opened.");

//		navigate to contact page 
		WebElement contact = driver2.findElement(By.xpath("//li[@id='nav-contact']/a[@href='#/contact']"));
		contact.click();

// 		verify contact page
		String expectedUrl = "https://jupiter.cloud.planittesting.com/#/contact";
		String actualUrl = driver2.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual URL is not the same as expected ");

// 		Sending value to mandatory field 

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html//input[@id='forename']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html//input[@id='email']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html//textarea[@id='message']")));

		WebElement forname = driver2.findElement(By.xpath("/html//input[@id='forename']"));
		WebElement email = driver2.findElement(By.xpath("/html//input[@id='email']"));
		WebElement message = driver2.findElement(By.xpath("/html//textarea[@id='message']"));

		String name = "John";
		forname.sendKeys(name);
		email.sendKeys("john.example@planit.net.au");
		message.sendKeys("Tell us about it ");

// 		click submit button

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//body[@class='ng-scope']/div[@class='container-fluid']//form[@name='form']//a")));
		// click on the button as soon as the "compose" button is visible
		driver2.findElement(By.xpath("//body[@class='ng-scope']/div[@class='container-fluid']//form[@name='form']//a"))
				.click();

//		wait for submission page to load up 

		try {
			do {
				continue;
			} while (!(driver2.findElements(By.xpath("//body/div[3]//h1[.='Sending Feedback']")).isEmpty()));
		} catch (Exception e) {
			System.out.println("Exception handled while waiting to load the submission page!");
		}

//  	validate submission message 

		WebElement submitClick = driver2.findElement(
				By.xpath("//body[@class='ng-scope']/div[@class='container-fluid']//div[@class='alert alert-success']"));
		String actualMessage = submitClick.getText();
		String expectedMessage = "Thanks " + name + ", we appreciate your feedback.";
		Assert.assertEquals(actualMessage, expectedMessage, "Expected and actual message is not same");

//		close browser 
		driver2.quit();

	}

	@Test(priority = 3)
	public void testCaseThree() {
// 		Create driver  
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver3 = new ChromeDriver();

//		wait declaration
		WebDriverWait wait = new WebDriverWait(driver3, 30);

// 		maximize browser window
		driver3.manage().window().maximize();

// 		open test page 
		String url = "http://jupiter.cloud.planittesting.com";
		driver3.get(url);
		System.out.println("Page is opened.");

//		navigate to contact page 
		WebElement contact = driver3.findElement(By.xpath("//li[@id='nav-contact']/a[@href='#/contact']"));
		contact.click();

// 		verify contact page
		String expectedUrl = "https://jupiter.cloud.planittesting.com/#/contact";
		String actualUrl = driver3.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual URL is not the same as expected ");

// 		Sending value to mandatory field 

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html//input[@id='forename']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html//input[@id='email']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html//textarea[@id='message']")));

		WebElement forname = driver3.findElement(By.xpath("/html//input[@id='forename']"));
		WebElement email = driver3.findElement(By.xpath("/html//input[@id='email']"));
		WebElement message = driver3.findElement(By.xpath("/html//textarea[@id='message']"));

		String name = "John";
		forname.sendKeys(name);
		email.sendKeys("johnnet.au");
		message.sendKeys("Tell us about it ");

//		validating the data provided 		
		WebElement emailFormatCheck = driver3.findElement(By.xpath("/html//span[@id='email-err']"));
		String expected_message = "Please enter a valid email";
		String actual_message = emailFormatCheck.getText();
		Assert.assertEquals(actual_message, expected_message, "Email format validation failed! ");

//		close browser 
		driver3.quit();

	}

	@Test(priority = 4)
	public void testCaseFour() {
// 		Create driver  
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver4 = new ChromeDriver();

//		wait declaration
		WebDriverWait wait = new WebDriverWait(driver4, 30);

// 		maximize browser window
		driver4.manage().window().maximize();

// 		open test page 
		String url = "http://jupiter.cloud.planittesting.com";
		driver4.get(url);
		System.out.println("Page is opened.");

//		navigate to shop page 
		WebElement contact = driver4.findElement(By.xpath("//li[@id='nav-shop']/a[@href='#/shop']"));
		contact.click();

// 		verify shop page navigation
		String expectedUrl = "https://jupiter.cloud.planittesting.com/#/shop";
		String actualUrl = driver4.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual URL is not the same as expected ");

// 		Sending value to mandatory field 
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//body[@class='ng-scope']/div[@class='container-fluid']//ul/li[6]//a")));
		WebElement funnyCow = driver4
				.findElement(By.xpath("//body[@class='ng-scope']/div[@class='container-fluid']//ul/li[6]//a"));
		funnyCow.click();
		funnyCow.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//body[@class='ng-scope']/div[@class='container-fluid']//ul/li[4]//a")));
		WebElement fluffyBunny = driver4
				.findElement(By.xpath("//body[@class='ng-scope']/div[@class='container-fluid']//ul/li[4]//a"));
		fluffyBunny.click();

//		navigating to Cart
		WebElement navigateCart = driver4.findElement(By.xpath("//li[@id='nav-cart']/a[@href='#/cart']"));
		navigateCart.click();

// 		verify shop page navigation
		String expectedShopUrl = "https://jupiter.cloud.planittesting.com/#/cart";
		String actualShopUrl = driver4.getCurrentUrl();
		Assert.assertEquals(actualShopUrl, expectedShopUrl, "Actual URL is not the same as expected ");

//		Verifying Funny Cow name
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[1]/td[1]")));
		WebElement cowName = driver4.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[1]/td[1]"));
		String expected_cow_name = "Funny Cow";
		String actual_cow_name = cowName.getText();
		Assert.assertTrue(actual_cow_name.contains(expected_cow_name), "actual and expected name are different");

//		Verifying Fluffy Bunny		

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[2]/td[1]")));

		WebElement bunnyName = driver4.findElement(By.xpath("/html/body/div[2]/div/form/table/tbody/tr[2]/td[1]"));
		String expected_bunny_name = "Fluffy Bunny";
		String actual_bunny_name = bunnyName.getText();
		Assert.assertTrue(actual_bunny_name.contains(expected_bunny_name), "actual and expected name are different");

//		close browser 
		driver4.quit();

	}

}
