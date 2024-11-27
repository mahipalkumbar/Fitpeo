
## Fitpeo Assignment: [GitHub Repository](https://github.com/mahipalkumbar/Fitpeo)

---

## Table of Contents:
1. **Prerequisites**
2. **Setting up the Project Locally**
   - Setting Up Java Path Variables
   - Clone the Repository
   - Installing TestNG in Eclipse
   - Install Dependencies
   - Running Tests Locally
3. **Setting up the Project on Server (Jenkins)**
   - Jenkins Setup
   - Jenkins Integration
   - Running Tests on Jenkins

---

## Prerequisites:

- **JDK (Java Development Kit)**  
  Version: `JDK 21`  
  - Download from Oracle's official website or use OpenJDK: [JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)  
  - To check if JDK is installed, run:  
    ```bash
    java -version
    ```

- **Eclipse IDE**  
  Version: `2024-09`  
  - Download and install from the [official website](https://www.eclipse.org/downloads/).  
  - Ensure to install the Java version of Eclipse.

- **Selenium**  
  - Selenium is a browser automation framework.  
  - Include the Selenium dependency in your `pom.xml`.

- **Maven**  
  Version: `apache-maven-3.9.9`  
  - Download from the [official website](https://maven.apache.org/download.cgi).  
  - To check if Maven is installed, run:  
    ```bash
    mvn -version
    ```

- **Git**  
  Version: `2.46.0`  
  - Download Git from the [official website](https://git-scm.com/).  
  - To check Git installation, run:  
    ```bash
    git --version
    ```

---

## Setting up the Project Locally:

### Setting Up Java Path Variables:
After installing JDK, you need to set up the Java environment variables:

1. **Set JAVA_HOME**:
   - Right-click on **This PC** or **Computer**, then click **Properties**.
   - Click on **Advanced system settings**.
   - Click on **Environment Variables**.
   - Under **System variables**, click **New**.
   - Set **Variable name** to `JAVA_HOME`.
   - Set **Variable value** to the path of your JDK installation (e.g., `C:\Program Files\Java\jdk-21`).

2. **Add Java to PATH**:
   - In the same **Environment Variables** window, find the `Path` variable under **System variables** and select it, then click **Edit**.
   - Click **New** and add the path to the `bin` directory of your JDK installation (e.g., `C:\Program Files\Java\jdk-21\bin`).
   - Click **OK** to close all dialog boxes.

---

### Clone the Repository:
1. Navigate to the directory where you want to clone your project.
2. Open a terminal and run the following command:
   ```bash
   git clone https://github.com/tech-nyx/qa-automation.git
## Open the Project in Eclipse:

1. Start **Eclipse**.
2. Select **File > Import**.
3. Choose **Existing Maven Projects**.
4. Browse to your project directory and finish the import.

---

## Installing TestNG in Eclipse:

1. Open **Eclipse**.
2. Go to **Help > Eclipse Marketplace**.
3. In the "Find" box, type `TestNG` and click **Go**.
4. Locate the **TestNG plugin** in the search results and click **Install**.
5. Follow the installation prompts and restart **Eclipse** when prompted.

### Verify Installation:

1. Right-click on your project > **New > Other**.
2. Expand the **TestNG** folder and select **TestNG Class**, then click **Next**.
3. Enter the class name and click **Finish**.

---

## Install Dependencies:

- **Maven** will automatically download the required dependencies listed in your `pom.xml`, which includes **Selenium**, **TestNG**, and any other libraries you need.

---

## Update Maven Project:

1. Right-click on the project in the **Project Explorer**.
2. Select **Maven > Update Project**.

---

## Running Tests Locally:

### Running Tests in Eclipse:

1. Right-click on your project in **Eclipse**.
2. Select **Run As > Maven test**.

### Running Tests Using Command Line:

1. Navigate to your project directory.
2. Run the following command:

    ```bash
    mvn test
    ```

### Alternative: Run Without Eclipse:

- Simply execute the `run.bat` file (which contains the command `mvn test` for running tests without opening Eclipse).

---

## Setting up the Project on Server (Jenkins):

### Jenkins Setup:

#### Install Jenkins:

1. Download the **Jenkins WAR** file from the official website.
2. To run Jenkins, execute:

    ```bash
    java -jar jenkins.war
    ```

#### Configure Maven in Jenkins:

1. In **Jenkins**, go to **Manage Jenkins > Global Tool Configuration**.
2. Add **Maven installation** by specifying the version and installation path of Maven.

#### Install Git Plugin:

1. Go to **Manage Jenkins > Manage Plugins**.
2. Install the **Git Plugin** to integrate your GitHub repository.

---

## Jenkins Integration:

### Create a Jenkins Job:

1. In **Jenkins**, click **New Item** and select **Maven Project**.
2. Give your project a name and click **OK**.

### Configure Git Repository:

1. Under the **Source Code Management** section, select **Git**.
2. Enter your repository URL (e.g., `https://github.com/tech-nyx/qa-automation.git`).

### Set Build Triggers:

- Set the job to build periodically or trigger the build based on changes in the repository using **Poll SCM**.

### Set Build Goals:

1. In the **Build** section, add the build goal as `test` to run your Maven tests:

    ```bash
    mvn test
    ```

---

## Running Tests on Jenkins:

- To run your Jenkins job, either click **Build Now** or set it to trigger automatically based on your configuration.
- View the console output to monitor test execution.
