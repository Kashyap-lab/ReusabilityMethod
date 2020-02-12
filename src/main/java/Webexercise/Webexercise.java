package Webexercise;

import javafx.scene.input.DataFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class Webexercise {


    static public WebDriver driver;

    // This will Open the browser by default for all the program in re-usability method
    @Before
    public void openBrowser()
    {   //Path for the chromedriver located within Webexercise in maven
        System.setProperty("webdriver.chrome.driver", "src/test/java/Webexercise/chromedriver.exe");
        driver = new ChromeDriver();
        // This will Maximize the Chrome browser window
        driver.manage().window().maximize();
        // It will wait to allow to load the browser
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    // This will close the browser automatically after completion of task
    @After
    public void closeBrowser() {
      driver.quit();
    }
    //Time Step Method
    public String timeStamp() {
        // Email address re-usability method
        DateFormat dateFormat = new SimpleDateFormat("ddmmyyhhmmss");
        Date date = new Date();
        return (dateFormat.format(date));

    }
    // Wait for ClickOnElement
    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }
    // Wait for Clickable Method
    public void waitForClickable(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    // Wait for visibility Method
    public void waitForVisibility(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    // Wait for ElementsIsPresent
    public void waitForElementsIsPresent(By by, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    // Wait for entertext
        public void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
        waitForClickable(by, 60);
    }
    // Wait gettextfromelement
    public String getTextFromElement(By by) {
       return driver.findElement(by).getText();
    }
    //Creating method selectFromDropDownByValue
    public void selectFromDropDownByValue(By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(text);
    }
    //Creating method selectFromDropDownByVisibleText
    public void selectFromDropDownByVisibleText(By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(text);
    }
    // creating method for selectFromDropDownByIndex
    public void selectFromDropDownByIndex(By by, int index) {
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(index);
    }
    @Test
    // Main Method to start demo.nopcommerce.com
    public void UserShouldAbleToRegisterSuccessfully()
    {
        // Link to website
        driver.get("https://demo.nopcommerce.com/");

        // This will click on register
        clickOnElement(By.linkText("Register"));

        // Will wait and allow webpage to load
        WebDriverWait wait = new WebDriverWait(driver, 30);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Enter text for firstname
        enterText(By.id("FirstName"), "Kashyap");

        // Enter text last name
        enterText(By.id("LastName"), "Shah");

        // Enter Date Of Birthday
        selectFromDropDownByValue(By.name("DateOfBirthDay"), "31");

        // Enter Date Of Birtt Month
        selectFromDropDownByIndex(By.name("DateOfBirthMonth"), 7);

        // Enter Date Of Birth Year
        selectFromDropDownByVisibleText(By.name("DateOfBirthYear"), "1978");

        // Enter Email Add with the help of Time Stamp Method
        enterText(By.id("Email"), "kashyap+" + timeStamp() + "@gmail.com");

        // Enter Password
        enterText(By.id("Password"), "kashyap123");

        // Enter Confirm Password
        enterText(By.id("ConfirmPassword"), "kashyap123");

        // Will wait and allow to load the field
        waitForVisibility(By.id("ConfirmPassword"), 10);

        // Click on register Button
        clickOnElement(By.id("register-button"));

        // Will tell you the status of registration complete
        String expected = "Your registration complete";

        //Will give you the output result
        String actual = getTextFromElement(By.className("result"));
        getTextFromElement(By.name("register-continue"));

        // Expected Vs Actual result
        Assert.assertEquals("Failed", expected, actual);
    }

    @Test
    //User should able to register successfully on OrangeHRM website with Main Method
    public void OrangeHrm()
    {
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");

        // Data Provided by customer
        enterText(By.id("txtUsername"),"Admin");

        enterText(By.id("txtPassword"),"admin123"); // Data provided by customer

        // click on submit
        clickOnElement(By.name("Submit"));

        waitForElementsIsPresent(By.cssSelector("a.panelTrigger"), 70); //User is able to login successfully

        //getTextFromElement(By.name("register-continue"));
        String expected = "Welcome Admin";

        //Will give you the output result
      String actual = "a.panelTrigger";

        // Expected Vs Actual result
       Assert.assertEquals("Failed", expected, actual);
    }

    @Test
    //User should able to register successfully on Uber Rider website in main method
    public void Uber()
    {
            driver.get("https://www.uber.com/gb/en/");

        // driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        try {
            Thread.sleep(2000); // System will go on sleep mode to allow loading the homepage(very Slow)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on element entered
        clickOnElement(By.xpath("//*[@id=\"/signup/\"]/div"));

        //click on element
        clickOnElement(By.xpath("//*[@id=\"root\"]/div/div/header/div[2]/div/div/div/div/div[7]/div/div[2]/a"));

        // Enter text first name
        enterText(By.id("firstName"),"Uber");

        // Enter text last name
        enterText(By.id("lastName"),"Rider");

        // Used Dropdownmethod to select country code
        selectFromDropDownByIndex(By.xpath("//*[@id=\"answerForm\"]/div[1]/div[3]/div[1]/div/div[1]/div/div/select"),+1);

        // Enter text tel. no
        enterText(By.id("mobile"),"012345678999");

        // Used Timestamp method to create various email address every time
        enterText(By.id("email"), "test+" + timeStamp() + "@test.com");

        // Enter password
        enterText(By.id("addPassword"),"uber123");

        // Click on element
        clickOnElement(By.xpath("//button[@type='submit']")); //User is able to Register successfully

        // expected result
        String expected = "I'm not robot";

        // actual reslut
        String actual = " I'm not a robot";

        // Expected Vs Actual result
        Assert.assertEquals("Fail",expected,actual);

    }
    @Test
    //User should able to login successfully on MuckPlus in main method
    public void MockPlus() //User should able to login successfully on MuckPlus
    {
        // website address
        driver.get("https://www.mockplus.com/");

       // click on element
        clickOnElement(By.className("user-btn"));

        // Time stamp method used for email address
        enterText(By.id("email"), "test+" + timeStamp() + "@test.com");

        // enter text password
        enterText(By.id("password"), "test123");

        // enter confirm password
        enterText(By.id("cofPassword"),"test123");

        // click on element checkbox
        clickOnElement(By.id("agree"));

        // click on element
        clickOnElement(By.id("register"));

        // expected result
        String expected = "Welcome to Mockplus";

        //actual result
        String actual = getTextFromElement(By.className("logo"));

        // Expected Vs Actual result
        Assert.assertEquals("Failed", expected, actual);
    }
    @Test
    //User should able to register successfully on Ocado website in main method
    public void OcadoRegistration()
    {
        // Website addres
        driver.get("https://www.ocado.com/webshop/startWebshop.do");

        //click on element
        clickOnElement(By.id("quickReg"));

        //wait for clickable to allow to load the page correctly
        waitForClickable(By.id("registration-submit-button"),20);

        // drop down menu for Title
        selectFromDropDownByVisibleText(By.id("title"),"Mr");

        // enter first name
        enterText(By.id("firstName"),"Kashyap");

        //enter last name
        enterText(By.id("lastName"),"shah");

        // used time stamp method for email add
        enterText(By.id("login"),"kash1+"+timeStamp()+"@yahoo.com");

        // enter password
        enterText(By.id("password"),"123Kashyap");

        // enter your postcode
        enterText(By.id("postcode"),"HA9 0AB");

        //  click on element
        clickOnElement(By.id("registration-submit-button"));

        //expected result
        String expected = "MyOcado";

        //actual result
        String actual = getTextFromElement(By.cssSelector("a.primary-bar-link"));

        // Expected Vs Actual result
        Assert.assertEquals("Fail",expected,actual);

    }

    // *Following website does not working, giving me error of "Email Address", Kindly help, Please & realize can not take two
    //consecutive same digits at the same time *
    @Test
    //User should able to register successfully on Asda Grocery.com in main method
    public void AsdaWebRegisterSuccessfully()
    {
        // website address
        driver.get("https://www.asda.com/register?redirect_uri=https://groceries.asda.com/?cmpid=ahc-_-ghs-_-asdacom-_-hp-_-nav-_-ghs&request_origin=gi");

        // used time stamp method for email address
        enterText(By.xpath("//input[@type='email']"),"test+" + timeStamp() + "@yahoo.com");

        // enter password
        enterText(By.xpath("//input[@type='password']"),"Kashyap123");

        // enter post code
        enterText(By.xpath("//input[@type='text']"),"HA9 0AB");

        // click on element
        clickOnElement(By.xpath("//label[@class='regCheckTnC check-box']//span[@class='checkmark']"));

        // click on element
        clickOnElement(By.className("submit"));

        // click on element
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));//able to login successfully but cannot automate the capcha

        // expected result
        String expected = "Welcome to";

        // actual result
        String actual = getTextFromElement(By.className("welcome-message__welcome-text"));

        // Expected Vs Actual result
        Assert.assertEquals("Fail",expected,actual);
    }

}











