<%@ page import="ua.pp.lazin.slownews.entity.NewsItem" %>
<%@ page import="java.util.List" %>
<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <c:import url="navBar.jsp"></c:import>

    </div>

    <div class="left curved container">
        <c:import url="leftBar.jsp"/>

    </div>

    <div class="center curved container">
        <h1>SlowNews Project
            <div class="h1Appendix">Sign in
            </div>
        </h1>
        <p>${message}</p>

        <form action="" id="reg-form" method="post">
            <table class="reg-table">
                <tr>
                    <td><b>Username*:</b></td>
                    <td><input id="Username" name="Username" type="text" maxlength="60" class="reg-input-long"/></td>
                </tr>
                <tr>
                    <td><b>Password*:</b></td>
                    <td><input id="Password" name="Password" type="password" maxlength="60" class="reg-input-long"/>
                    </td>
                </tr>

                <tr>
                    <td>
                    </td>
                    <td>
                        <input id="Submit" class="input-button" name="Submit" type="submit" value=" Sing in "/>
                    </td>
                </tr>
            </table>
        </form>

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