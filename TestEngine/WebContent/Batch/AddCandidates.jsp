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
    
</head>
<script type="text/javascript">

function validateAllocateForm()
{		
	var checked=0;
	var batchId = document.forms["candidateListForBatchForm"]["batchId"].value;
	if(batchId == "-1"){
	alert("Please select the Batch Id"); 
	return false;
	
	}
	var checkboxes = document.getElementsByName("batchallocation");
	for(var i = 0;i < checkboxes.length; i++){
		
		if(checkboxes[i].checked)
		{
			checked = 1;
			break;
		}
		
	}
	if(checked == 0) {
		alert("Please select a candidate for whoom batch needs to be allocated");
		return false;
		
	}
	
}
function checkAll(ele) {
    var checkboxes = document.getElementsByTagName('input');
    if (ele.checked) {
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].type == 'checkbox') {
                checkboxes[i].checked = true;
            }
        }
    } else {
        for (var i = 0; i < checkboxes.length; i++) {
            console.log(i)
            if (checkboxes[i].type == 'checkbox') {
                checkboxes[i].checked = false;
            }
        }
    }
}

function getCandidateDetails()
{
	 
	 var classId = document.forms["addCandidatesForm"]["classId"].value;
	 var sectionId = document.forms["addCandidatesForm"]["sectionId"].value;
	 
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
	 xmlhttp.open("GET","Batch/candidateListForBatch.jsp?class_id="+classId+"&section_id="+sectionId,true);
	 xmlhttp.send();
}






function validateBatchForm(){
	
	document.getElementById("batchnameerror").innerHTML="";
	
	var batchname = document.forms["createBatchForm"]["batchname"].value;
	if(batchname==null || batchname=="" || batchname.trim().length==0){
		document.getElementById("batchnameerror").innerHTML="Batch name required";
		
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
	<div class="contentWrap" align="center">
	
	<form name="addCandidatesForm">
	<table id="candidateSearch">
	<tr>
	
	<td>Select Class</td><td>
	<select name="classId" style="padding: 0px;">
	<option value="-1">--Select--</option>
	<c:forEach var="class" items="${ cList}">
	<option value="${class.classId}">${class.className}</option>
	</c:forEach>
	</select></td>
	
	<td>Select Section</td><td>
	<select name="sectionId" style="padding: 0px;">
	<option value="-1">--Select--</option>
	<c:forEach var="section" items="${ sList}">
	<option value="${section.sectionId}">${section.sectionName}</option>
	</c:forEach>
	</select></td>
	<td><input type="button" name ="Submit" value="Submit" onclick="getCandidateDetails()"></input></td>
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
