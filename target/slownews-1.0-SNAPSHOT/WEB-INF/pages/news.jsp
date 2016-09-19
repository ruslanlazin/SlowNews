<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.pp.lazin.slownews.entity.NewsItem" %>
<%@ page import="java.util.List" %>
<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>SlowNews.Lazin.pp.ua</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
    <script defer type="text/javascript" src="/js/rest.js"></script>
</head>

<body>
<div class="curved wrapper">
    <div class="navigation curved container">
        <c:import url="navBar.jsp"></c:import>
    </div>

    <div class="left curved container">
        <c:import url="leftBar.jsp"/>

    </div>

    <div class="center curved container">
        <h1>SlowNews Project
            <div class="h1Appendix">Slow news from all over the world
            </div>
        </h1>

        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>

        <c:forEach var="newsItem" items="${newsList}">

            <div class="newsItem">
                <h3><a href="${newsItem.link}"><span>${newsItem.title}</span></a>

                    <c:if test="${!newsItem.favorite}">
                        <img class="curved favor" src="/images/favorites-add.jpg" id="${newsItem.uri}"
                             title="add this news to your personal archive" name="add"
                             onclick=imgchange(this)>
                    </c:if>

                    <c:if test="${newsItem.favorite}">
                        <img class="curved favor" src="/images/favorites-remove.jpg" id="${newsItem.uri}"
                             title="remove this news from your personal archive" name="remove"
                             onclick=imgchange(this)>
                    </c:if>

                    <div class="lastUpdated">${newsItem.pubDate}
                    </div>
                </h3>

                <div class="newsItemImage">
                    <a href=${newsItem.link}><img src=${newsItem.pathToImage}> </a>
                </div>

                <div class="newsItemDescription">${newsItem.description}
                </div>
            </div>

        </c:forEach>

    </div>

    <div class="right curved container">
        <c:import url="rightBar.html"/>
    </div>
    <div class=" footer curved container">
        <c:import url="footer.html"/>
    </div>
</div>

<p class="copyright">Copyright &copy;2015- ${year}. All Rights Reserved</p>

</body>

</html>