package apiUtilities;

import java.io.IOException;
import java.lang.annotation.Repeatable;

import org.testng.annotations.DataProvider;

public class DataProviders {
    
    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException
    {
        String path = System.getProperty("user.dir") + "/userdata.xlsx";
        XLReader xl = new XLReader(path);

        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);

        String apidata[][] = new String[rownum][colcount];

        for(int i=1; i<=rownum; i++)
        {
            for(int j=0; j<colcount; j++)
            {
                apidata[i-1][j] = xl.getCellData("Sheet1", i, j);
            }
        }

        return apidata;
        
    }

    @DataProvider(name = "UpdatedData")
    public String[][] getUpdatedUserData() throws IOException
    {
        String path = System.getProperty("user.dir") + "/userdata.xlsx";
        XLReader xl = new XLReader(path);

        int rownum = xl.getRowCount("UpdatedUser");
        int colcount = xl.getCellCount("UpdatedUser", 1);

        String apidata[][] = new String[rownum][colcount];

        for(int i=1; i<=rownum; i++)
        {
            for(int j=0; j<colcount; j++)
            {
                apidata[i-1][j] = xl.getCellData("UpdatedUser", i, j);
            }
        }

        return apidata;
    }


    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException
    {
        String path = System.getProperty("user.dir") + "/userdata.xlsx";
        XLReader xl = new XLReader(path);

        int rownum = xl.getRowCount("Sheet1");
        String apiData[] = new String[rownum];

        for(int i=1; i<=rownum; i++)
        {
            apiData[i-1] = xl.getCellData("Sheet1", i, 1);
        }

        return apiData;
    }

    @DataProvider(name = "petimage")
    public String[][] getPetImage() throws IOException
    {
        XLReader xl = new XLReader(System.getProperty("user.dir") + "/userdata.xlsx");
        int rownum = xl.getRowCount("PostPet");
        int colnum = xl.getCellCount("PostPet", rownum);
        String apidata[][] = new String[rownum][colnum];

        for(int i=1; i<= rownum; i++)
        {
            for(int j=0; j<colnum; j++)
            {
                apidata[i-1][j] = xl.getCellData("PostPet", i, j);
            }
        }

        return apidata;

    }

    @DataProvider(name = "PetIdProvider")
    public String[] getPetID() throws IOException
    {
        XLReader xl = new XLReader(System.getProperty("user.dir") + "/userdata.xlsx");

        int rownum = xl.getRowCount("PostPet");
        String apidata[] = new String[rownum];

        for(int i=1; i<=rownum; i++)
        {
            apidata[i-1] = xl.getCellData("PostPet", i, 0);
        }

        return apidata;

    }

}
