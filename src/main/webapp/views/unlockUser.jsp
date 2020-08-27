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
<script>
function validatePwds() {
	var newPwd = $('#newPwd').val();
	var confirmPwd = $('#confirmPwd').val();
	if (newPwd != confirmPwd) {
		$('#errId').text('New Password & Confirm Password Should Be Same');
		return false;
	}
	return true;
}
</script>
</head>
<body>	
	<form:form action="unlockAcc" method="POST" modelAttribute="unlockAcc">
		<table>
		<br>
		
 			<tr>
			<th>Email:</th>
			<td>${email}</td>
            <td><form:input type="hidden" path="email" value="${email}"/></td>
  
 			</tr>
				<tr>
				<th>Temporary Password:</th>
				<td><form:input path="tempPwd" /></td>
			</tr>
			<tr>
				<th>New Password:</th>
				<td><form:input path="newPwd"/></td>
			</tr>
			<tr>
				<th>Confirm Password:</th>
				<td><form:input path="confirmPwd"/></td>
				
			</tr>
			<tr>
				<td>
				<br>
				<input type="submit" value="Reset">&nbsp;&nbsp; <input type="submit" value="Unlock" onclick="javascript:return validatePwds()"></td>
			</tr>
		</table>
						<td><span id="errId" style="color: red"></span></td>
		
	</form:form>
				<span style="color: red">${ExMsg}</span>
	
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
