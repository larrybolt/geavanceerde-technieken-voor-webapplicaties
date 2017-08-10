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
        <div class="col-md-4">
            <h2 class="text-center login-title">Please sign in</h2>
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
        <div class="col-md-8 well">
            <div id="blog">
                <h2>Blog</h2>
            <c:forEach items="${posts}" var="post">
                <article data-postid="${post.postId}">
                    <h3><c:out value="${post.title}"></c:out></h3>
                    <p><c:out value="${post.content}"></c:out></p>
                    <footer>
                        <c:out value="${post.datetime}"></c:out>
                    </footer>
                    <section>
                        <strong>Comments</strong>
                        <div class="comments">
                            <c:forEach items="${post.comments}" var="comment">
                                <%@ include file="components/blog-comment.jspf" %>
                            </c:forEach>
                        </div>
                        <form action="/blog/${post.postId}/comments" method="post" onsubmit="return postComment(this)">
                            <input type="hidden" name="postId" value="${post.postId}">
                            <div class="form-group">
                                <textarea name="comment" class="form-control" rows="3" required="required" placeholder="Your comment, be kind!"></textarea>
                            </div>
                            <div class="input-group">
                                <input type="text" name="name" placeholder="Optional name" class="form-control">
                                <span class="input-group-btn"><button type="submit" class="btn btn-primary">Post</button></span>
                            </div>
                        </form>
                    </section>
                </article>
            </c:forEach>
            </div>
        </div>
    </div>
</div>

<script id="blog-comment-template" type="javascript-template">
    <%@ include file="components/blog-comment.jspf" %>
</script>

<script type="application/javascript" src="/static/javascript/blog-comment.js"></script>
<%@ include file="components/footer.jspf"%>
