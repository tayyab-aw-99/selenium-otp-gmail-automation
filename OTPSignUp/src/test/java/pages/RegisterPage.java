package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RegisterPage {
    private WebDriver driver;


// TODO: Replace these locators with real ones from your target page
   private By fnameInput = By.xpath("//*[@id=\"central-input-first-name\"]");
   private By lnameInput = By.xpath("//*[@id=\"central-input-last-name\"]");
   private By phoneInput = By.xpath("//*[@id=\"root\"]/main/section/div[4]/section/form/div[1]/div[3]/div/div/input");

   private By emailInput = By.xpath("//*[@id=\"central-input-email\"]");
   private By passwordInput = By.xpath("//*[@id=\"central-input-password\"]");
   private By conpasswordInput = By.xpath("//*[@id=\"central-input-confirm-password\"]");
   private By privacy = By.xpath("//*[@id=\"privacy\"]");
   private By submitBtn = By.xpath("//*[@id=\"root\"]/main/section/div[4]/section/form/button");



   public RegisterPage(WebDriver driver) {
   this.driver = driver;
}


  public void open(String url) {
  driver.get(url);
  }
  public void enterName(String name) {
  driver.findElement(fnameInput).sendKeys(name);
  }
  public void enterName2(String name2) {
	  driver.findElement(lnameInput).sendKeys(name2);
  }
  public void enterphone(String ph) {
	  driver.findElement(phoneInput).sendKeys(ph);
  }
  public void enterEmail(String email) {
  driver.findElement(emailInput).sendKeys(email);
  }
  public void enterPassword(String pw) {
  driver.findElement(passwordInput).sendKeys(pw);
  }
  public void enterPassword2(String pw2) {
	  driver.findElement(conpasswordInput).sendKeys(pw2);
  }
  public void submit() {
  driver.findElement(privacy).click();
  }
  public void submit2() {
	  driver.findElement(submitBtn).click();
 }
}












