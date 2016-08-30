<%@ page import="ua.pp.lazin.slownews.controller.Login" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.pp.lazin.slownews.model.Storage" %>
<%@ page import="ua.pp.lazin.slownews.model.User" %>
<%@ page import="java.io.Writer" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: Laz
  Date: 24.08.2016
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<p>news page</p>
<%
    List<User> users = (List<User>) Storage.getFromMap("t");
    System.out.println(request.hashCode());
    for (User user : users) {
%>
<div>
Login is =<%=user.getLogin()%>
</div>
<%
     }
%>
<b>Total users:  </b>



</body>
</html>
