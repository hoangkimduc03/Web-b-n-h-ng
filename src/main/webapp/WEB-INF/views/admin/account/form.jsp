<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<form action="/admin/account/update/${account.username}" method="post">
    <input name="username" type="text" placeholder="Username" value="${account.username}" required>
    <input name="password" type="password" placeholder="Password" value="${account.password}" required>
    <input name="fullname" value="${account.fullname}" required>
    <input name="email" type="email" placeholder="Email" value="${account.email}" required>
    <button type="submit">Submit</button>
</form>