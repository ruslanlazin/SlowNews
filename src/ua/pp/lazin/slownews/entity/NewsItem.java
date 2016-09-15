package ua.pp.lazin.slownews.entity;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsItem implements Comparable {

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "link")
    private String link;

    @XmlElement(name = "pubDate")
    private String pubDate;

    @XmlElement(name = "thumbnail")
    private Media media;

    private final DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;


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
        return media.getUrl();
    }

    public void setPathToImage(String url) {
        media.setUrl(url);
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Date getPubTime() {
        LocalDateTime localDateTime = LocalDateTime.parse(pubDate, formatter);
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return (Date.from(zdt.toInstant()));
    }

    @Override
    public int compareTo(Object o) {
        NewsItem that = (NewsItem) o;
        return that.getPubTime().compareTo(this.getPubTime());
    }

    @XmlRootElement(name = "thumbnail")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Media {

        @XmlAttribute(name = "url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
