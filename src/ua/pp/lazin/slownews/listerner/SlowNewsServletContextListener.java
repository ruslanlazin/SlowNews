package ua.pp.lazin.slownews.listerner;

import ua.pp.lazin.slownews.integration.RssReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SlowNewsServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Thread rssReader = new Thread(new RssReader());
        rssReader.setDaemon(true);
        rssReader.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }


}

