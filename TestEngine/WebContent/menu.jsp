<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body>
<a class="toggleMenu" href="" style="display: none;">Click here for menu</a>
			<ul class="nav">
				<c:forEach var="i" items="${assignedmodules}">				
				<c:if test="${i eq 1 }">
				<li class="" >
					<a href="TestConfiguration.jsp" class="parent">HOME</a>
					
				</li>
				</c:if>
				<c:if test="${i eq 2 }">
				<li class="" >
					<a href="#" class="parent">USER</a>
					<ul>
					
						<li class=""><a href="UserController?action=userManagement">User Management</a></li>
						<%--<li><a href="">Role Management</a></li> --%>
					</ul>
				</li>
				</c:if>
				<c:if test="${i eq 3 }">
				<li class="" >
					<a href="#" class="parent">CANDIDATE</a>
					<ul>
					
						<li class=""><a href="CandidateContrller?action=addCandidate">Add Candidate</a></li>
						<%--<li><a href="CandidateContrller?action=listCandidates">View All Candidates</a></li> --%>
			           
			            <li><a href="CandidateContrller?action=bulkUploadCandidates">Upload Candidate Details</a></li>
			            

					</ul>
				</li>
				</c:if>
				<c:if test="${i eq 4 }">
				<li class="">
					<a href="#" class="parent">BATCH</a>
					<ul>
						<li class=""><a href="BatchController?action=createBatch">Create Batch</a></li>
						<li><a href="BatchController?action=addCandidatesToBatch">Add Candidate to Batch</a></li>
						<li><a href="BatchController?action=viewCandidatesInBatch">View Candidates in Batch</a></li>
									            
					</ul>
				</li>
				</c:if>
				<c:if test="${i eq 5 }">
				<li class="">
					<a href="#" class="parent">SUBJECT</a>
					<ul>
						<li class=""><a href="SubjectController?action=addSubject">Add Subject</a></li>
									            
					</ul>
				</li>
				</c:if>
				<c:if test="${i eq 6 }">
				
				<li class="">
					<a href="#" class="parent">QUESTIONS</a>
					<ul>
						<li class=""><a href="QuestionController?action=loadAddQuestionsPage">Add Questions</a></li>
						<li><a href="QuestionController?action=listQuestionsPage">List Questions</a></li>	
						<li><a href="QuestionPaperController?action=loadUploadQuestionPaperPage">Upload Question Paper</a></li>								            
					</ul>
				</li>
				</c:if>
				<c:if test="${i eq 7 }">
				<li class="">
					<a href="#" class="parent">TEST</a>
					<ul>
						<li class=""><a href="">Create Test</a></li>
												            
					</ul>
				</li>
				</c:if>
				<c:if test="${i eq 8 }">
				<li class="">
					<a href="#" class="parent">REPORTS</a>
					<ul>
						<li class=""><a href="#">Batchwise Report</a></li>
						<li><a href="#">Subjectwise Report</a></li>	
						<li><a href="#">Studentwise Report</a></li>								            
					</ul>
				</li>
				</c:if>
				<c:if test="${i eq 9 }">
				<li class="">
					<a href="#" class="parent">DOWNLOADS</a>
					<ul>
						<li class=""><a href="#">Candidate Template</a></li>
						<li><a href="#">Question Templates</a></li>	
														            
					</ul>
				</li>
				</c:if>
				</c:forEach>
				</ul>
</body>


</html>