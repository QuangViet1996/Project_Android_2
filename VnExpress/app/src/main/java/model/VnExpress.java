package model;

/**
 * Created by Joyboy on 9/19/2016.
 */
public class VnExpress {
    String link, image, video, title, description, date;

    public VnExpress() {
    }

    public VnExpress(String link, String image, String video, String title, String description, String date) {
        this.link = link;
        this.image = image;
        this.video = video;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
