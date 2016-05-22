<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    <style type="text/css">
    	.rightMenuInnerBg .cimm_formInput select {
    width: 90%;  
    margin-left: 10px;
}
    
    </style>
    
<title>CETEST-List Questions</title>
</head>
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
		 		
		 	</div>
		 	<div class="clear"></div>
		 	<div class="topNav_right">
		 	.
		 	</div>
		 	
		 </div>
		 <div class="clear"></div>
		<div class="clear"></div>

	<div class="Cimm-Column-Wrap">
		<div class="leftContent responsiveLeftCol"> 
				<div class="Cimm-ColumnLeft-Content cimm_boxShadow leftSliderPop">
					<div class="cimmleftSliderContent">
					<div id="left-nav-container">
						<%@ include file="/Questions/QuestionManagementLeftMenu.jsp" %>
					</div>
					</div>
					</div>
				</div>
		
		<div class="bodyContent responsiveMiddleCol" id="questionListId">
				<%@ include file="/Questions/ListQuestions.jsp" %>
		</div>
		<div class="rightColumn" style="right: 0px;">
		<div class="Cimm-ColumnRight-Content cimm_boxShadow rightSliderPop">
			<div class="cimmRightSliderContent">
				<div class="rightMenuInnerBg">
				
						<%@ include file="/Questions/advancedSearch.jsp" %>
					
				</div>
			</div>
		</div>
		</div>
	
	</div>
	</div>
	<div class="footer">
		<span id="footerpanelId">
	<div class="footer">
	<div class="footertext">
		<%@ include file="/footer.jsp" %>
	</div>
	</div>
	</span>
	</div>

<script src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script src="./js/jquery.min(1).js"></script>
<script type="text/javascript" src="./js/menu-script.js"></script>
<script src="./js/responsiveslides.min.js"></script>
<script src="./js/subModal.js"></script>
	

</body>
</html>