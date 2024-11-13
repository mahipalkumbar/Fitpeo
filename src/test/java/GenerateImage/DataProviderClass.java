package GenerateImage;



import java.io.File;


import java.io.IOException;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class DataProviderClass {
    // DataProvider for Image Generation Data
    @DataProvider(name = "imageGenerationData", parallel = false)
    public Object[][] getImageGenerationData(ITestContext context) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "TestDataGenerateImageAI" + File.separator + "GenerateImage AI.xlsx";
        
        String indexStr = context.getCurrentXmlTest().getParameter("index");
        
        int index = -1; // Default to -1 if no index is passed
        int totalRows;

        // If index is provided, parse it
        if (indexStr != null && !indexStr.isEmpty()) {
            try {
                index = Integer.parseInt(indexStr);  // Parse index
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid index value: " + indexStr, e);
            }
        }

        // Get the row and column counts from the Excel file
        int columnCount = ExcelUtils.getcellcount(filePath, "Sheet1", 1);  // Adjust as per your sheet structure
        totalRows = ExcelUtils.getRowcount(filePath, "Sheet1");

        Object[][] data;
        
        if (index >= 0) {
            data = new Object[1][columnCount];
            for (int j = 0; j < columnCount; j++) {
                data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet1", index + 1, j);  // Fetch data based on index
            }
        } else {
            int rowsToFetch = 1;  // Fetch the first row if no index is passed
            data = new Object[rowsToFetch][columnCount];
            
            for (int i = 0; i < rowsToFetch; i++) {
                for (int j = 0; j < columnCount; j++) {
                    data[i][j] = ExcelUtils.getcelldata(filePath, "Sheet1", i + 1, j);  // Fetch first row
                }
            }
        }

        return data;
    }

    // DataProvider for Image-to-Image Generation Data
    @DataProvider(name = "ImageToImageGenerationData")
    public Object[][] getImageToImageGenerationData() throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "TestDataGenerateImageAI" + File.separator + "GenerateImage AI.xlsx";
        
        int rowCount = 1;  // Only fetch data from row 1
        int columnCount = 7;  // Ensure exactly 7 columns are fetched

        Object[][] data = new Object[rowCount][columnCount];  // Only one row of data
        
        for (int j = 0; j < columnCount; j++) {
            data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet2", 1, j);  // Fetch row 1 (second row in the Excel sheet)
        }

        return data;
    }

    // DataProvider for Text-to-Video Generation Data
    @DataProvider(name = "TextToVideoGenerationData")
    public Object[][] getTextToVideoGenerationData() throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "TestDataGenerateImageAI" + File.separator + "GenerateImage AI.xlsx";
        
        int rowCount = 1;  // Only fetch data from row 1
        int columnCount = 25;  // Ensure exactly 25 columns are fetched
        
        Object[][] data = new Object[rowCount][columnCount];  // Only one row of data
        
        for (int j = 0; j < columnCount; j++) {
            data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet3", 1, j);  // Fetch row 1 (second row in the Excel sheet)
        }

        return data;
    }

    // DataProvider for Image-to-Video Generation Data
    @DataProvider(name = "ImageToVideoGenerationData")
    public Object[][] getImageToVideoGenerationData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\TestDataGenerateImageAI\\GenerateImage AI.xlsx";
        
        int rowCount = 1;  // Only fetch data from row 1
        int columnCount = 5;  // Ensure exactly 5 columns are fetched
        
        Object[][] data = new Object[rowCount][columnCount];  // Only one row of data
        
        for (int j = 0; j < columnCount; j++) {
            data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet4", 1, j);  // Fetch row 1 data
        }

        return data;
    }

    // DataProvider for Login Data
    @DataProvider(name = "Login")
    public Object[][] getLoginData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\TestDataGenerateImageAI\\GenerateImage AI.xlsx";
        
        int rowCount = 1;  // Only fetch data from row 1
        int columnCount = 4;  // Ensure exactly 4 columns are fetched
        
        Object[][] data = new Object[rowCount][columnCount];  // Only one row of data
        
        for (int j = 0; j < columnCount; j++) {
            data[0][j] = ExcelUtils.getcelldata(filePath, "LogIn", 1, j);  // Fetch row 1 data
        }

        return data;
    }

    // DataProvider for AutoAdManager Data
    @DataProvider(name = "AutoAdManager")
    public Object[][] getAutoAdManagerData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\TestDataGenerateImageAI\\AutoAdManager.xlsx";
        
        int columnCount = ExcelUtils.getcellcount(filePath, "Sheet1", 1);  // Get column count
        
        Object[][] data = new Object[1][columnCount];  // Only one row, excluding the header
        
        for (int j = 0; j < columnCount; j++) {
            data[0][j] = ExcelUtils.getcelldata(filePath, "Sheet1", 1, j);  // Fetch first row data
        }

        return data;
    }
}
