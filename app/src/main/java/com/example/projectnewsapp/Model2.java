package com.example.projectnewsapp;

public class Model2 {
    String name,image,by,time;

    public Model2(String name, String image,String by,String time) {
        this.name = name;
        this.image = image;
        this.by=by;
        this.time=time;
    }

    public Model2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
