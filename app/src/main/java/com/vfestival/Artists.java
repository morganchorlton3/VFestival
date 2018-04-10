package com.vfestival;

public class Artists {

    private String name, bio;
    private int thumbnail;

    public Artists(String name, String bio, int thumbnail) {
        this.name = name;
        this.bio = bio;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}
