package ua.pp.lazin.slownews.integration;

import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.Rss;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

public class JerseyRssReader extends TimerTask {

    @Override
    public void run() {

        final String url = "http://feeds.bbci.co.uk/news/rss.xml";

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);


        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_XML).accept(MediaType.APPLICATION_XML);
        Response response = invocationBuilder.get();

        Rss rss = response.readEntity(Rss.class);

        List<NewsItem> newsList = rss.getChannel().getNewsItemList();
        Collections.sort(newsList);
        NewsCache.getInstance().setNewsList(newsList);
    }
}



