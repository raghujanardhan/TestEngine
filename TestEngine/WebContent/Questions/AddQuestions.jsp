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
<script type="text/javascript" src="bootstrap-filestyle.min.js"> </script>
<script type="text/javascript">
function validateBatchForm(){
	
	document.getElementById("batchnameerror").innerHTML="";
	
	var batchname = document.forms["createBatchForm"]["batchname"].value;
	if(batchname==null || batchname=="" || batchname.trim().length==0){
		document.getElementById("batchnameerror").innerHTML="Batch name required";
		
		return false;
	}
}

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
	
		 </div>
		
		 
	
	<!--Big navigation bar -->	
				
		
		 <!--navigation ends here-->
		 <form action="QuestionController" method= "post" enctype="multipart/form-data" >
		 <div id="content" align="center">
		 <table id="candidateSearch" >
<tr>
<td>Question Id</td>
<td><input type="text" name="questionid" value=<%=session.getAttribute("seqnum")%> readonly="readonly" id="question" style="background-color: cyan" ></td>
<td>Select Class</td><td>
	<select name="classId">
	<option value="-1">--Select--</option>
	<c:forEach var="class" items="${classInfo}">
	<option value="${class.classId}">${class.className}</option>
	</c:forEach>
	</select></td>
<td>Subject</td><td>
<select name="subjectId">
	<option value="-1">--Select--</option>
	<c:forEach var="subject" items="${sList}">
	<option value="${subject.subjectId}">${subject.subjectName}</option>
	</c:forEach>
	</select>

</td>
<td><input  id="submitbtn" type="submit" name="action" value="Add Question"></td>

</tr>

</table>
	
		 
		 <div id="questiondiv">
		 
		 <table id="individualtable">
		
		  <tr><th>Question</th></tr>
			
			<tr><td>Please enter the question</td></tr>
		 <tr>
		 <td><textarea rows="38" cols="80" id ="questionarea" onkeypress="enableTab('questionarea')" name="question"></textarea></td>
		 <td></td>
		 </tr>
		 <tr><td align="center"><input type="file" name="file_question"></td></tr>
		 <tr height="25px"><td></td></tr>
		 <tr><td align="center">Difficulty Level&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 <select name="difficulty">
		 <option value="-1">--Select--</option>
		 <option value="1">Simple</option>
		 <option value="2">Medium</option>
		 <option value="3">Complex</option>
		 
		 
		 </select> </td>
		 
		 </tr>
		  <tr height="140px"><td></td></tr>
		 </table>
		 
		 
		 </div>
		 
		 <div id="answerdiv">
		 <table id="individualtable" >
		 <tr>
		
		 <th>Enter Options</th>
		 <th>Associate file</th>
		 <th>Correct Option</th>
		 
		 </tr>
		 <tr><td>Option 1</td></tr>
		 <tr>		 
		 <td><textarea rows="11" cols="90" id ="option1" onkeypress="enableTab('option1')" name="option1"></textarea></td>
		 <td align="center"><input type="file" name="file_op1"></td>
		 <td align="center"><input type="radio" name="answer" value="option1"></td>
		 
		 </tr>
		 
		 <tr><td>Option 2</td></tr>
		 <tr>		 
		 <td><textarea rows="11" cols="90" id ="option2" onkeypress="enableTab('option2')" name="option2"></textarea></td>
		 <td><input type="file" name="file_op2"></td>
		 <td align="center"><input type="radio" name="answer" value="option2"></td>
		 </tr>
		 
		 <tr><td>Option 3</td></tr>
		 <tr>		 
		 <td><textarea rows="11" cols="90" id ="option3" onkeypress="enableTab('option3')" name="option3"></textarea></td>
		 <td><input type="file" name="file_op3"></td>
		 <td align="center"><input type="radio" name="answer" value="option3"></td>
		 </tr>
		 <tr><td>Option 4</td></tr>
		 <tr>		 
		 <td><textarea rows="11" cols="90" id ="option4" onkeypress="enableTab('option4')" name="option4"></textarea></td>
		 <td><input type="file" name="file_op4"></td>
		 <td align="center"><input type="radio" name="answer" value="option4"></td>
		 </tr>
		 
		 
		 </table>
		 </div>

		 
		 </div>
		
	    </form>
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
