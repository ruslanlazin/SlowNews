package ua.pp.lazin.slownews.tests;

import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.Rss;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;


public class JerseyTest {


    public static void main(String[] args) {


        final String url = "http://feeds.bbci.co.uk/news/rss.xml";
        //final String url = "http://www.nytimes.com/services/xml/rss/nyt/HomePage.xml";

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_XML).accept(MediaType.TEXT_XML);
        Response response = invocationBuilder.get();

        Rss rss = response.readEntity(Rss.class);

        List<NewsItem> newsList = rss.getChannel().getNewsItemList();

        System.out.println(newsList);
    }
}