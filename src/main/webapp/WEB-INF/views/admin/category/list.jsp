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
    <form action="/admin/category/create" method="post">
        <label>Id :</label>
        <input name="id" type="text" placeholder="Id" required>
        <label>Name :</label>
        <input name="name" type="text" placeholder="Name" required>
        <button type="submit">Submit</button>
    </form>
    <table border="1">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${category}" var="c">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td><a href="/admin/category/edit/${c.id}">Edit</a></td>
                    <td><a href="/admin/category/delete/${c.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
