<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
		<!-- pagination -->
		<c:if test="${mission.id > 1}">
			<a href="${pageContext.request.contextPath}/mission/${mission.id - 1}" style="text-decoration: none">&lt;&lt;</a>
		</c:if>
		<span class="d"> <img src="<c:url value="/resources/img/ic/rank.png"/>" /> ${mission.name} <img src="<c:url value="/resources/img/ic/level.png"/>" /> ${mission.levelAvailable}+
		</span>
		<c:if test="${mission.id < amountMissions}">
			<a href="${pageContext.request.contextPath}/mission/${mission.id + 1}" style="text-decoration: none">&gt;&gt;</a>
		</c:if>
		<br />

		<!-- mission complete stars -->
		<c:forEach var="i" begin="1" end="${mission.maxComplete}" step="1">
			<c:choose>
				<c:when test="${i <= mission.amountComplete}">
					<img src="<c:url value="/resources/img/ic/star1.png"/>" />
				</c:when>
				<c:otherwise>
					<img src="<c:url value="/resources/img/ic/star2.png"/>" />
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	<hr />

	<c:forEach items="${mission.quests}" var="questItem">
		<c:choose>
			<c:when test="${mission.levelAvailable > hero.personage.level}">
				<div class="menu_x">${questItem.name}
			</c:when>
			<c:otherwise>
				<div class="b">
					<span class="quest">${questItem.name}</span>
			</c:otherwise>
		</c:choose>

		<table cellpadding="3" width="100%">
			<tr>
				<td align="left">
					<img src="<c:url value="/resources/img/ic/money.png"/>" /> <c:if test="${questItem.money > 0}">+</c:if>${questItem.money} 
					<img src="<c:url value="/resources/img/ic/authority.png"/>" /> +${questItem.authority}
					<img src="<c:url value="/resources/img/ic/energy.png"/>" /> -${questItem.energy}<br />
				</td>
				<td align="right">
					<c:choose>
						<c:when test="${questItem.completePercentage == 100}">
							<c:set value="prcnt_green" var="class_percent"/>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${questItem.completePercentage == 0}">
									<c:set value="prcnt_blue" var="class_percent"/>
								</c:when>
								<c:otherwise>
									<c:set value="prcnt_yellow" var="class_percent"/>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<div class="${class_percent}">
						${questItem.completePercentage}%
					</div>
				</td>
			</tr>
		</table>
		<c:forEach items="${questItem.requiredInventories}" var="requiredInventoryItem">
			<c:set value="${requiredInventoryItem.key}" var="inventory"/>
			<a href="${pageContext.request.contextPath}/thing/${inventory.id}">
				<img src="${pageContext.request.contextPath}/resources/img/${inventory.imagePath}"/> 
			</a>
			<c:choose>
				<c:when test="${hero.personage.inventories[inventory] >= requiredInventoryItem.value}">
					<c:set value="#ffffff" var="fontColor"/>
				</c:when>
				<c:otherwise>
					<c:set value="#ff0000" var="fontColor"/>
				</c:otherwise>
			</c:choose>
			<font color="${fontColor}">x${requiredInventoryItem.value}</font>
		</c:forEach>
		<br/>
		<c:forEach items="${questItem.bonusInventories}" var="bonusInventoryItem">
			<c:set value="${bonusInventoryItem.key}" var="inventory"/>
			<a href="${pageContext.request.contextPath}/thing/${inventory.id}">
				<img src="${pageContext.request.contextPath}/resources/img/${inventory.imagePath}"/> 
			</a>
			<c:choose>
				<c:when test="${bonusInventoryItem.value < 0 && hero.personage.inventories[inventory] < -bonusInventoryItem.value}">
					<c:set value="#ff0000" var="fontColor"/>
				</c:when>
				<c:otherwise>
					<c:set value="#ffffff" var="fontColor"/>
				</c:otherwise>
			</c:choose>
			<font color="${fontColor}">
				<c:if test="${bonusInventoryItem.value > 0}">+</c:if>
				${bonusInventoryItem.value}
			</font>
		</c:forEach>		
		<c:forEach items="${questItem.bonusPodgons}" var="bonusPodgonItem">
			<c:set value="${bonusPodgonItem.key}" var="podgon"/>

			<img src="${pageContext.request.contextPath}/resources/img/${podgon.imagePath}"/> 
			<c:choose>
				<c:when test="${bonusPodgonItem.value < 0 && hero.personage.podgons[podgon] < -bonusPodgonItem.value}">
					<c:set value="#ff0000" var="fontColor"/>
				</c:when>
				<c:otherwise>
					<c:set value="#ffffff" var="fontColor"/>
				</c:otherwise>
			</c:choose>
			<font color="${fontColor}">
				<c:if test="${bonusPodgonItem.value > 0}">+</c:if>
				${bonusPodgonItem.value}
			</font>
		</c:forEach>
		<br/>
		<div class="but1_2">
			<c:choose>
				<c:when test="${mission.levelAvailable > hero.personage.level}">
					<spring:message code="close" />
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${questItem.amountComplete >= questItem.clickToComplete}">
							<spring:message code="done" />		
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/mission/${mission.id}/quest/${questItem.id}"><spring:message code="do" /></a>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
		</div>
	</c:forEach>