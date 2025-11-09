package com.learn.DOG_API_PROJECT_JDBC.MODEL;

public class Breeds {
    private int breed_id;
    private String breed_name;

    public Breeds() {
    }

    public Breeds(int breed_id, String breed_name) {
        this.breed_id = breed_id;
        this.breed_name = breed_name;
    }

    public int getBreed_id() {
        return breed_id;
    }
    public void setBreed_id(int breed_id) {
        this.breed_id = breed_id;
    }

    public String getBreed_name() {
        return breed_name;
    }
    public void setBreed_name(String breed_name) {
        this.breed_name = breed_name;
    }

    @Override
    public String toString() {
        return String.format("Breed { Breed_id: %d, Breed_name: %s }", breed_id, breed_name);
    }
}
