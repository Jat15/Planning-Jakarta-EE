<%--
  Created by IntelliJ IDEA.
  User: JatMuadDib
  Date: 27/01/2023
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="${pageContext.request.contextPath}/users">Planing</a>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/users">List</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/user/add">Add</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/user/update?id=${sessionIdUser}">My Account</a>
        </li>
      </ul>
      <form class="d-flex" role="search" action="${pageContext.request.contextPath}/users/find" method="GET">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
<div>
  <c:forEach items="${errors}" var="error">
    <div class="alert alert-danger" role="alert">
        ${error}
    </div>
  </c:forEach>
  <c:if test = "${success != null}">
    <div class="alert alert-success" role="alert">
        ${success}
    </div>
  </c:if>
</div>
