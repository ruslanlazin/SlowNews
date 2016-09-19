package ua.pp.lazin.slownews.tests;

import org.glassfish.jersey.client.ClientResponse;
import ua.pp.lazin.slownews.entity.Channel;
import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.Rss;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


public class JerseyTest {


    public static void main(String[] args) {



        final String url = "http://feeds.bbci.co.uk/news/rss.xml";
        //final String url = "http://www.nytimes.com/services/xml/rss/nyt/HomePage.xml";
        final long tenMinutes = 600000;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);

        Invocation.Builder invocationBuilder = target.request(MediaType.TEXT_XML).accept(MediaType.TEXT_XML);
        Response response = invocationBuilder.get();


        String s = response.readEntity(String.class);

//        Rss rss = response.readEntity(Rss.class);
//
//        List<NewsItem> newsList = rss.getChannel().getNewsItemList();
//
//        System.out.println(newsList);







//        List<Channel> channels = response.getEntity(new ArrayList<Channel>());
//                System.out.println(result);






//        Client c = Client.create();
//        WebResource resource = c.resource(url);
//
//
//            ClientResponse response = resource.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
//            List<Channel> channels = response.getEntity(new GenericType<List<Channel>>() {
//            });
//
//            //Collections.sort(newsList);

//        List<NewsItem> newsList= channels.get(0).getNewsItemList();
//            System.out.println(newsList);


    }
}
