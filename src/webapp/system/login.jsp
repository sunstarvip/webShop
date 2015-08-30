<%--
  Created by IntelliJ IDEA.
  User: DarKnight
  Date: 2014/5/26 0026
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
Login Form!!!123
    <form action="/login.jsp" method="post">
        <br />用户帐号：<input type="text" name="username" id="username" value="" />
        <br />登录密码：<input type="password" name="password" id="password" value="" />
        <br />记住我：<input type="checkbox" name="rememberMe" value="true" />
        <br /><input value="登录" type="submit">
    </form>
</body>
</html>
