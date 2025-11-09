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

public class Show_all_images {
    private static final String API_URL = "https://dog.ceo/api/breeds/list/all";
    private static final Gson gson = new Gson();

    public List<String> get_images_of_all_breeds() {
        List<String> breedImages = new ArrayList<>();

        try{
            URL url = new URL(API_URL);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
            JsonObject breeds = jsonResponse.getAsJsonObject("message");

            for(String breed : breeds.keySet()){
                String image_url = "https://dog.ceo/api/breed/" + breed + "/images";
                List<String> images = getAllBreedImages(image_url);

                for(String image : images){
                    breedImages.add(image);
                }

            }
        }
        catch (Exception e){
            System.out.println("Error in getting images of all breeds"+e.getMessage());
        }
        //  System.out.println("images count : "+breedImages.size());
        return breedImages;
    }

    public List<String> getAllBreedImages(String image_url){
        List<String> images = new ArrayList<>();

        try{
            URL url =new URL(image_url);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
            JsonArray imageArray = jsonResponse.getAsJsonArray("message");

            for(int i=0; i<imageArray.size();i++){
                String BreedName = imageArray.get(i).getAsString();
                images.add(BreedName);
            }

        }
        catch (Exception e){
            System.out.println("Error in getting images of all breeds"+e.getMessage());
        }
        return images;
    }
}
