<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>Validation.jsp</title>

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<!-- 네비게이션 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Top.jsp" />
	<!-- 네비게이션 끝 -->
	<!-- 실제 내용 시작 -->
	<div class="container">
		<div class="page-header">
			<h1>
				스프링<small>Validation</small>
			</h1>
		</div>
		<fieldset>			﻿
			<legend>유효성 검증 </legend>
			<form action="<c:url value='/Validation/ValidationCheck.do'/>"	method="post">
				<table class="table table-bordered" bgcolor="gray" cellspacing="1" width="60%">
					<tr bgcolor="white">
						<td>이름</td>
						<!-- param.name으로 받을때는 한글 깨진다.설정 필요 -->
						<td><input type="text" name="name" value="${param.name}" />
							<!-- 에러 메시지 표시 <접두어:errors path="커멘트드객체명.속성명"/>
							단,커맨드 객체명은 소문자로 시작-->
						<span style="color: red; font-size: .8em">${nameError}<form:errors path="formCommand.name"/> </span></td>
					</tr>
					<tr bgcolor="white">
						<td>나이</td>
						<td><input type="text" name="years" value="${param.years}" />
							<span style="color: red; font-size: .8em">${yearsError }<form:errors path="formCommand.years"/></span></td>
					</tr>
					<tr bgcolor="white">
						<td>성별</td>
						<td><input type="radio" name="gender" value="남자" <c:if test="${param.gender=='남자' }">checked</c:if> />남자 
						<input	type="radio" name="gender" value="여자"	<c:if test="${param.gender=='여자' }">checked</c:if> />여자 
						<span style="color: red; font-size: .8em">${genderError }<form:errors path="formCommand.gender"/></span></td>
					</tr>

					<tr bgcolor="white">
						<td>관심사항</td>
						<td><input type="checkbox" name="inters" value="정치"
							<c:if test='${fn:indexOf(requestScope.inters,"정") !=-1 }'>checked</c:if> />정치
							<input type="checkbox" name="inters" value="경제"
							<c:if test='${fn:indexOf(inters,"경") !=-1 }'>checked</c:if> />경제
							<input type="checkbox" name="inters" value="스포츠"
							<c:if test='${fn:indexOf(inters,"스") !=-1 }'>checked</c:if> />스포츠
							<span style="color: red; font-size: .8em">${intersError}<form:errors path="formCommand.inters"/></span></td>
					</tr>
					<tr bgcolor="white">
						<td>학력</td>
						<td><select name="grade">
								<option value="">==학력선택==</option>
								<option value="초등학교"
									<c:if test="${fn:contains(param.grade,'초') }">selected</c:if>>초등학교</option>
								<option value="중학교"
									<c:if test="${fn:contains(param.grade,'중') }">selected</c:if>>중학교</option>
								<option value="고등학교"
									<c:if test="${fn:contains(param.grade,'고') }">selected</c:if>>고등학교</option>
								<option value="대학교"
									<c:if test="${fn:contains(param.grade,'대') }">selected</c:if>>대학교</option>
						</select> <span style="color: red; font-size: .8em">${gradeError }<form:errors path="formCommand.grade"/></span></td>

					</tr>
					<tr bgcolor="white">
						<td>자기소개</td>
						<td><textarea name="self" cols="30" rows="10">${param.self }</textarea>
							<span style="color: red; font-size: .8em">${selfError}<form:errors path="formCommand.self"/></span></td>
					</tr>
					<tr bgcolor="white" align="center">
						<td colspan="2"><input type="submit" value="확인" /></td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
	<!-- 실제 내용 끝 -->
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp" />
	<!-- 푸터 끝 -->
</body>
</html>