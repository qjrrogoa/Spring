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
<title>Write.jsp</title>

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
			<h1>한줄 메모 게시판<small>등록 페이지</small></h1>
			<div class="row">
				<div class="col-md-12">
					<form class="form-horizontal" method="post"
						action="<c:url value='/OneMemo/BBS/Edit.do'/>">
						<!-- 씨큐리티 적용:csrf취약점 방어용 -->
						<input type="hidden" name="no" value="${record.no}"/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="form-group">
							<label class="col-sm-2 control-label">제목</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="title" placeholder="제목을 입력하세요?" value="${record.title}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">내용</label>
							<!-- 중첩 컬럼 사용 -->
							<div class="col-sm-10">
								<div class="row">
									<div class="col-sm-8">
										<textarea class="form-control" name="content" rows="5" placeholder="내용 입력하세요">${record.content}</textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary">수정</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 실제 내용 끝 -->
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp"/>
	<!-- 푸터 끝 -->
</body>
</html>