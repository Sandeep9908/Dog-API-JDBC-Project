package com.learn.DOG_API_PROJECT_JDBC.Menu;

import com.learn.DOG_API_PROJECT_JDBC.Dog_api.List_All_Sub_Breeds;
import com.learn.DOG_API_PROJECT_JDBC.Dog_api.List_all_breeds;
import com.learn.DOG_API_PROJECT_JDBC.Dog_api.Show_all_images;
import com.learn.DOG_API_PROJECT_JDBC.Service.Breed_Service;
import com.learn.DOG_API_PROJECT_JDBC.Service.Images_Service;
import com.learn.DOG_API_PROJECT_JDBC.Service.Sub_breed_Service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class API_Data_to_Database {
    public static void saveDataToDatabase() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n======= SAVE API DATA =======");
            System.out.println("1. Save All Breeds to Database");
            System.out.println("2. Save All Sub-Breeds to Database");
            System.out.println("3. Save All images to Database");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    // Step 1: Save all breeds to the database
                    List_all_breeds breedApi = new List_all_breeds();
                    List<String> breeds = breedApi.get_all_breed_names();
                    Breed_Service service = new Breed_Service();
                    service.saveAllBreeds(breeds);
                    System.out.println("All breeds saved successfully!");
                    break;
                }

                case 2: {
                    // Step 2: Save all sub-breeds to database
                    Sub_breed_Service service = new Sub_breed_Service();
                    List_All_Sub_Breeds obj = new List_All_Sub_Breeds();
                    Map<String, List<String>> data = obj.get_all_sub_breeds();
                    service.addAllSubBreeds(data);
                    System.out.println("All sub-breeds saved successfully!");
                    break;
                }
                case 3: {
                    Show_all_images Images= new Show_all_images();
                    List<String> images = Images.get_images_of_all_breeds();
                    Images_Service service = new Images_Service();
                    service.addAllImages(images);
                    break;
                }
                case 4: {
                    System.out.println("Returning to main menu...\n");
                    return;
                }

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
