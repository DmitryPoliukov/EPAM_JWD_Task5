<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: radof
  Date: 08.09.2021
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flowers list</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
        table.center {
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>

<body>
<h1>FlowerList</h1>
<a href="index.jsp">home</a>
<table class="center">
    <th>FlowerName</th>
    <th>Origin</th>
    <th>ID</th>
    <th>SOIL</th>
    <th>ColorSteam</th>
    <th>ColorLeaf</th>
    <th>AverageSize</th>
    <th>Temperature</th>
    <th>IsPhotophilous</th>
    <th>Watering</th>
    <th>Multiplying</th>

    <c:forEach items="${flowerList}" var="item">
        <tr>
            <td><c:out value="${item.name}" /></td>
            <td><c:out value="${item.origin}" /></td>
            <td><c:out value="${item.id}" /></td>
            <td><c:out value="${item.soil}" /></td>
            <td><c:out value="${item.colorSteam}" /></td>
            <td><c:out value="${item.colorLeaf}" /></td>
            <td><c:out value="${item.averagePlantSize}" /></td>
            <td><c:out value="${item.temperature}" /></td>
            <td><c:out value="${item.isPhotophilous}" /></td>
            <td><c:out value="${item.watering}" /></td>
            <td><c:out value="${item.multiplying}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
