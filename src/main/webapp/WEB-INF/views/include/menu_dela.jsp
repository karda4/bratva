<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="c">
	<table width="100%">
		<tr>
			<c:forEach items="${businessMenuList}" var="menuItem">
				<td align="center"><c:choose>
						<c:when test="${title == menuItem}">
							<span class="d"><spring:message code="${menuItem}" /></span>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/${menuItem}"><spring:message code="${menuItem}" /></a>
						</c:otherwise>
					</c:choose></td>
			</c:forEach>
		</tr>
	</table>
</div>
<hr />