package com.example.john.lastread;

public
class Article {
    private String name;
    private String detail;
    private int imageId;
    public Article(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public String getDetail(){
        return detail;
    }
    public int getImageId(){
        return imageId;
    }
}
