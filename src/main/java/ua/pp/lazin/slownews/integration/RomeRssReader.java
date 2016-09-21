package ua.pp.lazin.slownews.integration;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import ua.pp.lazin.slownews.entity.NewsItem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.TimerTask;

public class RomeRssReader extends TimerTask {

    @Override
    public void run() {

        List<String> RssUrls = new ArrayList<>();
        RssUrls.add("http://feeds.bbci.co.uk/news/rss.xml");
        RssUrls.add("http://www.nytimes.com/services/xml/rss/nyt/HomePage.xml");


        try {
            parseRss(RssUrls);
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRss(List<String> rssUrls) throws FeedException, IOException {

        List<NewsItem> newsList = new ArrayList<>(100);
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

