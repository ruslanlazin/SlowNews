package ua.pp.lazin.slownews.listerner;

import ua.pp.lazin.slownews.integration.JerseyRssReader;
import ua.pp.lazin.slownews.integration.DomRssReader;
import ua.pp.lazin.slownews.integration.RomeRssReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Timer;

@WebListener
public class SlowNewsServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

//        new Timer().schedule(new DomRssReader(), 0, 600_000);      //Use DOM
//        new Timer().schedule(new JerseyRssReader(), 0, 600_000);      //Use Jersey
        new Timer().schedule(new RomeRssReader(), 0, 600_000);      //Use ROME
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }


}

