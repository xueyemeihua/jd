<%--
  Created by IntelliJ IDEA.
  User: ly
  Date: 2022/12/15
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<div align="center">
    <%--实现登录--%>
    <form action="/jd/login" method="post">
        用户名:<input name="lusername" value="${lusername}"><br>
        密码:<input name="lpwd"><br>
        <input type="submit" value="提交"><br>
    </form>
    <p style="color: red">${loginError}</p><br>

</div>

</body>
</html>
