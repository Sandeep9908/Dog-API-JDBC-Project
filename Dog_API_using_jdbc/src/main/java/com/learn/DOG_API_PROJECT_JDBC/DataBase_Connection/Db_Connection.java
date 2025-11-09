package com.learn.DOG_API_PROJECT_JDBC.DataBase_Connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Db_Connection {

    public static Connection getConnection() throws SQLException, IOException {
        Properties prop = new Properties();
        try(FileInputStream fis = new FileInputStream("src/main/resources/db.properties")){
            prop.load(fis);
        }catch(Exception e){
            System.out.println("Error in loadinging properties file");
        }

        String db_user =prop.getProperty("db_user");
        String db_password =prop.getProperty("db_password");
        String db_url =prop.getProperty("db_url");

        return DriverManager.getConnection(db_url,db_user,db_password);
    }
}
