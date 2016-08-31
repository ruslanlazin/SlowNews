<%@ page import="ua.pp.lazin.slownews.model.NewsItem" %>
<%@ page import="java.util.List" %>
<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy");
    request.setAttribute("year", sdf.format(new java.util.Date()));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>SlowNews.Lazin.pp.ua</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="curved wrapper">
    <div class="navigation curved container">
        <span class="nav-right"><a href="//lazin.pp.ua">Other projects</a></span>

        <div class="separator">
        </div>
    </div>

    <div class="left curved container">
        <%@include file="leftBar.html" %>
    </div>

    <div class="center curved container">
        <h1>SlowNews Project
            <div class="lastUpdated">Slow news from all over the world
            </div>
        </h1>


        <%
            List<NewsItem> newsList = (List<NewsItem>) request.getAttribute("newsList");
            for (NewsItem newsItem : newsList) {
        %>
        <div>
            <div class="newsItem">
                <h3><a href=<%=newsItem.getLink()%>><span><%=newsItem.getTitle()%></span></a>

                    <div class="lastUpdated"><%=newsItem.getPubDate()%>
                    </div>
                </h3>

                <div class="newsItemImage">
                    <a href=<%=newsItem.getLink()%>><img
                            src=<%=newsItem.getPathToImage()%>> <a/>

                </div>

                <div class="newsItemDescription"><%=newsItem.getDescription()%>
                </div>
            </div>
        </div>

        <%
                System.out.println(newsItem.getPubDate());
            }
        %>

    </div>

    <div class="right curved container">
        <%@include file="rightBar.html" %>
    </div>

    <div class="separator">
    </div>

    <div class=" footer curved container">
        <%@include file="footer.html" %>
    </div>
</div>

<p class="copyright">Copyright &copy;2015-${year}. All Rights Reserved</p>

</body>

</html>