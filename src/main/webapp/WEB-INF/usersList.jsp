<%--
  Created by IntelliJ IDEA.
  User: larbi
  Date: 26/01/2023
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Users List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

</head>
<body>

<div class="container">
  <header class="row">
    <%@include file="include/navbar.jsp" %>
  </header>
  <div class="row row-cols-1 justify-content-center">
    <c:forEach items="${users}" var="user">
    <div class="col col-auto card text-bg-light p-0 m-3" >
      <div class="card-header">${user.pseudo}</div>
      <div class="card-body">
        <h5 class="card-title">${user.lastName} ${user.firstName}</h5>
        <p class="card-text">${user.email}</p>
        <p>
          <a href="${pageContext.request.contextPath}/user/update?id=${user.id}" class="btn btn-primary">Details</a>
          <c:choose>
            <c:when test="${user.activate}">
              <a href="#" class="btn btn-success">Activate</a>
            </c:when>
            <c:otherwise>
              <a href="#" class="btn btn-danger">Desactivate</a>
            </c:otherwise>
          </c:choose>
        </p>
        <p>
          <a href="#" class="btn btn-primary">Edit</a>
          <a href="${pageContext.request.contextPath}/user/delete?id=${user.id}" class="btn btn-danger">Delete</a>
        </p>
      </div>
    </div>
    </c:forEach>
  </div>
</div>

</body>
</html>
