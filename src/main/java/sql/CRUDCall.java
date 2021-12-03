package sql;

import jsonDataReader.JsonDataReader;
import org.json.simple.JSONObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUDCall extends SqlCRUD{
    Connection connect;
    Statement statement;
    ResultSet resultSet=null;
    public CRUDCall(Connection connect,Statement statement) {
        super(connect,statement);
        this.connect=connect;
        this.statement=statement;
    }
    public ResultSet callCrud(String call,String jsonFile,String packageName,String objectName) {
        JsonDataReader jsonDataReader=new JsonDataReader();
        JSONObject jsonObj=jsonDataReader.readJson(jsonFile,packageName,objectName);
        switch(call){
            case "create" :
                create((String) jsonObj.get("tableName"),(String) jsonObj.get("createColumns"));
                break;
            case "insert" :
                insert((String) jsonObj.get("tableName"),(String) jsonObj.get("insertColumns"),(String) jsonObj.get("values"));
                break;
            case "update" :
                update((String) jsonObj.get("tableName"),(String) jsonObj.get("updateParameter"),(String) jsonObj.get("updateData"),(String) jsonObj.get("updateByParameter"),(String) jsonObj.get("updateByParameterValue"));
                break;
            case "delete" :
                delete((String) jsonObj.get("tableName"),(String) jsonObj.get("deleteParameter"),(String) jsonObj.get("deleteValue"));
                break;
            case "drop" :
                drop((String) jsonObj.get("tableName"));
                break;
            case "read" :
                resultSet = readData((String) jsonObj.get("readByParameter"),(String) jsonObj.get("tableName"));
                return resultSet;
            case "search" :
                resultSet=search((String) jsonObj.get("readByParameter"),(String) jsonObj.get("tableName"),(String) jsonObj.get("searchByParameter"),(String) jsonObj.get("searchByParameterValue"));
                return resultSet;
            default:
                return resultSet;
        }
        return resultSet;
    }
}
