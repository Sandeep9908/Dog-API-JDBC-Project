package com.learn.DOG_API_PROJECT_JDBC.Menu;

import com.learn.DOG_API_PROJECT_JDBC.MODEL.Breeds;
import com.learn.DOG_API_PROJECT_JDBC.Service.Breed_Service;
import com.learn.DOG_API_PROJECT_JDBC.Service.Images_Service;
import com.learn.DOG_API_PROJECT_JDBC.Service.Sub_breed_Service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DB_Data {
    public static void getData_from_database() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n======= GET DATA FROM DATABASE =======");
            System.out.println("1. List All Breeds from Database");
            System.out.println("2. List All Sub-Breeds from Database");
            System.out.println("3. LIST OF BREEDS WITH SUB-BREEDS");
            System.out.println("4. LIST All Images from Database");
            System.out.println("5. LIST all Images by Breed");
            System.out.println("6. LIST all Images by Sub-Breed");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    Breed_Service service = new Breed_Service();
                    List<Breeds> breeds = service.getAllBreeds();

                    if (breeds == null || breeds.isEmpty()) {
                        System.out.println("No breeds found in the database.");
                    } else {
                        System.out.println("All Breeds from Database:");
                        int count = 1;
                        for (Breeds breed : breeds) {
                            System.out.println(breed.getBreed_id()+ ". " + breed.getBreed_name());
                        }
                    }
                    break;
                }
                case 2: {
                    try{
                        Sub_breed_Service service = new Sub_breed_Service();
                        Map<String, List<String>> subBreeds = service.getAllSubBreeds_with_breed();
                        System.out.println("\n======= LIST OF All SUB-BREEDS =======");
                        if (subBreeds.isEmpty()) {
                            System.out.println("No breeds or sub-breeds found in the database.");
                        } else {
                            for(String breed : subBreeds.keySet()) {
                                List<String> subBreed_List = subBreeds.get(breed);
                                if(!subBreed_List.isEmpty()) {
                                    for (String subbreed : subBreed_List) {
                                        System.out.println(subbreed);
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception e){
                        System.out.println("Error in getting sub-breeds from database."+e.getMessage());
                    }
                    break;
                }

                case 3: {
                    try {
                        Sub_breed_Service service = new Sub_breed_Service();
                        Map<String, List<String>> data = service.getAllSubBreeds_with_breed();

                        System.out.println("\n======= LIST OF BREEDS WITH SUB-BREEDS =======");

                        if (data == null || data.isEmpty()) {
                            System.out.println("No breeds or sub-breeds found in the database.");
                        } else {
                            for (String breed : data.keySet()) {
                                List<String> subBreeds = data.get(breed);
                                if (subBreeds != null && !subBreeds.isEmpty()) {
                                    System.out.println(breed + " : " + String.join(", ", subBreeds));
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error while fetching breeds and sub-breeds from database: " + e.getMessage());
                    }
                    break;
                }

                case 4: {
                    Images_Service service = new Images_Service();
                    List<String> images = service.getAllImages();
                    if (images == null || images.isEmpty()) {
                        System.out.println("No images found in the database.");
                    }
                    else {
                        for (String image : images) {
                            System.out.println(image);
                        }
                    }
                    break;
                }

                case 5: {
                    Images_Service service = new Images_Service();
                    System.out.print("Enter breed name( Ex : bluetick , bulldog , sheepdog) : ");
                    String breed = sc.next();
                    try{
                        List<String> Breed_images = service.getAllImages_By_Breed(breed);
                        if (Breed_images == null || Breed_images.isEmpty()) {
                            System.out.println("No images found for the breed: " + breed);
                        }
                        else{
                            System.out.println("All images for the breed '" + breed + "':");
                            for (String image : Breed_images) {
                                System.out.println(image);
                            }
                        }
                    }
                    catch (Exception e){
                        System.out.println("Error while fetching images for the breed: " + e.getMessage());
                    }
                    break;
                }
                case 6: {
                    Images_Service service = new Images_Service();
                    System.out.print("Enter SubBreedName(Ex : miniature , shepherd , indian ) : ");
                    String subBreed = sc.next();

                    try{
                        List<String> subBreed_images = service.getAllImages_By_SubBreed(subBreed);
                        if (subBreed_images == null || subBreed_images.isEmpty()) {
                            System.out.println("No images found for the sub-breed: " + subBreed);
                        }
                        else{
                            System.out.println("All images for the sub-breed '" + subBreed);
                            for (String image : subBreed_images) {
                                System.out.println(image);
                            }
                        }
                    }catch (Exception e){
                        System.out.println("Error while fetching sub-breeds from database: " + e.getMessage());
                    }
                    break;
                }
                case 7: {
                    System.out.println("Returning to main menu...");
                    return;
                }

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
