<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function orderProductBy(criteria){
		document.getElementById('sortByCriteria').value = criteria;
		document.getElementById('orderByProductForm').submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product view</title>
</head>
<body>
<table>
		<tr bgcolor="silver">
			<td onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='default'" onclick="orderProductBy('id')">Id</td>
			<td onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='default'" onclick="orderProductBy('product_name')">Product name</td>
			<td onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='default'" onclick="orderProductBy('price')">Price</td>
			<td onmouseover="this.style.cursor='pointer'" onmouseout="this.style.cursor='default'" onclick="orderProductBy('category')">Category</td>
			<td>Action</td>
		</tr>
		<c:forEach var="p" items="${product}">
			<tr>
				<td>${p.id}</td>
				<td>${p.name}</td>
				<td>${p.price}</td>
				<td>${p.category}</td>
				<td>
				<form action="/spring-mvc-product/product/remove/" method="post">
				 <input type="hidden" name="id" value="${p.id}" />
				 <input type="submit" value="Remove" />	</form>
			</tr>
		</c:forEach>
	</table>

	<form action="/spring-mvc-product/product/" method="post">
		<table>
			<tr>
				<td>Enter product name</td>
				<td><input type="text" name="name" /></td>
			</tr>

			<tr>
				<td>Enter product price</td>
				<td><input type="text" name="price" /></td>
			</tr>

			<tr>
				<td>Enter product category</td>
				<td><input type="text" name="category" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Add" /></td>
			</tr>
		</table>
	</form>
	
		<form id="orderByProductForm" action="/spring-mvc-product/product/" method="get">
		<input type="hidden" id="sortByCriteria" name="sortByCriteria" value="id"/>
	</form>
</body>
</html>