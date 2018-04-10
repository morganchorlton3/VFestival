package com.vfestival;

public class Artists {

    private String name, bio, fLink, tLink, wlink;
    private int thumbnail;

    public Artists(String name, int thumbnail, String fLink, String tLink, String wLink, String bio) {
        this.name = name;
        this.fLink = fLink;
        this.tLink = tLink;
        this.wlink = wLink;
        this.thumbnail = thumbnail;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getfLink() {return fLink;}

    public String gettLink() {return tLink;}

    public String getWlink() {return wlink;}

    public int getThumbnail() {return thumbnail;}
}
