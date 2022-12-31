<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ly
  Date: 2022/12/15
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看所有业务</title>
    <style>
        table {
            border-collapse: collapse;
        }

        tr:hover td {
            background-color: aqua;
        }
    </style>

</head>
<body>
<div align="center">
    <table id="tb" align="center" border="1">
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
        <c:forEach var="bus" items="${buss}">
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
    <br>
    <a href="adminView.jsp">返回管理中心</a>
</div>
</body>
</html>
