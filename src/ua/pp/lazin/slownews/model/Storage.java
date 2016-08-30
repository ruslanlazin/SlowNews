package ua.pp.lazin.slownews.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class Storage {

    static private Map<String, Object> map = new HashMap<String, Object>();

    public static Object getFromMap(String reg) {
        return map.get(reg);
    }

    public static void addToMap(HttpServletRequest servletRequest, Object o) {
       // map.get(servletRequest.getContextPath() ).put(key, o);
    }
}
