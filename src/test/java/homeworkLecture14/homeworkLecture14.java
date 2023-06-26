package homeworkLecture14;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.asn1.isismtt.x509.ProfessionInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

// Test - logout from different pages, check that no logout is possible if there is no logged user
// 0. Navigate to Home page
// 1. Navigate to Login page by clicking Login tab button
// 2. Validate that the url is correct
// 3. Validate that the 'Sign in' text is visible
// 4. Enter correct username
// 5. Enter correct password
// 6. Click sign in button
// 7. Validate that the url is correct ( Home page expected )
// 8. Validate that there is a Profile tab button visible
// 9. Validate that there is a New post tab button visible
// 10. Click Profile button
// 11. Logout from Profile page
// 12. Validate that Login Button is visible
// 13. Login again
// 14. Validate that there is a Profile tab button visible
// 15. Click on Home Button
// 16. Logout from Home page
// 17. Validate that Login Button is visible
// 18. Validate that logout button is not visible

public class homeworkLecture14 {

    ChromeDriver driver;
    final String homepageUrl = "http://training.skillo-bg.com:4200/posts/all";

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(homepageUrl);
    }

    @Test
    public void logoutFromProfilePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebDriverWait smallWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        System.out.println("1. Navigate to Login page by clicking Login tab button");
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();

        System.out.println("2. Validate that the url is correct");
        String expectedUrl = "http://training.skillo-bg.com:4200/users/login";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        System.out.println("3. Validate that the 'Sign in' text is visible");
        WebElement signInElement = driver.findElement(By.xpath("//p[text()='Sign in']"));
        Assert.assertTrue(signInElement.isDisplayed(), "Sign in form is not visible");

        System.out.println("4. Enter correct username");
        WebElement usernameField = driver.findElement(By.name("usernameOrEmail"));
        usernameField.sendKeys("Teodor123");

        System.out.println("5. Enter correct password");
        WebElement passwordField = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
        passwordField.sendKeys("teodor123");

        System.out.println("6. Click sign in button");
        WebElement signInButton = driver.findElement(By.cssSelector("#sign-in-button"));
        signInButton.click();

        System.out.println("7. Validate that the url is correct ( Home page expected )");
        wait.until(ExpectedConditions.urlToBe(homepageUrl));

        System.out.println("8. Validate that there is a Profile tab button visible");
        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));

        smallWait.until(ExpectedConditions.visibilityOf(profileLink));

        System.out.println("9. Validate that there is a New post tab button visible");
        WebElement newPostLink = driver.findElement(By.linkText("New post"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(newPostLink.isDisplayed(), "New post link is not visible");

        System.out.println("10. Click Profile button");
        profileLink.click();

        System.out.println("11. Logout from Profile page.");
        WebElement logoutBtn = driver.findElement(By.cssSelector(".fas.fa-sign-out-alt.fa-lg"));
        logoutBtn.click();

        System.out.println("12. Validate that Login Button is visible.");
        WebElement loginBtn = driver.findElement(By.id("nav-link-login"));
        Assert.assertEquals(true, loginBtn.isDisplayed(), "Login Button is not visible");




    }

    @Test
    public void logoutFromHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebDriverWait smallWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        System.out.println("13. Login Again");
        WebElement loginButton = driver.findElement(By.id("nav-link-login"));
        loginButton.click();
        WebElement usernameField = driver.findElement(By.name("usernameOrEmail"));
        usernameField.sendKeys("Teodor123");
        WebElement passwordField = driver.findElement(By.cssSelector("input[formcontrolname='password']"));
        passwordField.sendKeys("teodor123");
        WebElement signInButton = driver.findElement(By.cssSelector("#sign-in-button"));
        signInButton.click();

        System.out.println("14. Validate that there is a Profile tab button visible");
        WebElement profileLink = driver.findElement(By.id("nav-link-profile"));
        smallWait.until(ExpectedConditions.visibilityOf(profileLink));

        System.out.println("15. Click on Home Button.");
        WebElement homeBtn = driver.findElement(By.id("nav-link-home"));
        homeBtn.click();

        System.out.println("16. Logout from Home page");
        WebElement logoutBtn = driver.findElement(By.cssSelector(".fas.fa-sign-out-alt.fa-lg"));
        logoutBtn.click();

        System.out.println("17. Validate that Login Button is visible.");
        WebElement loginBtnTest2 = driver.findElement(By.id("nav-link-login"));
        Assert.assertEquals(true, loginBtnTest2.isDisplayed(), "Login Button is not visible");

        System.out.println("18. Validate that logout button is not visible.");
        WebElement logoutTwo = driver.findElement(By.cssSelector(".fas.fa-sign-out-alt.fa-lg"));
        Assert.assertEquals(true, logoutTwo.isDisplayed(), "Logout Button is visible.");


}

    @AfterMethod
    public void cleanup() {
        driver.close();
    }
}