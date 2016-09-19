<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

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
        <c:import url="navBar.jsp"></c:import>
    </div>
</div>

<div class="left curved container">
    <c:import url="leftBar.jsp"></c:import>
</div>

<div class="center curved container">
    <h2>Congratulation!</h2>

    <p>You have successfully registered in this site. Please <a href="signin">Sign in</a></p>

</div>

<div class="right curved container">
    <c:import url="rightBar.html"></c:import>
</div>

<div class=" footer curved container">
    <c:import url="footer.html"></c:import>
</div>
</div>

<p class="copyright">Copyright &copy;2015-${year}. All Rights Reserved</p>

</body>

</html>
