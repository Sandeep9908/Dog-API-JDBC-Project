package com.learn.DOG_API_PROJECT_JDBC.Service;

import com.learn.DOG_API_PROJECT_JDBC.DAO.Images_DAO;
import com.learn.DOG_API_PROJECT_JDBC.DAO.Images_DAO_Implementation;

import java.util.List;

public class Images_Service {
    public final Images_DAO images_DAO = new Images_DAO_Implementation();

    public void addAllImages(List<String> images){
        try{
            images_DAO.clearAllImages();
            images_DAO.addAllImages(images);
        }
        catch(Exception e){
            System.out.println("Error in adding all images : "+e.getMessage());
        }
    }

    public List<String> getAllImages(){
        try{
            return images_DAO.getAllImages();
        }
        catch(Exception e){
            System.out.println("Error in getting all images : "+e.getMessage());
            return null;
        }
    }

    public List<String> getAllImages_By_Breed(String breedName) throws Exception{
        try{
            return images_DAO.getAllImages_By_Breed(breedName);
        }
        catch(Exception e){
            System.out.println("Error in getting all images : "+e.getMessage());
            return null;
        }
    }

    public List<String> getAllImages_By_SubBreed(String SubBreedName) throws Exception{
        try{
            return images_DAO.getAllImages_By_SubBreed(SubBreedName);
        } catch (Exception e) {
            System.out.println("Error in getting all images : "+e.getMessage());
            return null;
        }
    }

}
