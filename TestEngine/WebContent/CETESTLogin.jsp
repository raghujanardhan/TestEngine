<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/mystyle.css">
</head>
<body background="#fff">
<%--
<div id="logo">

<img src="./images/samartha1.jpg" width="300px" height="118px"> </div>  --%>
		
		
		<div id="ser">
		 <form id="login" name="loginform" method="post" action="LoginController">
		 <table align="right">
		 <tr><td><font face="Open Sans" font size="3">User Name</font></td><td><input type = "text" name="username"></td>
		 
		 <td><font face="Open Sans" font size="3">Password</font></td><td><input type = "password" name="password"></td>
		 
		 <td><input id="submitbtn" type = "submit" name="action" value="Login" ></td> 
		 </tr>
		 <tr align="center"> <td colspan="5" align="center">
		 <c:if test="${requestScope.validUser ne null}">
		 	<font color="red">
		 	<c:out value="${requestScope.validUser}"></c:out>
		 	</font>
		 </c:if>
		 </td></tr>
		 </table>
	    </form>
		</div>
		</body>
</html>