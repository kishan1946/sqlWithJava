package sqlBase;

import utilities.PropertyReader;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DbConnection {
    Connection connect;
    public Connection dbConnection() {
        try {
            String env = System.getProperty("env");
            Properties properties = new PropertyReader().getProperty(env);
            String url = properties.getProperty("jdbc") + properties.getProperty("domain") + properties.getProperty("host") + properties.getProperty("hostPort");
            return (new sql.DbConnection().connectToDb(properties.getProperty("originUrl"), url, properties.getProperty("dbName"), properties.getProperty("user"), properties.getProperty("password")));
        }catch (IOException e){
            e.printStackTrace();
        }
        return connect;
    }
}
