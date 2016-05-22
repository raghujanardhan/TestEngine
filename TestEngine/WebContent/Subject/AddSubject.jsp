<%@page import="testengine.beans.CandidateInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="testengine.modal.CandidateModal"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<title>CETEST</title>

	<meta character="utf-8">
	<meta name="viewport" content="width=device-width, initial-scal=1.0">
	<link rel="stylesheet" type="text/css" href="./css/mystyle.css">
	<link rel="stylesheet" href="./css/responsiveslides.css">
    <link rel="stylesheet" href="/TestEngine/css/themes.css">
    <link rel="stylesheet" href="./css/subModal.css">
    
</head>
<script type="text/javascript">
function validateSubjectForm(){
	
	document.getElementById("subjectnameerror").innerHTML="";
	
	var batchname = document.forms["addSubjectForm"]["subjectname"].value;
	if(batchname==null || batchname=="" || batchname.trim().length==0){
		document.getElementById("subjectnameerror").innerHTML="Subject name required";
		
		return false;
	}
}
</script>
<body>
<div class="wrap">
	<header id="header">
		<%@ include file="/header.jsp" %>

	</header>
	<%@ include file="/menu.jsp" %>
	
		 </div>
		 
		 
	
	<!--Big navigation bar -->	
				
		
		 <!--navigation ends here-->
		 
		 <div id="content" align="center">
		 
		 <b>Add New Subject</b>
		 	<br>
		 		<form action="SubjectController" method="post" name="addSubjectForm" onsubmit="return validateSubjectForm();">
		<div id="addSubject">
		<table id="batchtable" align="center" background="red" >
		<tr>
			<td>Subject ID </td>
			<td><input class="textbox" type="text" name="subjectId" value=<%=session.getAttribute("seqnum") %> readonly="readonly" style="background-color: cyan" ></td>
			
		</tr>
		<tr>
			<td>Subject <font color="red">*</font> </td>
			<td><input class="textbox" type="text" name="subjectname" ><div id = "subjectnameerror" style="color: red"></div></td>
			
		</tr>
		
		
		
		<tr>
			<td>Description</td>
			<td><textarea class="textbox" name="subjectdesc"  rows="4" cols="29"></textarea><div id = "descerror" style="color: red"></div></td>
			
		</tr>
		
	</table>
	
	
	<%if(request.getAttribute("status") != null){ %>
	<font color="blue">
	<%=request.getAttribute("status") %>
	</font>
	<%} %>
	
	
	<br>
		
		<input type="image" src="submitbutton.png" alt="Submit" name="action" value="addNewSubject" >
		<input type="image" src="clearbutton.png" alt="Submit" >
		</div>
		</form>
		 
		 </div>
		
	    
	<footer id="footer">
		<%@ include file="/footer.jsp" %>
	</footer>


<script src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script src="./js/jquery.min(1).js"></script>
<script type="text/javascript" src="./js/menu-script.js"></script>
<script src="./js/responsiveslides.min.js"></script>
<script src="./js/subModal.js"></script>

</body></html>
