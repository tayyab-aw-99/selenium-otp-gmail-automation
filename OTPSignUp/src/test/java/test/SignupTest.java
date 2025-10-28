package test;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.OTPPage;
import pages.RegisterPage;
import utils.GmailReader;
public class SignupTest extends BaseTest {
    // TODO: you need to add these values these are dummy
    private final String TEST_EMAIL = System.getenv().getOrDefault("TEST_EMAIL", "tayyab@gmail.com");
    private final String TEST_EMAIL_APP_PASSWORD = System.getenv().getOrDefault("TEST_EMAIL_APP_PASSWORD", "tayy yaba wanm alik");
    private final String TARGET_URL = "https://ai-web-builder-fe.vercel.app/auth/register?role=user"; // change

    @Test
    public void signupWithOtp() throws Exception {
        RegisterPage register = new RegisterPage(driver);
        register.open(TARGET_URL);

        String name = "Test ";
        String name2 =("User");
        String phone =("+1 (555) 123-4567");
        String email = TEST_EMAIL;
        String password = "Password@123";
        String conpassword ="Password@123";

        register.enterName(name);
        register.enterName2(name2);
        register.enterphone(phone);
        register.enterEmail(email);
        register.enterPassword(password);
        register.enterPassword2(conpassword);
        register.submit();
        register.submit2();

        // Now page should show OTP input
        OTPPage otpPage = new OTPPage(driver);

        // Read OTP from Gmail — regex must match your email content (first captured group)
        GmailReader reader = new GmailReader(TEST_EMAIL, TEST_EMAIL_APP_PASSWORD);
        String code = reader.fetchLatestOtp(60, "(\\d{4,8})"); // waits up to 60s, matches 4-8 digit code

        System.out.println("OTP found: " + code);

        otpPage.enterOtp(code);
        otpPage.clickVerify();

        // Add assertion that indicates successful registration. Replace locator/logic as needed.
        Thread.sleep(2000);
        
    }
}