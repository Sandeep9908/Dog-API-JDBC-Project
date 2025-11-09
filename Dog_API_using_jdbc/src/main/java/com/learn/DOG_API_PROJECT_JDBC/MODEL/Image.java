package com.learn.DOG_API_PROJECT_JDBC.MODEL;

public class Image {

    private int image_id;
    private int breed_id;
    private Integer sub_breed_id;  // nullable
    private String image_url;

    public Image() {
    }

    public Image(int image_id, int breed_id, Integer sub_breed_id, String image_url) {
        this.image_id = image_id;
        this.breed_id = breed_id;
        this.sub_breed_id = sub_breed_id;
        this.image_url = image_url;
    }

    public int getImage_id() {
        return image_id;
    }
    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getBreed_id() {
        return breed_id;
    }
    public void setBreed_id(int breed_id) {
        this.breed_id = breed_id;
    }

    public Integer getSub_breed_id() {
        return sub_breed_id;
    }
    public void setSub_breed_id(Integer sub_breed_id) {
        this.sub_breed_id = sub_breed_id;
    }

    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return String.format("Image { Image_id: %d, Breed_id: %d, Sub_breed_id: %s, Image_url: %s }",
                image_id, breed_id, sub_breed_id == null ? "NULL" : sub_breed_id.toString(), image_url);
    }

}
