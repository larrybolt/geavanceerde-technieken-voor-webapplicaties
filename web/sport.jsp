<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="components/header.jspf"%>
<!-- Navigation -->
<nav class="navbar fixed-top navbar-toggleable-md navbar-inverse bg-inverse">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarExample" aria-controls="navbarExample" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="container">
        <a class="navbar-brand" href="/">Fancy WebShop</a>
        <div class="collapse navbar-collapse" id="navbarExample">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Shop <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout, ${user.userName}</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container shop">

    <h2>Sport</h2>
    <div class="row">

        <div class="col-md-3">

            <c:if test="${user.role == 'ADMIN'}">
                <h3>Toevoegen</h3>
                <hr>
                <form action="/sport" method="post" id="sport">
                    <div class="form-group">
                        <label for="day">Dag</label>
                        <select class="form-control" name="Day" id="day">
                            <c:set var = "daysofweek" scope = "page" value = "Maandag;Dinsdag;Woensdag;Donderdag;Vrijdag;Zaterdag;Zondag"/>
                            <c:forTokens items = "${daysofweek}" delims = ";" var = "option">
                                <option value="${option}">${option}</option>
                            </c:forTokens>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="time">Tijd</label>
                        <input type="text" class="form-control" id="time" name="Time" placeholder="Tijd" required="required">
                    </div>
                    <div class="form-group">
                        <label for="sport">Sport</label>
                        <input type="text" class="form-control" id="sport" name="Sport" placeholder="Sport" required="required">
                    </div>
                    <div class="form-group">
                        <label for="sport">Naam</label>
                        <input type="text" class="form-control" id="name" name="Name" placeholder="Name" required="required">
                    </div>
                    <div class="form-group">
                        <label for="plaats">Plaats</label>
                        <input type="text" class="form-control" id="plaats" name="Location" placeholder="Plaats" required="required">
                    </div>
                    <button type="submit" class="btn btn-primary">Activiteit toevoegen</button>
                </form>
            </c:if>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-md-9">

            <div id="activities">

                <c:forEach items="${activities}" var="activity">
                    <%@ include file="components/activity.jspf" %>
                </c:forEach>

            </div>
            <script id="product-template" type="javascript-template">
                <%@ include file="components/activity.jspf" %>
            </script>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->
<script src="/static/javascript/exam-sport.js" defer="defer"></script>
<c:if test="${user.role == 'ADMIN'}">
<script src="/static/javascript/exam-admin-sport.js" defer="defer"></script>
</c:if>
<%@ include file="components/footer.jspf"%>
