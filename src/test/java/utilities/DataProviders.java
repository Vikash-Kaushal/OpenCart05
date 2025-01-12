package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="LoginData")
    public String[][] getData() throws IOException {

        String path = System.getProperty("user.dir")+"\\testData\\OpenCart_LoginData.xlsx";
        ExcelUtility xlutility = new ExcelUtility(path);
        int totalRow = xlutility.getRowCount("Sheet1");
        int totalCell = xlutility.getCellCount("Sheet1",1);

        String loginData[][] = new String[totalRow][totalCell];
        for(int i=1; i<= totalRow; i++){
            for(int j=0; j<totalCell; j++){
                loginData[i-1][j] = xlutility.getCellData("Sheet1",i,j);
            }
        }
        return loginData;
    }
}
