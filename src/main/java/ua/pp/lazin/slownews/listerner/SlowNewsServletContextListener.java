package ua.pp.lazin.slownews.listerner;

import ua.pp.lazin.slownews.integration.JerseyRssReader;
import ua.pp.lazin.slownews.integration.DomRssReader;
import ua.pp.lazin.slownews.integration.RomeRssReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SlowNewsServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

//        Thread rssReader = new Thread(new DomRssReader());      //Use Dom         or
//        Thread rssReader = new Thread(new JerseyRssReader());   //Use Jersey      or
        Thread rssReader = new Thread(new RomeRssReader());      //Use Rome

        rssReader.setDaemon(true);
        rssReader.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }


}

