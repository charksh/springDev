<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
    
<script src="//code.jquery.com/jquery-2.2.4.min.js"></script>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Insert title here</title>

<script>

	function test() {
	   	document.listForm.action = "<c:url value='/pension.do'/>";
	   	document.listForm.submit();	
	}
	
	function fn_excelUp(){
		 var form = document.excelUp;
		 form.action = "<c:url value='/excelUpload.do'/>";
		 form.target = "";
		 form.submit(); 
	}
	
</script>

</head>
<body>
	Hello World !!!!
	<form:form id="listForm" name="listForm">
		<ipuput type="text"/>
		<button id="ksh" onclick="test();">Click Me !!</button>
	</form:form>
	
	<form id="excelUp" name="excelUp" method="post" enctype="multipart/form-data">
	    <input type="file" id="excelFile" name="excelFile"/>
	    <input type="button" value="엑셀업로드" onClick="fn_excelUp()"/> 
	</form>
	
	<a href="<c:url value="/excelDownload.do"/>">Excel Export</a>
</body>
</html>