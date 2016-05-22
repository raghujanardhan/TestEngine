<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="testengine.beans.*" %>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/mystyle.css">

<script type="text/javascript">



function createBatch() {

	alert("Save..");
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	//location.reload(true);
	     document.getElementById("saveoutcome").innerHTML = xhttp.responseText;

	     
	    }
	  };	  
		var batchId = document.forms["createbatchform"]["batchId"].value;	
		var batchname = document.forms["createbatchform"]["batchname"].value;	
		var batchdesc = document.forms["createbatchform"]["batchdesc"].value;	
		var act = "addBatch";
		
	  var url = "BatchController?action="+act+"&batchId="+batchId+"&batchname="+batchname+"&batchdesc="+batchdesc;	  
	  xhttp.open("GET", url, true);
	  xhttp.send();
	
}



</script>

</head>
<body>
<form name="createbatchform">
	<h3 class="pull-left">New Batch</h3>
	<div class="clear"></div>
	<div class="errorMsgHandler"></div>
	<div class="clear"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Batch ID</div>
	<div class="cimm_formInput"><input type="text" name="batchId" maxlength="20" style="width: 90%" value=<%=session.getAttribute("seqnum") %> readonly="readonly" ></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Batch Name</div>
	<div class="cimm_formInput"><input type="text" name="batchname" maxlength="20" style="width: 90%"></div>
	<div class="clear"></div>

	<div class="cimm_formLabel"><span style="color:red;">*</span>Description</div>
	<div class="cimm_formInput"><textarea name="batchdesc" style="width: 90%"></textarea></div>
	<div class="clear"></div>
	<div class="clear"></div>	
	
	<br><br>
	<div align="center" style="border-top:1px solid #CCC;">
	<br>
		<input type="button" value="Save" onclick="createBatch()">
	</div>
	<div align="center" id="saveoutcome" style="color:red;">
	</div>




</form>

</body>

</html>