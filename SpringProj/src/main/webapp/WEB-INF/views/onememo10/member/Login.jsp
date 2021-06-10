<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- 실제 내용 시작 -->
<div class="container">
	<div class="page-header">
		<h1>
			한줄 메모 게시판<small>로그인 페이지</small>
		</h1>
	</div>
	<!-- 스프링 씨큐러티 사용하지 않을때 -->
	<%--	
	<c:if test="${! empty NotMember }">﻿
			<div class="row">
			<div class="col-xs-offset-1 col-xs-6 alert alert-warning fade in">
				<button class="close" data-dismiss="alert">
					<span>×</span>
				</button>
				${NotMember }
			</div>
		</div>
	</c:if>
	<div class="row">
		<c:if test="${not empty sessionScope.id }" var="isLogin">
			<div class="col-xs-offset-1 col-xs-6 alert alert-success">${sessionScope.id }님
				즐감하세요</div>
		</c:if>

		<c:if test="${not isLogin }">
			<div class="col-sm-12">
				<form class="form-horizontal" method="post"
					action="<c:url value='/OneMemo/Auth/LoginProcess.do'/>">
					<div class="form-group">
						<label for="id" class="col-sm-2 control-label">아이디</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="id" id="id"
								placeholder="아이디를 입력하세요">
						</div>
					</div>
					<div class="form-group">
						<label for="pwd" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="pwd" name="pwd"
								placeholder="비밀번호를 입력하세요">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-danger">로그인</button>
						</div>
					</div>
				</form>
			</div>
		</c:if>
	</div>
	--%>
	<!-- 스프링 씨큐러티 사용 -->
	
	<!-- 아이디와 비번 불일치로 인한 인증 실패 -->
	<c:if test="${not empty param.NotLogin }">
		<div class="row">
			<div class="col-xs-offset-1 col-xs-6 alert alert-warning fade in">
				<button class="close" data-dismiss="alert">
					<span>×</span>
				</button>
				아이디와 비번이 일치하지 않아요(인증실패)
			</div>
		</div>
	</c:if>
	
	<div class="row">
		<!--  인증된 사용자라면 즉 아이디와 비번일치 -->
		<sec:authorize access="isAuthenticated()">
			<div class="col-xs-offset-1 col-xs-6 alert alert-success"><sec:authentication property="principal.username"/> 님
				즐감하세요</div>
		</sec:authorize>
		<!-- 인증되지 않은 모든 사용자인 경우:로그인 폼 보여주기 -->
		<sec:authorize access="isAnonymous()">
			<div class="col-sm-12">
				<form class="form-horizontal" method="post"
					action="<c:url value='/OneMemo/Auth/LoginProcess.do'/>">
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}"/>
					<div class="form-group">
						<label for="id" class="col-sm-2 control-label">아이디</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="id" id="id"
								placeholder="아이디를 입력하세요">
						</div>
					</div>
					<div class="form-group">
						<label for="pwd" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-3">
							<input type="password" class="form-control" id="pwd" name="pwd"
								placeholder="비밀번호를 입력하세요">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-danger">로그인</button>
						</div>
					</div>
				</form>
			</div>
		</sec:authorize>
	</div>
	﻿
</div>
<!-- 실제 내용 끝 -->
