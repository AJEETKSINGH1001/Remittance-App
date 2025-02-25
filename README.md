**Overview**
This repository contains an Automation Test Framework developed using Appium, Selenium, and Java. It is designed to test the Wall Street Exchange Remittance App, ensuring functionality, 
security, and performance across multiple devices and platforms.

🎯 **Features**
✅ Cross-Platform Testing – Supports both Android & iOS testing
✅ Page Object Model (POM) – Ensures modular and reusable test scripts
✅ Parallel Execution – Uses TestNG for parallel execution across devices
✅ Integration with CI/CD – Seamlessly integrates with Jenkins/GitHub Actions
✅ Reporting – Generates detailed test reports using Extent Reports & Allure
✅ Data-Driven Testing – Uses Apache POI for reading test data from Excel
✅ Device Farm Compatibility – Can be executed on BrowserStack/Sauce Labs


🛠 **Tech Stack**
**Programming Language:** Java
**Framework:** TestNG
**UI Automation:** Appium + Selenium
**Build Tool:** Maven
**Logging:** Log4j
**Reporting:** Extent Reports, Allure
**Version Control:** GitHub


📥 **Installation**
🔹 Prerequisites
**Ensure the following tools are installed on your machine:**
🔹 Java (JDK 11 or later)
🔹 Maven
🔹 Appium Server
🔹 Android SDK / Xcode (for iOS)
🔹 Node.js (for Appium)
🔹 Device Emulator/Real Device

**Clone the Repository**
git clone https://github.com/AJEETKSINGH1001/Remittance-App.git
cd Remittance-App

🔹 **Install Dependencies**
     mvn clean install

🚀 **Running Tests**
🔹 **For Android**
    mvn test -Dplatform=android
🔹 **For iOS**
    mvn test -Dplatform=ios
🔹 **Run Specific Test Cases**
    mvn test -Dtest=LoginTest
🔹 **Run Tests in Parallel**
    mvn test -Dtestng.xml=parallel.xml
    
📊 **Test Reporting**
    After test execution, reports can be found in:

📂 **target/surefire-reports** – Default TestNG reports
📂 **test-output/ExtentReports.html** – Extent Reports
📂 **allure-results – Allure Reports** (Run allure serve allure-results to view)

🔄 **CI/CD Integration**
🔹 **Jenkins Integration**
   Add a Maven Job in Jenkins
   Set Build Command: mvn clean test
   Configure Post-Build Actions to generate reports

🎯 **Contribution Guidelines**
     welcome contributions!

1️⃣ Fork the repository
2️⃣ Create a new branch (git checkout -b feature-branch)
3️⃣ Commit changes (git commit -m "Added new test case")
4️⃣ Push to branch (git push origin feature-branch)
5️⃣ Create a pull request







