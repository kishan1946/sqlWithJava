package sql;

import org.testng.Reporter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlCRUD extends SqlCommons {
    Connection connect;
    Statement statement;
    ResultSet resultSet = null;

    public SqlCRUD(Connection connect, Statement statement) {
        super(connect,statement);
        this.connect=connect;
        this.statement=statement;
    }

    public void create(String tableName, String columns) {
        String query = String.format("create table IF NOT EXISTS %s (%s);", tableName, columns);
        createStatement();
        executeWriteQuery(query);
        Reporter.log("Table Created", true);
        closeStatement();
    }
    public void insert(String tableName, String columns, String values) {
        String query = String.format("insert into %s (%s) values (%s);", tableName, columns, values);
        createStatement();
        executeWriteQuery(query);
        Reporter.log("Row Inserted", true);
        closeStatement();
    }
    public void update(String tableName,String updateParameter,String updateData,String updateByParameter,String updateByParameterValue) {
        String query = String.format("update %s set %s='%s' where %s='%s';", tableName, updateParameter, updateData, updateByParameter, updateByParameterValue);
        createStatement();
        executeUpdateQuery(query);
        Reporter.log("Update successful", true);
        closeStatement();
    }
    public void delete(String tableName,String deleteParameter,String deleteValue) {
        String query = String.format("delete from %s where %s='%s'", tableName, deleteParameter, deleteValue);
        createStatement();
        executeUpdateQuery(query);
        Reporter.log("data deleted", true);
        closeStatement();
    }
    public void drop(String tableName) {
        String query = String.format("drop table %s", tableName);
        createStatement();
        executeUpdateQuery(query);
        Reporter.log("Table deleted", true);
        closeStatement();
    }
    public ResultSet readData(String parameter,String tableName) {
        String query = String.format("select %s from %s", parameter, tableName);
        createStatement();
        resultSet = executeReadQuery(query);
        return resultSet;
    }
    public ResultSet search(String parameter,String tableName,String searchByParameter,String searchByParameterValue) {
        String query = String.format("select %s from %s where %s='%s'", parameter, tableName, searchByParameter, searchByParameterValue);
        createStatement();
        resultSet = executeReadQuery(query);
        return resultSet;
    }
}
