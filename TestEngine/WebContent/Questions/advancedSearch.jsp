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



function searchQuestions() {
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	//location.reload(true);
	     document.getElementById("questionListId").innerHTML = xhttp.responseText;

	     
	    }
	  };	  
		var questionId = document.forms["searchQuestionform"]["questionId"].value;	
		var keyword = document.forms["searchQuestionform"]["keyword"].value;	
		var classId = document.forms["searchQuestionform"]["class"].value;	
		var subjectId = document.forms["searchQuestionform"]["subject"].value;
		var addedby = document.forms["searchQuestionform"]["addedby"].value;	
				
		var act = "searchQuestions";
		
	  var url = "QuestionController?action="+act+"&questionId="+questionId+"&keyword="+keyword+"&class="+classId+"&subject="+subjectId+"&addedby="+addedby;	  
	  xhttp.open("GET", url, true);
	  xhttp.send();
	
}



</script>
<title>Insert title here</title>
</head>
<body>
<form name="searchQuestionform">
	<h3 class="pull-left">Advanced Search</h3>
	<div class="clear"></div>
	<div class="errorMsgHandler"></div>
	<div class="clear"></div>
	<div class="clear"></div>
	
	<div class="cimm_formLabel"><span style="color:red;"></span>Question ID</div>
	<div class="cimm_formInput"><input type="text" name="questionId" maxlength="20" style="width: 90%" ></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;"></span>Keyword</div>
	<div class="cimm_formInput"><input type="text" name="keyword" maxlength="20" style="width: 90%"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;"></span>Class</div>
	<div class="cimm_formInput" >
			<select name="class">
				<option value="-1">--Select--</option>
				<c:forEach var="class" items="${cList}">
						<option value="${class.classId}">${class.className}</option>
				</c:forEach>
	
				
				</select>
				</div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;"></span>Subject</div>
			<div class="cimm_formInput">
			<select name="subject">
				<option value="-1">--Select--</option>
				<c:forEach var="subject" items="${sList}">
						<option value="${subject.subjectId}">${subject.subjectName}</option>
				</c:forEach>
	
				
				</select>
			
			</div>
	
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;"></span>Added by</div>
	<div class="cimm_formInput"><input type="text" name="addedby" maxlength="20" style="width: 90%" ></div>
	
	
	<div class="clear"></div>	
	
	<br><br>
	<div align="center" style="border-top:1px solid #CCC;">
	<br>
		<input type="button" value="Search" onclick="searchQuestions()">
	</div>
	




</form>
<script src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script src="./js/jquery.min(1).js"></script>
<script type="text/javascript" src="./js/menu-script.js"></script>
<script src="./js/responsiveslides.min.js"></script>
<script src="./js/subModal.js"></script>

</body>

</html>