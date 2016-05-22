<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CETEST</title>
</head>
<body>
<form method="post" action ="CandidateContrller">

<table id="candidateSearch" align="center">
<tr>
<td>Candidate Id</td><td><input type="text" name="candidateid"></td>
<td>Candidate Name</td><td><input type="text" name="candidatename"></td>
<%--<td>Sort Order<td><select name="order"><option value="-1">--Select--<option value="asc">Ascending</option><option value="desc">Descending</option></select></td> --%>
<td><input id="submitbtn" type="submit" name="action" value="Search"></input></td>
</tr>

</table>



</form>

</body>
</html>