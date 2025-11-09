package com.learn.DOG_API_PROJECT_JDBC.Dog_api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class List_All_Sub_Breeds {
    private static final String API_URL = "https://dog.ceo/api/breeds/list/all";
    private static final Gson gson = new Gson();

    public Map<String, List<String>> get_all_sub_breeds() {
        Map<String, List<String>> sub_breed_from_breed = new HashMap<>();

        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
            JsonObject message = jsonResponse.getAsJsonObject("message");

            for (String breed : message.keySet()) {
                JsonArray subBreeds = message.getAsJsonArray(breed);
                List<String> sub_Breeds = new ArrayList<>();

                if(subBreeds.size()>0){
                    for (int i = 0; i < subBreeds.size(); i++) {
                        sub_Breeds.add(subBreeds.get(i).getAsString());
                    }

                    sub_breed_from_breed.put(breed, sub_Breeds);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return sub_breed_from_breed;
    }
}
