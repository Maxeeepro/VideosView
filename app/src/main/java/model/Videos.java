package model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.Path;

public class Videos {
    private String subtitle;
    private List<String>sources;
    private String thumb;
    //this annotation need for adding name image property to model
    @SerializedName("image-480x270")
    private String imageSmall;
    //this annotation need for adding name image property to model
    @SerializedName("image-780x1200")
    private String imageLarge;
    private String title;
    private String studio;
    public Videos(String subtitle, List<String>sources, String thumb,  String title, String studio, String imageLarge, String imageSmall){
        this.subtitle = subtitle;
        this.sources = sources;
        this.thumb = thumb;
        this.imageSmall = imageSmall;
        this.imageLarge = imageLarge;
        this.title = title;
        this.studio = studio;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public String getThumb() {
        return "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/" + thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImageSmall() {
        return "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/" + imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public String getImageLarge() {
        Log.d("OnGetImageLarge", "OnGetImageLarge");
        return "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/" + imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
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
