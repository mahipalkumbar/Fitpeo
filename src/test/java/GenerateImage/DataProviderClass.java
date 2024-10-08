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
	        
	        // Get the index parameter from the TestNG XML
	        String indexStr = context.getCurrentXmlTest().getParameter("index");
	        int index = Integer.parseInt(indexStr);  // Parse index from String to int
	        
	        // Get the row and column counts from the Excel file
	        int columnCount = ExcelUtils.getcellcount(filePath, "Sheet1", 1);  // Adjust according to your sheet
	        
	        // Create an array to store only the row data corresponding to the index
	        Object[][] data = new Object[1][columnCount];
	        
	        // Fetch the specific row of data from the Excel sheet based on the index
	        for (int j = 0; j < columnCount; j++) {
	            data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet1", index + 1, j);  // Adjust for row numbers starting from 1
	        }
	        
	        return data;  // Return the single row of data for the specified index
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
