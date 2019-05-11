package com.example.projekat.Models;

public class Attachment {
    private int id;
    private String data;
    private String type;
    private String name;

    public Attachment() {

    }

    public Attachment(int id, String data, String type, String name) {
        this.id = id;
        this.data = data;
        this.type = type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
