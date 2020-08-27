<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file = "header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/app.js"></script>


  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#dob" ).datepicker();
  } );
  </script>
</head>
<body>
	<form:form action="userAccReg" method="post" modelAttribute="user">
		<table>
			<tr>
				<br>	
				<th>First Name:</th>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<th>Last Name:</th>
				<td><form:input path="LastName" /></td>
			</tr>
			<tr>
				<th>Email:</th>
				<td><form:input path="userEmail" /></td>
				<td><span id="emailMsg" style="color: red"></span></td>
				
			</tr>
			<tr>
				<th>Mobile:</th>
				<td><form:input path="phoneNumber" /></td>
			</tr>
			<tr>
				<th>Date-Of-Birth:</th>
				<td><input type="text" path="dob"></td>
				
			</tr>
			<tr>
				<th>Gender:</th>
				<td>Male:<form:radiobutton   path="gender"  value="M" id="gender" /> &nbsp;Female:<form:radiobutton path="gender" value="F" id="gender" />
				</td>
			</tr>
			 <tr>
				<th>Country:</th>
				<td><form:select path="countryId">
						<form:option value="">-Select-</form:option>
						<form:options items="${countries}" />
					</form:select></td>
			</tr> 
			<tr>
				<th>State:</th>
				<td><form:select path="stateId">
						<form:option value="">-Select-</form:option>
					</form:select></td>
			</tr>
			<tr>
				<th>City:</th>
				<td><form:select path="cityId">
						<form:option value="">-Select-</form:option>
					</form:select></td>
					
			</tr>

			<tr>
				<th></th>
				<td><br><input type="submit" value="Reset">&nbsp;&nbsp;<input
					type="submit" value="SignUp"></td>
			</tr>
		</table>
	</form:form>
	<br>
	<br>
<%@ include file = "footer.jsp" %>

	
</body>

</html>
