package testBase;

import java.io.IOException;
import java.util.Objects;

import org.openqa.selenium.WebDriver;


public  class DriverFactory {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private DriverFactory() {

	}
//
//	private static DriverFactory driverInstance = new DriverFactory();

	private static DriverFactory driverInstance = new DriverFactory();

	public static   DriverFactory getInstance() {
		if(driverInstance ==null) {
			driverInstance = new DriverFactory();
		}
		return driverInstance;
	}

	public static WebDriver getDriver() {
		if(driver==null) {
			driver.get();}
		return driver.get();
	}

	public static void setDriver(WebDriver driverParam) {
		driver.set(driverParam);

	}




	public static void removeDriver() throws IOException, InterruptedException {
		if (Objects.nonNull(getDriver())) {
//			driver.remove();
			Thread.sleep(2000);
			DriverFactory.getInstance().getDriver().quit();
		}
	}

//		public  static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//
//		private DriverFactory() {
//
//		}
//
//		//private static DriverFactory driverInstance = new DriverFactory();
//
////	public static  DriverFactory getInstance() {
////		return driverInstance;
////	}
//
//		public static WebDriver getDriver() {
//			return driver.get();
//		}
//
//		public  static WebDriver setDriver(WebDriver driverParam) {
//			driver.set(driverParam);
//			return driverParam;
//		}
//
//		public  static void closeBrowser() {
//			if(Objects.nonNull(getDriver())) {
//				getDriver().quit();
//				driver.remove();
//			}
//		}
}

