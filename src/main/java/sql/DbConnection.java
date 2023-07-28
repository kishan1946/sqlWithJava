package sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public Connection connectToDb(String originUrl,String url,String dbName, String user,String password) {
         Connection connect=null;
         try{
             Class.forName(originUrl);
             connect= DriverManager.getConnection(url+dbName,user,password);
             if(connect!=null){
                 System.out.println("connection established");
             }else {
                 System.out.println("Connect Failed");
             }
         } catch (Exception e) {
             System.out.println(e);;
         }
         return connect;
    }
}
