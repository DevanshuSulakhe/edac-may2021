package testBase;

import java.nio.file.Paths;

import java.time.Duration;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
    public WebDriver driver;

    public WebDriver openBrowser(String browserType) {
        try {
            if (browserType.equals("Mozilla")) {
                FirefoxOptions op = new FirefoxOptions();
                op.addArguments("-private");
                op.addArguments("--headless");
                // op.addArguments("--window-size=1024x768");
                // op.addArguments("--window-size=1024x768");
                op.addArguments("disable-infobars");
                op.addArguments("--incognito");
                op.addArguments("--no-sandbox");
                op.addArguments("--disable-dev-shm-usage");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(op);
                // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


			}  else if (browserType.equals("Chrome")) {
                ChromeOptions op = new ChromeOptions();
                op.addArguments("--headless");
//                op.addArguments("--headless");
                op.addArguments("--window-size=1024x768");
                op.addArguments("disable-infobars");
                op.addArguments("--incognito");
                op.addArguments("--no-sandbox");
                op.addArguments("--disable-dev-shm-usage");
                op.setBrowserVersion("latest");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(op);
                
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }
    public WebDriver openBrowserProfile(String browserType) {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", Paths.get("").toAbsolutePath().toString());
        try {
            if (browserType.equals("Mozilla")) {
                FirefoxOptions op = new FirefoxOptions();
                op.addArguments("-private");
                op.addArguments("--headless");
                op.addArguments("--window-size=1024x768");
                op.addArguments("disable-infobars");
                op.addArguments("--incognito");
                op.addArguments("--no-sandbox");
                op.addArguments("--disable-dev-shm-usage");
                WebDriverManager.chromiumdriver().setup();
                driver = new FirefoxDriver(op);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            } else if (browserType.equals("Chrome")) {
                ChromeOptions op = new ChromeOptions();
//				op.addArguments("--headless");
                // size=(1024, 768))
                op.addArguments("--window-size=1024x768");
                op.setExperimentalOption("prefs", chromePrefs);
                op.addArguments("disable-infobars");
                op.addArguments("--incognito");
                op.addArguments("--no-sandbox");
                op.addArguments("--remote-allow-origins=*");
                op.addArguments("--disable-dev-shm-usage");
                op.setBrowserVersion("latest");
                op.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(op);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
                driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(40));
                driver.manage().deleteAllCookies();
            }

           ;



        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }
}