<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file = "header.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/app.js"></script>
 	<form:form action="userAccLogin" method="post" modelAttribute="loginUser">
 	
		<table>
		<thead>
		<br>		
		<b>Login</b>
		</thead>
			<tr></tr>
			
			<tr>
				<th>User Email:</th>
				<td><form:input type="text" path="email" /></td>
			
			</tr>
			<tr>
				<th>Password:</th>
				<td><form:input type="password" path="password" /></td>
			
			</tr>
			<tr>
				<td><input type="submit" value="Reset">&nbsp;<input
					type="submit" value="Login"></td>
			</tr>
				
		</table>
	</form:form>
	<br>
	<span id="emailMsg" style="color: red" >${ErrorMsg}</span>
	<span id="lockedMsg" style="color: red">${LockedMsg}</span>
	<br>
	<br>
	<a href="register">Register User</a>&nbsp;
	<a href="forgotPassword">Forgot Password</a>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
<%@ include file = "footer.jsp" %>

</body>
</html>
