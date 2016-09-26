package model;

import android.graphics.Bitmap;

/**
 * Created by Joyboy on 9/19/2016.
 */
public class VnExpress {
    String link, video, title, description, date;
    String image;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public VnExpress(String link, String video, String title, String description, String date, String image) {
        this.link = link;
        this.video = video;
        this.title = title;
        this.description = description;
        this.date = date;
        this.image = image;
    }

    public VnExpress() {
    }
}
