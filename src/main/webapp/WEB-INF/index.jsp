<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Login/Register</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<div class="container">
		<h1 class="display-2">Welcome</h1>
		<div class="register">
			<h3>Register</h3>
			<p><form:errors path="user.*"/></p>
	    
	    	<form:form method="POST" action="/registration" modelAttribute="user">

	        <p>
	            <form:label class="col-sm-4 col-form-label" path="name">Name:</form:label>
	            <form:input class="form-control col-sm-6" type="text" path="name"/>
	        </p>
	        <p>
	            <form:label class="col-sm-4 col-form-label" path="email">Email:</form:label>
	            <form:input class="form-control col-sm-6" type="email" path="email"/>
	        </p>
	        <p>
	            <form:label  class="col-sm-4 col-form-label" path="password">Password:</form:label>
	            <form:password class="form-control col-sm-6" path="password"/>
	        </p>
	        <p>
	            <form:label class="col-sm-4 col-form-label" path="passwordConfirmation">Password Confirmation:</form:label>
	            <form:password class="form-control col-sm-6" path="passwordConfirmation"/>
	        </p>
	        <input class="btn btn-warning" type="submit" value="Register"/>
	    </form:form>
		</div>
		<div class="login">
			<h3>Login</h3>
			    <p><c:out value="${error}" /></p>
		    <form method="post" action="/login">
		        <p>
		            <label class="col-sm-4 col-form-label" for="email">Email</label>
		            <input class="form-control col-sm-6" type="text" id="email" name="email"/>
		        </p>
		        <p>
		            <label class="col-sm-4 col-form-label" for="password">Password</label>
		            <input class="form-control col-sm-6" type="password" id="password" name="password"/>
		        </p>
		        <input class="btn btn-warning" type="submit" value="Login"/>
		    </form>  
		</div>
	</div>


</body>
</html>