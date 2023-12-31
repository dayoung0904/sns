<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="h-100 header bg-info d-flex justify-content-between">
	<%-- logo --%>
	<div class="logo d-flex align-items-center">
		<h1 class="text-white ml-3">Marondalgram</h1>
	</div>

	<%-- 로그인 정보 --%>
	<div>
		<c:if test="${not empty userName}">
			<span>${userName}님 안녕하세요</span>
			<a href = "/user/sign-out">로그아웃</a>
		</c:if>
	</div>
</div>