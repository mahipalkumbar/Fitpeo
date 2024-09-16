package GenerateImage;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
    @DataProvider(name = "imageGenerationData")
    public Object[][] getImageGenerationData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\TestDataGenerateImageAI\\GenerateImage AI.xlsx";
        
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
