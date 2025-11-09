package com.learn.DOG_API_PROJECT_JDBC.DAO;

import java.util.List;

public interface Images_DAO {
    void addAllImages(List<String> images) throws Exception;
    void clearAllImages() throws Exception;
    List<String> getAllImages() throws Exception;
    List<String> getAllImages_By_Breed(String breedName) throws Exception;
    List<String> getAllImages_By_SubBreed(String SubBreedName) throws Exception;
}
