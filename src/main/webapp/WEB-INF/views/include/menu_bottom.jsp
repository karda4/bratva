<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<br />
<br />
<div align="center">
	<c:if test="${menuBottomBack}">
		<div class="but1_2">
			<a href="${menuBottomBackHref}"><spring:message code="back" /></a>
		</div>
	</c:if>
	<c:if test="${menuBottomMenu}">
		<div class="but1_2">
			<a href="${pageContext.request.contextPath}/menu"><spring:message code="menu" /></a>
		</div>
	</c:if>
	<c:if test="${menuBottomHelp}">
		<div class="but1_2">
			<a href="${pageContext.request.contextPath}/help"><spring:message code="help" /></a>
		</div>
	</c:if>
</div>
