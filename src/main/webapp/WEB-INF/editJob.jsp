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
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<title>Edit Job</title>
</head>
<body>
	<header>
		<h1>Edit Your Job Posting</h1>
		<nav>
			<a href="/jobs">Back</a>
			<a href="/logout">Logout</a>
		</nav>
	</header>
	<form:form action="/jobs/update/${job.id}" method="POST" modelAttribute="job">
		<input type="hidden" name="_method" value="put"/>
		<form:input type="hidden" path="jobOwner" value="${user.id}"></form:input>
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
		<input type="submit" value="Edit Job"/>
	</form:form>
</body>
</html>