<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	/* look for all checkboes that have a class 'chk' attached to it and check if it was checked */
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
		var username = document.forms["addUserForm"]["username"].value;	
		var name = document.forms["addUserForm"]["name"].value;	
		var email = document.forms["addUserForm"]["email"].value;	
		var phone = document.forms["addUserForm"]["phone"].value;	
		var act = "saveUser";
		
	  var url = "UserController?action="+act+"&username="+username+"&name="+name+"&email="+email+"&phone="+phone+"&modules="+modulesselected;	  
	  xhttp.open("GET", url, true);
	  xhttp.send();
	
}



</script>
<title>Insert title here</title>
</head>
<body>
<form name="addUserForm">
	<h3 class="pull-left">New User</h3>
	<div class="clear"></div>
	<div class="errorMsgHandler"></div>
	<div class="clear"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>UserName</div>
	<div class="cimm_formInput"><input type="text" name="username" maxlength="20" style="width:90% !important;"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Name</div>
	<div class="cimm_formInput"><input type="text" name="name" maxlength="20" style="width:90% !important;"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Email</div>
	<div class="cimm_formInput"><input type="text" name="email" maxlength="20" style="width:90% !important;"></div>
	<div class="clear"></div>
	<div class="cimm_formLabel"><span style="color:red;">*</span>Phone</div>
	<div class="cimm_formInput"><input type="text" name="phone" maxlength="20" style="width:90% !important;"></div>
	<div class="clear"></div>
	<br><br>
	<div align="center" style="border-top:1px solid #CCC;">
	</div>
	
	<h3 class="pull-left">Assign Modules</h3> 
	<div class="clear"></div>
	<h3 class="pull-left">Select All   <input type="checkbox" class="moduleChkBx1" onchange="checkAll(this)" name="chk[]"></h3> 
	<div class="clear"></div>
	<div class="errorMsgHandler"></div>
	<div class="clear"></div>	
	<c:forEach var="modules" items="${mList}" >
		<div class="clear"></div>
			<input type="checkbox" class="moduleChkBx"  name=${modules.moduleName } value = ${modules.moduleId}>${modules.moduleName }</input>
	</c:forEach>
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