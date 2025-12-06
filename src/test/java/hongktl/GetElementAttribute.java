package hongktl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class GetElementAttribute {
    WebDriver driver;
    private  By email = By.xpath("//label[text()='E-mail']/following-sibling::input");
    private  By pwd = By.xpath("//label[text()='Mật khẩu']/following-sibling::input");
    private  By pwdBtn = By.xpath("//span[contains(text(),'Đăng nhập')]");
    String emailInValid = "emailInvalid";
    String emailValid = "tamdd1998@gmail.com";
    String pwdValid = "123456aA@";
    String pwdInvalid = "pwdInvalid";
    @BeforeClass
    public void setUP() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://103.121.90.177:8888/");
        Thread.sleep(3000);
    }
    @Test
    public void verifyUI(){
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Đăng nhập']")).isDisplayed(),"Label khong hien thi");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Vui lòng')]")).isDisplayed(),"Text please khong hien thi");
        Assert.assertTrue(driver.findElement(email).isDisplayed(),"Ô Email không hiển thị");
        Assert.assertTrue(driver.findElement(pwd).isDisplayed(),"Ô Mật khẩu  không hiển thị");
        Assert.assertTrue(driver.findElement(pwdBtn).isDisplayed(),"Button Đăng nhập không hiển thị");
    }
    @Test
    public void loginInValid_InputBlank() {
        driver.findElement(pwdBtn).click();
        String msgEmail1 = driver.findElement(By.xpath("//label[text()='E-mail']/parent::div/parent::div/following-sibling::div/div/div/div")).getAttribute("textContent").trim();
        Assert.assertEquals(msgEmail1, "Email là bắt buộc");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Mật khẩu là bắt buộc')]")).isDisplayed());
    }
    @Test
            public void loginInavld(){
        System.out.println("TC2: Nhập sai email ");
        driver.findElement(email).sendKeys(emailInValid);
        driver.findElement(pwd).sendKeys(pwdValid);
        driver.findElement(pwdBtn).click();
        String msgEmail2 = driver.findElement(By.xpath("//label[text()='E-mail']/parent::div/parent::div/following-sibling::div/div/div/div")).getAttribute("textContent").trim();
        Assert.assertEquals(msgEmail2,"Chưa đúng định dạng email");
}
    @Test
    public void loginValid(){
        //Điền thông tin valid
        System.out.println("TC1: Nhập đúng thông tin");
        driver.findElement(email).sendKeys(emailValid);
        driver.findElement(pwd).sendKeys(pwdValid);
        Assert.assertEquals(driver.findElement(pwd).getAttribute("type"),"password");
        driver.findElement(By.xpath("//button[@aria-label='Mật khẩu appended action']")).click();
        Assert.assertEquals(driver.findElement(pwd).getAttribute("type"),"text");
        driver.findElement(pwdBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement page =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Dữ liệu cơ sở']")));
        Assert.assertTrue(page.isDisplayed());
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

