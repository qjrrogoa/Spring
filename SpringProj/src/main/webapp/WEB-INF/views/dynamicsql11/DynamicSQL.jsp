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
<title>DynamicSQL.jsp</title>

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
	<!-- 네비게이션 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Top.jsp" />
	<!-- 네비게이션 끝 -->
	<!-- 실제 내용 시작 -->
	<div class="container">
		<div class="page-header">
			<h1>
				마이바티스<small>동적 SQL</small>
			</h1>
			<fieldset>

				<legend>
					동적 SQL<span style="color: red; font-size: 1.5em">${message}</span>
				</legend>
				<h2>if문</h2>
				<a href="<c:url value="/MyBatis/If1.do?title=제목"/>">WHERE절 일부에 사용 첫번째</a><br /> ​ 
				<a href="<c:url value="/MyBatis/If2.do?title=제목&name=이길동&content=내용"/>">WHERE절 일부에 사용 두번째</a>
				
				<h2>choose~when~otherwise</h2>
				<a href="<c:url value="/MyBatis/choose.do?title=제목&name=이길동&content=내용"/>">WHERE절 일부에 사용</a>
				<!-- 아래 3개의 파라미터가 전달 안되도 에러 안남 -->
				<h2>
					<where>
				</h2>
				<a href="<c:url value="/MyBatis/where.do?title=제목&name=이길동&content=내용"/>"><where></a>

				<h2>
					<trim>
				</h2>
				<!-- 아래 3개의 파라미터가 전달 안되도 에러 안남 -->

				<a href="<c:url value="/MyBatis/trim1.do?title=제목&name=이길동&content=내용"/>"><trim>검색문</a><br />
				<a href="<c:url value="/MyBatis/trim2.do?no=7&title=제목&name=이길동&content=내용"/>"><trim>수정문</a>
				<h2>
					<set>
				</h2>
				<!-- update 하고자 하는 칼럼을 동적으로 포함시키기 위해 사용 -->
				<a href="<c:url value="/MyBatis/set.do?no=6&title=제목"/>"><set>수정문</a>
				<h2>
					<foreach>
				</h2>
				<!-- update 하고자 하는 칼럼을 동적으로 포함시키기 위해 사용 -->
				<a href="<c:url value="/MyBatis/foreach.do"/>"><foreach></a>
			</fieldset>
		</div>
	</div>
	<!-- 실제 내용 끝 -->
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp" />
	<!-- 푸터 끝 -->
</body>
</html>