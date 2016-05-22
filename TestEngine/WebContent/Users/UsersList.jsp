<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>

<script type="text/javascript">
function deleteUser(userId){
	var delConfirmation = confirm("Do you want to delete the Candidate ?");
	if(delConfirmation == true) {
		
		//var url = "/Candidate/editCandidateDetails.jsp?action=deleteCandidate&candidateId="+candidateId;
		document.forms[0].action = "UserController?action=deleteUser&userId="+userId;
		document.forms[0].method = "post"; // "get"
		document.forms[0].submit();
	}
}
function editUser(userId){
	
		
		//var url = "/Candidate/editCandidateDetails.jsp?action=deleteCandidate&candidateId="+candidateId;
		document.forms[0].action = "UserController?action=editUser&userId="+userId;
		document.forms[0].method = "post"; // "get"
		document.forms[0].submit();
	
	
}
</script>

<link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/styles_CMS.css">
    <link rel="stylesheet" href="./css/commonStyles.css">
    <link rel="stylesheet" href="./css/CIMM2TouchMainStyle.css">
     <link rel="stylesheet" href="./css/font-awesome.min.css">
</head>
<body>
<table class="rich-table list_user">
<thead class="rich-table-thead">
<tr class="rich-table-subheader ">
<th class="rich-table-subheadercell  actionColWidth">
User Id
</th>
<th class="rich-table-subheadercell  actionColWidth">
Username
</th>
<th class="rich-table-subheadercell  actionColWidth">
Name
</th>
<th class="rich-table-subheadercell  actionColWidth">
Email
</th>
<th class="rich-table-subheadercell  actionColWidth">
Mobile
</th>
<th class="rich-table-subheadercell  actionColWidth">
Action
</th>
</tr>
</thead >
<tbody>
<c:forEach var="users" items="${uList}" >
<tr class="rich-table-row rich-table-firstrow ">
	<td class="rich-table-cell ">${users.userId } </td>
	<td class="rich-table-cell ">${users.userName } </td>
	<td class="rich-table-cell ">${users.name } </td>
	<td class="rich-table-cell ">${users.email } </td>
	<td class="rich-table-cell ">${users.mobile } </td>
	<td class="rich-table-cell "><img src="./images/edit.png" title="Edit" onclick="editUser('${users.userId}')" >&nbsp;&nbsp;&nbsp;<img src="./images/delete.png" title="Delete" onclick="deleteUser('${users.userId}')"></td>
</tr>
</c:forEach>
</tbody>
</table>
<div class="table-footer">
<div class="total-count">
	Showing ${requestScope.currentPage} out of ${requestScope.noOfPages} Pages 
</div>
<div class="pagination-right">
	<ul class="pagination-container">
		<li>
			<a class="fist-page" href="">
			<i class="pagi-icon fa fa-angle-double-left"></i>
			</a>
		</li>
		<li>
			<a class="fist-page" href="" >
				<i class="pagi-icon fa fa-angle-left"></i>
			</a>
		</li>
		<li>
		<a class="fist-page" href=""></a>
		</li>
		<li>
			<a class="fist-page" href="">
			<i class="pagi-icon fa fa-angle-right"></i>
			</a>
		</li>
		<li>
			<a class="fist-page" href="">
			<i class="pagi-icon fa fa-angle-double-right"></i>
			</a>
		</li>
		
	</ul>
</div>
</div>
</body>
</html>