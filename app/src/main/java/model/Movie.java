package model;

import com.google.gson.annotations.SerializedName;

import web.RecyclerInterface;

public class Movie {
    private String subtitle;
    private String sources;
    private String thumb;
    //this annotation need for adding name image property to model
    @SerializedName("image-480x270")
    private String imageSmall;
    //this annotation need for adding name image property to model
    @SerializedName("image-780x1200")
    private String imageLarge;
    private String title;
    private String studio;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImageSmall() {
        return imageSmall;
    }
    //adding main URL and rest to Image Path
    public void setImageSmall(String imageSmall) {
        this.imageSmall = RecyclerInterface.JSONURL + imageSmall;
    }

    public String getImageLarge() {
        return imageLarge;
    }
    //adding main URL and rest to Image Path
    public void setImageLarge(String imageLarge) {
        this.imageLarge = RecyclerInterface.JSONURL + imageLarge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
}
