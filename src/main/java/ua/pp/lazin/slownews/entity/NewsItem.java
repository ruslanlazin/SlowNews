package ua.pp.lazin.slownews.entity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsItem implements Comparable, Serializable, Cloneable{

    @XmlElement(name = "guid")
    private String uri;

    @XmlElement(name = "source")
    private String source;

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "link")
    private String link;

    @XmlElement(name = "pubDate")
    private Date pubDate;

    @XmlElement(name = "pathToImage")
    private String pathToImage = "/images/no_image_available.png";

    private boolean favorite;


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

    public void setPathToImage(String url) {
        pathToImage = url;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int compareTo(Object o) {
        NewsItem that = (NewsItem) o;
        return that.getPubDate().compareTo(this.getPubDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsItem)) return false;

        NewsItem newsItem = (NewsItem) o;

        if (!uri.equals(newsItem.uri)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return uri.hashCode();
    }

    @Override
    public Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    return null;
    }

}
