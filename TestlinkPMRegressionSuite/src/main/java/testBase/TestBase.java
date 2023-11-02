package testBase;

import com.epam.reportportal.example.cucumber6.attributes.Constants;
import com.google.common.base.Function;
import com.spire.doc.Document;

import com.spire.doc.FileFormat;
import me.champeau.ld.UberLanguageDetector;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.hamcrest.collection.IsMapContaining;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class TestBase {
    public static String getEOEVal;
    static Properties prop = new Properties();

    public static Map<String, Object> flData = new HashMap<>();
    public static ArrayList<String> FLUser = new ArrayList<>();

    public static org.slf4j.Logger APP_LOGS = LoggerFactory.getLogger(TestBase.class);
    public static ArrayList<String> Users = new ArrayList<>();
    public static ArrayList<String> assignmentNumber = new ArrayList<>();
    public static ArrayList<String> countofasn = new ArrayList<>();
    public static List<String> listOfElementsOnASN = new ArrayList<>();
    public static List<String> listOfElementsOnInterfaces = new ArrayList<>();
    public static TreeMap<String, String> getASNInfoOnInterfaces = new TreeMap<String, String>();
    public static TreeMap<String, String> getASNInfo = new TreeMap<String, String>();

    public static int index = 0;
    private final String XPATH = "xpath";
    private final String NAME = "name";
    private final String CLASS = "class";
    private final String CSS = "css";
    private final String ID = "id";
    private final String LINKTEXT = "linktext";
    private final String TAGNAME = "tagname";
    public Connection conn = null;
    static Statement statement;
    static ResultSet resultSet;
    static By locator;
    // File file;
    // Paths.get("").toAbsolutePath().toString()
    // Path filePath =
    // Paths.get(Paths.get("").toAbsolutePath().toString()+"\\TrashData");
    private String locatorType;
    private String locatorValue;
    public final int getVal = 0;
    public String language = null;
    Object[] objArray = new Object[4];
    WebDriverWait wait;
    public static String screenshotSubFolderName;
    int retryCounter = 0;

    // BrowserFactory bf = new BrowserFactory();

    public void launchApplication(String objectRepo) throws IOException {
        try {
            String Browser = getvalueFrompropFile("Browser", objectRepo);
            String url = getvalueFrompropFile("CAWUrl", objectRepo);

            DriverFactory.setDriver(new BrowserFactory().openBrowserProfile(Browser));
            WebDriver driver = DriverFactory.getInstance().getDriver();
            System.out.println("Driver reference: {}" + driver);
            driver.manage().window().maximize();
            driver.navigate().to(url);
            driver.manage().deleteAllCookies();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void launchApp_CustomProfile(String objectRepo) throws IOException {

        try {
            String Browser = getvalueFrompropFile("Browser", objectRepo);
            String url = getvalueFrompropFile("PMUATUrl", objectRepo);

            DriverFactory.setDriver(new BrowserFactory().openBrowserProfile(Browser));
            WebDriver driver = DriverFactory.getInstance().getDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.navigate().to(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static enum CheckPoints {
        YES, ITISBOTHINFORMATIVEANDCONSISE,
    }

    ;

    public void waitForElement(String object) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(setLocatorType(object)));
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }


    public String returnRandomStringFormat(int indices) throws IOException, SQLException, ClassNotFoundException {
        // ArrayList<String> assignmentNumber = new ArrayList<>();

        index = indices;

        if (assignmentNumber.isEmpty()) {
            for (int i = 0; i < Constants.iteration; i++) {
                assignmentNumber.add(getRandomString() + "-" + getRandomNumber());
                APP_LOGS.info(Collections.synchronizedList(assignmentNumber).get(i));
            }
            //	dbRecordExist(Constants.checkAsnInDB, String.valueOf(assignmentNumber.get(index)));
            APP_LOGS.info(assignmentNumber.get(index));
            return assignmentNumber.get(index);

        } else {
            //	APP_LOGS.info(assignmentNumber.get(index));


        }
        //return assignmentNumber.get(index);
        return String.valueOf(assignmentNumber.get(index));
        //return dbRecordExist(Constants.checkAsnInDB,String.valueOf(assignmentNumber.get(index)));
    }


    public String ValidateASNStatus(String object) {
        int n = 0;

        WebDriver driver = DriverFactory.getInstance().getDriver();
        for (int i = 0; i < 1; i++) {
            String totalcount = driver.findElement(setLocatorType(object)).getText();
            countofasn.add(totalcount);

        }

        return countofasn.get(n);
    }


    public static String getvalueFrompropFile(String key, String GEOLocators) throws IOException {
        FileReader reader = null;
        try {
            reader = new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                    + File.separator + "resources" + File.separator + "locators" + File.separator + "AllGeoLocators"
                    + File.separator + "QA" + File.separator + GEOLocators + ".properties");
            prop.load(reader);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return prop.getProperty(key);
    }

    public static String getvalueFrompropFileforStage(String key, String GEOLocators) throws IOException {
        FileReader reader = null;
        try {
            reader = new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                    + File.separator + "resources" + File.separator + "locators" + File.separator + "AllGeoLocators"
                    + File.separator + "Stage" + File.separator + GEOLocators + ".properties");
            prop.load(reader);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return prop.getProperty(key);
    }

    public boolean verifyText(String object, String data) {
        APP_LOGS.debug("Verifying the text");

        WebDriver driver = DriverFactory.getInstance().getDriver();
        try {
            String expected = data;

            String actual = driver.findElement(setLocatorType(object)).getText().trim();

            APP_LOGS.info("Actual Text is : " + actual + " and Expected text is " + expected);

            APP_LOGS.info("Actual Text is {} : ", actual);
            if (actual.equalsIgnoreCase(expected))
                return true;
            else
                return false;
        } catch (Throwable e) {

            return false;
        }
    }


    public void refresh() {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.navigate().refresh();

    }



    public boolean isElementPresent(String objectName) {
        APP_LOGS.info("Checking object {} presence ", objectName);

        WebDriver driver = DriverFactory.getInstance().getDriver();
        int count = driver.findElements(setLocatorType(objectName)).size();
        if (count == 0)
            return false;
        else
            return true;
    }

    public boolean isNumberOfElementsPresent(String objectName, int listSize) {
        APP_LOGS.info("Checking object presence {} ", objectName);

        WebDriver driver = DriverFactory.getInstance().getDriver();
        int count = driver.findElements(setLocatorType(objectName)).size();
        if (count == listSize)
            return true;
        else
            return false;
    }

    public void loadPage() {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(Constants.Wait)));
        wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState")
                .equals("complete"));
    }

    public void getCount(String object, String GEOLocators) throws IOException {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        String totalcount = driver.findElement(setLocatorType(object)).getText();
        storevalueInpropFile("Usertotalcount", totalcount, GEOLocators);
    }

    public void getActualDeadline(String object, String GEOLocators) throws IOException {
        WebDriver driver = DriverFactory.getInstance().getDriver();

        String ActualDeadline = driver.findElement(setLocatorType(object)).getText();
        storevalueInpropFile("ActualDeadline", ActualDeadline, GEOLocators);
    }


    public void compareDetails() throws SQLException, IOException, ClassNotFoundException {
        try {

            assertThat(getASNInfo, IsMapContaining.hasValue(getASNInfoOnInterfaces.get("sczdvxfcbg")));
            assertThat(getASNInfo, IsMapContaining.hasValue(getASNInfoOnInterfaces.get("zsdxcv")));
            assertThat(getASNInfo, IsMapContaining.hasValue(getASNInfoOnInterfaces.get("xc")));
            assertThat(getASNInfo, IsMapContaining.hasValue(getASNInfoOnInterfaces.get("xc")));
            assertThat(getASNInfo, IsMapContaining.hasValue(getASNInfoOnInterfaces.get("dfg")));
            assertThat(getASNInfo, IsMapContaining.hasValue(getASNInfoOnInterfaces.get("szdxf")));
            assertThat(getASNInfo, IsMapContaining.hasValue(getASNInfoOnInterfaces.get("zxdx")));
            assertThat(getASNInfo, IsMapContaining.hasValue(getASNInfoOnInterfaces.get("zsdxfc")));
            assertThat(getASNInfo, IsMapContaining.hasValue(getASNInfoOnInterfaces.get("sadf")));
            assertThat(getASNInfo, IsMapContaining.hasValue(getASNInfoOnInterfaces.get("Sdxf")));
            APP_LOGS.info("Checkdetails for Assignment Info for" + returnRandomStringFormat(index) + getASNInfo.values() + " is same for Checkdetails of FilePrep " + getASNInfoOnInterfaces.values());

        } catch (Exception e) {
            APP_LOGS.info("Checkdetails for Assignment Info for" + returnRandomStringFormat(index) + getASNInfo.values() + " is not same for Checkdetails of FilePrep " + getASNInfoOnInterfaces.values());
        }

    }

    public boolean compareDataStructure() {
        boolean found = false;
        if (getASNInfo.equals(getASNInfoOnInterfaces)) {
            APP_LOGS.info("Both data storage are compared to same");
            found = true;
        }
        return found;
    }


    /*

     * @param - locator requies
     * Returns list of objects
     */
    public void listingOfObjectsOnASN(String Object) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        List<WebElement> listOfData = driver.findElements(setLocatorType(Object));
        for (WebElement ele : listOfData) {
            listOfElementsOnASN.add(ele.getText());
        }
    }

    /*

     * @param - locator requies
     * Returns list of objects on multiple intefaces as per main ASN info
     */
    public void listingOfObjectsOnInterfaces(String Object) {
        try {
            getASNInfoOnInterfaces.clear();
            listOfElementsOnInterfaces.clear();
            WebDriver driver = DriverFactory.getInstance().getDriver();
            List<WebElement> listOfData = driver.findElements(setLocatorType(Object));
            for (WebElement ele : listOfData) {
                listOfElementsOnInterfaces.add(ele.getText());
            }
            Map<String, List<String>> bigMap =
                    listOfElementsOnInterfaces.stream()
                            .collect(Collectors.toMap(java.util.function.Function.identity(), e -> new ArrayList<String>()));
            for (Map.Entry<String, List<String>> entry : bigMap.entrySet()) {
                getASNInfoOnInterfaces.put(entry.getKey(), entry.getKey());
            }
        } catch (Exception e) {
            APP_LOGS.error("There is some error detected while adding ASN Info details on interface; please check", e);
        }
    }


    /* Ensures all checkboxes are checked as part of review
     * Scroll webELement using JavaScript Executor
     * @param- object
     */
    public void reviewCheckboxes(String object) {
        String objectArray[] = prop.getProperty(object).split(Constants.OBJECT_SPLIT);
        WebDriver driver = DriverFactory.getInstance().getDriver();
        int counter = 0;
        boolean found = false;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        while (counter <= Constants.retryCounter) {
            try {
                // validate if all dropdowns are selected before file prep click
                for (int i = 0; i < driver.findElements(setLocatorType(objectArray[0])).size(); i++) {
                    found = true;
                    if (found) {
                        scrollToElementUsingJS(objectArray[0]);
                        pause("1");
                        jse.executeScript(Constants.jsConstant, driver.findElements(setLocatorType(objectArray[0])).get(i));
                        //   driver.findElements(setLocatorType(objectArray[0])).get(i).click();
                        APP_LOGS.info("enabling {i} each review checklist on Interface");
                    }
                }
            } catch (StaleElementReferenceException e) {
                APP_LOGS.warn("Handling stale element reference exception while selecting the drop down values");
                counter++;
                continue;
            } catch (Exception e) {
                APP_LOGS.warn("Handling runtime exception while reviewing checklist in delivery prep");
                counter++;
                continue;
            }
            break;
        }
    }


    public String LastLineInfo(String object) {
        int n = 0;
        WebDriver driver = DriverFactory.getInstance().getDriver();
        for (int i = 0; i < 1; i++) {
            String Information = driver.findElement(setLocatorType(object)).getText();
            Users.add(Information);

        }

        return Users.get(n);
    }





    /*
     * Below utility method identifies the Operating System and kills getDriver() &
     * opened browser at run time; Author Rarya
     */
    public void killProcess() throws Exception {
        try {

            APP_LOGS.info(System.getProperty("os.name"));
            if (System.getProperty("os.name").equalsIgnoreCase("MAC OS X")) {
                Runtime.getRuntime().exec(Constants.macKillDriver);
                Runtime.getRuntime().exec(Constants.macKillBrowser);
            } else if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
                Runtime.getRuntime().exec(Constants.linuxKillDriver);
                Runtime.getRuntime().exec(Constants.macKillBrowser);
            } else {
                Runtime.getRuntime().exec(Constants.windKillBrowser);
                Runtime.getRuntime().exec(Constants.WindKillDriver);

            }
        } catch (IndexOutOfBoundsException e) {
            APP_LOGS.error(e.toString(), "Script could not kill browser and its getDriver() while execution happens");
        } catch (Exception e) {
            APP_LOGS.error(e.toString(), "Script could not kill browser and its getDriver() while execution happens");
        }
    }

    /**
     * Keyword to wait for file to be downloaded at respective location Author -
     *
     * @param timeoutSeconds
     */
    public boolean verifyFileDownloaded(String fileName, int timeoutSeconds) {
        boolean found = false;
        try {
            File dir = new File(Paths.get("").toAbsolutePath().toString());
            for (int waitForDownload = 0; waitForDownload < timeoutSeconds; waitForDownload++) {
                File[] dir_contents = dir.listFiles();
                for (int i = 0; i < dir_contents.length; i++) {
                    if (dir_contents[i].getName().equals(fileName)) {
                        APP_LOGS.info("File {} has downloaded successfully to respective location", fileName);
                        return found = true;
                    }
                }
            }
        } catch (Exception e) {
            APP_LOGS.info("File {} has not downloaded successfully to respective location; please check", fileName);

        }
        return found;
    }


    public void getTotalCount(String object, String GEOLocators) throws IOException {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        String totalcount = driver.findElement(setLocatorType(object)).getText();
        storevalueInpropFile("GroupBytotalcount", totalcount, GEOLocators);
    }

    public void getDate(String object, String GEOLocators) throws IOException {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        String getdate = driver.findElement(setLocatorType(object)).getText();
        storevalueInpropFile("Clientdeadline", getdate, GEOLocators);
    }


    /**
     * Verifies whether the expected string contains the specified sequence of char
     * values on the web page
     *
     * @param object locator which is used to find the element like
     *               id,name,class,xpath etc.
     * @param data   expected text to be present on the web page
     * @return PASS/FAIL
     */
    public boolean verifyTextContains(String object, String data) {
        APP_LOGS.debug("Verifying the text");

        WebDriver driver = DriverFactory.getInstance().getDriver();
        try {
            String actual = driver.findElement(setLocatorType(object)).getText().trim();
            String expected = data;
            System.out.println("Actual text is " + actual + "and expected text is " + expected);
            if (actual.contains(expected))
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void checkAssignmentDetails(String object, String data) throws InterruptedException, IOException {
        String objectArray[] = prop.getProperty(object).split(Constants.OBJECT_SPLIT);
        String CS_DataMatch[] = prop.getProperty("CS_Data").split(Constants.DATA_SPLIT);
        verifyText(objectArray[0], CS_DataMatch[17]);
        verifyText(objectArray[1], CS_DataMatch[13]);
        verifyText(objectArray[2], CS_DataMatch[18]);
        verifyText(objectArray[3], CS_DataMatch[19]);
        verifyText(objectArray[4], CS_DataMatch[20]);
        verifyText(objectArray[5], CS_DataMatch[6]);
        verifyText(objectArray[6], CS_DataMatch[3]);
        verifyText(objectArray[7], CS_DataMatch[3]);
        verifyText(objectArray[8], CS_DataMatch[21]);
        verifyText(objectArray[8], CS_DataMatch[3]);
        verifyText(objectArray[8], CS_DataMatch[10]);
    }

    public void sendHumanKeys(String object, String data) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        Random r = new Random();
        for (int i = 0; i < data.length(); i++) {
            try {
                Thread.sleep((int) (r.nextGaussian() * 15 + 100));
            } catch (InterruptedException e) {
            }
            String s = new StringBuilder().append(data.charAt(i)).toString();
            driver.findElement(setLocatorType(object)).sendKeys(s);
        }
    }

    public boolean verifyTwoObjects(String object, String object1) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        String object12 = driver.findElement(setLocatorType(object)).getText();
        String object10 = driver.findElement(setLocatorType(object1)).getText();
        if (object12.contentEquals(object10)) {
            APP_LOGS.info(object10, "matches to ", object12);
            return true;
        } else {

            APP_LOGS.debug(object12, " not matches to {}", object10);

            return false;
        }
    }

    /**
     * Scrolls down to specified element
     *
     * @return PASS/FAIL
     * @author Ranjan G
     */
    public void scrollDown() {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)");
    }

    public boolean verifybuttonnoteditable(String object) {

        APP_LOGS.debug("Verifying checkbox selected");
        WebDriver driver = DriverFactory.getInstance().getDriver();
        try {
            String checked = driver.findElement(setLocatorType(object)).getAttribute("disabled");
            if (checked != null)
                return true;
            else
                return false;

        } catch (Throwable e) {
            return false;
        }
    }

    public void enterTextfromfile(String object, String data) {
        APP_LOGS.info("Writing in text box");
        WebDriver driver = DriverFactory.getInstance().getDriver();
        String datafromfile = prop.getProperty(data);
        if (driver.findElement(setLocatorType(object)).isEnabled()) {
            driver.findElement(setLocatorType(object)).clear();
            driver.findElement(setLocatorType(object)).sendKeys(datafromfile);
        }
    }

    /**
     * Mouse hover to the element and then click on the particular Link
     *
     * @param object Locator of the element
     * @author Ranjan Gupta
     */

    public void mouseHoverAndClick(String object) {
        APP_LOGS.info("Hovering and clicking on link " + object);
        WebDriver driver = DriverFactory.getInstance().getDriver();
        WebElement element = driver.findElement(setLocatorType(object));
        new Actions(driver).moveToElement(element).moveToElement(driver.findElement(setLocatorType(object))).click()
                .build().perform();
    }

    public void hardRefresh() throws AWTException, NumberFormatException, InterruptedException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL + KeyEvent.VK_SHIFT + KeyEvent.VK_R);
        pause("1");
        robot.keyRelease(KeyEvent.VK_CONTROL + KeyEvent.VK_SHIFT + KeyEvent.VK_R);
    }






    /**
     * @param object
     */
    public void stubbornClick(String object) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        boolean clicked = false;
        if (driver.findElement(setLocatorType(object)).isDisplayed()) {
            click(object);
            clicked = true;
        }
        if (clicked) {
            APP_LOGS.info("User alerts got accepted successfully {}");
        } else {
            click(object);
        }
    }

    /**
     * @param time
     */
    public void retryClick(int time, String locator) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        while (retryCounter <= Constants.retryCounter) {
            try {
                for (int i = 0; i < time; i++) {
                    if (isElementPresent(locator)) {
                        break;
                    }
                }
                WebElement element = driver.findElement(setLocatorType(locator));
                new Actions(driver).moveToElement(element).moveToElement(element).click().build().perform();
                break;
            } catch (Exception e) {
                APP_LOGS.info(" Locator {} is not getting clicked by script ; please check the failure ", locator);
                APP_LOGS.info("Retrying {}", retryCounter);
            }
            retryCounter++;
        }

    }

    /**
     * Verifies the checkbox button whether selected or not; if it is not checked
     * then
     *
     * @param object (start and ending xpath)locator which is used to find the
     *               element like id,name,class,xpath etc.
     * @return PASS/FAIL
     */



    public void waitForElementToBestaleRefreshed(String object) {
        APP_LOGS.debug("Waiting for an element to be refreshed");
        WebDriver driver = DriverFactory.getInstance().getDriver();
        WebElement element = driver.findElement(setLocatorType(object));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
    }

    /**
     * An expectation for checking the Element presence on a page.
     *
     * @param locator used to find the element
     * @return true when the element found, false otherwise
     */
    public boolean isElementPresent(By locator) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (NoSuchElementException e) {
            {
                return false;
            }
        } catch (TimeoutException timeout) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    /**
     * Clicks on the element if the specified condition is true
     *
     * @param object locator which is used to find the element like
     *               id,name,class,xpath etc.
     * @return PASS/FAIL
     * @author Ranjan Gupta
     */
    public void clickLinkIfElementPresent(String object) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        if (isElementPresent(setLocatorType(object))) {

            WebElement element = driver.findElement(setLocatorType(object));
            new Actions(driver).moveToElement(element).click().perform();
        }
    }

    public void submit(String object) throws NumberFormatException, InterruptedException {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        APP_LOGS.info("Writing in text box" + object);
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(setLocatorType(object))).sendKeys(Keys.ENTER);
        pause("1");
    }

    /**
     * Enters the text into a text a box
     *
     * @param object locator which is used to find the element like
     *               id,name,class,xpath etc.
     * @param data   the test data to passed into the textbox
     * @return PASS/FAIL
     * @author Ranjan Gupta
     */
    public void enterText(String object, String data) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        APP_LOGS.info("Writing in text box" + object);
        driver.findElement(setLocatorType(object)).clear();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(setLocatorType(object))).sendKeys(data);
    }


    public void pressEnter() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public void pressTab() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

    }

    public void waitForElementInteractability(String object) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        APP_LOGS.info("Waiting for an element to be interactable");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable((setLocatorType(object))));
    }

    public void waitForURL(String data) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(ExpectedConditions.urlContains(getValueFromPropertiesFile(data)));
        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
        }
    }

    public void waitForAjax() {
        try {
            WebDriver driver = DriverFactory.getInstance().getDriver();
            WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(Constants.Wait)));
            ExpectedCondition<Boolean> expectation;
            expectation = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driverjs) {
                    JavascriptExecutor js = (JavascriptExecutor) driverjs;
                    return js.executeScript("return((window.jQuery != null) && (jQuery.active === 0))").equals("true");
                }
            };
            driverWait.until(expectation);
        } catch (TimeoutException exTimeout) {
        } catch (WebDriverException exWebDriverException) {
        }
    }

    public void captureScreenShot(String file) {
        try {
            WebDriver driver = DriverFactory.getInstance().getDriver();
            if (screenshotSubFolderName == null) {
                LocalDateTime myDateObj = LocalDateTime.now();
                System.out.println("Before formatting: " + myDateObj);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yy_HH_mm_ss");

                screenshotSubFolderName = myDateObj.format(myFormatObj);
                System.out.println("After formatting: " + screenshotSubFolderName);
            }
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFIle = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(System.getProperty("user.dir") + File.separator + "ScreenShot" + File.separator
                    + screenshotSubFolderName + File.separator + file);
            try {
                FileUtils.copyFileToDirectory(sourceFIle, destFile);

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Failure detected ; please check !");

        } catch (Exception e) {
            APP_LOGS.error("Failure detected while taking screenshot ; please check !");
            e.printStackTrace();
        }
    }

    public void FLUnarchive(String Object) throws InterruptedException {
        scrollToElementUsingJS("FLNavbar");
        fluentWait("FLNavbar");
        clickLinkOrButton("FLNavbar");
        waitForAjax();
        fluentWait("Filters");
        clickLinkOrButton("Filters");
        loadPage();
        fluentWait("Archievedbutton");
        clickLinkOrButton("Archievedbutton");
        waitForElementInteractability("searchFL");
        enterText("searchFL", Object);
        submit("searchFL");
        waitForAjax();
        clickLinkOrButton("selectsearchedFL");
        loadPage();
        fluentWait("Actionbutton");
        clickLinkOrButton("Actionbutton");
        fluentWait("UnArchivebutton");
        clickLinkOrButton("UnArchivebutton");
        loadPage();
    }

    public void loginApplication(String object) throws IOException, NumberFormatException, InterruptedException {
        try {
            String objectArray[] = getvalueFrompropFile("loginApplicationLocators", Constants.createASNLocator)
                    .split(Constants.OBJECT_SPLIT);
            pause("2");
            launchApplication(Constants.createASNLocator);
//              clickLinkOrButton(objectArray[0]);
            waitForElementInteractability(objectArray[1]);
            enterText(objectArray[1], getValueFromPropertiesFile("CSadminmail"));
            fluentWait(objectArray[2]);
            enterText(objectArray[2], getValueFromPropertiesFile("password"));
            clickLinkOrButton(objectArray[3]);
            waitForURL("PMURL");
            waitForAjax();
            if (verifyText("alertforlogout", "User already logged in. Log out from other devices and try again.") == true) {

                WebDriver driver = DriverFactory.getInstance().getDriver();
                fluentWait("logoutfromotherdevices");
                clickLinkOrButton("logoutfromotherdevices");
                driver.get(getValueFromPropertiesFile("PMUATUrl"));
                waitForElementInteractability(objectArray[1]);
                enterText(objectArray[1], getValueFromPropertiesFile("CSadminmail"));
                fluentWait(objectArray[2]);
                enterText(objectArray[2], getValueFromPropertiesFile("password"));
                clickLinkOrButton(objectArray[3]);
//                waitForAjax();
                waitForURL("PMURL");
            }
        } catch (Exception e) {
            String objectArray[] = getvalueFrompropFile("loginApplicationLocators", Constants.createASNLocator)
                    .split(Constants.OBJECT_SPLIT);
            WebDriver driver = DriverFactory.getInstance().getDriver();
            driver.get(getValueFromPropertiesFile("PMUATUrl"));
            waitForElementInteractability(objectArray[1]);
            enterText(objectArray[1], getValueFromPropertiesFile("CSadminmail"));
            fluentWait(objectArray[2]);
            enterText(objectArray[2], getValueFromPropertiesFile("password"));
            clickLinkOrButton(objectArray[3]);
            waitForAjax();
            waitForURL("PMURL");
        }

    }










    public void doubleClick(String object) {
        APP_LOGS.info("Double clicking on link {} ", object);
        WebDriver driver = DriverFactory.getInstance().getDriver();
        WebElement element = driver.findElement(setLocatorType(object));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).doubleClick().build().perform();
    }

    public void StoreValInPropertyFile(String value) {
        prop.setProperty(prop.getProperty("GlobalAsnNumber"), value);
    }


    public boolean verifyCheckBoxSelected(String object) {
        APP_LOGS.debug("Verifying checkbox selected");
        try {
            String checked = DriverFactory.getInstance().getDriver().findElement(setLocatorType(object))
                    .getAttribute("checked");
            if (checked != null)
                return true;
            else
                return false;

        } catch (Throwable e) {
            return false;
        }
    }


    public String extendeddays() {
        Date d = new Date();
        //outputting the date in user reading format.
        SimpleDateFormat dF = new SimpleDateFormat("EEE, MMM dd, yyyy hh:mm");
        String dS = dF.format(d);
        System.out.println(" The present date is : " + dS);
        // creating the instance for calendar class
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        // adding 3 years, 2 months, and 1 day
        // c.add(Calendar.YEAR, 3) ;
        // c.add(Calendar.MONTH, 2) ;
        c.add(Calendar.DATE, 3);
        // getting the new date from the calendar
        Date d1 = c.getTime();

        // printing the new date
        String newDate = dF.format(d1);
        System.out.println(" The updated date is : " + newDate);
        return newDate;
    }


    public String StageExtendedDays() {
        Date d = new Date();
        //outputting the date in user reading format.
        SimpleDateFormat dF = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        String dS = dF.format(d);
        System.out.println(" The present date is : " + dS);
        // creating the instance for calendar classs
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        // adding 3 years, 2 months, and 1 day
        // c.add(Calendar.YEAR, 3) ;
        // c.add(Calendar.MONTH, 2) ;
        c.add(Calendar.DATE, 2);
        // getting the new date from the calendar
        Date d1 = c.getTime();

        // printing the new date
        String newDate = dF.format(d1);
        System.out.println(" The updated date is : " + newDate);
        return newDate;
    }

    public String ReduceDays(int amount) {
        Date d = new Date();
        //outputting the date in user reading format.
        SimpleDateFormat dF = new SimpleDateFormat("EEE, MMM dd, yyyy hh:mm");
        String dS = dF.format(d);
        System.out.println(" The present date is : " + dS);
        // creating the instance for calendar class
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        // adding 3 years, 2 months, and 1 day
        // c.add(Calendar.YEAR, 3) ;
        // c.add(Calendar.MONTH, 2) ;
        c.add(Calendar.DATE, amount);
        // getting the new date from the calendar
        Date d1 = c.getTime();

        // printing the new date
        String newDate = dF.format(d1);
        System.out.println(" The updated date is : " + newDate);
        return newDate;
    }






    /**
     * Submits the entered text
     *
     * @param data
     * @return PASS/FAIL
     * @author Ranjan Gupta
     */

    public void pause(String data) throws NumberFormatException, InterruptedException {
        long time = (long) Double.parseDouble(data);
        Thread.sleep(time * 1000L);
    }

    public static String getRandomString() {

        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZaaccddffvvwwsdtegfjddsdsdkjklmnopqrstuvwxyz";

        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            salt.append(CHARS.charAt(index));
        }
        String saltStr = salt.toString().toUpperCase();
        return saltStr;
    }

    /*
     * Utility method to generate random numbers for numerous purpose
     *
     * @argument - integer Author @Rarya
     */
    public static String getRandomNumber() throws IOException {

        String CHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 2) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            salt.append(CHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    /**
     * Select the random value from a drop down if the input parameter is not equal
     * to "Random Value" else it will select the specified value from drop down
     *
     * @param object , locator to be passed
     * @param data   , Input parameter
     * @return PASS/FAIL
     * @throws InterruptedException
     * @author Ranjan Gupta
     */
    public void selectList(String object, String data) throws InterruptedException {
        APP_LOGS.info("Selecting from list");

        WebDriver driver = DriverFactory.getInstance().getDriver();
        try {
            // logic to find a random value in list
            List<WebElement> droplist_cotents = driver.findElements(setLocatorType(object));
            for (int i = 0; i < droplist_cotents.size(); i++) {
                if (droplist_cotents.get(i).getText().equalsIgnoreCase(data))
                    ;
                droplist_cotents.get(i).click();
            }
        } catch (Exception e) {
            APP_LOGS.info("Getting exception ; please check {}", e.getMessage());
            ;
        }
    }

    /*
     * Author - Devanshu Sulakhe Entering the 1 month Extended date from the Current
     * Date in the given format
     */

    public String extendeddate() throws IOException {


        WebDriver driver = DriverFactory.getInstance().getDriver();
        // Create object of SimpleDateFormat class and decide the format
        DateFormat dayname = new SimpleDateFormat("EEE");
        DateFormat monthFormat = new SimpleDateFormat("MM");
        DateFormat dayFormat = new SimpleDateFormat("dd");
        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy hh:mm");
        // get current date time with Date()
        Date date = new Date();
        // Now format the date
        String date0 = dayname.format(date);
        String date1 = yearFormat.format(date);
        String date5 = timeFormat.format(date);
        String date2 = monthFormat.format(date);
        String date3 = dayFormat.format(date);
        int MonthInteger = Integer.parseInt(date2); // month
        int dayInteger = Integer.parseInt(date3);
        if (dayInteger == 31 || dayInteger == 30 || dayInteger == 29) {
            MonthInteger = MonthInteger + 1;
            dayInteger = 2;
        }

//        if (dayInteger == 31 || dayInteger == 30 || dayInteger == 29) {
//            dayInteger = 28;
//        }
        int DateIntegar = dayInteger + 10;

        int date4 = MonthInteger;// month incremented
        if (date4 == 13) {
            date4 = 1;
        }

//        int date4 = dateInteger + 1;// month incremented
//        if (date4 == 13) {
//            date4 = 1;
//        }
        int yearIntegar = Integer.parseInt(date1);
        int date6 = yearIntegar;
//        if (date4 == 1) {
//            date6 = yearIntegar + 1;
//        }

//        String totaldate = date0 + ", " + date4 + " " + dayInteger + ", " + date6 + " " + date5;
        String totaldate = date0 + ", " + date4 + " " + DateIntegar + ", " + date6 + " " + date5;

        APP_LOGS.info(totaldate);
        return (totaldate);

    }


    public void GetTimeInHours(String object) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        String ActualTime = driver.findElement(setLocatorType(object)).getText().trim();
        double TimeInHours1 = Double.parseDouble((ActualTime.substring(0, 1))) * 24;
        double TimeInHours2 = Double.parseDouble((ActualTime.substring(4, 6)));
        double TimeInHours3 = Double.parseDouble((ActualTime.substring(10, 12))) / 60;

        double TimeInHours = TimeInHours1 + TimeInHours2 + TimeInHours3;
        flData.put("TimeTODeadLine", TimeInHours);
        APP_LOGS.info("ASN Client Deadline in Hours =" + TimeInHours);
    }


    public boolean VerifyMinMaxValue(String object, double MINPercentage, double MAXPercentage, double days) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        final DecimalFormat decfor = new DecimalFormat("0.00");
        boolean value = false;
        double MinValue = Double.parseDouble(decfor.format(MINPercentage / 100 * Double.parseDouble(flData.get("TimeTODeadLine").toString())));
        double MaxValue = Double.parseDouble(decfor.format(MAXPercentage / 100 * Double.parseDouble(flData.get("TimeTODeadLine").toString())));
        String TATAsPerBiz = driver.findElement(setLocatorType(object)).getText().trim();
        double TATHours = Double.parseDouble(TATAsPerBiz);
        APP_LOGS.info("Verifying Value is in Between Min and Max Value{}", TATHours);
        if ((TATHours >= MinValue) && (TATHours <= MaxValue)) {
            value = true;
            APP_LOGS.info(TATHours + " TATHours value is showing as expected on Auto Allocation Management screen");
        }

        return value;
    }


    public LocalDate FLDeadlineDate() throws IOException {
//		Date today = new Date();
//		LocalDate today =  LocalDate.now();
        LocalDate today = LocalDate.now();
        LocalDate ld2 = today.plusDays(1);

        return (ld2);

    }

    public LocalDate DeadlineDate() throws IOException {
//		Date today = new Date();
//		LocalDate today =  LocalDate.now();
        LocalDate today = LocalDate.now();
        LocalDate ld2 = today.plusDays(30);

        return (ld2);

    }


    /**
     * To select the value of a drop down using its visible text.
     *
     * @param object locator used to find the element
     * @param data   which is used to select the visible text from dropdown
     * @return PASS/FAIL
     * @throws InterruptedException
     * @throws NumberFormatException
     * @author Ranjan Gupta
     */
    public void selectByText(String object, String data) throws NumberFormatException, InterruptedException {
        APP_LOGS.debug("Selecting from list");

        WebDriver driver = DriverFactory.getInstance().getDriver();
        WebElement element = driver.findElement(setLocatorType(object));
        Select select = new Select(element);
        pause("1");
        select.selectByVisibleText(data);
    }


    public String getValueFromPropertiesFile(String data) {
        return prop.getProperty(data);
    }

    public ExpectedCondition<Boolean> filepresent() {
        return new ExpectedCondition<Boolean>() {
            @Override

            public Boolean apply(WebDriver driver) { // batch.file.prep (3).xlsx C:\\TrashDataAutomation\\trashData\\
                File f = new File(Paths.get("").toAbsolutePath().toString());
                return f.exists();
            }

            @Override
            public String toString() {
                return String.format("file to be present within the time specified");
            }
        };
    }




    public void TakeScreenshots() {
        try {
            // Create a robot instance to capture the screen
            Robot robot = new Robot();

            // Get the screen dimensions
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            // Capture the screenshot
            BufferedImage screenshot = robot.createScreenCapture(screenRect);

            // Specify the output file path
            File outputFile = new File("screenshot" + returnRandomStringFormat(index) + ".png");

            // Write the captured screenshot to the output file
            ImageIO.write(screenshot, "png", outputFile);

            System.out.println("Screenshot saved to: " + outputFile.getAbsolutePath());
        } catch (AWTException | IOException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }





    public void waitTillFileDownloads(int timeToWait) {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWait));
        wait.until(filepresent());
    }

    public void fluentWait(String object) {
        try {
            WebDriver driver = DriverFactory.getInstance().getDriver();
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(setLocatorType(object));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * Perform at object 2 if object 1 is not found author -Ranjan G
     */
    public void click_SetOfObjects(String object1, String object2) {
        if (isElementPresent(setLocatorType(object1))) {
            retryClick(1, object1);
        } else {
            retryClick(1, object2);
        }
    }

    public void scrollModal() throws AWTException {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        try {
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void acceptAlertIfAvailable(long timeout) {
        long waitForAlert = System.currentTimeMillis() + timeout;
        boolean boolFound = false;
        do {
            try {
                Alert alert = DriverFactory.getInstance().getDriver().switchTo().alert();
                if (alert != null) {
                    alert.accept();
                    boolFound = true;
                }
            } catch (NoAlertPresentException ex) {
            }
        } while ((System.currentTimeMillis() < waitForAlert) && (!boolFound));
    }

    public String tgetAllLInks(String data) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        List<WebElement> links = driver.findElements(By.tagName("body"));
        APP_LOGS.info("Total links are " + links.size());
        String texts = null;
        for (int i = 0; i < links.size(); i++) {
            WebElement ele = links.get(i);
            texts = ele.getText();// getAttribute("href");
            if (ele.isDisplayed() && i == 0) {
                String getDatFromProperty = prop.getProperty(data);
                String getLangOfTextUsed = texts.replace(texts, getDatFromProperty);
                UberLanguageDetector detector = UberLanguageDetector.getInstance();
                language = detector.detectLang(getLangOfTextUsed);
                APP_LOGS.info("The country language used in webpage is having: " + language);
            }
            APP_LOGS.info("Link or text present on the current web page: " + texts);
            // verifyLinkActive(url);
        }
        return texts + " \n The country language code used on webpage is : " + language;
    }

    public void scrollUpToElement(String object) throws InterruptedException {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        try {
            Thread.sleep(1000);

            Actions a = new Actions(driver);
            // scroll down a page
            a.sendKeys(Keys.PAGE_UP).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
            Actions a = new Actions(driver);
            // scroll down a page
            a.sendKeys(Keys.PAGE_UP).build().perform();
        }
    }

    public void clickAndFeedData(String object, String data) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        String objectArray[] = prop.getProperty(object).split(Constants.OBJECT_SPLIT);
        try {
            int counter = 0;
            while (counter <= 3) {
                if (isElementPresent(setLocatorType(objectArray[0]))) {
                    ((JavascriptExecutor) driver).executeScript(Constants.jsConstant,
                            driver.findElement(setLocatorType(objectArray[0])));
                    fluentWait(objectArray[1]);
                    sendHumanKeys(objectArray[1], data);
                    ;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Scrolls down to specified element
     *
     * @param object
     * @return PASS/FAIL
     * @author Ranjan Gupta
     */
    public void scrollDownToElement(String object) throws InterruptedException {

        WebDriver driver = DriverFactory.getInstance().getDriver();
        try {
            Thread.sleep(1000);
            Actions a = new Actions(driver);
            // scroll down a page
            a.sendKeys(Keys.PAGE_DOWN).build().perform();
        } catch (Exception e) {
            e.printStackTrace();
            Actions a = new Actions(driver);
            // scroll down a page
            a.sendKeys(Keys.PAGE_DOWN).build().perform();
        }
    }

    /**
     * Uploads the file
     *
     * @param data Image file name
     * @param data file name
     * @return PASS/FAIL
     * @throws InterruptedException
     */
    public void uploadImage(String data) throws InterruptedException {

        WebDriver driver = DriverFactory.getDriver();
        WebElement uploadFile = driver.findElement(By.xpath(Constants.uploadFileLOcator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(Constants.javaScriptAttribute, uploadFile);
        String imgPath = System.getProperty("user.dir") + File.separator + "files";
        Thread.sleep(500);
        if (data.equals(Constants.testFileName)) {
            uploadFile.sendKeys(imgPath + File.separator + data);
            APP_LOGS.info("User uploading the Original file");
        } else if (data.equals(Constants.EmailTransactionfile)) {
            uploadFile.sendKeys(imgPath + File.separator + data);
            APP_LOGS.info("User uploading the Original file");
        } else if (data.equals(Constants.PDFfile)) {
            uploadFile.sendKeys(imgPath + File.separator + data);
            APP_LOGS.info("User uploading the Original file");
        } else if (data.equals(Constants.jpgfile)) {
            uploadFile.sendKeys(imgPath + File.separator + data);
            APP_LOGS.info("User uploading the Original file");
        } else if (data.equals(Constants.textfile)) {
            uploadFile.sendKeys(imgPath + File.separator + data);
            APP_LOGS.info("User uploading the Original file");
        } else if (data.equals(Constants.VIPtagfile)) {
            uploadFile.sendKeys(imgPath + File.separator + data);
            APP_LOGS.info("User uploading the Original file");
        } else {
            uploadFile.sendKeys(System.getProperty("user.dir") + File.separator + data);
            APP_LOGS.info("User uploading the downloaded file from root location");
        }
    }


    public boolean waitForElementDisplayed(String object) {

        WebDriver driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(setLocatorType(object)));
            return true;

        } catch (Exception e) {
            APP_LOGS.error(e.getMessage());
            return false;
        }
    }

    public void storevalueInpropFile(String key, String value, String GEOLocators) throws IOException {
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        try {
            File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                    + File.separator + "resources" + File.separator + "locators" + File.separator + "AllGeoLocators"
                    + File.separator + "QA" + File.separator + GEOLocators + ".properties");
            fileIn = new FileInputStream(file);

            prop.load(fileIn);
            prop.setProperty(key, value);
            fileOut = new FileOutputStream(file);
            prop.store(fileOut, "utilsEssentials");
        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            try {
                fileOut.close();
            } catch (Exception e) {
                APP_LOGS.error(e.getMessage());
            }
        }
    }

    /// ****************Application Independent functions************************
    // // opening the browser

    /**
     * Sets the type of the Locator based on the given input
     *
     * @param element locator to be passed
     * @return a By which locates elements by the value of the
     * "id/name/classname/xpath etc" attribute.
     * @author Ranjan Gupta
     */

    public By setLocatorType(String element) {

        String[] orTokens;
        try {
            orTokens = prop.getProperty(element).split("#");
        } catch (Exception e) {
            orTokens = element.split("#");
        }
        locatorType = orTokens[0];
        locatorValue = orTokens[1];
        switch (locatorType) {
            case XPATH: {
                try {
                    String[] locatorVal = locatorValue.split("!");
                    locator = By.xpath(locatorVal[0]);
                    break;
                } catch (Exception e) {
                    locator = By.xpath(locatorValue);
                    break;
                }
            }
            case ID: {
                locator = By.id(locatorValue);
                break;
            }
            case CSS: {
                locator = By.cssSelector(locatorValue);
                break;
            }
            case NAME: {
                locator = By.name(locatorValue);
                break;
            }
            case CLASS: {

                locator = By.className(locatorValue);
                break;
            }
            case LINKTEXT: {

                locator = By.linkText(locatorValue);
                break;
            }
            case TAGNAME: {

                locator = By.tagName(locatorValue);
                break;
            }
            default: {

                locator = null;
                break;
            }
        }
        return locator;

    }

    // navigates to a URL
    public void navigate(String URL) {

        WebDriver driver = DriverFactory.getDriver();
        APP_LOGS.info("Naviating to " + prop.getProperty(URL));
        driver.get(prop.getProperty(URL));
    }

    // clicking on any object
    public void click(String objectName) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        APP_LOGS.info("Clicking on {} ", objectName);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable((setLocatorType(objectName)))).click();
    }

    public void clickLinkOrButton(String object) {
        APP_LOGS.info("Clicking on link {} ", object);
        WebDriver driver = DriverFactory.getInstance().getDriver();
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(Constants.jsConstant, driver.findElement(setLocatorType(object)));
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(Constants.jsConstant, driver.
                    findElement(setLocatorType(object)));
            click(object);
        }
    }








    public void enterData(String object, String data) throws InterruptedException {
        WebDriver driver = DriverFactory.getDriver();
        JavascriptExecutor runJS = ((JavascriptExecutor) driver);
        // runJS.executeScript("arguments[0].value='1a:b5:8e:c0:9a:99';", sendval);
        runJS.executeScript("arguments[0].value=" + "'" + data + "'" + ";", driver.findElement(setLocatorType(object)));
        Thread.sleep(1000);
        // runJS.executeScript("arguments[0].value="+data+";", sendval);
    }

    /**
     * Verifies the Link text with the expected output
     *
     * @param data expected output
     * @return PASS/FAIL
     * @author Ranjan Gupta
     */
    public boolean verifyUrl(String data) {
        APP_LOGS.info("Verifying link Text");
        try {
            String actual = DriverFactory.getInstance().getDriver().getCurrentUrl();
            String expected = getValueFromPropertiesFile(data);
            APP_LOGS.info("Actual Link Text is " + actual);
            if (actual.contains(expected))
                return true;
            else
                return false;

        } catch (Throwable e) {
            return false;
        }
    }











    public void scrollToElementUsingJS(String object) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(setLocatorType(object)));
    }






    public void AcceptTrackedChanges() throws SQLException, IOException, ClassNotFoundException {
        //Create a Document instance
        Document doc = new Document();
        //Load the sample Word document
        doc.loadFromFile("Trinka_test_"
                + returnRandomStringFormat(index).replace("-", "")
                + ".docx");
        //Accept all changes in the document
//        doc.setTrackChanges(true);


//Accepts all the tracked changes revisions.

        try {
            doc.acceptChanges();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Save the document
        doc.saveToFile("asedrfg" +
                returnRandomStringFormat(index).replace("-", "")
                + ".docx", FileFormat.Docx);


    }


    public void UpdatingDocxFile(String data, String data1) {
        try {
            // Open the existing DOCX file
            XWPFDocument doc = new XWPFDocument(
                    new FileInputStream(new File(System.getProperty("user.dir") + File.separator + data)));
            // Get the document's body element
//            doc.setTrackRevisions(true);
            XWPFParagraph para = doc.createParagraph();
            XWPFRun run = para.createRun();
            run.setText("Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!Some  texts getting updated by automaiton scripts automatically!");
            // Save the changes to the file
            FileOutputStream out = new FileOutputStream(
                    new File(System.getProperty("user.dir") + File.separator + data));
            doc.write(out);
            out.close();

            System.out.println("File updated successfully!");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }


    public void RenamefileName(String fileName, String RenamefileName) {

        // Replace "sourceFilePath" and "destinationFilePath" with the actual file paths.
        String sourceFilePath = System.getProperty("user.dir") + File.separator + "files" + File.separator + fileName;
        String destinationFilePath = System.getProperty("user.dir") + File.separator + fileName;

        try {
            // Open the source file for reading
            File sourceFile = new File(sourceFilePath);
            FileInputStream fileInputStream = new FileInputStream(sourceFile);

            // Create a new destination file for writing
            File destinationFile = new File(destinationFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);

            // Read the contents of the source file and write them to the destination file
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            // Close the source and destination files
            fileInputStream.close();
            fileOutputStream.close();

            // Rename the destination file
            String newFileName = RenamefileName; // Replace "newfile.txt" with the desired new file name
            File newFile = new File(destinationFile.getParent(), newFileName);
            if (destinationFile.renameTo(newFile)) {
                System.out.println("File copied and renamed successfully!");
            } else {
                System.out.println("Failed to rename the file.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void verifyLastLine() throws SQLException, IOException, ClassNotFoundException {
        loadPage();
        fluentWait("Lastline");

        Assert.assertTrue(verifyText("Lastline", returnRandomStringFormat(index) + " | " + getASNInfo.get("ServiceName") + " | " + getASNInfo.get("ClientDeadLine1") + getASNInfo.get("ClientDeadLine2") + "," + getASNInfo.get("ClientDeadLine3") + " " + getASNInfo.get("TimeZone") + " | " + getASNInfo.get("InputFormat") + "to " + getASNInfo.get("OutputFormat")));
    }









    public void enterTextUsingJS(String object, String data) throws InterruptedException {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        WebElement sendval = driver.findElement(setLocatorType(object));
        JavascriptExecutor runJS = ((JavascriptExecutor) driver);
        runJS.executeScript("arguments[0].value=" + "'" + data + "'" + ";", sendval);
        Thread.sleep(3000);
    }

    public void selectRandomList(String object) throws InterruptedException, AWTException {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        Random num = new Random();
        int i = 0;
        int counter = 0;
        while (counter <= Constants.retryCounter) {
            List<WebElement> list_FLRatings_allOptions = driver.findElements(setLocatorType(object));
            try {
                // logic to find a random value in list
                for (i = 0; i < list_FLRatings_allOptions.size(); i++) {
                    //   int index = num.nextInt(list_FLRatings_allOptions.size());
                    //   loadPage();
                    APP_LOGS.info("Selecting option from select drop down is " + list_FLRatings_allOptions.get(i).getText());
                    list_FLRatings_allOptions.get(i).click();
                    list_FLRatings_allOptions.get(i).click();
                    if (i == list_FLRatings_allOptions.size() - 1) {
                        break;
                    }
                }

            } catch (IndexOutOfBoundsException e) {
                APP_LOGS.info("Failure detected while selecting the ratings dropdown; please check", e.getMessage());
                hardRefresh();
                refresh();
                counter++;
                continue;
            } catch (Exception e) {
                APP_LOGS.info("Failure detected while selecting the ratings dropdown; please check", e.getMessage());
                //  hardRefresh();
                //   refresh();
                counter++;
                continue;
            }
            break;
        }
    }

    public void fillFLAdvanceReport(String object, String AROption) throws InterruptedException, AWTException, IOException {
        int counter = 0;
        String objectArray[] = prop.getProperty(object).split(Constants.OBJECT_SPLIT);
        WebDriver driver = DriverFactory.getInstance().getDriver();
        while (counter <= retryCounter) {
            try {
                List<WebElement> selectARAsYes = driver.findElements(setLocatorType(objectArray[0]));
                for (int i = 0; i < selectARAsYes.size(); i++) {
                    if (selectARAsYes.get(i).getText().trim().equalsIgnoreCase(Constants.ARWithYesOp)) {
                        APP_LOGS.info("Selecting AR option from select drop down is " + selectARAsYes.get(i).getText());
                        pause("1");
                        selectARAsYes.get(i).click();
                        break;
                    }
                }
                fluentWait(objectArray[1]);
                selectRandomList(objectArray[1]);
                fluentWait(objectArray[2]);
                selectRandomList(objectArray[2]);
                selectRandomList(objectArray[3]);
            } catch (Exception e) {
                APP_LOGS.error("There is failure in AR report section so suite tries one more time to run; please check the logs for same  ", e.getMessage());
                hardRefresh();
                counter++;
                continue;
            }
            break;
        }
    }


    public void FL_CiticalInfo() throws InterruptedException, AWTException {
        int counter = 0;
        while (counter <= retryCounter) {
            try {
                waitForURL("CriticalInfoUrlForFL");
                fluentWait("feedHrTimeTakenValue");
                enterText("feedHrTimeTakenValue", Constants.feedHrTimeTakenValue);
                fluentWait("feedMinTimeTakenValue");
                enterText("feedMinTimeTakenValue", Constants.feedMinTimeTakenValue);
                click("selectReadioButtonForTimeSufficient");
                fluentWait("selfSMERatingOptions");
                selectRandomList("selfSMERatingOptions");
                fluentWait("clickNextFromFLRatingScreen");
                retryClick(1, "clickNextFromFLRatingScreen");
            } catch (Exception e) {
                APP_LOGS.error("Critical info section is failing due to reason", e.getMessage());
                counter++;
                stubbornClick("clickPrevious");
                hardRefresh();
                continue;
            }
            break;
        }
    }

    public void scrollToBottom() {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }


    public void tickCheckboxes(String object) {
        try {
            List<WebElement> listOfCheckBox = DriverFactory.getInstance().getDriver()
                    .findElements(setLocatorType(object));
            boolean found = false;
            for (int i = 0; i < listOfCheckBox.size(); i++) {
                if (!listOfCheckBox.get(i).isDisplayed()) {
                    scrollToBottom();
                    found = true;
                }
                if (found)
                    listOfCheckBox.get(i).click();
                else {
                    listOfCheckBox.get(i).click();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void OpenDocxFile(String object) {

        File docxFile = new File(System.getProperty("user.dir") + File.separator + "Trinka_test_" + object + ".enago");

        try {
            Desktop.getDesktop().open(docxFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


