<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ly
  Date: 2022/12/17
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上架业务</title>
</head>
<body>

<div align="center">
    <form action="/jd/doPubBus" method="post">
        业务编号:<input name="pbid"><br>
        <input type="submit" value="上架"><br>
    </form>
    <br>
    <p style="color: red">${upbError}</p>
    <br>
    <table border="1">
        <thead>
        <tr>
            <th>编号</th>
            <th>标题</th>
            <th>内容</th>
            <th>价格</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bus" items="${unpbuss}" >
            <tr>
                <td>${bus.bid}</td>
                <td>${bus.btitle}</td>
                <td>${bus.binfo}</td>
                <td>${bus.bprice}</td>
                <td>${bus.bcod}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
