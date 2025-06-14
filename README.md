# Unit Converter with Database History

A simple Java Swing application that allows users to convert units between different categories (Length, Weight, Temperature) and saves the conversion history to a MySQL database.

## Features

* **Unit Conversion:** Supports conversion for Length, Weight, and Temperature units.
* **Dynamic Unit Selection:** Units update automatically based on the selected category.
* **Database Integration:** Saves every successful conversion record to a MySQL database.
* **Conversion History View:** Allows users to view all past conversion records stored in the database.
* **Basic Input Validation:** Handles non-numeric input and empty input fields.
* **Robust Error Handling:** Provides user-friendly messages for invalid inputs and database connection issues.

## Technologies Used

* Java Swing
* JDBC (Java Database Connectivity)
* MySQL Database

## Setup Instructions

To get this project running on your local machine, follow these steps:

### 1. MySQL Database Setup

First, you need to set up the MySQL database and table.

* **Install MySQL Server:** If you don't have MySQL installed, download and install it from the official MySQL website.
* **Open MySQL Client:** Use a tool like MySQL Workbench, DBeaver, or the MySQL command-line client.
* **Create Database and Table:** Execute the following SQL commands:

    ```sql
    CREATE DATABASE IF NOT EXISTS unit_converter_db;

    USE unit_converter_db;

    CREATE TABLE IF NOT EXISTS conversion_history (
        id INT AUTO_INCREMENT PRIMARY KEY,
        category VARCHAR(50) NOT NULL,
        from_unit VARCHAR(50) NOT NULL,
        to_unit VARCHAR(50) NOT NULL,
        input_value DOUBLE NOT NULL,
        result DOUBLE NOT NULL,
        timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
    );
    ```

### 2. Get MySQL JDBC Driver (Connector/J)

Your Java application needs a driver to connect to MySQL.

* **Download:** Go to the official MySQL Connector/J download page: [https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/)
    * Select "Platform Independent" and download the "ZIP Archive".
    * Extract the downloaded file and locate the `mysql-connector-j-x.y.z.jar` (e.g., `mysql-connector-j-8.4.0.jar`).

* **Add to Project Classpath:**
    * **Manual (Recommended for this project size):** Create a folder named `lib` in your project's root directory. Copy the `mysql-connector-j-x.y.z.jar` file into this `lib` folder.
        * **In your IDE (e.g., IntelliJ IDEA, Eclipse):** Add this JAR file to your project's build path/classpath.
            * **IntelliJ:** Right-click the `mysql-connector-j-x.y.z.jar` in your project view -> "Add as Library...".
            * **Eclipse:** Right-click on your project -> Properties -> Java Build Path -> Libraries tab -> "Add JARs..." or "Add External JARs..." and select the JAR.
    * **(Alternative for Maven/Gradle projects):** If you're using a build tool, add the dependency to your `pom.xml` (Maven) or `build.gradle` (Gradle) as shown in the "Part 2: External Setup" section above.

### 3. Configure Database Credentials

* Open the `ConversionDAO.java` file.
* Locate the line: `private static final String DB_PASSWORD = "YOUR_MYSQL_ROOT_PASSWORD";`
* Replace `"YOUR_MYSQL_ROOT_PASSWORD"` with your actual MySQL `root` user password.

### 4. Compile and Run the Application

* **Using an IDE (Recommended):**
    * Open the project in your IDE.
    * Ensure all `.java` files are correctly placed in their respective package directories (`src/com/example/` or your chosen package).
    * Run the `main` method in `UnitConverterWithDB.java`.
* **Using Command Line:**
    * Navigate to your `your-project-root/src` directory.
    * Compile the Java files:
        ```bash
        javac -cp ".:../lib/mysql-connector-j-8.4.0.jar" com/example/*.java
        # On Windows, use a semicolon: javac -cp ".;..\lib\mysql-connector-j-8.4.0.jar" com\example\*.java
        ```
        (Replace `mysql-connector-j-8.4.0.jar` with your downloaded version, and adjust path if your package is different).
    * Run the application:
        ```bash
        java -cp ".:../lib/mysql-connector-j-8.4.0.jar" com.example.UnitConverterWithDB
        # On Windows: java -cp ".;..\lib\mysql-connector-j-8.4.0.jar" com.example.UnitConverterWithDB
        ```
        (Again, adjust paths and package name as necessary).

## Usage

1.  Select a conversion category (Length, Weight, or Temperature).
2.  Enter the numeric value you want to convert in the "Input Value" field.
3.  Select the "From Unit" and "To Unit" from the respective dropdowns.
4.  Click the "Convert" button to see the result.
5.  Click the "View History" button to see a table of all past conversions saved in the database.

## Troubleshooting

* **`ClassNotFoundException: com.mysql.cj.jdbc.Driver`:** This means the MySQL JDBC driver is not correctly added to your project's classpath. Double-check step 2 of "Setup Instructions".
* **`SQLException: Access denied for user 'root'@'localhost'`:** Your MySQL password in `ConversionDAO.java` is incorrect, or the `root` user doesn't have privileges. Verify your MySQL `root` password.
* **`SQLException: Unknown database 'unit_converter_db'`:** The database `unit_converter_db` has not been created in your MySQL server. Refer to step 1 of "Setup Instructions".
* **`SQLException: Table 'unit_converter_db.conversion_history' doesn't exist`:** The `conversion_history` table hasn't been created. Refer to step 1 of "Setup Instructions".