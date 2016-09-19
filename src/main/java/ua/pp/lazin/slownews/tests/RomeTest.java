package ua.pp.lazin.slownews.tests;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jdom2.Element;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class RomeTest {


    public static void main(String[] args) throws FeedException, IOException {


//        final String url = "http://feeds.bbci.co.uk/news/rss.xml";
//        final String url = "http://www.guardian.co.uk/rssfeed/0,,11,00.xml";
        final String url = "http://www.nytimes.com/services/xml/rss/nyt/HomePage.xml";

        RomeTest parser = new RomeTest();

        SyndFeed syndFeed = parser.parseFeed(url);

        System.out.println(syndFeed.getEntries().get(1).getUri());

        List<Element> foreignMarkups = syndFeed.getEntries().get(3).getForeignMarkup();
        for (Element foreignMarkup : foreignMarkups) {
            if (foreignMarkup.getAttributeValue("url") != null) {
                String imgURL = foreignMarkup.getAttribute("url").getValue();
                System.out.println(imgURL);

            }
        }
        System.out.println(syndFeed.getEntries().get(1).getLink());
        System.out.println(syndFeed.getEntries().get(1).getTitle());
        System.out.println(syndFeed.getEntries().get(1).getDescription().getValue());
        System.out.println(syndFeed.getEntries().get(1).getPublishedDate());
        System.out.println(syndFeed.getTitle());


        // parser.printRSSContent(syndFeed);

    }

    public SyndFeed parseFeed(String url)

            throws IllegalArgumentException, MalformedURLException, FeedException, IOException

    {

        return new SyndFeedInput().build(new XmlReader(new URL(url)));

    }


    public void printRSSContent(SyndFeed feed)

    {

        System.out.println("About feed:");

        System.out.println("Author: " + feed.getAuthor());

        System.out.println("Authors:");

        if (feed.getAuthors() != null)

        {

            for (Object author : feed.getAuthors())

            {

                System.out.println(((SyndPerson) author).getName());

                System.out.println(((SyndPerson) author).getEmail());

                System.out.println(((SyndPerson) author).getUri());

                System.out.println();

            }

        }

        System.out.println("Title: " + feed.getTitle());

        System.out.println("Title Ex: " + feed.getTitleEx());

        System.out.println("Description: " + feed.getDescription());

        System.out.println("Description Ex: " + feed.getDescriptionEx());

        System.out.println("Date" + feed.getPublishedDate());

        System.out.println("Type: " + feed.getFeedType());

        System.out.println("Encoding: " + feed.getEncoding());

        System.out.println("(C) " + feed.getCopyright());

        System.out.println();


        for (Object object : feed.getEntries())

        {

            SyndEntry entry = (SyndEntry) object;

            System.out.println(entry.getTitle() + " - " + entry.getAuthor());

            System.out.println(entry.getLink());

            for (Object contobj : entry.getContents())

            {

                SyndContent content = (SyndContent) contobj;

                System.out.println(content.getType());

                System.out.println(content.getValue());

            }


            SyndContent content = entry.getDescription();

            if (content != null)

                System.out.println(content.getValue());


            System.out.println(entry.getPublishedDate());

            System.out.println();

        }

    }
}