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
<title>controller.jsp</title>

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
		<div class="page-header">
			<h1>스프링<small>컨트롤러</small></h1>
			<fieldset>
				<legend>컨트롤러 구현하기<span style="color: red;font-size: 1.5em;">${requestScope.message}${param.paramvar}</span></legend>
				<h2>하나의 컨트롤러 클래스로 여러 요청 처리하기(여러개의 컨트롤러 메소드 사용)</h2>
				<ul>
					<li><a href="<c:url value="/Controller/OneClass/List.do"/>">목록 요청</a></li>
					<li><a href="<c:url value="/Controller/OneClass/Edit.do"/>">수정 요청</a></li>
					<li><a href="<c:url value="/Controller/OneClass/Delete.do"/>">삭제 요청</a></li>
					<li><a href="<c:url value="/Controller/OneClass/View.do"/>">상세보기 요청</a></li>
				</ul>
				<h2>하나의 컨트롤러 메소드로 여러 요청 처리하기 - String[] 사용</h2>
				<ul>
					<li><a href="<c:url value="/Controller/OneMethod/List.do?paramvar=1"/>">목록 요청</a></li>
					<li><a href="<c:url value="/Controller/OneMethod/Edit.do?paramvar=2"/>">수정 요청</a></li>
					<li><a href="<c:url value="/Controller/OneMethod/Delete.do?paramvar=3"/>">삭제 요청</a></li>
					<li><a href="<c:url value="/Controller/OneMethod/View.do?paramvar=4"/>">상세보기 요청</a></li>
				</ul>
				<h2>하나의 컨트롤러 메소드로 여러 요청 처리하기 - URL패턴상의 패스로 사용</h2>
				<ul>
					<li><a href="<c:url value="/Controller/OneMethodNoparam/List.do"/>">목록 요청</a></li>
					<li><a href="<c:url value="/Controller/OneMethodNoparam/Edit.do"/>">수정 요청</a></li>
					<li><a href="<c:url value="/Controller/OneMethodNoparam/Delete.do"/>">삭제 요청</a></li>
					<li><a href="<c:url value="/Controller/OneMethodNoparam/View.do"/>">상세보기 요청</a></li>
				</ul>
			</fieldset>
		
		</div>
		
		
	</div>
	<!-- 실제 내용 끝 -->
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp"/>
	<!-- 푸터 끝 -->
</body>
</html>