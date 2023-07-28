package sqlTesting;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sql.CRUDCall;
import sqlBase.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDataTest {
    private Connection connect;
    private Statement statement;
    private ResultSet resultSet;

    @BeforeTest
    public void dbConnect() {
        connect=new DbConnection().dbConnection();
    }
    public ResultSet getResultSet(String call,String packageName,String jsonFile,String objectName) {
        CRUDCall crudCall = new CRUDCall(connect, statement);
        resultSet = crudCall.callCrud(call, jsonFile, packageName, objectName);
        return resultSet;
    }
    @DataProvider(name = "checkTablePresentInDB")
    public Object[][] checkTablePresentInDB() {
        return  new Object[][] {{"search","utilities","dbDataTest","checkTablePresentInDB"}};
    }
    @Test(dataProvider = "checkTablePresentInDB")
    public void checkTablePresentInDB(String call,String packageName,String jsonFile,String objectName) throws SQLException {
//        select count(table_name) from information_schema.tables where table_schema='public';
        resultSet=getResultSet(call, packageName, jsonFile,objectName);
        int count= 0;
        while(resultSet.next()){
            count=resultSet.getInt("count");
        }
        System.out.println(count);
        Reporter.log(String.valueOf(count));
        Assert.assertTrue(count>0);
    }
    @DataProvider(name = "checkTableNameConvention")
    public Object[][] checkTableNameConvention() {
        return  new Object[][] {{"search","utilities","dbDataTest","checkTableNameConvention"}};
    }
    @Test(dataProvider = "checkTableNameConvention")
    public void checkTableNameConvention(String call,String packageName,String jsonFile,String objectName) throws SQLException {
//        select table_name from information_schema.tables where table_schema='public';
        resultSet=getResultSet(call, packageName, jsonFile,objectName);
        String table_name;
        boolean namingConvention=true;
        while(resultSet.next()){
            table_name=resultSet.getString("table_name");
            Reporter.log(table_name);
            if(table_name.contains(" ") || table_name.contains("-")){
                namingConvention=false;
                break;
            }
        }
        Assert.assertTrue(namingConvention);
    }
    @DataProvider(name = "checkSizeOfColumn")
    public Object[][] checkSizeOfColumn() {
        return  new Object[][] {{"search","utilities","dbDataTest","checkSizeOfColumn"}};
    }
    @Test(dataProvider = "checkSizeOfColumn")
    public void checkSizeOfColumn(String call,String packageName,String jsonFile,String objectName) throws SQLException {
//        select column_name from information_schema.columns where table_name='product';
        resultSet=getResultSet(call, packageName, jsonFile,objectName);
        while (resultSet.next()){
            Reporter.log(resultSet.getString("column_name"));
        }
    }
    @DataProvider(name = "checkDataTypeOfColumn")
    public Object[][] checkDataTypeOfColumn() {
        return  new Object[][] {{"search","utilities","dbDataTest","checkDataTypeOfColumn"}};
    }
    @Test(dataProvider = "checkDataTypeOfColumn")
    public void checkDataTypeOfColumn(String call,String packageName,String jsonFile,String objectName) throws SQLException {
//        select column_name,data_type from information_schema.columns where table_name='product';
        resultSet=getResultSet(call, packageName, jsonFile,objectName);
        boolean checkDataType=true;
        while (resultSet.next()){
            String dataType=resultSet.getString("data_type");
            if ((!dataType.equals("integer")) && (!dataType.equals("character varying"))){
                checkDataType=false;
                break;
            }
            Reporter.log(resultSet.getString("column_name")+" | "+resultSet.getString("data_type"));

        }
        Assert.assertTrue(checkDataType);
    }
}
