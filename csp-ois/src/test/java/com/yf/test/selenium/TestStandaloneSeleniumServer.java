package com.yf.test.selenium;

import java.net.MalformedURLException;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
/**
 * 第一支 Selenium Test 測試程式
 * @author yfwong
 * @date 2018-12-08
 */
public class TestStandaloneSeleniumServer {
	//@Test
	public void testFirefoxDriver() throws MalformedURLException {
		this.execute(DesiredCapabilities.firefox());
	}
	
	@Test
	public void testChromeDriver() throws MalformedURLException {
		this.execute(DesiredCapabilities.chrome());
	}
	
	private void execute(final DesiredCapabilities capability) throws MalformedURLException {
		 System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
         ChromeOptions chromeOptions = new ChromeOptions();
         chromeOptions.addArguments("--headless");
         chromeOptions.addArguments("--no-sandbox");

         WebDriver driver = new ChromeDriver(chromeOptions);
         driver.get("https://google.com");
         try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

         if (driver.getPageSource().contains("I'm Feeling Lucky")) {
                 System.out.println("Pass");
         } else {
                 System.out.println("Fail");
         }
         driver.quit();
	}
}
