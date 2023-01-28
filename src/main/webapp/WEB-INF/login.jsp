<%--
  Created by IntelliJ IDEA.
  User: JatMuadDib
  Date: 28/01/2023
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Home</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>
    <body class="container">
    <header class="row">
        <h1>Gestion de planing</h1>
    </header>
    <main class="row">
        <form action="${pageContext.request.contextPath}/" method="post">

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon2">Email</span>
                <input type="email" class="form-control" placeholder="Email" aria-label="Email" aria-describedby="basic-addon2" name="email" value="${email}">
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon9">Password</span>
                <input type="password" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="basic-addon9" name="password">
            </div>

            <div class="text-center mb-3">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
        </form>
    </main>
    <footer class="row">

    </footer>
</body>
</html>