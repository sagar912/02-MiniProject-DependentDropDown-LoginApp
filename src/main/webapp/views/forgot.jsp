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
<body>	
	
</body>
 	<form:form action="forgotPasswords" method="post" modelAttribute="user">
		<table>
		<thead>
		<br>		
		<p>Enter Registered Email</p>
		</thead>
			<tr></tr>
			
			<tr>
				<th>User Email:</th>
				<td><form:input type="text" path="userEmail" /></td>
			
			</tr>
			<tr>
				<td>&nbsp;<input type="submit" value="Submit"></td>
			</tr>
	
		</table>
	</form:form>
	<span id="forgotPasswordMsg" style="color: green">${forgotPasswordMsg}</span>
	<br>
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
