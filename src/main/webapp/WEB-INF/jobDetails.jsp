<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<title>Job Details</title>
</head>
<body>
	<header>
		<nav>
			<a href="/jobs">Back</a>
			<a href="/">Logout</a>
		</nav>
	</header>
	<main class="detail-box">
		<h1><c:out value="${job.title}"></c:out></h1>
		<div class="info-box">
			<p><c:out value="${job.description}"></c:out></p>
			<p>Location: <c:out value="${job.location}"></c:out></p>
			<p>Posted By: <c:out value="${job.jobOwner.firstName} ${job.jobOwner.lastName}"></c:out></p>
			<p>Posted On: <fmt:formatDate value="${job.createdAt}" pattern="MM-dd-yyyy" /></p>
		</div>
		
		<c:if test="${job.jobTaker == null}"> <%--TAKES THE ADD BUTTON AWAY IF THE JOB HAS BEEN TAKEN --%>
		
		<form:form action="/jobs/add/${job.id}" method="POST" modelAttribute="updatedJob">
			<input type="hidden" name="_method" value="PUT"/>
			<form:input type="hidden" path="jobOwner" value="${job.jobOwner.id }"></form:input>
			<form:input type="hidden" path="jobTaker" value="${user.licensedWorker.id}"></form:input>
			<form:input type="hidden" path="title" value="${job.title}"></form:input>
			<form:input type="hidden" path="description" value="${job.description}"></form:input>
			<form:input type="hidden" path="location" value="${job.location}"></form:input>
			<input type="submit" value="Add to My Jobs" id="add-button-details"/>
		</form:form>
		
		</c:if>
		
	</main>
</body>
</html>