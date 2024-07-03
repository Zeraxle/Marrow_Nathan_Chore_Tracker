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
<title>Login and Registration</title>
</head>
<body>	
	<h1 class="h1-index">Welcome to Chore Tracker</h1>
		<p><c:out value="${notLoggedIn}"></c:out></p>
		
		
	<main class="index-main">
		<div class="reg-div">
			<h2>Register</h2>
			<form:form action="/register" method="POST" modelAttribute="newUser">
				<div class="form-group">
					<form:label path="firstName">First Name: </form:label>
					<form:errors path="firstName"></form:errors>
					<form:input path="firstName"></form:input>
				</div>
				<div class="form-group">
					<form:label path="lastName">Last Name: </form:label>
					<form:errors path="lastName"></form:errors>
					<form:input path="lastName"></form:input>
				</div>
				<div class="form-group">
					<form:label path="email">Email: </form:label>
					<form:errors path="email"></form:errors>
					<form:input path="email"></form:input>
				</div>
				<div class="form-group">
					<form:label path="password">Password: </form:label>
					<form:errors path="password"></form:errors>
					<form:input type="password" path="password"></form:input>
				</div>
				<div class="form-group">
					<form:label path="confirmPassword">Confirm Password: </form:label>
					<form:errors path="confirmPassword"></form:errors>
					<form:input type="password" path="confirmPassword"></form:input>
				</div>
				<input type="submit" value="Create User" />
			</form:form>
		</div>
		<div class="log-div">
			<h2>Login</h2>
			<form:form action="/login" method="POST" modelAttribute="newLogin">
				<div class="form-group">
					<form:label path="email">Email: </form:label>
					<form:errors path="email"></form:errors>
					<form:input path="email"></form:input>
				</div>
				<div class="form-group">
					<form:label path="password">Password: </form:label>
					<form:errors path="password"></form:errors>
					<form:input type="password" path="password"></form:input>
				</div>
				<input type="submit" value="Login" />
			</form:form>
		</div>
	</main>	
</body>
</html>