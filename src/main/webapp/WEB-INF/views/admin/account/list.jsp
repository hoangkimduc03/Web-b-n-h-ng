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
    <form action="/admin/account/create" method="post">
        <label>Username :</label>
        <input name="username" type="text" placeholder="Username" required>
        <label>Password :</label>
        <input name="password" type="password" placeholder="Password" required>
        <label>Fullname :</label>
        <input name="fullname" placeholder="Fullname" required>
        <label>Email :</label>
        <input name="email" type="email" placeholder="Email" required>
        <button type="submit">Submit</button>
    </form>
    <table border="1">
        <thead>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Fullname</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${accounts}" var="account">
                <tr>
                    <td>${account.username}</td>
                    <td>${account.password}</td>
                    <td>${account.fullname}</td>
                    <td>${account.email}</td>
                    <td><a href="/admin/account/edit/${account.username}">Edit</a></td>
                    <td><a href="/admin/account/delete/${account.username}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
