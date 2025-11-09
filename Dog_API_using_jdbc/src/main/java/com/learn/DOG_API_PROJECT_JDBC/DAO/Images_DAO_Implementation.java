package com.learn.DOG_API_PROJECT_JDBC.DAO;

import com.learn.DOG_API_PROJECT_JDBC.DataBase_Connection.Db_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Images_DAO_Implementation implements Images_DAO {

    // clear all images in images table
    public void clearAllImages(){
        String query = "TRUNCATE TABLE images;";
        try(
                Connection conn = Db_Connection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
        ){
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Error in clearing all images"+e.getMessage());
        }
    }

    // add all images to the images table
    public void addAllImages(List<String> images) throws Exception{

        String breed_id_query ="Select breed_id from breeds where breed_name = ?";
        String subBreed_id_query="Select sub_breed_id from sub_breeds where sub_breed_name = ? ";

        String query = "INSERT INTO images (breed_id, sub_breed_id, image_url) VALUES (?, ?, ?)";

        try(
                Connection conn = Db_Connection.getConnection();
                PreparedStatement ps_breed_id = conn.prepareStatement(breed_id_query);
                PreparedStatement ps_subBreed_id = conn.prepareStatement(subBreed_id_query);
                PreparedStatement ps = conn.prepareStatement(query);
        ){
            conn.setAutoCommit(false);
            int batch_size = 200;
            int counter = 0;

            for(String image : images){
                List<String> names = get_breed_and_subBredd_name(image);
                
                String breed_name=names.get(0);
                String subBreed_name=names.get(1);


                ps_breed_id.setString(1, breed_name);
                ResultSet rs_breed_id = ps_breed_id.executeQuery();
                int breed_id = -1;
                if (rs_breed_id.next()) {
                    breed_id = rs_breed_id.getInt(1);
                }
                rs_breed_id.close();


                if(subBreed_name != null && !subBreed_name.equals("null")){
                    ps_subBreed_id.setString(1, subBreed_name);

                    ResultSet rs_subBreed_id = ps_subBreed_id.executeQuery();
                    int subBreed_id = -1;
                    if (rs_subBreed_id.next()) {
                        subBreed_id = rs_subBreed_id.getInt(1);
                    }
                    rs_subBreed_id.close();

                    ps.setInt(1, breed_id);
                    ps.setInt(2, subBreed_id);
                    ps.setString(3, image);
                    ps.addBatch();
                    counter++;
                }
                else{
                    ps.setInt(1, breed_id);
                    ps.setNull(2, java.sql.Types.INTEGER);
                    ps.setString(3, image);
                    ps.addBatch();
                    counter++;
                }

                if(counter == batch_size){
                    ps.executeBatch();
                    counter = 0;
                    ps.clearBatch();
                }
            }
            if(counter >0){
                ps.executeBatch();
            }
            conn.commit();
            System.out.println("add all images successfully");

        }
        catch(Exception e){
            System.out.println("Error in addAllImages : "+e.getMessage());
        }
    }
    // get breed and sub-breed names by using image_url
    public List<String> get_breed_and_subBredd_name(String image){
        List<String> names = new ArrayList<>();

        try{
            String[] parts = image.split("/");

            for(int i = 0; i < parts.length; i++){
                if(parts[i].equals("breeds") && i+1 < parts.length) {
                    String breed = parts[i + 1];
                    if (breed.contains("-")) {
                        String[] breedSplit = breed.split("-");
                        names.add(breedSplit[0]);
                        names.add(breedSplit[1]);
                    }
                    else{
                        names.add(breed);
                        names.add("null");
                    }
                    break;
                }
            }
        }catch(Exception e){
            System.out.println("Error in getting breed and subbreed : "+e.getMessage());
        }
        return  names;
    }

    public List<String> getAllImages(){
        List<String> images = new ArrayList<>();
        String query = "SELECT image_url FROM images";

        try(
                Connection conn = Db_Connection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ){
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String image_url = rs.getString("image_url");
                images.add(image_url);
            }
        }
        catch (Exception e){
            System.out.println("Error in listAllImages : "+e.getMessage());
        }
        return images;
    }

    public List<String> getAllImages_By_Breed(String breedName) throws Exception{
        List<String> breed_images = new ArrayList<>();

        String query = "select image_url from images where breed_id= (select breed_id from breeds where breed_name = ?)";

        try(
                Connection conn = Db_Connection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ){
            ps.setString(1, breedName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String image_url = rs.getString("image_url");
                breed_images.add(image_url);
            }

        } catch (Exception e) {
            System.out.println("Error in listAllImages_By_Breed : "+e.getMessage());
        }
        return  breed_images;
    }

    public List<String> getAllImages_By_SubBreed(String SubBreedName) throws Exception{
        List<String> SubBreed_images = new ArrayList<>();

        String query = "select image_url from images where sub_breed_id in (select sub_breed_id from sub_breeds where sub_breed_name = ?)";

        try(
                Connection conn = Db_Connection.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ){
            ps.setString(1, SubBreedName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String image_url = rs.getString("image_url");
                SubBreed_images.add(image_url);
            }
        }
        catch (Exception e){
            System.out.println("Error in listAllImages_By_SubBreed : "+e.getMessage());
        }
        return  SubBreed_images;
    }



}
