package com.example.contacts;

public class Contacts {
    private String name;
    private String image_url;
    private String position;

    public Contacts() {
    }

    public Contacts(String name, String image_url, String position) {
        this.name = name;
        this.image_url = image_url;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
