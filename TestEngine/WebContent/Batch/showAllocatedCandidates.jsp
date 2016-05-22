


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
    <style type="text/css">
    	.rightMenuInnerBg .cimm_formInput select {
    width: 90%;  
    margin-left: 10px;
}
</head>
<script type="text/javascript">

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
		
		 <div id="content" align="center">
		  <h3><font color="blue">Candidate Allocation Status</font></h3>
		 <table id="listforbatch">
        <tr>
        	
            <th>Candidate Id</th>
            <th>Candidate Name</th>
            <th>Class</th>
            <th>Section</th>
            <th>Status</th>
            
        </tr>
 
        <c:forEach var="candidate" items="${canList}" >
            <tr>
                <td>${candidate.candidateId}</td>
                <td>${candidate.candidateName}</td>
                <td>${candidate.className}</td>
                <td>${candidate.sectionName}</td>
                <td>Allocated to batch ${requestScope.batchId}</td>
            </tr>
        </c:forEach>
    </table>
 
		 
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
