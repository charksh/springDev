<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="//code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- 
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.min.js"/>
<script src="//cdn.jsdelivr.net/jqgrid/4.6.0/jquery.jqGrid.min.js"/>
-->
     
<html>
<head><title>Home</title>
<script>
	var arrList;
	
	$(document).ready(function() {
		var iFunc = function() {
			alert("ksh");
		};
		
		var iFuncs = {
			a : function() {
				alert("a");
			},
			
			b : function() {
				alert("b");
			}
		};
		
		$("#ksh").click("on", function() {
			iFuncs.a();
			/*
			$("#gridKsh").jqGrid({
				  url :  "/house.do"
				, dataType : "json"
				, colNames : ['name']
				, colModel : [
				              {name:"aa", index:"aa", width:"50"}
				  ]
				, rowNum : 10
				, rowList : [10]
				, viewrecords : true
				, caption : "JSON Example"
			});
			*/
			
			var form = [
				{"aa":"aa", "bb":"bb"}
			];
			
			$.ajax({
				  url : "/house.do"
				, type : "POST"
				, dataType : "json"
				, contentType : "application/json; charset=UTF-8"
				, data : JSON.stringify(form)
				, success : function(data) {
					console.log(data);
					alert("success !!");
				}
				, error : function(a, b, error) {
					alert(error);
				}
			});
			
		});
	});
</script>
</head>
<body> 
<h1>
	Hello world! (안녕)
</h1>

<a href="<c:url value="/excelDownload.do"/>">Excel Export</a>

<input id="ksh" type="button" value="button"/><p>

<c:set var="cnt" value="100"/>
<c:set var="num" value="12345"/>

<c:forEach var="i" begin="1" end="10" step="1" >
	<c:out value="${i + cnt}"/>
</c:forEach>

<P>  The time on the server is ${serverTime}. </P>
<p> <fmt:formatNumber value="${num}" type="currency" currencySymbol="W"/> </p>
<p> <fmt:formatNumber value="${num}" type="number"/> </p>
<p> <fmt:formatNumber value="0.3" type="percent"/> </p>
<p> <fmt:formatNumber value="45678123.456" pattern="#,###.00"/> </p>

<p> <fmt:formatDate value="<%=new java.util.Date() %>" pattern="yyyy/MM/dd"/> </p>
<p> 
	<fmt:parseDate var="varDate" value="20171230" pattern="yyyyMMdd"/> 
	<fmt:formatDate value="${varDate}" pattern="yyyy-MM-dd"/>
</p>

<p> ${ fn:substring("12345",0,3) } </p>

요청 URI : ${ pageContext.request.protocol } <p> ${ pageContext.servletConfig.servletName } <p>
<%= request.getServerName() %> <p>

<span id="spanList"></span><p>

<c:forEach var="ksh" items="${list}">
	<c:out value="${ksh}"/>
</c:forEach>
<p>
${hash.CC}

<div id="gridKsh"></div>

</body>
</html>
