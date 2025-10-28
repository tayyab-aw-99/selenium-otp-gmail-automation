package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OTPPage {
    private WebDriver driver;
    // TODO: Replace with your OTP input locator
    private By otpInput = By.xpath("//*[@id=\"root\"]/main/section/div[4]/section/form/div/div[2]/input");
    private By verifyBtn = By.xpath("//*[@id=\"root\"]/main/section/div[4]/section/form/button");

    public OTPPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterOtp(String code) {
        driver.findElement(otpInput).sendKeys(code);
    }

    public void clickVerify() {
        driver.findElement(verifyBtn).click();
    }
}
