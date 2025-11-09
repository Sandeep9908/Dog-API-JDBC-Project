package com.learn.DOG_API_PROJECT_JDBC.Menu;

import com.learn.DOG_API_PROJECT_JDBC.Dog_api.List_All_Sub_Breeds;
import com.learn.DOG_API_PROJECT_JDBC.Dog_api.List_all_breeds;
import com.learn.DOG_API_PROJECT_JDBC.Dog_api.Show_all_images;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class API_Operations {

    public static void apiOperationsMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n======= API OPERATIONS =======");
            System.out.println("1. List All Breeds");
            System.out.println("2. List All Sub Breeds");
            System.out.println("3. List All Sub Breeds with Breed ");
            System.out.println("4. List All images");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    List_all_breeds list = new List_all_breeds();
                    List<String> breeds = list.get_all_breed_names();
                    System.out.println("\nAll Breeds:");
                    int count = 1;
                    for (String breed : breeds) {
                        System.out.println(count++ + ". " + breed);
                    }
                    break;
                }

                case 2: {
                    List_All_Sub_Breeds subList = new List_All_Sub_Breeds();
                    Map<String, List<String>> data = subList.get_all_sub_breeds();
                    System.out.println("\nBreed → Sub-breeds:");
                    for (String breed : data.keySet()) {
                        List<String> subBreeds = data.get(breed);
                        if (!subBreeds.isEmpty()) {
                            for (String subbreed : subBreeds) {
                                System.out.println(subbreed);
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    List_All_Sub_Breeds subList = new List_All_Sub_Breeds();
                    Map<String, List<String>> data = subList.get_all_sub_breeds();

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
                    break;
                }
                case 4:{
                    Show_all_images showAllImages = new Show_all_images();
                    List<String> breedImages = showAllImages.get_images_of_all_breeds();

                    for(String image_url : breedImages){
                        System.out.println(image_url);
                    }

                    break;
                }
                case 5: {
                    System.out.println("Returning to main menu...\n");
                    return;
                }

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
