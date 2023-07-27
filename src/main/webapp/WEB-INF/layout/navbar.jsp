<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary p-0">
  <div class="navbar-nav collapse navbar-collapse">
    <a class="nav-item nav-link active" href="/">Sản phẩm</a>
    <a class="nav-item nav-link" href="/about">Giới thiệu</a>
  </div>

  <ul class="navbar-nav ml-auto">
    <li class="nav-item no-arrow">
      <c:if test="${cart.total > 0}">
        <a href="/cart" class="nav-link mt-2 text-light">
          <img alt="" style="width:40px"
            src="../images/cart.png" />
            (${cart.total})
        </a>
      </c:if>
    </li>
    <li class="nav-item no-arrow">
      <a class="nav-link dropdown-toggle p-0" data-bs-toggle="dropdown" href="#">
        ${sessionScope.username}
        <img alt="" class="rounded-circle" style="width:60px"
          src="https://i.pinimg.com/236x/06/cc/c2/06ccc2790730675e2f57cda41469afd9.jpg" />
      </a>
      <div class="dropdown-menu dropdown-menu-end">
        <a class="dropdown-item" href="/logout">
          Đăng xuất
        </a>
      </div>
    </li>
  </ul>
</nav>