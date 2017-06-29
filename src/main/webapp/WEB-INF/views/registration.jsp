<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/WEB-INF/views/include/head-include.jsp" />
<body>
	<div>
		<font color="#ffffff"> <spring:message code="registration" />
		</font>
	</div>
	<hr />
	${registrationError}
	<form:form name="form2" method="post" action="registration" commandName="userForm">
		<table>
			<tr>
				<td><font color="#ff0000">*</font>
				<spring:message code="nick" /></td>
				<td><form:input path="nick" type="text" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:errors path="nick"/></td>
			</tr>
			<tr>
				<td><font color="#ff0000">*</font>
				<spring:message code="password" /></td>
				<td><form:input path="password" type="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:errors path="password"/></td>
			</tr>
			<tr>
				<td><spring:message code="phone" /></td>
				<td><form:input path="phone" type="text" /></td>
			</tr>
			<tr>
				<td><spring:message code="friend" />:</td>
				<td><input name="friend" type="text" /></td>
			</tr>
		</table>
		<div align="left">
			<font color="#ff0000"><spring:message code="reg_necessarily" /></font>
		</div>
		<br />
		<form:radiobutton path="sex" value="0"/>
		<spring:message code="sex0" />
		<form:radiobutton path="sex" value="1"/>
		<spring:message code="sex1" />		
		<br />
		<br />
		<input class="enter" type="submit" name="Submit" value="<spring:message code='registration'/>" />
	</form:form>
	<div class="but2">
		<a href="main"> <spring:message code="back" />
		</a>
	</div>
</body>
</html>