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
    <link rel="stylesheet" href="./css/CIMM2TouchMainStyle.css">
     <link rel="stylesheet" href="./css/font-awesome.min.css">
     
     
    
    
    
</head>
<script type="text/javascript">
	function searchUser() {

			var searchParam = document.getElementById("searchparam").value;
			 
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
			     document.getElementById("userListId").innerHTML=xmlhttp.responseText;
			     }
			   }
			 xmlhttp.open("GET","UserController?action=searchUser&searchParam="+searchParam,true);
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
		<div class="contentWrap">
			<div class="topContent">
		 	<div class="topNav_left">
		 	.
		 	</div>
		 	
		 	<div class="topNav_middle">
		 		<span class="view-fieldsetValue">Enter Username/Id to Search</span> &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="searchparam" id ="searchparam" style="width: 300px"> <input type="submit" value="Search" onclick="searchUser()">&nbsp;&nbsp; <span class="view-fieldsetValue">Total Count : ${requestScope.totalRecords}</span>
		 	</div>
		 	<div class="clear"></div>
		 	<div class="topNav_right">
		 	.
		 	</div>
		 	
		 </div>
		<div class="clear"></div>
		 <div class="Cimm-Column-Wrap">
				<div class="leftContent responsiveLeftCol"> 
				<div class="Cimm-ColumnLeft-Content cimm_boxShadow leftSliderPop">
					<div class="cimmleftSliderContent">
					<div id="left-nav-container">
					<%@ include file="/Users/UserManagementLeftMenu.jsp" %>
					</div>
					</div>
					</div>
				</div>
				
				<div class="bodyContent responsiveMiddleCol" id="userListId">
					<%@ include file="/Users/UsersList.jsp" %>
				</div>
			
				<div class="rightContent responsiveRightCol"> 
					<div class="rightMenuInnerBg mainMinHeight cimm_boxShadow">
					<div class="cimmrightSliderContent">
					<div id="right-nav-container">
					<c:choose>
				
					<c:when test="${requestScope.fromWhere eq 'fromEditUser' }">
						<%@ include file="/Users/EditUser.jsp" %>
					</c:when>
					<c:otherwise>
						<%@ include file="/Users/AddUser.jsp" %>
					</c:otherwise>
					</c:choose>
					</div>
					</div>
					</div>
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

