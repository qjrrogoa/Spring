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
<title>Resource.jsp</title>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
	<!-- 네비게이션 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Top.jsp"/>
	<!-- 네비게이션 끝 -->
	<!-- 실제 내용 시작 -->
	<div class="container">
		<div class="page-header">
			<h1>스프링<small>Resource</small></h1>
		<fieldset>
			<legend>${value}리소스(.properties) ${resource}</legend>
			<a href="<c:url value="/Resource/Resource.do"/>">리소스</a>
		</fieldset>
		</div>
		<!-- 리소스파일 : .properties로 끝나는 파일로, 스프링 프레임워크에서 자원으로 사용된다.
		키값 = 값의 쌍으로 자원(상수)을 등록한다. 주석처리는 #을 사용.
		
		리소스 파일은 이클립스의 classpath인 src폴더 하위의 어디에든 위치하면 된다.
		(new → File → 확장자는 .properties로 생성) -->
	</div>
	<!-- 실제 내용 끝 -->
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp"/>
	<!-- 푸터 끝 -->
</body>
</html>