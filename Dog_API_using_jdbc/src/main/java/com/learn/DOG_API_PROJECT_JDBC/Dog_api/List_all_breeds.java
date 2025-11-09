package com.learn.DOG_API_PROJECT_JDBC.Dog_api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class List_all_breeds {
    private static  final String api_url = "https://dog.ceo/api/breeds/list/all";
    private static final Gson gson = new Gson();

    public List<String> get_all_breed_names(){
        List<String> breed_names = new ArrayList<>();
        try{
            URL url = new URL(api_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String Line;
            StringBuffer response = new StringBuffer();
            while ((Line = reader.readLine()) != null){
                response.append(Line);
            }
            reader.close();

            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
            JsonObject messageObject = jsonResponse.getAsJsonObject("message");


            for (String breed : messageObject.keySet()) {
                breed_names.add(breed);
            }

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return breed_names;
    }
}
