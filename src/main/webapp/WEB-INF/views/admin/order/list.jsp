<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/7/2023
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Address</th>
                <th>Crearedate</th>
                <th>Username</th>
                <th>Price</th>
                <th>Product</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${orderD}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.order.address}</td>
                    <td>${p.order.createdate}</td>
                    <td>${p.order.account.username}</td>
                    <td>${p.price}</td>
                    <td>${p.product.name}</td>
                    <td><a href="/admin/product/edit/${p.id}">Edit</a></td>
                    <td><a href="/admin/product/delete/${p.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
