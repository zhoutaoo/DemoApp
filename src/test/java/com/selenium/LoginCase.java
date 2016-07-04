package com.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class LoginCase {

	private InternetExplorerDriver driver;

	@Before
	public void setUp() throws Exception {
		// System.setProperty("webdriver.chrome.driver",
		// "D:/Program Files/Google/Chrome/Application/chromedriver.exe");
		driver = new InternetExplorerDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	@Test
	public void loginCase() {

		driver.get("http://localhost:8080/jltms");
		WebElement username = driver.findElement(By.id("j_username"));
		WebElement password = driver.findElement(By.id("j_password"));
		WebElement submit = driver.findElement(By.className("sub"));
		username.sendKeys("admin");
		password.sendKeys("admin");
		submit.submit();

		Assert.assertEquals("华太", driver.getTitle());
		takeScreenShot(driver.getTitle());
	}

	public void takeScreenShot(String name) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
