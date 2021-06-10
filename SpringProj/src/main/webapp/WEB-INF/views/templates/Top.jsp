<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	function logout(){
		//location.replace('<c:url value="/OneMemo/Auth/Logout.do"/>');
		$("#logoutForm").submit();//스프링 씨큐리티 사용시 csrf를 활성화 시킬때
	}

</script>
<style>
body {
	padding-top: 50px;
}
</style>

<!-- 로그아웃 GET방식으로 POST방식으로 변경하기 위한 폼태그 추가 -->
<form id="logoutForm" method="post" action="<c:url value="/OneMemo/Auth/Logout.do"/>">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}"/>
</form>
<!-- 상단메뉴 시작 -->
<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">	
	<div class="container-fluid">
		<div class="navbar-header">
			<!--화면 크기가 작을때 보여지는 네비게이션바(모바일용)  -->
			<button class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#collapse-menu">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- CI표시 -->
			<a class="navbar-brand" href="<c:url value="/DataRoom/Index.kosmo"/>"><span
				class="glyphicon glyphicon-education"></span> <strong>KOSMO<strong></strong></a>
		</div>
		<!-- 화면 크기가 클때 상단에 보여지는 네비게이션바(데스크탑용) -->
		<div class="collapse navbar-collapse" id="collapse-menu">
			<!-- 네비게이션바에 폼 추가 -->
			<form class="navbar-form navbar-right">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="검색" />
				</div>
				<button class="btn btn-info">확인</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/"/>">HOME</a></li>
				<!-- 스프링 씨큐러티 사용하지 않을때 -->
				<%-- 
				<c:if test="${not empty sessionScope.id }" var="isLogin">
					<li><a href="javascript:logout()">로그아웃</a></li>
				</c:if>
				<c:if test="${not isLogin }">					
					<li><a href="<c:url value="/OneMemo/Auth/Login.do"/>">로그인</a></li>
				</c:if>
				--%>
				<!-- 
				     스프링 씨큐리티 사용시 :단,<security:csrf disabled="false" />
				     설정시 로그아웃을 GET방식이 아닌 POST방식으로 해야한다
				    true로 지정시에는 GET방식이어도 상관없다(CSRF공격은 막지 못한다)
				-->
				<sec:authorize access="isAuthenticated()">
					<li><a href="javascript:logout()">로그아웃</a></li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<li><a href="<c:url value="/OneMemo/Auth/Login.do"/>">로그인</a></li>
				</sec:authorize>
				<li><a href="<c:url value="/OneMemo/BBS/List.do"/>">한줄 댓글 게시판</a></li>
				<li><a href="#">공지사항</a></li>
				
			</ul>
		</div>
	</div>
</nav>
<!-- 상단메뉴 끝 -->
