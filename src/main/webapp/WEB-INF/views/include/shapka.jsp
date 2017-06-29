<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<table width="100%">
	<tr>
		<td align="center"><img src="<c:url value="/resources/img/ic/level.png"/>" /> ${hero.personage.level}</td>
		<td align="center"><img src="<c:url value="/resources/img/ic/health.png"/>" /> ${hero.personage.health}</td>
		<td align="center"><img src="<c:url value="/resources/img/ic/energy.png"/>" /> ${hero.personage.energy}</td>
		<td align="center"><img src="<c:url value="/resources/img/ic/power.png"/>" /> ${hero.personage.power}</td>
		<td align="center"><img src="<c:url value="/resources/img/ic/money.png"/>" /> ${hero.personage.money}</td>
	</tr>
</table>
<hr />
<%-- <c:if test="${not message.available}">
	<c:if test="${message.setTreatEnergyByPodgon}">
		<spring:message code="mess5" /> <a href=""><spring:message code="treat2" /> -1 ${treatPodgon.name}</a>
	</c:if>
</c:if> --%>
<c:if test="${hero.personage.reachNewLevel}">
	<spring:message code="mess14" /> <a href=""><spring:message code="up" /></a>
</c:if>
<div>
	<font color="#ffffff"><spring:message code="${title}" /></font>
</div>
<hr />