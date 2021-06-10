<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal.authorities" var="authorities"/>
<!-- 
@SessionAttributes("id")와 컨틀로러 메소드의 인자로 @ModelAttribute("id") String id 사용시
아래 로그인 여부 체크를 위한 인클루드 불필요
 -->
<%--@ include file="/WEB-INF/views/common/IsLogin.jsp" --%>

<style>
	.titleClick:hover, .commentDelete:hover {
		text-decoration: underline;
	}
	
	.titleClick, .commentDelete {
		color: lightblue;
		cursor: pointer;
	}
</style>

<!-- 실제 내용 시작 -->
<div class="container">
	<div class="page-header">
		<h1>
			한줄 메모 게시판<small>상세보기 페이지</small>
		</h1>
	</div>
	<!-- 씨큐리티 사용시:사용자 권한 출력 div -->
	<div>
		<div class="col-md-offset-2 col-md-8">
			<ul style="list-style: decimal;">
				<c:forEach items="${authorities }" var="authority">
					<li>${authority }</li>
				</c:forEach>
			</ul>
		</div>
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
				<!-- 아래는 씨큐리티 미 사용시 -->
				<%-- <c:if test="${sessionScope.id == record.id }">--%>
				<!-- 아래는 씨큐리티 사용시 -->
				<sec:authentication property="principal.username" var="username"/>				
				<c:if test="${username == record.id}">
					<li><a
						href="<c:url value='/OneMemo/BBS/Edit.do?no=${record.no}'/>"
						class="btn btn-success">수정</a></li>
					<li><a href="javascript:isDelete();" class="btn btn-success">삭제</a></li>
				</c:if>
				<%-- </c:if>--%>

				<li><a
					href="<c:url value='/OneMemo/BBS/List.do?nowPage=${param.nowPage}'/>"
					class="btn btn-success">목록</a></li>
			</ul>
		</div>
	</div>
	​

	<!-- row -->
	<div class="row">
		<div class="col-md-12">
			<div class="text-center">
				​ ​

				<!-- 한줄 코멘트 입력 폼-->
				<!-- 마이바티스의 리절트맵 테스트용:<%--${record.comments.size()} --%> -->
				<h2>한줄 댓글 입력 폼</h2>
				<form class="form-inline" id="frm">
					<!-- 씨큐리티 적용:csrf취약점 방어용 -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="no"
						value="${record.no}" />
					<!-- 수정 및 삭제용 파라미터 -->
					<input type="hidden" name="lno" /> <input placeholder="댓글을 입력하세요"
						id="title" class="form-control" type="text" size="50"
						name="linecomment" /> <input class="btn btn-success" id="submit"
						type="button" value="등록" />
				</form>
				<div class="row">
					<!-- 한줄 코멘트 목록-->
					<!-- ajax로 아래에 코멘트 목록 뿌리기 -->
					<div id="comments" class="col-md-offset-3 col-md-6">
						<!-- 아래 태그는 마이바티스 resultMap의 collection태그사용시 -->
						<h2>한줄 댓글 목록</h2>
						<table class='table table-bordered'>
							<tr>
								<th class='text-center col-md-2'>작성자</th>
								<th class='text-center'>코멘트</th>
								<th class='text-center col-md-2'>작성일</th>
								<th class='text-center col-md-2'>삭제</th>

							</tr>
							<tbody class="comment-title">
								<c:if test="${not empty record.comments}">
									<c:forEach var="comment" items="${record.comments}">
										<tr class="comment${comment.lno}">
											<td>${comment.name }</td>
											<td><c:if test="${username==comment.id }">
													<span class='titleClick' title="${comment.lno}">${comment.lineComment }</span>
												</c:if> <c:if test="${username!=comment.id }">
													${comment.lineComment }
												</c:if></td>
											<td>${comment.lpostDate }</td>
											<td><c:if test="${username==comment.id }">
													<span href="#" class='commentDelete' title="${comment.lno}">삭제</span>
												</c:if> <c:if test="${username!=comment.id }">
													삭제불가
												</c:if></td>
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
		/*
		두번째 방식		
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
		*/
		
		//코멘트 입력 및 수정처리]
		var action,type;
		$("#submit").click(function(){
			console.log("클릭 이벤트 발생:"+$(this).val());
			console.log($("#frm").serialize());
			if($(this).val() == "등록"){
				action = "<c:url value="/OneMemo/Comment/Write.do"/>";	
				type = "post";
			}
			else{
				action = "<c:url value="/OneMemo/Comment/Edit.do"/>";
				type="put";
			}
			
			console.log("lno설정:",$('input[name=lno]').val());
			console.log($("#frm").serialize());
			//ajax로 요청]			
			$.ajax({
				url:action,
				data:$("#frm").serialize(),
				dataType:"text",
				type:"post",
				success:function(data){//댓글 입력 성공 
					console.log("서버로부터 받은 데이타:"+data);
					
					if($("#submit").val() =="등록"){
						//입력시 시작
						var name = data.split(":")[0];
						var lno = data.split(":")[1];					
						var date = new Date();
						var lpostDate=date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate();
						var newComment="<tr class='comment"+lno+"'><td>"+name+"</td><td><span class='titleClick'  title='"+lno+"'>"+$("#title").val()+"</span></td><td>"+lpostDate+"</td><td><span  class='commentDelete' title='"+lno+"'>삭제</span></td></tr>";
						$(".comment-title").prepend(newComment);
						//입력시 끝
					}
					else{
						//원래글을 수정한 글로 변경
						$(".titleClick[title="+data+"]").html($("#title").val());
						//버튼의 텍스트 변경
						$("#submit").val("등록");
					}					
					//입력이나 수정시 댓글 클리어 및 포커스 주기
					$('#title').val("");
					$("#title").focus();				
					
				}				
			});				
		});///////////////
		
		//코멘트 제목 클릭시 수정처리하기(UI변경)
		$(document).on('click','.titleClick',function(){
			console.log('클릭이벤트 발생:'+$(this).html());
			//클릭한 제목으로 텍스트박스 값 설정
			$("#title").val($(this).html());
			//버튼은 "등록"에서 "수정"으로
			$("#submit").val("수정");
			//댓글 입력 form의 hidden타입의 속성중 name="lno"의 value속성 설정
			console.log("댓글 키값 : ",$(this).attr("title"));
			$('input[name=lno]').val($(this).attr("title"));
			
		});
		
		//삭제 클릭시 삭제처리하기
		$(document).on('click','.commentDelete',function(){
			var lno = $(this).attr('title');
			
			if(confirm("정말로 삭제할래?")){
				console.log("코멘트 삭제:","lno="+lno+"&"+$("#frm").serialize());
				//삭제 처리]
				$.ajax({
					url:"<c:url value="/OneMemo/Comment/Delete.do"/>",
					type:"post",
					data:"lno="+lno+"&"+$("#frm").serialize(),
					dataType:'text'				
				}).done(function(data){
					console.log("삭제 성공");
					//html DOM에서 요소 삭제					
					$(".comment"+lno).remove();				
					
				}).fail(function(e){
					console.log(e);
				});
			}
			
		});
		
	function isDelete(){
		if(confirm("정말로 삭제 할래?")){
			location.replace("<c:url value="/OneMemo/BBS/Delete.do?no=${record.no}"/>");
		}
	}
	</script>

