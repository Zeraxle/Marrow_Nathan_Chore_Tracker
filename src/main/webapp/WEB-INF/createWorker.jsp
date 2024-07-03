<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<title>Get Worker License</title>
</head>
<body>
	<header>
		<nav>
			<h1>Create Your License</h1>
			<a href="/jobs">Back</a>
			<a href="/logout">Logout</a>
		</nav>
	</header>
	<p><c:out value="${notLicensed}"></c:out></p>
	<main>
		<h2>Want to take a job? Create your license here!</h2>
		<form:form action="/workers/create" method="POST" modelAttribute="worker">
			<form:input type="hidden" path="userWithLicense" value="${userId}" />
			<div>
				<form:label path="firstName">First Name: </form:label>
				<form:errors path="firstName"></form:errors>
				<form:input path="firstName"></form:input>
			</div>
			<div>
				<form:label path="lastName">Last Name: </form:label>
				<form:errors path="lastName"></form:errors>
				<form:input path="lastName"></form:input>
			</div>
			<input type="submit" value="Get Your License" />
		</form:form>
	</main>
</body>
</html>