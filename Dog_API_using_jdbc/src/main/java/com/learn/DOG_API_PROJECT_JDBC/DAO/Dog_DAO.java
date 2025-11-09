package com.learn.DOG_API_PROJECT_JDBC.DAO;

import com.learn.DOG_API_PROJECT_JDBC.MODEL.Breeds;

import java.util.List;
import java.util.Map;

public interface Dog_DAO {
    void addAllBreeds(List<String> breeds) throws Exception;
    void clearAllBreeds() throws Exception;
    List<Breeds> getAllBreeds() throws Exception;
}
