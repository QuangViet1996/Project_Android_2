package candra.me.jsoupexample;

/**
 * Created by Joyboy on 9/17/2016.
 */
public class News {
    private String title;
    private String description;
    private String date;
    private String link;
    private String image;

    public News() {
        this.title = null;
        this.description = null;
        this.link = null;
        this.date = null;
        this.image = null;
    }

    public News(String title, String description, String date, String link, String image) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.link = link;
        this.image = image;
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
}
