<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>handlermapping.jsp</title>

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

</head>
<body>
	<!-- 네비게이션 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Top.jsp"/>
	<!-- 네비게이션 끝 -->
	<!-- 실제 내용 시작 -->
	<div class="container">
		<div class="jumbotron">
			<h1>스프링<small>핸들러 매핑</small></h1>
		</div>
		
		<fieldset>
			<legend>HandlerMapping<span style="color: red;font-size: 1.4em">${message}</span></legend>
			<h2>디폴트(기본) 핸들러 매핑</h2>
			<ul>
				<li><a href="<c:url value='/HandlerMapping/BeanNameUrl.do'/>">BeanNameUrlHandlerMapping</a></li>
				<li><a href="<c:url value='/HandlerMapping/Annotation.do'/>">DefaultAnnotationHandlerMapping</a></li>
			</ul>
			<h2>SimpleUrlHandlerMapping(기본 핸들러 매핑이 아님)</h2>
			<ul>
				<li><a href="<c:url value='/HandlerMapping/SimpleUrlFirst.do'/>">SimpleFirst.do</a></li>
				<li><a href="<c:url value='/HandlerMapping/SimpleUrlSecond.do'/>">SimpleSecond.do</a></li>
			</ul>
		</fieldset>
	</div>
	<!-- 실제 내용 끝 -->
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp"/>
	<!-- 푸터 끝 -->
</body>
</html>