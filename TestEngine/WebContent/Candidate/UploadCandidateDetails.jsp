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
    
</head>
<body>
<div class="wrap">
	<header id="header">
		<%@ include file="/header.jsp" %>

	</header>
	<%@ include file="/menu.jsp" %>
	
		 </div>
		 
		 
	
	<!--Big navigation bar -->	
				
		
		 <!--navigation ends here-->
		 
		 <div class="contentWrap" align="center">
		 
		 <div class="cimmLabel-box cimm_whiteBg" >
		 <div class="cimmLabel-header">Candidate Details Upload</div>
		 <div class="cimmLabel-dataBody" style="height:210px;">
		 <span style="font-size: 12px; color:#065b8d; font-weight: bold; ">Add the excel file and click on upload File</span>
		
		 
		 
		 <form action="CandidateContrller?action=uploadCandidateDetails" method="post" enctype="multipart/form-data">  
		 
		 
		 
		 <table border="1" >
		 
				<tr><td align="center"><input type="file" name="fname"/></td><td><input type="submit" value="Upload File" /></td></tr>

			</table>
				<font color="red">
				<%
					if(request.getAttribute("message")!= null){%>
						<%=request.getAttribute("message") %>

				<%
	
		}

	%>
</font>


 
</form>

		
		 </div>
		 </div>
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
