<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" href="./css/style.css">
 <script type="text/javascript">
	function loadAddQuestions() {
		document.location.href = '${pageContext.request.contextPath}/QuestionController?action=loadAddQuestionsPage';
		}

 </script>
 
<title></title>
</head>
<body>
<ul>
<li class="has-sub open">
			<a href="" class="user-menu">
				<span class="menu-text">Question Management</span>
			</a>
			<ul class="leftnav-submenu">
				<li>
					<a href="#" onclick="loadAddQuestions();">Add Question</a>
				</li>
				
			</ul>
		</li>
	</ul>
</body>
</html>