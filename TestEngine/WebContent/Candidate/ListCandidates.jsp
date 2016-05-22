<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>

<script type="text/javascript">
function deleteCandidate(candidateId){
	alert("cid-->"+candidateId);
	var delConfirmation = confirm("Do you want to delete the Candidate ?");
	if(delConfirmation == true) {
		document.forms[0].action = "CandidateContrller?action=deleteCandidate&candidateid="+candidateId;
		document.forms[0].method = "post"; // "get"
		document.forms[0].submit();
	}
}
function editCandidate(candidateId){
		document.forms[0].action = "CandidateContrller?action=editCandidate&candidateid="+candidateId;
		document.forms[0].method = "post"; // "get"
		document.forms[0].submit();
	
	
}
</script>
</head>
<body>
<table class="rich-table list_user">
<thead class="rich-table-thead">
<tr class="rich-table-subheader ">
<th class="rich-table-subheadercell  actionColWidth">
Candidate Id
</th>
<th class="rich-table-subheadercell  actionColWidth">
Candidate Name
</th>
<th class="rich-table-subheadercell  actionColWidth">
Class
</th>
<th class="rich-table-subheadercell  actionColWidth">
Section
</th>
<th class="rich-table-subheadercell  actionColWidth">
Email
</th>
<th class="rich-table-subheadercell  actionColWidth">
Phone
</th>
<th class="rich-table-subheadercell  actionColWidth">
Address
</th>
<th class="rich-table-subheadercell  actionColWidth">
Action
</th>
</tr>
</thead >
<tbody>
<c:forEach var="candidate" items="${candList}" >
<tr class="rich-table-row rich-table-firstrow ">
	<td class="rich-table-cell ">${candidate.candidateId} </td>
	<td class="rich-table-cell ">${candidate.candidateName}</td>
	<td class="rich-table-cell ">${candidate.className} </td>
	<td class="rich-table-cell ">${candidate.sectionName}</td>
	<td class="rich-table-cell ">${candidate.email}</td>
	<td class="rich-table-cell ">${candidate.phone}</td>
	<td class="rich-table-cell ">${candidate.address}</td>
	<td class="rich-table-cell "><img src="./images/edit.png" title="Edit" onclick="editCandidate('${candidate.candidateId}')" >&nbsp;&nbsp;&nbsp;<img src="./images/delete.png" title="Delete" onclick="deleteCandidate('${candidate.candidateId}')"></td>
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
			<i class="pagi-icon fa fa-angle-double-left">First</i>
			</a>
		</li>
		<li>
			<a class="fist-page" href="" >
				<i class="pagi-icon fa fa-angle-left">Previous</i>
			</a>
		</li>
		<li>
		<a class="fist-page" href=""></a>
		</li>
		<li>
			<a class="fist-page" href="">
			<i class="pagi-icon fa fa-angle-right">Next</i>
			</a>
		</li>
		<li>
			<a class="fist-page" href="">
			<i class="pagi-icon fa fa-angle-double-right">Last</i>
			</a>
		</li>
		
	</ul>
</div>
</div>
<script src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script src="./js/jquery.min(1).js"></script>
<script type="text/javascript" src="./js/menu-script.js"></script>
<script src="./js/responsiveslides.min.js"></script>
<script src="./js/subModal.js"></script>
</body>
</html>