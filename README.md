# 🧪 QA Automation Framework (UI + API)

This is a hybrid automation testing framework built with Java, combining both **API testing (RestAssured)** and **UI testing (Selenium)** into a unified structure with **TestNG** and **ExtentReports**.

---

## 🚀 What This Project Covers

- ✅ **REST API testing** using RestAssured
- ✅ **UI testing** using Selenium WebDriver
- ✅ Combined **ExtentReports** logging for both test types
- ✅ Configurable through `.properties` and `.json` files
- ✅ Uses TestNG for parallel-ready structured test execution
- ✅ Clean project structure with reusable utilities and base classes

---

## 🛠 Tools & Technologies

- Java
- RestAssured
- Selenium WebDriver
- TestNG
- ExtentReports
- WebDriverManager
- Jackson (for JSON parsing)
- GitHub

---

## 🧱 Project Structure

src/
├── main/
│   └── java/
│       └── com.harshada/
│           ├── api/
│           │   ├── base/          # API base class (BaseAPITest)
│           │   ├── client/        # RestAssured config (APIClient)
│           │   ├── endpoints/     # All API endpoints (UserEndpoints)
│           │   ├── payload/       # POJO for request body (UserPayload)
│           │   └── utils/         # API helpers like response assertion (APIUtil)
│           ├── base/              # UI base class (TestBase)
│           ├── config/            # config.properties file
│           ├── pages/             # UI Page Objects (LoginPage, HomePage)
│           └── util/              # UI test utilities (TestUtil)
│
├── test/
│   └── java/
│       └── com.harshada/
│           ├── api.test/          # API test classes (UserCRUDTests)
│           └── test/              # UI test classes (LoginPageTest, HomePageTest)


---

## 🧪 How to Run

### Prerequisites:
- Java 11+
- Maven
- TestNG

## ▶️ Run All Tests

Right-click `testng.xml` → Run as TestNG Suite

---

## 🔸 Sample Test Data

User creation and updates are data-driven using `userData.json`:

```json
{
  "name": "Harshada QA",
  "gender": "female",
  "email": "harshada.qa+test@domain.com",
  "status": "active"
}
```

## 📊 Reporting
ExtentReports HTML report is generated under:
test-output/Reports/
It includes:
All test steps
Screenshots (for UI failures)
API request/response logs
Overall pass/fail summary

📁 Sample Report
A sample ExtentReport HTML file is available under the SampleReport/ folder so you can preview the output without executing tests.
The report includes:
Step-level logging
Request/response payloads
Test status and time
Combined logs for UI and API tests

## ✍️ Author
Harshada Patil
QA Engineer | Manual + Automation
