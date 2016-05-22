<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<title>CETEST</title>

	<meta character="utf-8">
	<meta name="viewport" content="width=device-width, initial-scal=1.0">
<link rel="stylesheet" type="text/css" href="./css/mystyle.css">
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
<body>
<div class="wrap">
	  <header id="header">
		<%@ include file="/header.jsp" %>
	</header>
	<%@ include file="/menu.jsp" %>
	</div>
	<div class="contentWrap">
	
	<form name="qpuploadform" id="qpupload" action="UploadQPController" method="post" enctype="multipart/form-data">
	<table id="candidateSearch">
	<tr>	
	<td>Question Paper Id</td><td><input type="text" name= "qpid" value="${sessionScope.seqnum}" style="background-color: cyan" readonly="readonly"></input></td>
	<td>Select Class</td><td>
	<select name="classId" style="padding: 0px;">
	<option value="-1">--Select--</option>
	<c:forEach var="class" items="${ cList}">
	<option value="${class.classId}">${class.className}</option>
	</c:forEach>
	</select></td>
	
	<td>Select Section</td>
	<td>
	<select name="sectionId" style="padding: 0px;">
	<option value="-1">--Select--</option>
	<c:forEach var="section" items="${ sList}">
	<option value="${section.sectionId}">${section.sectionName}</option>
	</c:forEach>
	</select></td>
	<td>Select Subject</td>
	<td>
	<select name="subjectId" style="padding: 0px;">
	<option value="-1">--Select--</option>
	<c:forEach var="subject" items="${ subList}">
	<option value="${subject.subjectId}">${subject.subjectName}</option>
	</c:forEach>
	</select>
	</td>
	<td>Number of Questions</td>
	<td><input type="text" name="totalnum" size="10"></input></td>
	<td>Upload Question Paper</td><td><input type="file" size="50" name="qpfile"></td>
	<td><input id="submitbtn" type="submit" value="Upload"></td>
	
</tr>
<tr align="center" style="width: 100%">

	<td align="center">
	<%if(request.getAttribute("uploadstatus")!= null) {
		%>
		<%=request.getAttribute("uploadstatus") %>
		<%
	}
	 %>
	</td>
	

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


</body>
</html>