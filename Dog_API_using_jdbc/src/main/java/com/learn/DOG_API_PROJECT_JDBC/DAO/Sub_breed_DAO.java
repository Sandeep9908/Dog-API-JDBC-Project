package com.learn.DOG_API_PROJECT_JDBC.DAO;

import java.util.List;
import java.util.Map;

public interface Sub_breed_DAO {
    void addAllSubBreeds(Map<String, List<String>> sub_breeds) throws Exception;
    void clearAllSubBreeds() throws Exception;
    Map<String, List<String>> getAllSubBreeds_with_breed() throws Exception;
}
