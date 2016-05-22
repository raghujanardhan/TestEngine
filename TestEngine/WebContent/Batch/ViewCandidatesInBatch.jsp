<%@page import="testengine.beans.CandidateInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="testengine.modal.CandidateModal"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<title>CETEST</title>

	<meta character="utf-8">
	<meta name="viewport" content="width=device-width, initial-scal=1.0">
	
	<link rel="stylesheet" href="./css/responsiveslides.css">
    <link rel="stylesheet" href="/TestEngine/css/themes.css">
    <link rel="stylesheet" href="./css/subModal.css">

    <link rel="stylesheet" href="./css/themes.css">
    <link rel="stylesheet" href="./css/elements.css">
    <link rel="stylesheet" href="./css/js_composer.css">
    <link rel="stylesheet" href="./css/grid.css">
    <link rel="stylesheet" href="./css/theme-style.css">
    
    <link rel="stylesheet" href="./css/frontend.css">
    <link rel="stylesheet" href="./css/cimmResposive.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/styles_CMS.css">
    <link rel="stylesheet" href="./css/commonStyles.css">
    <link rel="stylesheet" href="./css/CIMM2TouchMainStyle.css">
    <link rel="stylesheet" href="./css/font-awesome.min.css">
    
</head>
<script type="text/javascript">
function getCandidateDetails()
{	
	var batchId = document.forms["viewCandidatesForm"]["batchId"].value;
	var xmlhttp;    
	 /* if (str=="")
	   {
	   document.getElementById("custData").innerHTML="";
	   return;
	   } */
	 if (window.XMLHttpRequest)
	   {// code for IE7+, Firefox, Chrome, Opera, Safari
	   xmlhttp=new XMLHttpRequest();
	   }
	 else
	   {// code for IE6, IE5
	   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	   }
	 xmlhttp.onreadystatechange=function()
	   {
	   if (xmlhttp.readyState==4 && xmlhttp.status==200)
	     {
	     document.getElementById("custData").innerHTML=xmlhttp.responseText;
	     }
	   }
	 xmlhttp.open("GET","BatchController?action=viewCandidates&batch_id="+batchId,true);
	 xmlhttp.send();
}
</script>
<body>
<div class="wrap">
	<header id="header">
		<%@ include file="/header.jsp" %>

	</header>
	<%@ include file="/menu.jsp" %>
	
		 </div>
	<div class="contentWrap" align="center">
		 
		 <form name="viewCandidatesForm">
	<table id="candidateSearch">
	<tr>
	
	<td>Select Batch</td><td>
	<select name="batchId" style="padding: 0px;">
	<option value="-1">--Select--</option>
	<c:forEach var="batch" items="${bList}">
	<option value="${batch.batchId}">${batch.batchId}-${ batch.batchName}</option>
	</c:forEach>
	</select></td>
	
	<td><input id="submitbtn" type="button" name ="Submit" value="Submit" onclick="getCandidateDetails()"></input></td>
</tr>
	
	</table>
</form>
		
	<div id="custData"></div>	 
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
