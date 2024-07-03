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
<title>Home Page</title>
</head>
<body>
	<header>
		<nav class="dashboard-nav">
			<div>
				<h1>Welcome, <c:out value="${user.firstName}"></c:out></h1>
				<a href="/logout">Logout</a>
			</div>
			<div>
				<a href="/jobs/new">Post a Job!</a>
				<c:if test="${user.licensedWorker == null}">
				<a href="/workers/new">Become a Worker!</a>
				</c:if>
			</div>	
		</nav>
	</header>
	<main class="dashboard-main">
		<table class="table table-bordered" id="table">
			<thead>
				<tr>
					<th>Job</th>
					<th>Location</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="job" items="${allJobs}">
 				<c:if test="${job.jobTaker.id == null }"> <%--FOR TAKING JOBS OFF THE JOB BOARD --%>

				<tr>
					<td><c:out value="${job.title}"></c:out></td>
					<td><c:out value="${job.location}"></c:out></td>
					<td id="dash-links">
						<a href="/jobs/show/${job.id}">View</a> 
						<form:form action="/jobs/add/${job.id}" method="POST" modelAttribute="updatedJob">
							<input type="hidden" name="_method" value="PUT"/>
							<form:input type="hidden" path="jobOwner" value="${job.jobOwner.id }"></form:input>
							<form:input type="hidden" path="jobTaker" value="${user.licensedWorker.id}"></form:input>
							<form:input type="hidden" path="title" value="${job.title}"></form:input>
							<form:input type="hidden" path="description" value="${job.description}"></form:input>
							<form:input type="hidden" path="location" value="${job.location}"></form:input>
							<input type="submit" value="Add" id="add-button"/>
						</form:form>
						
						<c:if test="${job.jobOwner.id == loggedInUser }"> <%--FOR DISPLAYING EDIT AND CANCEL TO THE APPROPRIATE USER --%>
						
						<a href="/jobs/edit/${job.id}">Edit</a>
						<form action="/jobs/delete/${job.id}" method="POST">
							<input type="hidden" name="_method" value="DELETE"/>
							<input type="submit" value="Cancel" id="cancel-button"/>
						</form>
						
						</c:if>
					</td>
				</tr>
				
				</c:if>
				</c:forEach>
				
			</tbody>
		</table>
		
		<p><c:out value="${jobOwnerWarning }"></c:out></p> <%--TO KEEP JOB OWNERS FROM TAKING THEIR OWN POSTED JOBS --%>
		
		<table class="table table-bordered" id="table">
			<thead>
				<tr class="table-row">
					<td>My Jobs</td>
					<td>Actions</td>
				</tr>
			</thead>
			<tbody >
				
				<c:forEach var="myJob" items="${allMyJobs}"> 
				
				<tr>
					<td><c:out value="${myJob.title}"></c:out></td>
					<td id="dash-links">
						<a href="/jobs/show/${myJob.id}">View</a> 
						<form action="/jobs/delete/${myJob.id}" method="POST">
							<input type="hidden" name="_method" value="DELETE"/>
							<input type="submit" value="Done" id="cancel-button"/>
						</form>
					</td>
				</tr>
				
				</c:forEach>
				
			</tbody>
		</table>
	</main>
</body>
</html>