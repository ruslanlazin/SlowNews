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
    <script defer type="text/javascript" src="/js/reg-form-validator.js"></script>
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
        <h2>Congratulation!</h2>

        <p>You have successfully registered in this site. But we don't know why.</p>

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
