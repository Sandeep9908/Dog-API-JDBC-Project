package com.learn.DOG_API_PROJECT_JDBC.DAO;

import com.learn.DOG_API_PROJECT_JDBC.DataBase_Connection.Db_Connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Sub_breed_DAO_implementation implements Sub_breed_DAO {

    public  void clearAllSubBreeds() throws Exception{
        try(
                Connection conn = Db_Connection.getConnection();
                Statement stmt = conn.createStatement();
        ){
            String query = "TRUNCATE TABLE sub_breeds;";
            stmt.execute(query);
        }
        catch(Exception e){
            System.out.println("Error in removing sub_breeds data"+e.getMessage());
        }
    }

    public void addAllSubBreeds(Map<String, List<String>> data) throws SQLException, IOException {

        String get_breed_id = "select breed_id from breeds where breed_name = ?";
        String insert_sub_breed_data = "insert into sub_breeds(breed_id,sub_breed_name) values(?,?)";

        try (
                Connection conn = Db_Connection.getConnection();
                PreparedStatement ps_breed_id = conn.prepareStatement(get_breed_id);
                PreparedStatement ps_sub_breed_data = conn.prepareStatement(insert_sub_breed_data);
        ){
            conn.setAutoCommit(false);

            for(String breed_name : data.keySet()){
                List<String> sub_breeds = data.get(breed_name);

                ps_breed_id.setString(1, breed_name);
                ResultSet rs_breed_Id = ps_breed_id.executeQuery();

                if(rs_breed_Id.next()){
                    int id=rs_breed_Id.getInt("breed_id");

                    for(String sub_breed : sub_breeds){
                        ps_sub_breed_data.setInt(1,id );
                        ps_sub_breed_data.setString(2, sub_breed);
                        ps_sub_breed_data.addBatch();
                    }
                }
            }
            ps_sub_breed_data.executeBatch();
            conn.commit();
//            System.out.println("all sub breed inserted successfully");

        }
        catch (SQLException e){
            System.out.println("Error in inserting sub breed data"+e.getMessage());
        }
    }

    public Map<String, List<String>> getAllSubBreeds_with_breed() throws Exception {
        Map<String, List<String>> SubBreeds = new HashMap<>();


        final String QUERY = """
                select b.breed_name, sb.sub_breed_name from breeds b
                        inner join sub_breeds sb on b.breed_id = sb.breed_id;
            """;



        try (Connection conn = Db_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QUERY);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String breedName = rs.getString("breed_name");
                String subBreedName = rs.getString("sub_breed_name");
                SubBreeds.putIfAbsent(breedName, new ArrayList<>());
                SubBreeds.get(breedName).add(subBreedName);
            }
        }
    return SubBreeds;
    }
}
