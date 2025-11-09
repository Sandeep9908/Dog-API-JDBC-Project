package com.learn.DOG_API_PROJECT_JDBC.DAO;

import com.learn.DOG_API_PROJECT_JDBC.MODEL.Breeds;
import com.learn.DOG_API_PROJECT_JDBC.DataBase_Connection.Db_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Breed_DAO implements Dog_DAO {

    public void clearAllBreeds() throws Exception {

        Connection conn = Db_Connection.getConnection();
        Statement stmt = conn.createStatement();
        String  query = "TRUNCATE TABLE breeds";
        stmt.execute(query);
    }

        public void addAllBreeds(List<String> breeds) throws Exception{

        String query = "insert into breeds (breed_name) values (?)";
        try(
                Connection conn = Db_Connection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ){
            conn.setAutoCommit(false);

            int total = breeds.size();
            int batch =30;
            int count = 0;
            for(String breed : breeds){
                ps.setString(1, breed);
                ps.addBatch();
                count++;

                if(count%batch==0) {
                    ps.executeBatch();
                    count = 0;
                    ps.clearBatch();
                }
            }
            if(count%batch!=0) {
                ps.executeBatch();
            }
            conn.commit();
        }
        catch(Exception e){
            System.out.println("Error in inserting breeds: " + e.getMessage());
        }

    }

    public List<Breeds> getAllBreeds() throws Exception {
        List<Breeds> breedList = new ArrayList<>();
        String query = "SELECT * FROM breeds";
        try(
                Connection conn = Db_Connection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ){
            while(rs.next()){
                Breeds breeds = new Breeds();
                breeds.setBreed_id(rs.getInt("breed_id"));
                breeds.setBreed_name(rs.getString("breed_name"));
                breedList.add(breeds);
            }
        }
        return breedList;
    }

}
