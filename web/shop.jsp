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
                    <a class="nav-link" href="/sport">Sport <span class="sr-only">(current)</span></a>
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

    <div class="row">

        <div class="col-md-3">

            <%@ include file="components/chat.jspf" %>
            <c:if test="${user.role == 'ADMIN'}">
                <h3>Add Product</h3>
                <hr>
                <form action="/products" method="post" id="addproduct">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="Catchy product name" required="required">
                    </div>
                    <div class="form-group">
                        <label for="imgUrl">Image Url</label>
                        <input type="text" class="form-control" id="imgUrl" name="imageUrl" placeholder="http://..." required="required">
                    </div>
                    <div class="form-group">
                        <label for="price">Price</label>
                        <input type="text" class="form-control" id="price" name="price" placeholder="999.99" required="required">
                    </div>
                    <div class="form-group">
                        <label for="stockstatus">Stock status</label>
                        <select class="form-control" name="stockstatus" id="stockstatus">
                            <c:set var = "stockoptions" scope = "page" value = "in stock;few left;sold out"/>
                            <c:forTokens items = "${stockoptions}" delims = ";" var = "option">
                                <option value="${option}">${option}</option>
                            </c:forTokens>
                            <option value="custom">custom</option>
                        </select>
                    </div>
                    <div class="form-group hidden" id="customstockstatus">
                        <label for="stockstatuscustom">Custom Stock status</label>
                        <input type="text" class="form-control" name="customstockstatus" id="stockstatuscustom" placeholder="Delivered soon">
                    </div>
                    <div class="form-group">
                        <label for="desc">Description</label>
                        <textarea class="form-control" id="desc" name="description" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Product</button>
                </form>
            </c:if>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-md-9">

            <div class="row" data-lastupdatetimestamp="${products_lastupdatetimestamp}" id="products">

                <c:forEach items="${products}" var="product">
                    <%@ include file="components/product.jspf" %>
                </c:forEach>

            </div>
            <script id="product-template" type="javascript-template">
                <%@ include file="components/product.jspf" %>
            </script>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->
<script src="/static/javascript/shop-products.js"></script>
<c:if test="${user.role == 'ADMIN'}">
<script src="/static/javascript/shop-admin.js"></script>
</c:if>
<script src="/static/javascript/shop-chat.js" defer="defer"></script>
<%@ include file="components/footer.jspf"%>
