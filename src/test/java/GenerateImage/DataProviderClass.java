package GenerateImage;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
	@DataProvider(name = "imageGenerationData", parallel = false)
    public Object[][] getImageGenerationData(ITestContext context) throws IOException {
        // Get the file path to the Excel file
        String filePath = System.getProperty("user.dir") + "\\TestDataGenerateImageAI\\GenerateImage AI.xlsx";

        // Get the index parameter from the TestNG XML, if not passed, it will be null
        String indexStr = context.getCurrentXmlTest().getParameter("index");
        
        // Initialize variables
        int index = -1; // Default to -1 to indicate no specific index was set
        int totalRows;   // Variable to hold the total number of rows

        // If the index is provided, parse it
        if (indexStr != null && !indexStr.isEmpty()) {
            try {
                index = Integer.parseInt(indexStr);  // Parse index from String to int
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid index value: " + indexStr, e);
            }
        }

        // Get the row and column counts from the Excel file
        int columnCount = ExcelUtils.getcellcount(filePath, "Sheet1", 1);  // Adjust according to your sheet
        totalRows = ExcelUtils.getRowcount(filePath, "Sheet1"); // Method to get total row count

        // Create an array to hold the data
        Object[][] data;

        // If index is specified, fetch that specific row; otherwise, fetch all rows
        if (index >= 0) {
            // Create an array to store only the row data corresponding to the index
            data = new Object[1][columnCount];
            for (int j = 0; j < columnCount; j++) {
                data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet1", index + 1, j);  // Adjust for row numbers starting from 1
            }
        } else {
            // Fetch the first 3 rows if index is not specified
        	int rowsToFetch = 1; // Limit to 1 row
        	data = new Object[rowsToFetch][columnCount];

        	for (int i = 0; i < rowsToFetch; i++) {
        	    for (int j = 0; j < columnCount; j++) {
        	        data[i][j] = ExcelUtils.getcelldata(filePath, "Sheet1", i + 1, j);  // Fetch only the first row
        	    }
        	}

        }


        return data;  // Return the data
    }



	@DataProvider(name = "ImageToImageGenerationData")
	public Object[][] getImageToImageGenerationData() throws IOException {
	    String filePath = System.getProperty("user.dir") + "\\TestDataGenerateImageAI\\GenerateImage AI.xlsx";

	    // We need to fetch exactly row 1 (the second row in the sheet)
	    int rowCount = 1; // We're fetching data only from row 1 (second row of the Excel)
	    int columnCount = 7; // Ensure you're fetching exactly 7 columns

	    Object[][] data = new Object[rowCount][columnCount]; // Only one row of data

	    for (int j = 0; j < columnCount; j++) {
	        // Fetch data from row 1 (index 1, since row 0 is the header)
	        data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet2", 1, j); // Fetch row 1 data
	    }

	    return data;
	}
	
	@DataProvider(name = "TextToVideoGenerationData")
	public Object[][] getTextToVideoGenerationData() throws IOException {
	    String filePath = System.getProperty("user.dir") + "\\TestDataGenerateImageAI\\GenerateImage AI.xlsx";

	    // We need to fetch exactly row 1 (the second row in the sheet)
	    int rowCount = 1; // We're fetching data only from row 1 (second row of the Excel)
	    int columnCount = 25; // Ensure you're fetching exactly 7 columns

	    Object[][] data = new Object[rowCount][columnCount]; // Only one row of data

	    for (int j = 0; j < columnCount; j++) {
	        // Fetch data from row 1 (index 1, since row 0 is the header)
	        data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet3", 1, j); // Fetch row 1 data
	    }

	    return data;
	}

	
	@DataProvider(name = "ImageToVideoGenerationData")
	public Object[][] getImageToVideoGenerationData() throws IOException {
	    String filePath = System.getProperty("user.dir") + "\\TestDataGenerateImageAI\\GenerateImage AI.xlsx";

	    // We need to fetch exactly row 1 (the second row in the sheet)
	    int rowCount = 1; // We're fetching data only from row 1 (second row of the Excel)
	    int columnCount = 4; // Ensure you're fetching exactly 7 columns

	    Object[][] data = new Object[rowCount][columnCount]; // Only one row of data

	    for (int j = 0; j < columnCount; j++) {
	        // Fetch data from row 1 (index 1, since row 0 is the header)
	        data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet4", 1, j); // Fetch row 1 data
	    }

	    return data;
	}
	
	@DataProvider(name = "Login")
	public Object[][] getLoginData() throws IOException {
	    String filePath = System.getProperty("user.dir") + "\\TestDataGenerateImageAI\\GenerateImage AI.xlsx";

	    // We need to fetch exactly row 1 (the second row in the sheet)
	    int rowCount = 1; // We're fetching data only from row 1 (second row of the Excel)
	    int columnCount = 4; // Ensure you're fetching exactly 7 columns

	    Object[][] data = new Object[rowCount][columnCount]; // Only one row of data

	    for (int j = 0; j < columnCount; j++) {
	        // Fetch data from row 1 (index 1, since row 0 is the header)
	        data[0][j] = ExcelUtils.getcelldata(filePath, "LogIn", 1, j); // Fetch row 1 data
	    }

	    return data;
	}
	

    
    @DataProvider(name = "AutoAdManager")
    public Object[][] getAutoAdManagerData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\TestDataGenerateImageAI\\AutoAdManager.xlsx";
        
        // Get the number of columns in the second row (data row) to fetch data accordingly
        int columnCount = ExcelUtils.getcellcount(filePath, "Sheet1", 1); // Row 1 is the first data row

        // Create an Object array for one row (the first row with data) and column count
        Object[][] data = new Object[1][columnCount]; // Only 1 row, excluding the header

        // Start from row 1 (second row in Excel) and fetch data
        for (int j = 0; j < columnCount; j++) {
            data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet1", 1, j); // Fetch row 1 (first data row)
        }

        return data;
    }
}
