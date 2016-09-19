package ua.pp.lazin.slownews.integration;


import org.glassfish.jersey.client.ClientResponse;
import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.Rss;
import ua.pp.lazin.slownews.entity.User;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.util.Collections;
import java.util.List;

public class JerseyRssReader implements Runnable {

    @Override
    public void run() {

        final String url = "http://feeds.bbci.co.uk/news/rss.xml";
        final long tenMinutes = 600000;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        String result = target.request().get(String.class);

        System.out.println(result);


        while (true) {

            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
            Response response = invocationBuilder.get();

            Rss rss = response.readEntity(Rss.class);

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
}


