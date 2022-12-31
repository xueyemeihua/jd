<%--
  Created by IntelliJ IDEA.
  User: ly
  Date: 2022/12/15
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>业务添加</title>
</head>
<body>
<div align="center">
    <form action="/jd/addBus" method="post">
        业务标题:<input name="btitle"><br>
        业务描述:<input name="binfo"><br>
        业务价格:<input name="bprice"><br>
        业务状态:<input name="bcod"><br>
        <input type="submit" value="添加业务">
    </form>
</div>
</body>
</html>
