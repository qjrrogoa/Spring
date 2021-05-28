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
<title>View.jsp</title>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
 .titleClick:hover {
  color: #106eea;
  font-weight: bold;
  transition: 0.3s;
  cursor: pointer;
}

.commentDelete:hover{
  color: #ea103f;
  font-weight: bold;
  transition: 0.3s;
  cursor: pointer;
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
			<h1>한줄 메모 게시판<small>상세보기 페이지</small></h1>
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
					<ul id="pillMenu" class="nav nav-pills center-block" style="width: 200px; margin-bottom: 10px">
						<c:if test="${sessionScope.id == record.id}">
						<li><a href="<c:url value='/OneMemo/BBS/Edit.do?no=${record.no}'/>" class="btn btn-success">수정</a></li>
						<li><a href="javascript:isDelete();" class="btn btn-success deleteClick">삭제</a></li>
						</c:if>
						<li><a href="<c:url value='/OneMemo/BBS/List.do?nowPage=${param.nowPage}'/>" class="btn btn-success">목록</a></li>
					</ul>
				</div>
			</div>
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<div class="text-center">
						<!-- 한줄 코멘트 입력 폼-->
						<!-- 마이바티스의 resultMap 테스트용: <%--\${record.comments.size()} --%>-->
						<h2>한줄 댓글 입력 폼</h2>
						<form class="form-inline" id="frm" method="post">
							<!-- 씨큐리티 적용:csrf취약점 방어용 -->
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="hidden" name="no" value="${record.no}" /> ​
							<!-- 수정 및 삭제용 파라미터 -->
							<input type="hidden" name="lno"/>
							<input placeholder="댓글을 입력하세요" id="title" class="form-control" type="text" size="50" name="linecomment" />
							<input class="btn btn-success" id="submit" type="button" value="등록" />
						</form>
						<div class="row">
							<!-- 한줄 코멘트 목록-->
							<!-- ajax로 아래에 코멘트 목록 뿌리기 -->
							<div id="comments" class="col-md-offset-3 col-md-6"></div>
							<!-- 아래 태그는 마이바티스 resultMap의 collection태그 사용시에 가능 -->
							<h2>한줄 댓글 목록</h2>
							<table class='table table-bordered'>
								<tr>
									<th class='text-center col-md-2'>작성자</th>
									<th class='text-center'>코멘트</th>
									<th class='text-center col-md-2'>작성일</th>
									<th class='text-center col-md-2'>옵션</th>
								</tr>
								<tbody class="comment-title">
								<c:if test="${not empty record.comments}">
									<c:forEach var="comment" items="${record.comments}">
										<tr class="comment${comment.lno}">
											<td>${comment.name}</td>
											<td>
											<c:if test="${sessionScope.id == comment.id}">
												<span class="titleClick" title="${comment.lno}">${comment.lineComment}</span>
											</c:if>
											<c:if test="${sessionScope.id != comment.id}">${comment.lineComment}</c:if>
											</td>
											<td>${comment.lpostDate}</td>
											<c:if test="${sessionScope.id==comment.id}">
											<td><span class='commentDelete' title='${comment.lno}'>삭제</span></td>
											</c:if>
											<c:if test="${sessionScope.id!=comment.id}">
											<td>삭제불가</td>
											</c:if>
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
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp"/>
	<!-- 푸터 끝 -->
	<script>
//	showComments(); //MyBatis의 ResultMap 태그의 collection태그 적용시는 잠깐 주석
	
	function showComments(){
		//contentType을 생략시 디폴트값 : application/x-www-form-urlencoded
		//아래는 POST방식으로 전송하므로 key = value 형태로 변환되어서 요청바디에 저장됨 ex) no=2
		$.ajax({
			"url":"<c:url value='/OneMemo/Comment/List.do'/>",
			"data":{"no":"${record.no}"},
			//"header":{"Content-Type":"application/json;charset=UTF-8"},
			"type":"post",
			//"contentType":"application/json;charset=UTF-8",
			"dataType":"json",
			"success":showComments_,
			"error":function(e){
				console.log(e);
			}
		});
	}
	
	//실제 댓글 목록을 보여주는 함수
	function showComments_(data) {
		console.log("서버에서 전송받은 데이터(댓글 목록) : ",data);
		var comments = "<h2>한줄 댓글 목록</h2>";
		comments += "<table class='table table-bordered'>";
		comments += "<tr>"
		comments += "<th class='text-center col-md-2'>작성자</th>"
		comments += "<th class='text-center'>코멘트</th>"
		comments += "<th class='text-center col-md-2'>작성일</th>"
		comments += "<th class='text-center col-md-2'>삭제</th></tr>"
		if(data.length==0){
			comments +="<tr><td colspan='4'>등록한 댓글이 없어요.</td></tr>";
		}
		$.each(data,function(index,element){
			comments += "<tr><td>"+element["NAME"]+"</td>";
			comments += "<td>"+element["LINECOMMENT"]+"</td>";
			comments += "<td>"+element["LPOSTDATE"]+"</td>";
			comments += "<td>삭제/삭제불가</td></tr>"
		});
		comments += "</table>";
		$('#comments').html(comments);
	}
	
	//코멘트 입력 및 수정처리
	var action;
	$('#submit').click(function(){
		console.log("클릭이벤트 발생 : "+$(this).val());
		if($(this).val() == "등록") {
			action = "<c:url value='/OneMemo/Comment/Write.do'/>";
		}
		else {
		action = "<c:url value='/OneMemo/Comment/Edit.do'/>";
		}
		//ajax로 요청]

		$.ajax({
			url:action,
			//$("#frm").serialize() → form태그에 작성된 입력 데이터들을 쿼리스트링 형태로 바꿔줌
			data:$("#frm").serialize(),
			dataType:"text",
			//form 태그에서 method를 지정해도 ajax에서 작성된 type으로 적용됨
			type:"post",
			success:function(data){
				console.log("서버로부터 받은 데이터 : "+data);
				
				if($("#submit").val() == "등록"){
				//입력 시작
				var name = data.split(":")[0]; 
				var lno = data.split(":")[1]; 
				var date = new Date();
				var lpostDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
				var newComment="<tr class='comment"+lno+"'><td>"+name+"</td><td><span class='titleClick' title='"+lno+"'>"+$("#title").val()+"</span></td><td>"+lpostDate+"</td><td><span class='commentDelete' title='"+lno+"'>삭제</span></td></tr>";
				//$(".comment-title").append(newComment);
				$(".comment-title").prepend(newComment);
				//입력 끝
				} else{
					//원래 글을 수정한 글로 변경
					$(".titleClick[title="+data+"]").html($("#title").val());
					//수정 클릭시 등록으로 텍스트 변경					
					$("#submit").val("등록");
				}
				//댓글 입력이나 수정시 입력창 클리어 및 포커스 주기 (바로 이어서 입력할수있게)
				$('#title').val("");
				$('#title').focus();
			}////success
		});///ajax
	});///'#submit'
	
	//코멘트 제목 클릭시 수정처리하기(등록 UI변경)
	$(document).on('click','.titleClick',function(){
		console.log('코멘트 클릭 : '+$(this).html());	
		//클릭한 제목으로 텍스트 박스 설정
		$("#title").val($(this).html());
		//버튼은 "등록"에서 수정으로 변경
		$("#submit").val("수정");
		//댓글 입력 form의 hidden 타입인 lno에 값을 넣어주기
		console.log("댓글 키값 : ",$(this).attr("title"));
		$('input[name=lno]').val($(this).attr("title"));
	});
	
	//삭제 클릭시 삭제처리하기(등록 UI변경)
	$(document).on('click','.commentDelete',function(){
		if(confirm("정말로 삭제하시겠습니까?")){//사용자가 '확인' 클릭시 true 반환되어서 if로 들어옴
			var lno = $(this).attr("title");
			console.log("리스너 안에서의 this 클릭한 태그에 대한 정보가 담겨있음 : ",this);
			$.ajax({
				"url":"<c:url value='/OneMemo/Comment/Delete.do'/>",
				"data":"lno="+$(this).attr("title"),
				"type":"post",
				"dataType":"text",
			}).done(function(data){
				console.log("삭제 성공 - "+data);
				//HTML상에서 요소(엘리먼트) 삭제
				console.log("done 안에서의 this는 function에 대한 정보가 담겨 있음 : ",this);
				$(".comment"+lno).remove();
			}).fail(function(e){
				console.log("코멘트 삭제 실패 : ",e);
			});
			
		}
	});
	
	function isDelete(){
		if(confirm("게시글을 삭제하시겠습니까?")){
			location.replace("<c:url value='/OneMemo/BBS/Delete.do?no=${record.no}'/>");
		}
	}///isDelete
	</script>
</body>
</html>