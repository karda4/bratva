<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
<body>
	<jsp:include page="/WEB-INF/views/include/shapka.jsp" />
	
	<table width="100%">
		<c:forEach items="${menuList}" var="menuItem">
			<tr>
				<td><a href="${menuItem}"><spring:message code="${menuItem}" /></a></td>
			</tr>
		</c:forEach>
		<tr>
			<td><a href="logout"><spring:message code="logout" /></a></td>
		</tr>
	</table>
</body>
</html>