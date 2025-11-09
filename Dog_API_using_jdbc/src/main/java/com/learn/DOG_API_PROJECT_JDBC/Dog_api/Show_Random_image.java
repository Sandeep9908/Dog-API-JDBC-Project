package com.learn.DOG_API_PROJECT_JDBC.Dog_api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Show_Random_image {
    private static String api_url = "https://dog.ceo/api/breeds/image/random";
    private static Gson  gson = new Gson();

    public void get_random_image(){
        try{
            URL url = new URL(api_url);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String Line = reader.readLine();


            JsonObject response = gson.fromJson(Line,JsonObject.class);
            String imageUrl = response.get("message").getAsString();

            System.out.println(imageUrl);

            

        }
        catch(Exception e){
            System.out.println("Error in getting random image : "+e.getMessage());
        }
    }

}
