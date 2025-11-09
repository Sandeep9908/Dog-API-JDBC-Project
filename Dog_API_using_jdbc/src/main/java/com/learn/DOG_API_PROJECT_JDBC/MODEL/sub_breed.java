package com.learn.DOG_API_PROJECT_JDBC.MODEL;

public class sub_breed {
    private String breed_name;
    private int breed_id;
    private int sub_breed_id;

    public sub_breed(){ }

    public sub_breed(int sub_breed_id, int breed_id,String breed_name) {
        this.sub_breed_id = sub_breed_id;
        this.breed_id = breed_id;
        this.breed_name = breed_name;
    }

    public int getSub_breed_id() {
        return sub_breed_id;
    }
    public void setSub_breed_id(int sub_breed_id) {
        this.sub_breed_id = sub_breed_id;
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

    public String to_String(){
        return String.format("sub_breed_id : %d,breed_id: %d, breed_name: %s", sub_breed_id,breed_id, breed_name);
    }

}
