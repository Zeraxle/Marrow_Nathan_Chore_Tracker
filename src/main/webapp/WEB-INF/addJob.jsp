<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/style.css">
<title>Post a Job</title>
</head>
<body>
	<header>
		<h1>Post a Job</h1>
		<nav>
			<a href="/jobs">Back</a>
			<a href="/logout">Logout</a>
		</nav>
	</header>
	<form:form action="/jobs/create" method="POST" modelAttribute="newJob">
		<form:input type="hidden" path="jobOwner" value="${userId}"></form:input>
		<div>
			<form:label path="title">Title: </form:label>
			<form:errors path="title"></form:errors>
			<form:input path="title"></form:input>
		</div>
		<div>
			<form:label path="description">Description: </form:label>
			<form:errors path="description"></form:errors>
			<form:textarea path="description"></form:textarea>
		</div>
		<div>
			<form:label path="location">Location: </form:label>
			<form:errors path="location"></form:errors>
			<form:input path="location"></form:input>
		</div>
		<input type="submit" value="Post Job"/>
	</form:form>
</body>
</html>