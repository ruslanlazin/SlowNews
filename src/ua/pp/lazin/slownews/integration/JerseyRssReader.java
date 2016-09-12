package ua.pp.lazin.slownews.integration;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.Rss;

import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

public class JerseyRssReader implements Runnable {

    @Override
    public void run() {

        final String url = "http://feeds.bbci.co.uk/news/rss.xml";
        final long tenMinutes = 600_000;

        Client c = Client.create();

        WebResource resource = c.resource(url);
        ClientResponse response = resource.accept(MediaType.APPLICATION_ATOM_XML_TYPE).get(ClientResponse.class);

        Rss rss = response.getEntity(new GenericType<Rss>() {
        });

        List<NewsItem> newsList = rss.getChannel().getNewsItemList();
        Collections.sort(newsList);
        NewsCache.getInstance().setNewsList(newsList);

        try {
            Thread.sleep(tenMinutes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


