package ua.pp.lazin.slownews.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebFilter("/*")
public class AddYearFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy");
        servletRequest.setAttribute("year",sdf.format(new java.util.Date()));
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
