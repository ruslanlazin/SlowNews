package ua.pp.lazin.slownews.integration;


import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.pp.lazin.slownews.entity.NewsItem;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RomeRssReader implements Runnable {
    private final DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;


    @Override
    public void run() {

        final long tenMinutes = 600000;
        List<String> RssUrls = new ArrayList<>();
        RssUrls.add("http://feeds.bbci.co.uk/news/rss.xml");
        RssUrls.add("http://www.nytimes.com/services/xml/rss/nyt/HomePage.xml");


        while (true) {
            try {
                parseRss(RssUrls);
            } catch (FeedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(tenMinutes);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseRss(List<String> rssUrls) throws FeedException, IOException {

        List<NewsItem> newsList = new ArrayList<>(128);
        final RomeRssReader parser = new RomeRssReader();

        for (String url : rssUrls) {

            SyndFeed syndFeed = parser.parseFeed(url);

            String source = syndFeed.getTitle();

            for (Object object : syndFeed.getEntries()) {

                SyndEntry entry = (SyndEntry) object;

                NewsItem newsItem = new NewsItem();
                newsItem.setUri(entry.getUri());
                newsItem.setTitle(entry.getTitle());
                newsItem.setLink(entry.getLink());
                newsItem.setDescription(entry.getDescription().getValue());
                newsItem.setPubDate(entry.getPublishedDate());
                newsItem.setSource(source);

                List<org.jdom2.Element> foreignMarkups = entry.getForeignMarkup();
                for (org.jdom2.Element foreignMarkup : foreignMarkups) {
                    if (foreignMarkup.getAttributeValue("url") != null) {
                        newsItem.setPathToImage(foreignMarkup.getAttribute("url").getValue());
                    }
                }
                newsList.add(newsItem);
            }

        }
        Collections.sort(newsList);
        NewsCache.getInstance().setNewsList(newsList);
    }

    public SyndFeed parseFeed(String url) throws IllegalArgumentException, FeedException, IOException {
        return new SyndFeedInput().build(new XmlReader(new URL(url)));
    }

}

