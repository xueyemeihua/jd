<%--
  Created by IntelliJ IDEA.
  User: ly
  Date: 2022/12/15
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <%--实现注册--%>
    <form action="/jd/register" method="post">
        用户名:<input name="rusername" value="${rusername}"><br>
        密码:<input name="rpwd"><br>
        <input type="submit" value="提交">
    </form>
    <p style="color: red">${registerError}</p>
</div>
</body>
</html>
