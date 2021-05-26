<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
@SessionAttributes("id")와 컨틀로러 메소드의 인자로 @ModelAttribute("id") String id 사용시
아래 로그인 여부 체크를 위한 인클루드 불필요
 -->
<%--@ include file="/WEB-INF/views/common/IsLogin.jsp" --%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>View.jsp</title>

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
				한줄 메모 게시판<small>상세보기 페이지</small>
			</h1>
		</div>
		<div class="row">
			<div class="col-md-offset-2 col-md-8">
				<table class="table table-bordered table-striped">
					<tr>
						<th class="col-md-2 text-center">번호</th>
						<td>${record.no}</td>
					</tr>
					<tr>
						<th class="text-center">제목</th>
						<td>${record.title}</td>
					</tr>
					<tr>
						<th class="text-center">작성자</th>
						<td>${record.name}</td>
					</tr>
					<tr>
						<th class="text-center">등록일</th>
						<td>${record.postDate}</td>
					</tr>
					<tr>
						<th class="text-center" colspan="2">내용</th>
					</tr>
					<tr>
						<td colspan="2">${record.content}</td>
					</tr>
				</table>
			</div>
		</div>
		<!-- row -->
		<div class="row">
			<div class="col-md-offset-2 col-md-8">
				<!-- .center-block 사용시 해당 블락의 크기를 지정하자 -->
				<ul id="pillMenu" class="nav nav-pills center-block"
					style="width: 200px; margin-bottom: 10px">
					
					<c:if test="${sessionScope.id == record.id }">
						<li><a
							href="<c:url value='/OneMemo/BBS/Edit.do?no=${record.no}'/>"
							class="btn btn-success">수정</a></li>
						<li><a href="javascript:isDelete();" class="btn btn-success">삭제</a></li>
					</c:if>
					
					<li><a
						href="<c:url value='/OneMemo/BBS/List.do?nowPage=${param.nowPage}'/>"
						class="btn btn-success">목록</a></li> 
				</ul>
			</div>
		</div>	​

		<!-- row -->
		<div class="row">
			<div class="col-md-12">
				<div class="text-center">​ ​

					<!-- 한줄 코멘트 입력 폼-->
					<!-- 마이바티스의 리절트맵 테스트용:<%--${record.comments.size()} --%> -->
					<h2>한줄 댓글 입력 폼</h2>
					<form class="form-inline" id="frm">
						<!-- 씨큐리티 적용:csrf취약점 방어용 -->
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> 
							<input type="hidden" name="no"	value="${record.no}" />
						<!-- 수정 및 삭제용 파라미터 -->
						<input type="hidden" name="lno" /> 
						<input placeholder="댓글을 입력하세요"
							id="title" class="form-control" type="text" size="50"
							name="linecomment" /> 
							<input class="btn btn-success" id="submit"
							type="button" value="등록" /> 
					</form>
					<div class="row">
						<!-- 한줄 코멘트 목록-->
						<!-- ajax로 아래에 코멘트 목록 뿌리기 -->
						<div id="comments" class="col-md-offset-3 col-md-6">
							<!-- 아래 태그는 마이바티스 resultMap의 collection태그사용시 -->
							<h2>한줄 댓글 목록</h2>
							<table class='table table-bordered'>
								<tr >
									<th class='text-center col-md-2'>작성자</th>
									<th class='text-center'>코멘트</th>
									<th class='text-center col-md-2'>작성일</th>
									<th class='text-center col-md-2'>삭제</th>
									
								</tr>
								<tbody class="comment-title">								
									<c:if test="${not empty record.comments}">
										<c:forEach var="comment" items="${record.comments}">
											<tr>
												<td>${comment.name }</td>
												<td>
												<c:if test="${sessionScope.id==comment.id }">
													<a href="#">${comment.lineComment }</a>
												</c:if>
												<c:if test="${sessionScope.id!=comment.id }">
													${comment.lineComment }
												</c:if>
												</td>
												<td>${comment.lpostDate }</td>
												<td>삭제</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 실제 내용 끝 -->
	<script>
		//showComments();//마이바티스의 ResultMap 태그의 collection태그 적용시는 주석
	
		//현재 글번호에 대한 모든 댓글을 요청하는 함수
		function showComments(){
			
			//contentType키를 생략시 디폴트:x-www-form-urlencoded
			//post방식이라 요청바디에 데이타가 key=value형태로 변환되서 전송 
			//예: no=2
			$.ajax({
				"url":"<c:url value='/OneMemo/Comment/List.do'/>",
				"data":{"no":"${record.no}"},			
				"dataType":"json",
				"type":"post",
				"success":showComments_,
				"error":function(e){
					console.log(e);
				}
				
			});
			
		}////////////
		//실제 댓글 목록을 뿌려주는 함수]
		function showComments_(data){
			console.log("서버에서 전송받은 데이타(댓글 목록):",data);
			var comments ="<h2>한줄 댓글 목록</h2>";
			comments +="<table class='table table-bordered'>";
			comments +="<tr><th class='text-center col-md-2'>작성자</th><th class='text-center'>코멘트</th><th class='text-center col-md-2'>작성일</th><th class='text-center col-md-2'>삭제</th></tr>";
			if(data.length==0){
				comments +="<tr><td colspan='4'>등록한 댓글이 없어요</td></tr>";
			}
			$.each(data,function(index,element){
				if ("${username}" == element['ID'])//씨큐리티 사용시
					comments += "<td class='text-left'><span class='commentEdit' title='"+element['LNO']+"' style='cursor:pointer'>"
							+ element['LINECOMMENT']
							+ '</span></td>';
				else
					comments += "<td class='text-left'>"
							+ element['LINECOMMENT'] + "</td>";

				comments += "<td>" + element['LPOSTDATE'] + "</td>";
				comments += "<td>";
				//if ("${sessionScope.id}" == element['ID'])
				if ("${username}" == element['ID'])
					comments += "<span class='commentDelete' title='"+element['LNO']+"' style='cursor:pointer'>삭제</span></td>";
				else
					comments += "<span style='color:gray;font-size:.7em'>삭제불가</span>";	
			});
			comments+="</table>";
			$('#comments').html(comments);
		}////////////
		
		
		//코멘트 입력 및 수정처리]
		var action;
		$("#submit").click(function(){
			console.log("클릭 이벤트 발생:"+$(this).val());
			console.log($("#frm").serialize());
			if($(this).val() == "등록")
				action = "<c:url value="/OneMemo/Comment/Write.do"/>";	
			else
				action = "<c:url value="/OneMemo/Comment/Edit.do"/>";	
			//ajax로 요청]	
			
			$.ajax({
				url:action,
				data:$("#frm").serialize(),
				dataType:"text",
				type:"post",
				success:function(name){//댓글 입력 성공 
					console.log("서버로부터 받은 데이타:"+name);
					var date = new Date();
					var lpostDate=date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate();
					var newComment="<tr><td>"+name+"</td><td><a href='#'>"+$("#title").val()+"</a></td><td>"+lpostDate+"</td><td>삭제</td></tr>";
					$(".comment-title").prepend(newComment);
					//입력댓글 클리어 및 포커스 주기
					$('#title').val("");
					$("#title").focus();
					
				}
				
			});	
		});
	
	</script>
	
	
	
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp" />
	<!-- 푸터 끝 -->
</body>
</html>