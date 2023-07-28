package sql;

import org.testng.Reporter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlCommons {
    private Connection connect;
    private Statement statement;
    private ResultSet resultSet;

    public SqlCommons(Connection connect,Statement statement) {
        this.connect = connect;
        this.statement=statement;
    }

    public void createStatement(){
        try{
            statement=connect.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void executeWriteQuery(String query){
        try{
            statement.execute(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public ResultSet executeReadQuery(String query){
        try{
            resultSet=statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return resultSet;
    }
    public void executeUpdateQuery(String query){
        try{
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void closeStatement(){
        try {
            statement.close();
            Reporter.log("statement closed",true);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
