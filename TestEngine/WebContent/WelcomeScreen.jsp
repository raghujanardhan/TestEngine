<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function validateLogin(){
	document.getElementById("usererror").innerHTML="";
	document.getElementById("passworderror").innerHTML="";
	var username = document.forms["loginform"]["username"].value;
	var password = document.forms["loginform"]["password"].value;
	if(username==null || username==""){		
		document.getElementById("usererror").innerHTML="Username Required";
		
		return false;
	}
	if(password==null || password==""){
		document.getElementById("passworderror").innerHTML="Password Required";
		return false;
	}
	
}
function clearMessages() {
	document.getElementById("usererror").innerHTML="";
	document.getElementById("passworderror").innerHTML="";
}


</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="testengine.css" media="screen" />
<title>Test Engine</title>
</head>
<body>

<form name="loginform" method="post" action="ValidateUser">
<div id="full">
	<div id ="header" align="center">
	<img src="TestEngineHeader.jpg" width="100%" height="100">

</div>
<div id = "login">
<table id="logintable" align="center">
<tr>
	<td>Username</td>
	<td><input type="text" name="username"><div id="usererror" style="color: red"></div></td>
</tr>

<tr>
	<td>Password</td>
	<td><input type="password" name="password"><div id="passworderror" style="color: red"></div>
	</td>
</tr> 

<tr>
	
	<td></td>
	<td><input type="submit" value="Submit" onclick="return validateLogin()">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" name="Reset" onclick="clearMessages()"></td>
</tr>


</table>
<div id = "errordiv" align="center">
<%
if(request.getAttribute("message") != null){ 
%>

<font color="red">
<%=request.getAttribute("message") %>

</font>
	
<%	
}

%>


</div>
</div>
<div id="footer" align="center">

</div>
</div>
</form>
</body>
</html>
