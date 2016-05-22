<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


    <style type="text/css">
    	.rightMenuInnerBg .cimm_formInput select {
   		 width: 90%;  
    	margin-left: 10px;
}
    
    </style>
    
</head>
<body>
<c:choose>
<c:when test="${requestScope.canList eq '-1' }">
<c:out value="No candidates are allocated for this batch"></c:out>
</c:when>
<c:otherwise>
	<table class="rich-table list_user" >
<thead class="rich-table-thead">
<tr class="rich-table-subheader ">
<th class="rich-table-subheadercell  actionColWidth">
Batch Id
</th>
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


</tr>
</thead >

<tbody>
<c:forEach var="candidate" items="${canList}" >
<tr class="rich-table-row rich-table-firstrow ">
	<td class="rich-table-cell ">${requestScope.batchId }</td>
	<td class="rich-table-cell ">${candidate.candidateId}</td>
	<td class="rich-table-cell ">${candidate.candidateName}</td>
	<td class="rich-table-cell ">${candidate.className}</td>
	<td class="rich-table-cell ">${candidate.sectionName}</td>
	<td class="rich-table-cell ">${candidate.email}</td>
	<td class="rich-table-cell ">${candidate.phone}</td>
	<td class="rich-table-cell ">${candidate.address}</td>
	
</tr>
</c:forEach>
</tbody>
</table>
</c:otherwise>
</c:choose>
</body>
</html>