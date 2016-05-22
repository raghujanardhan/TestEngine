
<!DOCTYPE html>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
	<title>CETEST</title>

	<meta character="utf-8">
	<meta name="viewport" content="width=device-width, initial-scal=1.0">
	<link rel="stylesheet" type="text/css" href="./css/mystyle.css">
	<link rel="stylesheet" href="css/responsiveslides.css">
    <link rel="stylesheet" href="TestEngine/css/themes.css">
    <link rel="stylesheet" href="css/subModal.css">

    <link rel="stylesheet" href="css/themes.css">
    <link rel="stylesheet" href="css/elements.css">
    <link rel="stylesheet" href="css/js_composer.css">
    <link rel="stylesheet" href="css/grid.css">
    <link rel="stylesheet" href="css/theme-style.css">
    
    <link rel="stylesheet" href="css/frontend.css">
    <link rel="stylesheet" href="css/cimmResposive.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/styles_CMS.css">
    <link rel="stylesheet" href="css/commonStyles.css">
    <link rel="stylesheet" href="css/CIMM2TouchMainStyle.css">
     <link rel="stylesheet" href="css/font-awesome.min.css">
     <link rel="stylesheet" href="css/jquery.gridster.css">
     <style type="text/css">
    .dashBoardWrap {
	min-height: 1700px;
	}
	
	 [data-col="3"] { left:1730px; }
 [data-col="2"] { left:870px; }
 [data-col="1"] { left:10px; }
 [data-row="16"] { top:2410px; }
 [data-row="15"] { top:2250px; }
 [data-row="14"] { top:2090px; }
 [data-row="13"] { top:1930px; }
 [data-row="12"] { top:1770px; }
 [data-row="11"] { top:1610px; }
 [data-row="10"] { top:1450px; }
 [data-row="9"] { top:1290px; }
 [data-row="8"] { top:1130px; }
 [data-row="7"] { top:970px; }
 [data-row="6"] { top:810px; }
 [data-row="5"] { top:650px; }
 [data-row="4"] { top:490px; }
 [data-row="3"] { top:330px; }
 [data-row="2"] { top:170px; }
 [data-row="1"] { top:10px; }
 [data-sizey="1"] { height:140px; }
 [data-sizey="2"] { height:300px; }
 [data-sizey="3"] { height:460px; }
 [data-sizey="4"] { height:620px; }
 [data-sizey="5"] { height:780px; }
 [data-sizey="6"] { height:940px; }
 [data-sizey="7"] { height:1100px; }
 [data-sizey="8"] { height:1260px; }
 [data-sizey="9"] { height:1420px; }
 [data-sizey="10"] { height:1580px; }
 [data-sizey="11"] { height:1740px; }
 [data-sizey="12"] { height:1900px; }
 [data-sizey="13"] { height:2060px; }
 [data-sizey="14"] { height:2220px; }
 [data-sizey="15"] { height:2380px; }
 [data-sizex="1"] { width:840px; }
 [data-sizex="2"] { width:1700px; }
	
	
	
	
	</style>
  
	<!--[if lt IE 9]>
		<script src="js/modernizr.custom.js"></script>
	<![endif]-->
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    


</head>
<script type="text/javascript">

function enableTab(id) {
	
    var el = document.getElementById(id);
    el.onkeydown = function(e) {
        if (e.keyCode === 9) { // tab was pressed

            // get caret position/selection
            var val = this.value,
                start = this.selectionStart,
                end = this.selectionEnd;

            // set textarea value to: text before caret + tab + text after caret
            this.value = val.substring(0, start) + '\t' + val.substring(end);

            // put caret at right position again
            this.selectionStart = this.selectionEnd = start + 1;

            // prevent the focus lose
            return false;

        }
    };
}

</script>

<body>
<div class="wrap">
	<header id="header">
		<%@ include file="/header.jsp" %>

	</header>
	<%@ include file="/menu.jsp" %>
	<!--Big navigation bar -->	
				
		
		 <!--navigation ends here-->
		 
		 <div class="contentWrap">
		 <form action="QuestionController" method= "post" enctype="multipart/form-data" >
		 <table id="candidateSearch" style="padding-left:20% !important; padding-right:  20% !important;"  >
<tr>
<td>Question Id</td>
<td><input type="text" name="questionid" value=<%=session.getAttribute("seqnum")%> readonly="readonly" id="question" style="background-color: cyan" ></td>
<td>Select Class</td><td>
	<select name="classId" style="padding: 0px;">
	<option value="-1">--Select--</option>
	<c:forEach var="class" items="${classInfo}">
	<option value="${class.classId}">${class.className}</option>
	</c:forEach>
	</select></td>
<td>Subject</td><td>
<select name="subjectId" style="padding: 0px;">
	<option value="-1">--Select--</option>
	<c:forEach var="subject" items="${sList}">
	<option value="${subject.subjectId}">${subject.subjectName}</option>
	</c:forEach>
	</select>

</td>
<td><input  id="submitbtn" type="submit" name="action" value="Add Question"></td>


	<c:if test="${requestScope.save ne null  }">
		<td><c:out value="${requestScope.save}"></c:out></td>
	</c:if>
</tr>

</table>
		 <div class="dashboardContentWrap" style="margin-top: 0;">
		 	<div class="clear"></div>
		 	<div class="dashboardContent gridster ready">
		 	<ul style="height: 960px; width: 1720px; position: relative;">
		 	<li class="cimm_boxShadow gs-w" data-row="1" data-col="1" data-sizex="2" data-sizey="2">
		 		
		 		<h4>Question</h4>
		 			<textarea id ="questionarea" onkeypress="enableTab('questionarea')" name="question" style="height:180px !important;width: 1650px !important;padding-left:20px; border:1px solid #eee !important; "></textarea>
		 			
		 			<table style="padding-left:30% !important; padding-right:  30% !important; ">
		 			<tr>
		 			<tr style="height: 5px;"></tr>
		 			<tr>
		 			<td><h4>Click to Upload the file</h4></td><td><input type="file" name="file_question"></td>
		 			<td><h4>Difficulty Level : </h4></td><td>
		 			<select name="difficulty" style="padding: 0px; width: 100px !important; background:#ededed !important;">
		 				<option value="-1">--Select--</option>
		 				<option value="1">Simple</option>
						<option value="2">Medium</option>
						<option value="3">Complex</option>
		 		</select>
		 			</td>
		 			
		 			</tr>
		 			
		 			
		 			</table>
		 			
		 		</li>
		 		
		 		<li class="cimm_boxShadow gs-w" data-row="3" data-col="1" data-sizex="1" data-sizey="2">
		 		<h4>Option 1</h4>
		 		<textarea id ="option1" onkeypress="enableTab('option1')" name="option1" style="height:180px !important;width: 800px !important;padding-left:20px; border:1px solid #eee !important; "></textarea>
		 		<table style="padding-left:15% !important; padding-right:  15% !important; ">
		 			<tr>
		 			<tr style="height: 5px;"></tr>
		 			<tr>
		 			<td><h4>Click to Upload the file</h4></td><td><input type="file" name="file_op1"></td>
		 			<td><h4>Mark As correct answer</h4></td><td>
		 			<input type="radio" name="answer" value="option1">
		 			</td>
		 			
		 			</tr>
		 			</table>
		 		
		 		
		 		</li>
		 		<li class="cimm_boxShadow gs-w" data-row="3" data-col="2" data-sizex="1" data-sizey="2">
		 		<h4>Option 2</h4>
		 		<textarea id ="option2" onkeypress="enableTab('option2')" name="option2" style="height:180px !important;width: 800px !important;padding-left:20px; border:1px solid #eee !important; "></textarea>
		 		<table style="padding-left:15% !important; padding-right:  15% !important; ">
		 			<tr>
		 			<tr style="height: 5px;"></tr>
		 			<tr>
		 			<td><h4>Click to Upload the file</h4></td><td><input type="file" name="file_op2"></td>
		 			<td><h4>Mark As correct answer</h4></td><td>
		 			<input type="radio" name="answer" value="option2">
		 			</td>
		 			
		 			</tr>
		 			</table>
		 		</li>
		 		<li class="cimm_boxShadow gs-w" data-row="5" data-col="1" data-sizex="1" data-sizey="2">
		 		<h4>Option 3</h4>
		 		<textarea id ="option3" onkeypress="enableTab('option3')" name="option3" style="height:180px !important;width: 800px !important;padding-left:20px; border:1px solid #eee !important; "></textarea>
		 		<table style="padding-left:15% !important; padding-right:  15% !important; ">
		 			<tr>
		 			<tr style="height: 5px;"></tr>
		 			<tr>
		 			<td><h4>Click to Upload the file</h4></td><td><input type="file" name="file_op3"></td>
		 			<td><h4>Mark As correct answer</h4></td><td>
		 			<input type="radio" name="answer" value="option3">
		 			</td>
		 			
		 			</tr>
		 			</table>
		 		</li>
		 		<li class="cimm_boxShadow gs-w" data-row="5" data-col="2" data-sizex="1" data-sizey="2">
		 		<h4>Option 4</h4>
		 		<textarea id ="option4" onkeypress="enableTab('option4')" name="option4" style="height:180px !important;width: 800px !important;padding-left:20px; border:1px solid #eee !important; "></textarea>
		 		<table style="padding-left:15% !important; padding-right:  15% !important; ">
		 			<tr>
		 			<tr style="height: 5px;"></tr>
		 			<tr>
		 			<td><h4>Click to Upload the file</h4></td><td><input type="file" name="file_op4"></td>
		 			<td><h4>Mark As correct answer</h4></td><td>
		 			<input type="radio" name="answer" value="option4">
		 			</td>
		 			
		 			</tr>
		 			</table>
		 		</li>
		 		
		 	</ul>
		 	
		 	
		 	</div>
		 	
		 	
		 	
		 	
		 	</div>
		 	
		 </div>
	
		 </form>
	<footer id="footer">
		<%@ include file="/footer.jsp" %>
	</footer>
</div>

<script src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="./js/menu-script.js"></script>
<script type="text/javascript" src="./js/responsiveslides.min.js"></script>
<script type="text/javascript" src="./js/jquery.gridster.js"></script>

</body></html>