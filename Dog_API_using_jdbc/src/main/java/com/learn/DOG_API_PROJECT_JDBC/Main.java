package com.learn.DOG_API_PROJECT_JDBC;

import com.learn.DOG_API_PROJECT_JDBC.Dog_api.List_All_Sub_Breeds;
import com.learn.DOG_API_PROJECT_JDBC.Dog_api.List_all_breeds;
import com.learn.DOG_API_PROJECT_JDBC.MODEL.Breeds;
import com.learn.DOG_API_PROJECT_JDBC.Menu.API_Data_to_Database;
import com.learn.DOG_API_PROJECT_JDBC.Menu.API_Operations;
import com.learn.DOG_API_PROJECT_JDBC.Menu.DB_Data;
import com.learn.DOG_API_PROJECT_JDBC.Service.Breed_Service;
import com.learn.DOG_API_PROJECT_JDBC.Service.Sub_breed_Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.learn.DOG_API_PROJECT_JDBC.Menu.API_Data_to_Database.saveDataToDatabase;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("======= menu option ======");
            System.out.println("1. API Operations");
            System.out.println("2. Save API Data to Database");
            System.out.println("3. Get data from database");
            System.out.println("4. Exit");

            System.out.print("Please choose the options : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    API_Operations.apiOperationsMenu();
                    break;
                case 2:
                    API_Data_to_Database.saveDataToDatabase();
                    break;
                case 3:
                    DB_Data.getData_from_database();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
