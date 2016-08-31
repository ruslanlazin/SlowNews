package ua.pp.lazin.slownews.model;

import java.util.Date;

/**
 * Created by Laz on 30.08.2016.
 */
public class NewsItem implements Comparable {
    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String pathToImage;
    private Date pubTime;


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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    @Override
    public int compareTo(Object o) {
        NewsItem that = (NewsItem) o;
        return that.getPubTime().compareTo(this.getPubTime());
    }
}
