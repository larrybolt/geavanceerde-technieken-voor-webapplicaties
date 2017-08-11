<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="components/header.jspf"%>
<!-- Navigation -->
<nav class="navbar fixed-top navbar-toggleable-md navbar-inverse bg-inverse">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarExample" aria-controls="navbarExample" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="container">
        <a class="navbar-brand" href="#">WebShop</a>
        <div class="collapse navbar-collapse" id="navbarExample">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/signup">Sign-up</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h1 class="text-center login-title">Create an account</h1>
            <div class="account-wall">
                <img class="profile-img" src="/static/css/user.png" alt="">
                <form class="form-signup" action="/signup" method="post">
                    <c:forEach items="${errors}" var="error">
                        <p class="error">${error}</p>
                    </c:forEach>
                    <div class="form-group">
                        <input type="email" class="form-control" name="email" value="${email}" placeholder="Email" required autofocus>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="userName" value="${userName}" placeholder="Username" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="firstName" value="${firstName}" placeholder="First Name" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="lastName" value="${lastName}" placeholder="Last Name" required>
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="gender" required>
                            <c:forEach items="${genders}" var="gender">
                                <option value="${gender}">${gender.gender}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="role" required>
                                <c:forEach items="${roles}" var="role">
                                <option value="${role}">${role.role}</option>
                                </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon">Date of birth</span>
                            <input type="date" class="form-control" name="dateofbirth" value="${dateofbirth}" required style="flex-direction: row;" required>
                            <!-- Fix for bug in bootstrap 4: https://github.com/twbs/bootstrap/issues/21651#issuecomment-272802603 -->
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" value="${password}" placeholder="Password" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="passwordRepeat" value="${passwordRepeat}" placeholder="Repeat Password" required>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
                    </div>
                </form>
            </div>
            <a href="/login" class="text-center new-account">Already have an account</a><br><br>
        </div>
    </div>
</div>

<%@ include file="components/footer.jspf"%>
