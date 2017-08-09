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
                        <a class="nav-link" href="/login">Login <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/signup">Sign-up</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Page Content -->

<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <h1 class="text-center login-title">Please sign in</h1>
            <div class="account-wall">
                <img class="profile-img" src="/static/css/user.png" alt="">
                <form class="form-signin" action="/login" method="post">
                <input type="text" name="username" class="form-control" placeholder="Username" value="${username}" required autofocus>
                <input type="password" name="password" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                    <p class="error">${error}</p>
                </form>
            </div>
            <a href="/signup" class="text-center new-account">Create an account</a><br><br>
            <blockquote class="blockquote">demo accounts: <br>user/user<br>support/support<br>admin/admin</blockquote>
        </div>
    </div>
</div>

<%@ include file="components/footer.jspf"%>
