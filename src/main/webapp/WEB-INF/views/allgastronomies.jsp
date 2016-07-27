<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>University Enrollments</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>List of Gastronomies</h2>
	<table>
		<tr>
			<td>NAME</td><td>Joining Date</td><td>Salary</td><td>SSN</td><td></td>
		</tr>
		<c:forEach items="${gastronomies}" var="gastronomy">
			<tr>
			<td>${gastronomy.name}</td>
			<td>${gastronomy.joiningDate}</td>
			<td>${gastronomy.salary}</td>
			<td><a href="<c:url value='/edit-${gastronomy.ssn}-gastronomy' />">${gastronomy.ssn}</a></td>
			<td><a href="<c:url value='/delete-${gastronomy.ssn}-gastronomy' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/new' />">Add New gastronomy</a>
</body>
</html>