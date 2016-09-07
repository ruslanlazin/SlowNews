package ua.pp.lazin.slownews.integration;


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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RssReader implements Runnable {

    @Override
    public void run() {

        final String url = "http://feeds.bbci.co.uk/news/rss.xml";
        final long tenMinutes = 600_000;
        final DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

        while (true) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(url);

                document.getDocumentElement().normalize();

                List<NewsItem> newsList = new ArrayList<NewsItem>(50);

                // loop through each item
                NodeList items = document.getElementsByTagName("item");
                for (int i = 0; i < items.getLength(); i++) {
                    Node n = items.item(i);
                    if (n.getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    Element xmlNewsItem = (Element) n;

                    NewsItem newsItem = new NewsItem();

                    Element titleElem = (Element) xmlNewsItem.getElementsByTagName("title").item(0);
                    if (titleElem != null) {
                        newsItem.setTitle(titleElem.getChildNodes().item(0).getNodeValue());
                    }

                    Element descriptionElem = (Element) xmlNewsItem.getElementsByTagName("description").item(0);
                    if (descriptionElem != null) {
                        newsItem.setDescription(descriptionElem.getChildNodes().item(0).getNodeValue());
                    }

                    Element linkElem = (Element) xmlNewsItem.getElementsByTagName("link").item(0);
                    if (linkElem != null) {
                        newsItem.setLink(linkElem.getChildNodes().item(0).getNodeValue());
                    }

                    Element pubDateElem = (Element) xmlNewsItem.getElementsByTagName("pubDate").item(0);
                    if (pubDateElem != null) {
                        newsItem.setPubDate(pubDateElem.getChildNodes().item(0).getNodeValue());
                    }

                    Element pathToImageElem = (Element) xmlNewsItem.getElementsByTagName("media:thumbnail").item(0);
                    if (pathToImageElem != null) {
                        newsItem.setPathToImage(pathToImageElem.getAttribute("url"));
                    }

                    LocalDateTime localDateTime = LocalDateTime.parse(newsItem.getPubDate(), formatter);

                    ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
                    newsItem.setPubTime(Date.from(zdt.toInstant()));

                    newsList.add(newsItem);
                }
                Collections.sort(newsList);
                NewsCache.getInstance().setNewsList(newsList);

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
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
}

