<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<form action="/admin/category/update/${category.id}" method="post">
    <input name="id" type="text" placeholder="Id" value="${category.id}" required>
    <input name="name" type="text" placeholder="Name" value="${category.name}" required>
    <button type="submit">Submit</button>
</form>