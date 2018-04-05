package com.vfestival;


public class Artist {

    private String Name;
    private String Category ;
    private String Description ;
    private int Thumbnail ;

    public Artist() {
    }

    public Artist(String name, String category, String description, int thumbnail) {
        Name = name;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
    }


    public String getTitle() {
        return Name;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
