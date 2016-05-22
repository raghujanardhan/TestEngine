<%@page import="testengine.beans.BatchInfo"%>
<%@page import="testengine.utility.Suppliments"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="testengine.modal.*" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

   
</head>
<body>

<form name="candidateListForBatchForm" action="BatchController" method="post" onsubmit="return validateAllocateForm();">
<input type="hidden" name="action" value="allocateToBatch">
<%
BatchModal bm = new BatchModal();
ArrayList<BatchInfo> batchList =  bm.loadBatches();
request.setAttribute("bList", batchList);
%>
<table>
<tr>
<td>
Select Batch:</td>
<td>
<select name="batchId" style="padding: 0px;">
	<option value="-1">--Select--</option>
	<c:forEach var="batch" items="${ bList}">
	<option value="${batch.batchId}">${batch.batchId}|${batch.batchName}</option>
	</c:forEach>
	</select></td>
	</tr>
	</table>
<div id="gap"></div>
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
Select All <INPUT type="checkbox" onchange="checkAll(this)" name="chk[]" />
</th>

</tr>
</thead >
<tbody>
<%


String qry = "select * from candidatereg where classid=? and sectionid=? ";

try
{
	String c_id = request.getParameter("class_id");
	String s_id = request.getParameter("section_id");
	
	int class_id = Integer.parseInt(c_id);
	int section_id = Integer.parseInt(s_id);
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testengine","root","admin");
	PreparedStatement stat = con.prepareStatement(qry);
	stat.setInt(1, class_id);
	stat.setInt(2, section_id);
	ResultSet rs = stat.executeQuery();
	
	while(rs.next()){
		
		if(rs.getInt("allocationflag") == 0){
%>

<tr class="rich-table-row rich-table-firstrow ">
	<td class="rich-table-cell "><%=rs.getString("candidateid") %></td>
	<td class="rich-table-cell "><%=rs.getString("candidatename") %></td>
	<td class="rich-table-cell "><%=Suppliments.getClass(rs.getInt("classid")) %></td>
	<td class="rich-table-cell "><%=Suppliments.getSection(rs.getInt("sectionid")) %></td>
	<td class="rich-table-cell "><input type="checkbox" name="batchallocation" value='<%=rs.getString("candidateid") %>' ></td>
	
</tr>

<%
		}
		else { %>
		
		<tr class="rich-table-row rich-table-firstrow ">
	<td class="rich-table-cell "><%=rs.getString("candidateid") %></td>
	<td class="rich-table-cell "><%=rs.getString("candidatename") %></td>
	<td class="rich-table-cell "><%=Suppliments.getClass(rs.getInt("classid")) %></td>
	<td class="rich-table-cell "><%=Suppliments.getSection(rs.getInt("sectionid")) %></td>
	<td class="rich-table-cell ">Allocation Done</td>
	
</tr>
	
		
		<%
			
		
		}
	}
}
catch(NumberFormatException e){
	System.out.println("Exception is "+e);
}
%>
</tbody>
</table>

<div id="gap"></div>
<center>
		
		<input type="submit" value="Allocate to Batch" onclick="return validateAllocateForm();">
</center>

</form>
<script src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
<script src="./js/jquery.min(1).js"></script>
<script type="text/javascript" src="./js/menu-script.js"></script>
<script src="./js/responsiveslides.min.js"></script>
<script src="./js/subModal.js"></script>
</body>
</html>
