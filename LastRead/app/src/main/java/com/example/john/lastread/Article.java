package com.example.john.lastread;

public
class Article {
    private String name;
    private int imageId;
    public Article(String name,int imageId){
        this.name=name;
        this.imageId=imageId;

    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
