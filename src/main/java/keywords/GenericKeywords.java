package keywords;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class GenericKeywords{
	public static WebDriver driver = null;
	public Properties prop = null;
	public static ExtentTest test;
	SoftAssert softAssert = null;
	public GenericKeywords() {
		prop = new Properties();
		String propertiesFile = System.getProperty("user.dir") + "\\src\\test\\resources\\projectData.properties";
		try {
			FileInputStream file = new FileInputStream(propertiesFile);
			prop.load(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		softAssert = new SoftAssert();
	}
	
	public void openBrowser(String browserKey) {
		String browserName = prop.getProperty(browserKey);
		if(browserName.equalsIgnoreCase("chrome")) {
			logInfo("Opening Browser: " + browserName.toUpperCase());
			System.out.println("Opening Browser: " + browserName.toUpperCase());
			System.getProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30000));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30000));
		} else {
			logInfo("Opening Browser: " + browserName.toUpperCase());
			System.out.println("Opening Browser: " + browserName.toUpperCase());
			System.getProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30000));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30000));
		}
	}
	
	public void openUrl(String urlKey) {
		logInfo("Opening url: " + prop.getProperty(urlKey));
		driver.get(prop.getProperty(urlKey));
	}
	
	public void enterKeyOnElement(String key, String locatorKey) {
		  logInfo("Enter value: " + prop.getProperty(key) + "in the locator:" + prop.getProperty(locatorKey));
		  getElement(locatorKey).sendKeys(prop.getProperty(key));
	}
	
	public void clickButton(String locatorKey) {
		  logInfo("Clicked the button located in " + locatorKey);
		  getElement(locatorKey).click();
	}
	
	public By getLocator(String locatorKey) {
		By by;
		if(locatorKey.endsWith("_id")) {
			by = By.id(prop.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_name")) {
			by = By.name(prop.getProperty(locatorKey));		
		}else if(locatorKey.endsWith("_xpath")) {
			by = By.xpath(prop.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_css")) {
			by = By.cssSelector(prop.getProperty(locatorKey));
		}else {
			by = By.linkText(prop.getProperty(locatorKey));
		}
		return by;
	}
	
	public WebElement getElement(String locatorKey) {
		WebElement element;
		element = driver.findElement(getLocator(locatorKey));
		return element;
	}
	
	public void quitDriver() {
		driver.quit();
	}
	
	public void setTestReport(ExtentTest test) {
		this.test = test;
	}
	
	public void logInfo(String msg) {
		test.log(Status.INFO, msg);
	}
	
	public void logSkip(String msg) {
		test.log(Status.SKIP, msg);
	}
	
	public void logWarning(String msg) {
		test.log(Status.WARNING, msg);
	}
	
	public void logError(String msg) {
		test.log(Status.FAIL, msg);
	}
	
	public void reportFail(String msg) {
		logError(msg);
		softAssert.fail(msg);
	}
	
	public void reportAll() {
		softAssert.assertAll();
	}
	
	public boolean isLoginSuccessful() {
		System.out.println(driver.getCurrentUrl());
		logInfo("Fectch current url: " + driver.getCurrentUrl());
		return driver.getCurrentUrl().contains("inventory.html");
	}

}
