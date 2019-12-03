<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>New Course</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<div class="container">
		<h1 class="display-2">Create a New Course</h1>
		<p><form:errors path="course.*"/></p>
		<form:form method="POST" action="/courses/create" modelAttribute="course">
		<p>
            <form:label class="col-sm-2 col-form-label" path="name">Course Name:</form:label>
            <form:input class="form-control col-sm-6" type="text" path="name"/>
        </p>
        <p>
        	<form:label class="col-sm-2 col-form-label" path="instructor">Instructor Name:</form:label>
        	<form:input class="form-control col-sm-6" type="text" path="instructor"/>
        <p>
            <form:label class="col-sm-2 col-form-label" path="capacity">Capacity:</form:label>
            <form:input class="form-control col-sm-6" type="text" path="capacity"/>
        </p>
        <input class="btn btn-warning" type="submit" value="Create"/>
        </form:form>
	</div>


</body>
</html>