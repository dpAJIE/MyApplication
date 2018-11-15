package com.hrdcdn.user.myapplication;

public class Model {
    private String title;
    private String desc;
    private String image;
    private String imageName;


    public Model(String title, String desc, String image, String imageName) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.imageName = imageName;
    }



    public Model(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageName() { return imageName; }

    public void setImageName(String imageName) { this.imageName = imageName; }

}
