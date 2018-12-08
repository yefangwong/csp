package com.yf.test.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestStandaloneSeleniumServer {
	@Test
	public void testFirefoxDriver() throws MalformedURLException {
		this.execute(DesiredCapabilities.firefox());
	}
	
	@Test
	public void testChromeDriver() throws MalformedURLException {
		this.execute(DesiredCapabilities.chrome());
	}
	
	private void execute(final DesiredCapabilities capability) throws MalformedURLException {
		WebDriver driver = new RemoteWebDriver(new URL("http://172.16.171.1:4444/wd/hub"),
				capability);
		driver.get("http://www.javacodegeeks.com");
		WebElement element = driver.findElement(By.name("s"));
		element.sendKeys("selenium");
		element.submit();
		assertThat(driver.getTitle(),
				is("You searched for selenium | Java Code Geeks"));
		driver.quit();
	}
}
