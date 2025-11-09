package com.learn.DOG_API_PROJECT_JDBC.Dog_api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Show_one_random_image_of_breed {
    private static final String api_url = "https://dog.ceo/api/breeds/list/all";
    private static final Gson gson = new Gson();

    public void get_images_of_breed(){

        try{
            URL url = new URL(api_url);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();

            JsonObject json_response = gson.fromJson(response.toString(),JsonObject.class);
            JsonObject breeds = json_response.getAsJsonObject("message");
            int count=1;
            for(String breed : breeds.keySet()){
                String api_url_breed = "https://dog.ceo/api/breed/" + breed + "/images/random";

                String breed_url = get_Breed_image(api_url_breed);
                
                System.out.println(count +"  "+ breed+" -> "+breed_url);
                count++;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String get_Breed_image(String api_url_breed){
        String imageUrl = null;
        try{
            URL breed_url = new URL(api_url_breed);
            HttpURLConnection connection = (HttpURLConnection) breed_url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();

            JsonObject json_response = gson.fromJson(response.toString(),JsonObject.class);
            imageUrl = json_response.get("message").getAsString();

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return imageUrl;
    }
}
