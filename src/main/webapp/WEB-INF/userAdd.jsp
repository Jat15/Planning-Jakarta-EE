<%--
  Created by IntelliJ IDEA.
  User: JatMuadDib
  Date: 27/01/2023
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add User</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>

    <body class="container">

    <header class="row">



    </header>
    <main class="row">
        <form action="${pageContext.request.contextPath}/user/add" method="post">
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon1">Pseudo</span>
                <input type="text" class="form-control" placeholder="Pseudo" aria-label="Pseudo" aria-describedby="basic-addon1" name="pseudo">
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon2">Email</span>
                <input type="email" class="form-control" placeholder="Email" aria-label="Email" aria-describedby="basic-addon2" name="email">

            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon3">Last Name</span>
                <input type="text" class="form-control" placeholder="Last Name" aria-label="Last Name" aria-describedby="basic-addon3" name="lastName">
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon4">First Name</span>
                <input type="text" class="form-control" placeholder="First Name" aria-label="First Name" aria-describedby="basic-addon4" name="firstName">
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon5">Avatar</span>
                <input type="image" class="form-control" placeholder="Avatar" aria-label="Avatar" aria-describedby="basic-addon5" name="avatar">
            </div>
            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon6">Birthdate</span>
                <input type="date" class="form-control" placeholder="Birthdate" aria-label="Birthdate" aria-describedby="basic-addon6" name="birthdate">
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon8">Phone</span>
                <input type="tel" class="form-control" placeholder="Phone" aria-label="Phone" aria-describedby="basic-addon8" name="phone">
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon9">Password</span>
                <input type="password" class="form-control" placeholder="Password" aria-label="Password" aria-describedby="basic-addon9" name="password">
            </div>

            <div class="input-group mb-3">
                <div class="input-group-text">
                    <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Activate" aria-describedby="basic-addon10" name="activate">
                </div>
                <span class="input-group-text" id="basic-addon10">Activate</span>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon11">Street</span>
                <input type="text" class="form-control" placeholder="Street" aria-label="Street" aria-describedby="basic-addon11" name="street">
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon12">City</span>
                <input type="text" class="form-control" placeholder="City" aria-label="City" aria-describedby="basic-addon12" name="city">
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon13">Country</span>
                <input type="text" class="form-control" placeholder="Country" aria-label="Country" aria-describedby="basic-addon13" name="country">
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" id="basic-addon14">Zip</span>
                <input type="text" class="form-control" placeholder="Zip" aria-label="Zip" aria-describedby="basic-addon14" name="zip">
            </div>
            <div class="text-center mb-3">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </form>
    </main>
    <footer class="row">




    </footer>

</body>
</html>