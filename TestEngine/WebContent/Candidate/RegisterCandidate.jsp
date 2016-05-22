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



function saveUser() {

	alert("Save..");
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	//location.reload(true);
	     document.getElementById("saveoutcome").innerHTML = xhttp.responseText;

	     
	    }
	  };	  
		var canditateId = document.forms["registercandidateform"]["canditateId"].value;	
		var canditatename = document.forms["registercandidateform"]["canditatename"].value;	
		var classId = document.forms["registercandidateform"]["class"].value;	
		var section = document.forms["registercandidateform"]["section"].value;
		var canditateaddress = document.forms["registercandidateform"]["canditateaddress"].value;	
		var canditateemail = document.forms["registercandidateform"]["canditateemail"].value;
		alert("Email-->"+canditateemail);	
		var canditatephone = document.forms["registercandidateform"]["canditatephone"].value;		
		var act = "registerCandidate";
		
	  var url = "CandidateContrller?action="+act+"&canditateId="+canditateId+"&canditatename="+canditatename+"&class="+classId+"&section="+section+"&canditateaddress="+canditateaddress+"&canditateemail="+canditateemail+"&canditatephone="+canditatephone;	  
	  xhttp.open("GET", url, true);
	  xhttp.send();
	
}



</script>
<title>Insert title here</title>
</head>
<body>
<form name="registercandidateform">
	<h3 class="pull-left">New Candidate</h3>
	<div class="clear"></div>
	<div class="errorMsgHandler"></div>
	<div class="clear"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Candidate ID</div>
	<div class="cimm_formInput"><input type="text" name="canditateId" maxlength="20" style="width: 90%" value=<%=session.getAttribute("seqnum") %> readonly="readonly" ></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Candidate Name</div>
	<div class="cimm_formInput"><input type="text" name="canditatename" maxlength="20" style="width: 90%"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Class</div>
	<div class="cimm_formInput" ><select name="class">
				<option  value="-1" >-Select-</option>
				<% ArrayList<CandidateInfo> classList = (ArrayList<CandidateInfo>)application.getAttribute("classInfo");
				for(int i = 0;i<classList.size();i++){
					%>
				<option value=<%=classList.get(i).getClassId() %>><%=classList.get(i).getClassName() %></option>
				
				
				<%} %>
				</select></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>section</div>
			<div class="cimm_formInput"><select name="section"  >
			<option value="-1">-Select-</option>
				<option value="1">A Section</option>
				<option value="2">B Section</option>
				</select></div>
	
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Address</div>
	<div class="cimm_formInput"><textarea name="canditateaddress" style="width: 90%"></textarea></div>
	<div class="clear"></div>
	
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Email</div>
	<div class="cimm_formInput"><input type="text" name="canditateemail" maxlength="20" style="width: 90%"></div>
	<div class="clear"></div>
	
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Mobile</div>
	<div class="cimm_formInput"><input type="text" name="canditatephone" maxlength="20" style="width: 90%" ></div>
	<div class="clear"></div>
	
	
	<div class="clear"></div>	
	
	<br><br>
	<div align="center" style="border-top:1px solid #CCC;">
	<br>
		<input type="button" value="Save" onclick="saveUser()">
	</div>
	<div align="center" id="saveoutcome" style="color:red;">
	</div>




</form>

</body>

</html>