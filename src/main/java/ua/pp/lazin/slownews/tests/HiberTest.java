package ua.pp.lazin.slownews.tests;

import ua.pp.lazin.slownews.entity.NewsItem;
import ua.pp.lazin.slownews.entity.User;
import ua.pp.lazin.slownews.persistance.DaoFactory;
import ua.pp.lazin.slownews.persistance.UserDao;

import java.util.Date;

public class HiberTest {
    public static void main(String[] args) {


        User user = new User();
        user.setLogin("ee");
        user.setPassword("ee");
        user.setEmail("ee@tt.ii");

        //DaoFactory.getUserDao().saveUser(user);


        NewsItem newsItem = new NewsItem();
        newsItem.setPubDate(new Date());
        newsItem.setDescription("hgfjhsdfhfkjg");
        newsItem.setPathToImage("jksdhkjzshksdhfkhf");
        newsItem.setSource("fhfhfkhfklafhlskdflfkh");
        newsItem.setTitle("jhkjshgkshglksghklh");
        newsItem.setFavorite(true);
        newsItem.setUri("fhkjhgkjhgksjhgkgh");
        //DaoFactory.getNewsDao().saveNews(newsItem);
        user.getPersonalNews().add(newsItem);

        DaoFactory.getUserDao().saveUser(user);
        user.setLastName("hkjhkjhkhkhkhkhkhkhkhkhkhkkj");
        user.setFirstName("hkjhkjhkhkhkj");
        newsItem.setUri("3465");
        user.getPersonalNews().add(newsItem);
        //DaoFactory.getUserDao().updateUser(user);




    }

}
