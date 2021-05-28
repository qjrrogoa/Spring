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
<title>List.jsp</title>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
	a:hover{
	text-decoration: none;
	color:#106eea;
	}
</style>
</head>
<body>
	<!-- 네비게이션 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Top.jsp"/>
	<!-- 네비게이션 끝 -->
	<!-- 실제 내용 시작 -->
	<div class="container">
		<div class="page-header">
			<h1>한줄 메모 게시판<small>리스트</small></h1>
			<!-- 작성하기 버튼 -->
			<div class="row">
				<div class="col-md-12 text-right">
					<a href="<c:url value="/OneMemo/BBS/Write.do"/>" class="btn btn-success">등록</a>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12"></div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table
						class="table table-bordered table-hover table-condensed text-center">
						<!-- 테이블 컬럼폭은 col-*-*계열로 설정 -->
						<tr>
							<th class="col-md-1 text-center">번호</th>
							<th class="text-center">제목</th>
							<th class="col-md-1 text-center">작성자</th>
							<th class="col-md-2 text-center">작성일</th>
						</tr>
						<c:if test="${empty listPagingData.lists}" var="isEmpty">
							<tr>
								<td colspan="4">등록된 게시물이 없어요</td>
							</tr>
						</c:if>
						<c:if test="${not isEmpty}">
							<c:forEach items="${listPagingData.lists}" var="item" varStatus="loop">
		                     <tr>
		                        <td>${listPagingData.totalRecordCount - (((listPagingData.nowPage - 1) * listPagingData.pageSize) + loop.index)}</td>
		                        <td class="text-left"><a href="<c:url value="/OneMemo/BBS/View.do?no=${item.no}&nowPage="/><c:out value="${param.nowPage}" default="1"/>">${item.title}</a>
		                        &nbsp;<span class="badge">${item.commentCount}</span></td>
		                        <td>${item.name}</td>
		                        <td>${item.postDate}</td>
		                     </tr>
		                  </c:forEach>
						</c:if>
					</table>
				</div>
				<!-- column -->
			</div>
			<!-- row -->
			<!-- 페이징 -->
			<div class="row">
				<div class="col-md-12 text-center">${listPagingData.pagingString}</div>
			</div>
			<!-- 검색용 UI -->
			<div class="row">
				<div class="text-center">
					<form class="form-inline" method="post" action="<c:url value='/OneMemo/BBS/List.do'/>">
						<div class="form-group">
							<select name="searchColumn" class="form-control">
								<option value="title">제목</option>
								<option value="name">작성자</option>
								<option value="content">내용</option>
							</select>
						</div>
						<div class="form-group">
							<input type="text" name="searchWord" class="form-control" />
						</div>
						<button type="submit" class="btn btn-primary">검색</button>
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