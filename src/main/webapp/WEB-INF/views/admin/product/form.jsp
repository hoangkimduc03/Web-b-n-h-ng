<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<form action="/admin/product/update/${product.id}" method="post">
    <input name="name" type="text" placeholder="Name" value="${product.name}" required>
    <input name="price" type="text" placeholder="Price" value="${product.price}" required>
    <input name="image" value="${product.image}" required>
    <button type="submit">Submit</button>
</form>