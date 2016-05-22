<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      <%@ page import="java.util.*" %>
      <%@ page import="testengine.beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

function checkAll(ele) {
    var checkboxes = document.getElementsByTagName('input');
    if (ele.checked) {
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].type == 'checkbox') {
                checkboxes[i].checked = true;
            }
        }
    } else {
        for (var i = 0; i < checkboxes.length; i++) {
            console.log(i)
            if (checkboxes[i].type == 'checkbox') {
                checkboxes[i].checked = false;
            }
        }
    }
}

function saveUser() {

var chkArray = [];
	
	/* look for all checkboes that have a class 'moduleChkBx' attached to it and check if it was checked */
	$(".moduleChkBx:checked").each(function() {
		chkArray.push($(this).val());
	});
	
	/* we join the array separated by the comma */
	var selected;
	modulesselected = chkArray.join(',') ;
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	//location.reload(true);
	     document.getElementById("saveoutcome").innerHTML = xhttp.responseText;

	     
	    }
	  };	  
	  var userid = document.forms["addUserForm"]["userid"].value;		  
		var username = document.forms["addUserForm"]["username"].value;	
		var name = document.forms["addUserForm"]["name"].value;	
		var email = document.forms["addUserForm"]["email"].value;	
		var phone = document.forms["addUserForm"]["phone"].value;	
		var act = "editandsaveUser";
		
	  var url = "UserController?action="+act+"&userid="+userid+"&username="+username+"&name="+name+"&email="+email+"&phone="+phone+"&modules="+modulesselected;	  
	  xhttp.open("GET", url, true);
	  xhttp.send();
}
</script>
<title>Insert title here</title>
</head>
<body>
<form name="addUserForm">
	<h3 class="pull-left">Edit User</h3>
	<div class="clear"></div>
	<div class="errorMsgHandler"></div>
	<div class="clear"></div>
	<div class="clear"></div>
	<c:forEach var="user" items="${uListEdit}" >
	<input type="hidden" name="userid" value=${user.userId}>
	<div class="cimm_formLabel"><span style="color:red;">*</span>UserName</div>
	<div class="cimm_formInput"><input type="text" name="username" value = ${user.userName } maxlength="20" style="width:90% !important;"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Name</div>
	<div class="cimm_formInput"><input type="text" name="name" value = ${user.name } maxlength="20" style="width:90% !important;"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Email</div>
	<div class="cimm_formInput"><input type="text" name="email" value = ${user.email } maxlength="20" style="width:90% !important;"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Phone</div>
	<div class="cimm_formInput"><input type="text" name="phone" value = ${user.mobile} maxlength="20" style="width:90% !important;"></div>
	</c:forEach>
	<div class="clear"></div>
	<br><br>
	<div align="center" style="border-top:1px solid #CCC;">
	</div>
	
	<h3 class="pull-left">Assigned Modules</h3> 
	<div class="clear"></div>
	<h3 class="pull-left">Select All   <input type="checkbox" class="moduleChkBx1" onchange="checkAll(this)" name="chk[]"></h3> 
	<div class="clear"></div>
	<div class="errorMsgHandler"></div>
	<div class="clear"></div>	
	<%--Putting scriptlets inside JSP is ugly but for time being this will be there..  --%>
	<%--
	<c:forEach var="modules" items="${mList}" >
		<div class="clear"></div>
		<c:forEach var="i" items="${modList}"  >
		<c:choose>
				<c:when test="${modules.moduleId eq i }">
					<input type="checkbox" class="moduleChkBx"  name=${modules.moduleName } value = ${modules.moduleId} checked="checked" >${modules.moduleName }							
				</c:when>	
				<c:otherwise>
					<input type="checkbox" class="moduleChkBx"  name=${modules.moduleName } value = ${modules.moduleId} >${modules.moduleName }
				</c:otherwise>	
			</c:choose>
		</c:forEach>
		<c:set var="i" value="${fn:length(modList)+1}"></c:set>		
	</c:forEach> --%>
	<%
	ArrayList<ModuleInfo> allList = new ArrayList();
	allList = (ArrayList<ModuleInfo>)request.getAttribute("mList");
	System.out.println("allList-->"+allList);
	ArrayList selectedList = new ArrayList();
	selectedList = (ArrayList)request.getAttribute("modList");
	System.out.println("selectedList-->"+selectedList);
	if(selectedList != null) {
	boolean match = false;	
	first : for(int i = 0;i<allList.size();i++) {
		second : for(int j = 0;j<selectedList.size();j++) {			
			if(allList.get(i).getModuleId() == (Integer)selectedList.get(j)){%>
			<input type="checkbox" class="moduleChkBx" name=<%=allList.get(i).getModuleName()%> value=<%=allList.get(i).getModuleId()%> checked="checked"><%=allList.get(i).getModuleName()%>
			<%
			match = true;
			continue first;
			}
			else {
				match = false;
				continue second;
			
			}
		}
		if(match == false) {%>
		<input type="checkbox" class="moduleChkBx" name=<%=allList.get(i).getModuleName()%> value=<%=allList.get(i).getModuleId()%> ><%=allList.get(i).getModuleName()%>
		<%
			
		}
	}
	}
	else
	{
		for(int i = 0;i<allList.size();i++) {%>
			<input type="checkbox" class="moduleChkBx" name=<%=allList.get(i).getModuleName()%> value=<%=allList.get(i).getModuleId()%> ><%=allList.get(i).getModuleName()%>
		<%}
	}
	
	 %>
	<br><br>
	<div align="center" style="border-top:1px solid #CCC;">
	<br>
		<input type="button" value="Save" onclick="saveUser()">
	</div>
	<div align="center" id="saveoutcome" style="color:red;">
	</div>




</form>
</body>
</html>