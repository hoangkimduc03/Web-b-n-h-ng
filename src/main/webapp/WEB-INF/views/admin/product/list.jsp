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
    <form action="/admin/product/create" method="post">
        <input name="name" type="text" placeholder="Name" required>
        <input name="price" type="text" placeholder="Price" required>
        <input name="image" type="text" placeholder="Image" required>
        <select name="category" required>
            <option value="">Chọn nhóm sản phẩm</option>
            <c:forEach items="${categoryList}" var="category">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>

        <button type="submit">Submit</button>
    </form>
    <table border="1">
        <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Image</th>
                <th>Category</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${product}" var="p">
                <tr>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${p.image}</td>
                    <td>${p.category}</td>
                    <td><a href="/admin/product/edit/${p.id}">Edit</a></td>
                    <td><a href="/admin/product/delete/${p.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
