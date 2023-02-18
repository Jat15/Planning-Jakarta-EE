<%--
  Created by IntelliJ IDEA.
  User: larbi
  Date: 26/01/2023
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Users List</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">
</head>
<body class="d-flex flex-column justify-content-between min-vh-100">
  <div class="container-fluid">
    <header class="row">
      <%@include file="include/navbar.jsp" %>
    </header>
  </div>
  <div class="container">
  <main class="row row-cols-1 row-cols-lg-3 justify-content-center">
    <c:forEach items="${users}" var="user">
    <div class="col card text-bg-light p-0 m-3" >
      <div class="card-header ${user.activate ? "text-success": "text-danger"}">
        <span class="h3">
          <c:choose>
            <c:when test="${user.activate}">
              <i class="ri-check-line"></i>
            </c:when>
            <c:otherwise>
              <i class="ri-close-line"></i>
            </c:otherwise>
          </c:choose>
            ${user.pseudo}
        </span>
        <c:choose>
          <c:when test="${user.role.name == 'User'}">
            <span class="badge text-bg-primary">User</span>
          </c:when>
          <c:when test="${user.role.name == 'Admin'}">
            <span class="badge text-bg-warning">Admin</span>
          </c:when>
          <c:when test="${user.role.name == 'Super'}">
            <span class="badge text-bg-danger">Super Admin (wtf ?)</span>
          </c:when>
          <c:otherwise>
            <span class="badge text-bg-info">WTFFFFFFFF !!!!!</span>
          </c:otherwise>
        </c:choose>
      </div>
      <div class="card-body">
        <div class="d-flex  flex-row flex-lg-column flex-xl-row justify-content-around">
          <div class=" d-flex justify-content-center align-content-center">
            <div class="" style="width: 150px;">
              <img src="${user.avatar}" class="img-fluid" alt="Avater of ${user.pseudo}">
            </div>
          </div>
          <div class="flex-fill d-flex flex-column justify-content-center align-content-center">
            <div>
              <h5 class="card-title"><i class="ri-user-line"></i> ${user.lastName} ${user.firstName}</h5>
              <p class="card-text"><i class="ri-mail-line"></i> ${user.email}</p>
            </div>
            <div class="justify-self-end d-flex flex-row justify-content-end justify-content-lg-around">
              <a href="${pageContext.request.contextPath}/user/update?id=${user.id}" class="btn btn-primary m-1"><i class="ri-edit-line"></i> <span class="d-none d-lg-inline">Update</span></a>
              <a href="${pageContext.request.contextPath}/user/delete?id=${user.id}" class="btn btn-danger m-1"><i class="ri-user-unfollow-line">  <span class="d-none d-lg-inline">Delete</span></i></a>
            </div>
          </div>
        </div>
      </div>
    </div>
    </c:forEach>
  </main>
  </div>
<%@include file="include/footer.jsp" %>
</body>
</html>
