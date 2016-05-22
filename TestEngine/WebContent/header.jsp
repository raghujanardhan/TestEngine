<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/mystyle.css">
</head>
<script type="text/javascript">
	function cetestLogout(){

		}



</script>

<body background="#fff">

<div id="logo"><a href="#">

<%--<img src="./images/samartha1.jpg" width="300px" height="118px"> --%> </a></div>
		
		
		<div id="ser">
		
		<table>
		<tr><td>Welcome, ${sessionScope.user} !</td><td width="20px"></td><td><a href="Logout?action=logout">Logout</a></td></tr>
		
		
		</table> 
		 
		</div>
		 
		</body>
</html>