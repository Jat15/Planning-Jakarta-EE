<%--
  Created by IntelliJ IDEA.
  User: JatMuadDib
  Date: 27/01/2023
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="${pageContext.request.contextPath}/users">Planing</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
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
  </div>
</nav>
<div>
  <c:forEach items="${errors}" var="error">
    <div class="alert alert-danger" role="alert">
        ${error}
    </div>
  </c:forEach>
</div>
