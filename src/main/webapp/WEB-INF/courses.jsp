<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Courses</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<div class="container">
	<div class="header">
			<h1 class="display-4 logged-in">Welcome, <c:out value="${user.name}"/></h1>
			<a href="/logout">Logout</a>
		</div>
		<div class="header2">
			<h4 class="logged-in">Courses:</h4>
			<a class="first" href="/courses/asc">Sort Low to High   </a>
			<a class="second" href="/courses/desc">Sort High to Low</a>
		</div>
		<table class="table">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Course</th>
		      <th scope="col">Instructor</th>
		      <th scope="col">Registered Students</th>
		       <th scope="col">Action</th>
		    </tr>
		  </thead>
		  <tbody>
			  <c:forEach var="course" items="${courses }">
			    <tr>
			      <th><a href="/courses/${course.id}"><c:out value="${course.name}"/></a></th>
			      <td><c:out value="${course.instructor}"/></td>
			      <td> <c:out value="${course.students.size()}"/>/<c:out value="${course.capacity}"/></td>
			      <td>
		      		<c:choose>
		      			<c:when test="${course.students.contains(user) }">
							<p>Already Added</p>
		      			</c:when>
		      			<c:otherwise>
		      				<c:choose>
			      					<c:when test="${course.students.size()== course.capacity}">
										<p>Full</p>
			      					</c:when>
		      					<c:otherwise>
		      						<a href="/courses/${course.id }/addstudent">Add</a>
		      					</c:otherwise>
		      				</c:choose>
		      			</c:otherwise>
		      		</c:choose>
				  </td>
			    </tr>
	    	  </c:forEach>
		  </tbody>
		</table>
		<a href="/courses/new" class="btn btn-warning">Add a Course</a>
	</div>


</body>
</html>