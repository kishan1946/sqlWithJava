package sqlTesting;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sql.CRUDCall;
import sqlBase.DbConnection;

import java.sql.Connection;
import java.sql.Statement;

public class DbCRUDTest {
    private Connection connect;
    private Statement statement;

    @DataProvider(name = "dbCrudCall")
    public Object[][] dbCrudCall() {
        return  new Object[][] {{"create","utilities","product",null}};
    }

    @BeforeTest
    public void dbConnect() {
        connect=new DbConnection().dbConnection();
    }
    @Test(dataProvider = "dbCrudCall")
    public void crudTest(String call,String packageName,String jsonFile,String objectName) {
//        Manual testing from pgAdmin4
        CRUDCall crudCall=new CRUDCall(connect,statement);
        crudCall.callCrud(call,jsonFile,packageName,objectName);
    }
}
