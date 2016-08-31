<%@ page import="java.util.List" %>
<%@ page import="ua.pp.lazin.slownews.model.NewsItem" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<%
    List<NewsItem> newsList = (List<NewsItem>) request.getAttribute("newsList");

    for (NewsItem newsItem : newsList) {
%>
<div>
Title is =<%=newsItem.getTitle()%>
</div>
<%
     }
%>


</body>
</html>
