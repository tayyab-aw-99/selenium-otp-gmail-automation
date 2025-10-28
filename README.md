
**Selenium Java Signup Project (OTP via Gmail)**

**Overview:**
This project automates a user signup process using Selenium WebDriver and TestNG. It includes OTP verification by reading emails from Gmail through JavaMail (IMAP). The goal is to demonstrate a complete end-to-end automation scenario that involves both frontend interaction and backend validation through email.

---

**What this project contains:**

* A Maven-based Selenium + TestNG framework for automation.
* Page Object Model (POM) structure with `RegisterPage` and `OTPPage`.
* `GmailReader` utility that connects to Gmail via IMAP to fetch OTPs securely.
* `SignupTest` class that performs the registration and OTP verification flow.

---

**About Project Structure:**
The project follows a clean, modular structure using Maven and TestNG for scalability and maintainability.

```
selenium-signup-otp/
├── pom.xml                 → Maven dependency manager (Selenium, TestNG, JavaMail)
├── README.txt              → Project documentation
└── src/
    └── test/
        └── java/
            ├── base/
            │   └── BaseTest.java        → Sets up and tears down WebDriver
            ├── pages/
            │   ├── RegisterPage.java    → Handles user input on signup form
            │   └── OTPPage.java         → Handles OTP input and verification
            ├── utils/
            │   └── GmailReader.java     → Connects to Gmail IMAP and extracts OTP
            └── tests/
                └── SignupTest.java      → End-to-end signup + OTP verification test
```

**Key structure explanation:**

* **base/** — Contains reusable setup and teardown logic for WebDriver.
* **pages/** — Follows Page Object Model (POM) for better maintainability. Each page action is encapsulated in its class.
* **utils/** — Utility classes like GmailReader for backend or third-party integrations.
* **tests/** — Houses the TestNG test scripts that combine page and utility logic to execute full test cases.
* **pom.xml** — Central file for managing dependencies, build lifecycle, and plugins.

---

**About pom.xml:**
The `pom.xml` file defines all required dependencies. It automatically manages Selenium, TestNG, JavaMail (Jakarta Mail), and WebDriverManager. When you run `mvn clean install`, Maven downloads and configures all libraries so the framework is ready to execute tests.

---

**About JavaMail:**
JavaMail (Jakarta Mail) is used to securely connect to Gmail. It reads unread emails, identifies the OTP using a regular expression, and returns the code for test verification. It connects via IMAP to Gmail’s server (`imap.gmail.com` on port 993).

---

**Gmail App Password Setup:**
Because Gmail no longer supports direct password login for external apps, you must create an **App Password**:

1. Enable **2-Step Verification** in your Google Account.
2. Navigate to **Google Account → Security → App Passwords**.
3. Generate a new password for the “Mail” app.
4. Use this 16-character password in your automation test (not your main Gmail password).
5. Store this securely as an environment variable — **never hardcode it** in code.

---

**Running the Test:**

1. Set your Gmail test credentials (email and app password) as environment variables.
2. Update the signup URL and form field locators in the `RegisterPage` and `OTPPage` classes.
3. Run the project using `mvn test`.
   The test will:

* Launch the signup page.
* Fill in registration details.
* Retrieve the OTP from Gmail.
* Verify the OTP and confirm successful registration.

---

**Security Notes:**

* Always use a dedicated Gmail account for testing purposes.
* Never commit or share real credentials.
* Use environment variables or a secrets manager for secure handling of sensitive information.

---

**Summary:**
This project provides a clean, modular, and secure Selenium TestNG setup that automates user signup flows involving OTP verification via Gmail. It effectively integrates frontend automation (Selenium) with backend validation (JavaMail), making it a professional, real-world-grade test automation framework.
