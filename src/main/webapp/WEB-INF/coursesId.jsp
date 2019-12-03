<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>This Course</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

	<div class="container">
			<h1 class="display-2"><c:out value="${course.name}"/></h1>
			<h5>Instructor: <c:out value="${course.instructor}"/></h5>
			<h5>Sign Ups: <c:out value="${course.students.size()}"/></h5>
			<a class="first" href="/courses/${course.id }/asc">Sort Date Asc   </a>
			<a class="second" href="/courses/${course.id }/desc">Sort Date Desc</a>
			<table class="table eventTable">
			  <thead class="thead-dark">
			    <tr>
			      <th scope="col">Name</th>
			      <th scope="col">Sign Up Date</th>
			      <th scope="col">Action</th>
			    </tr>
			  </thead>
			  <tbody>
				  <c:forEach var="students" items="${course.students }">
				    <tr>
				      <th><c:out value="${students.name}"/></th>
				      <td><fmt:formatDate pattern ="MM/dd/yyyy" value ="${students.createdAt}"/></td>
				      <td>
				      	<c:choose>
		      				<c:when test="${students==user }">
								<a href="/courses/${course.id }/remove">Drop this Course</a>
		      				</c:when>
		      			</c:choose>
				      </td>
				    </tr>
		    	  </c:forEach>
			  </tbody>
			</table>
			<a class="btn btn-warning" href="/courses/${course.id }/edit">Edit</a>
		    <a class="btn btn-warning" href="/courses/${course.id }/delete">Delete</a>
	
	</div>


</body>
</html>