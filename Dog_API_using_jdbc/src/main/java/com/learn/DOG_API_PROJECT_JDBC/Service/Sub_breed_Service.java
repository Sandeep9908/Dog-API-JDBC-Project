package com.learn.DOG_API_PROJECT_JDBC.Service;

import com.learn.DOG_API_PROJECT_JDBC.DAO.Dog_DAO;
import com.learn.DOG_API_PROJECT_JDBC.DAO.Sub_breed_DAO;
import com.learn.DOG_API_PROJECT_JDBC.DAO.Sub_breed_DAO_implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sub_breed_Service {
    public final Sub_breed_DAO subBreedDao = new Sub_breed_DAO_implementation();

    public void addAllSubBreeds(Map<String, List<String>> sub_breeds){
        try {
            subBreedDao.clearAllSubBreeds();
            subBreedDao.addAllSubBreeds(sub_breeds);
//            System.out.println("All sub-breeds inserted successfully via service layer.");
        } catch (Exception e) {
            System.out.println("Error while adding sub-breeds: " + e.getMessage());
        }
    }

    public Map<String, List<String>> getAllSubBreeds_with_breed() throws Exception {
        return subBreedDao.getAllSubBreeds_with_breed();
    }
}
