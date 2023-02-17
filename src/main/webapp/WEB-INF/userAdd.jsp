<%--
  Created by IntelliJ IDEA.
  User: JatMuadDib
  Date: 27/01/2023
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add User</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>

    <body class="d-flex flex-column justify-content-between min-vh-100">

    <div class="container-fluid">
        <header class="row">
            <%@include file="include/navbar.jsp" %>
        </header>
    </div>
    <div class="container">
        <main class="row">
            <c:choose>
                <c:when test="${id > 0}">
                    <form action="${pageContext.request.contextPath}/user/update?id=${id}" method="post">
                </c:when>
                <c:otherwise>
                    <form action="${pageContext.request.contextPath}/user/add" method="post">
                </c:otherwise>
            </c:choose>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Pseudo</span>
                    <input type="text" class="form-control" placeholder="Pseudo" aria-label="Pseudo" aria-describedby="basic-addon1" name="pseudo" value="${pseudo}">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon2">Email</span>
                    <input type="email" class="form-control" placeholder="Email" aria-label="Email" aria-describedby="basic-addon2" name="email" value="${email}">

                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon3">Last Name</span>
                    <input type="text" class="form-control" placeholder="Last Name" aria-label="Last Name" aria-describedby="basic-addon3" name="lastName" value="${lastName}">
                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon4">First Name</span>
                    <input type="text" class="form-control" placeholder="First Name" aria-label="First Name" aria-describedby="basic-addon4" name="firstName" value="${firstName}">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon5">Avatar</span>
                    <input type="image" class="form-control" placeholder="Avatar" aria-label="Avatar" aria-describedby="basic-addon5" name="avatar" value="${avatar}">
                </div>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon6">Birthdate</span>
                    <input type="date" class="form-control" placeholder="Birthdate" aria-label="Birthdate" aria-describedby="basic-addon6" name="birthdate" value="${birthdate}">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon8">Phone</span>
                    <input type="tel" class="form-control" placeholder="Phone" aria-label="Phone" aria-describedby="basic-addon8" name="phone" value="${phone}">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon9">Password</span>
                    <input type="password" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="basic-addon9" name="password">
                </div>

                <div class="input-group mb-3">
                    <div class="input-group-text">
                        <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Activate" aria-describedby="basic-addon10" name="activate" ${activate? "checked" : ""}>
                    </div>
                    <span class="input-group-text" id="basic-addon10">Activate</span>
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon11">Street</span>
                    <input type="text" class="form-control" placeholder="Street" aria-label="Street" aria-describedby="basic-addon11" name="street" value="${street}">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon12">City</span>
                    <input type="text" class="form-control" placeholder="City" aria-label="City" aria-describedby="basic-addon12" name="city" value="${city}">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon13">Country</span>
                    <input type="text" class="form-control" placeholder="Country" aria-label="Country" aria-describedby="basic-addon13" name="country" value="${country}">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon14">Zip</span>
                    <input type="text" class="form-control" placeholder="Zip" aria-label="Zip" aria-describedby="basic-addon14" name="zip" value="${zip}">
                </div>
                <div class="text-center mb-3">
                    <select class="form-select" aria-label="Choose role for user" name="idRole">
                        <c:forEach items="${roles}" var="role">
                            <option value=${role.id} ${role.name == roleName ? "selected":""} ${ role.id < myRole ? "" : "disabled"}>${role.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="text-center mb-3">
                    <c:choose>
                        <c:when test="${id > 0}">
                            <button type="submit" class="btn btn-primary">Update ${id} </button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn btn-primary">Add ${id} </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </form>
        </main>
    </div>
    <%@include file="include/footer.jsp" %>
</body>
</html>
