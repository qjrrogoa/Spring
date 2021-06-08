<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>home.jsp</title>

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/styles/common.css"/>">
<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<!-- 네비게이션 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Top.jsp"/>
	<!-- 네비게이션 끝 -->
	<!-- 실제 내용 시작 -->
	<div class="container">
		<div class="page-header">
			<h1>스프링<small>프레임워크</small></h1>		
		</div>
		
		<p>현재 시간: ${serverTime }</p>
		<fieldset>
			<legend>static resource(정적자원:이미지,동영상,.css,.js파일등) 표시방법</legend>
			<h2>servlet-content.xml 파일에 설정된 resource태그 사용:webapp/resources디렉토리 아래에 리소스 저장해야 한다</h2>
			<!-- 빈 설정파일  servlet-context.xml의 매핑명으로 경로 설정-->
			<img src="<c:url value="/static/images/sumnail.png"/>" alt="매핑 이름으로"/>
			<h2>servlet-content.xml 파일에 설정된 resource태그 사용:webapp디렉토리 아래에 리소스 저장</h2>
			<img src="<c:url value="/images/sumnail.png"/>" alt="매핑 이름으로"/>
			<h2>&lt;resources&gt;태그 미 사용:&lt;default-servlet-handler/&gt;-디렉토리 구조로 매핑</h2>
			<img src="<c:url value="/images/sumnail.png"/>" alt="디렉토리구조로 접근"/>
		</fieldset>
		<fieldset>
			<legend>스프링 익히기</legend>
			<ul style="list-style: decimal;">
				<li><a href="<c:url value="/handlermapping.do"/>">핸들러 매핑</a></li>
				<li><a href="<c:url value="/controller.do"/>">컨트롤러</a></li>
				<li><a href="<c:url value="/viewresolver.do"/>">뷰 리졸버</a></li>
				<li><a href="<c:url value="/returntype.do"/>">컨트롤러 메소드의 반환타입</a></li>
				<li><a href="<c:url value="/injection.do"/>">Dependency Injection(의존성 주입)</a></li>
				<li><a href="<c:url value="/annotation.do"/>">Annotation</a></li>
				<li><a href="<c:url value="/database.do"/>">데이타베이스</a></li>
				<li><a href="<c:url value="/resource.do"/>">리소스</a></li>
				<li><a href="<c:url value="/validation.do"/>">유효성 검증</a></li>
				<li><a href="<c:url value="/dynamicsql.do"/>">마이바티스 동적 SQL</a></li>
				<li><a href="<c:url value="/ajax.do"/>">Ajax요청</a></li>
				<li><a href="<c:url value="/exception.do"/>">예외처리</a></li>
				<li><a href="<c:url value="/fileupdown.do"/>">파일 업로드/다운로드</a></li>
				<li><a href="<c:url value="/AOP.do"/>">AOP</a></li>
			</ul>
		</fieldset>
		
		
	</div>
	
	<!-- 실제 내용 끝 -->
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp"/>
	<!-- 푸터 끝 -->
</body>
</html>