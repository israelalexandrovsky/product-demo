<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <title>Product Store</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Product Store</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/products">All Products</a></li>
            <li><a href="/add">New Product</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <c:choose>
         <c:when test="${mode=='VIEW'}">
             <table class="table">
                 <thead>
                 <tr>
                     <th>Product Name</th>
                     <th>Category</th>
                     <th>Price</th>
                     <th>Edit</th>
                     <th>Delete</th>
                 </tr>
                 </thead>
                 <tbody>
                 <c:forEach var ="product" items="${products}">
                     <tr>
                         <td>${product.name}</td>
                         <td>${product.category.name}</td>
                         <td>${product.price}</td>
                         <td><a href="update/${product.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
                         <td><a href="delete/${product.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                     </tr>
                 </c:forEach>
                 </tbody>
             </table>
     </c:when>
     <c:when test="${mode=='EDIT'}">
         <form action="/save" method="POST">
             <input type="hidden" class="form-control" value="${product.id}"  name="id" id="id">
             <div class="form-group">
                 <label for="name">Product Name</label>
                 <input type="text" class="form-control" value="${product.name}"  name="name" id="name">
             </div>
             <div class="form-group">
                 <label for="category">Category</label>
                 <select class="selectpicker"  id="category"  name="category.id">
                     <c:forEach var="cat" items="${categories}">
                      <c:choose>
                         <c:when test="${cat.id==selected}">
                           <option value="${cat.id}" selected>${cat.name}</option>
                        </c:when>
                        <c:otherwise >
                           <option value="${cat.id}">${cat.name}</option>
                        </c:otherwise>
                      </c:choose>
                     </c:forEach>
                 </select>
             </div>
             <div class="form-group">
                 <label for="price">Price:</label>
                 <input type="number" step="0.01" min="0" class="form-control" value="${product.price}" id="price" name="price">
             </div>
             <button type="submit" class="btn btn-default">Update</button>
         </form>
     </c:when>
     <c:when test="${mode=='NEW'}">
            <form action="/saveNew" method="POST">
                <input type="hidden" class="form-control" value="${product.id}"  name="id" id="id">
                <div class="form-group">
                    <label for="name">Product Name</label>
                    <input type="text" class="form-control" value="${product.name}"  name="name" id="name">
                </div>
                <div class="form-group">
                    <label for="category">Category</label>
                    <select class="selectpicker"  id="category"  name="category.id">
                        <c:forEach var="cat" items="${categories}">
                         <option value="${cat.id}">${cat.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="number" step="0.01" min="0" class="form-control" value="${product.price}" id="price" name="price">
                </div>
                <button type="submit" class="btn btn-default">Update</button>
            </form>
        </c:when>
    </c:choose>
</div>

</body>
</html>
