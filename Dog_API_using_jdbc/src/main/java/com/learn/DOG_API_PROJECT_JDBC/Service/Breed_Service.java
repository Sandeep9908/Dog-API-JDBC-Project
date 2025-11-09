package com.learn.DOG_API_PROJECT_JDBC.Service;

import com.learn.DOG_API_PROJECT_JDBC.DAO.Breed_DAO;
import com.learn.DOG_API_PROJECT_JDBC.DAO.Dog_DAO;
import com.learn.DOG_API_PROJECT_JDBC.MODEL.Breeds;

import java.util.List;

public class Breed_Service {
    public final Dog_DAO dog_dao = new Breed_DAO();

        public void saveAllBreeds(List<String> breeds) {
            try {
                dog_dao.clearAllBreeds();
                dog_dao.addAllBreeds(breeds);
            } catch (Exception e) {
                System.err.println("Error saving breeds: " + e.getMessage());
            }
        }

        public List<Breeds> getAllBreeds() {
            try{
                return dog_dao.getAllBreeds();
            }
            catch (Exception e){
                System.err.println("Error getting breeds: " + e.getMessage());
                return null;
            }
        }

}
