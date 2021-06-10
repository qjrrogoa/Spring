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
<title>Websocket.jsp</title>

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
	<jsp:include page="/WEB-INF/views/templates/Top.jsp"/>
	<!-- 네비게이션 끝 -->
	<!-- 실제 내용 시작 -->
	<div class="container">
		<div class="page-header">
			<h1>스프링<small>웹소켓</small></h1>			
		</div>
		<fieldset>
			<legend>웹소켓 클라이언트</legend>
			<form>
				<div class="form-group">
					<label for="nickname" class="col-sm-1">닉네임</label>
					<div class="col-sm-4">
						<input class="form-control " type="text" id="nickname">
					</div>
				</div>
				<input class="btn btn-info" type="button" id="enterBtn" value="입장">
				<input class="btn btn-danger" type="button" id="exitBtn" value="퇴장">


				<div class="form-group">
					<h4>대화내용</h4>
					<div id="chatArea">
						<div id="chatMessage"
							style="height: 300px; border: 1px gray solid; overflow: auto"></div>
					</div>
				</div>

				<div class="form-group">
					<label for="message" class="col-sm-1">메시지</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" id="message" />
					</div>
				</div>
				<input class="btn btn-success" type="button" id="sendBtn" value="전송">
			</form>
		</fieldset>
		
		
	</div>
	<!-- 실제 내용 끝 -->
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp"/>
	<!-- 푸터 끝 -->
	<script>
		/*
		채팅 테스트
		localhost를 아이피로 변경 소스 및 브라우저 URL도 변경
		그리고 인바운드 규칙추가 403,9090
	    */
	  	//웹소켓 객체 저장용
	  	var wsocket;
	  	//닉 네임 저장용
	  	var nickname;
	   //입장버튼 클릭시 ]-서버와 연결된 웹소켓 클라이언트 생성
	  	$('#enterBtn').one('click',function(){
	  		wsocket = new WebSocket("ws://192.168.0.25:9090<c:url value="/chat-ws.do"/>");
	  		console.log('wsocket:',wsocket);
	  		//서버와 연결된 웹 소켓에 이벤트 등록
	  		wsocket.onopen = open;
	  		wsocket.onclose = function(){
	  			appendMessage("연결이 끊어 졌어요");
	  		};
	  		wsocket.addEventListener("message",receiveMessage);
	  		wsocket.onerror=function(e){console.log('에러발생:',e)};
	  	});
	  	//퇴장버튼 클릭시]
	   $('#exitBtn').one('click',function(){
		   wsocket.send('msg:'+nickname+'가(이) 퇴장 했어요');
		   wsocket.close();
	   });
	
	 //전송버튼 클릭시]
	 $('#sendBtn').click(function(){
		 sendMessage();
	 });
	 //메시지 입력후 전송 버튼 클릭이 아닌 엔터키 처리]
	 $('#message').on('keypress',function(e){
		 console.log('e.keyCode:%s,e.which:%s',e.keyCode,e.which);
		 var keyCode = e.keyCode ? e.keyCode : e.which;
		 if(keyCode==13){//엔터 입력
			 sendMessage();
		 	
			
		 }
		 
	 });
		//함수 정의]
		//서버에 연결되었을때 호출되는 콜백함수
		var open = function(){
			//서버로 연결한 사람의 정보(닉네임) 전송
			//msg:kim가(이) 입장했어요
			//사용자가 입력한 닉네임 저장
			nickname = $('#nickname').val();
			wsocket.send('msg:'+nickname+"가(이) 입장했어요");
			appendMessage("연결되었어요");			
		}
		//메시지를 DIV태그에 뿌려주기 위한 함수]
		var appendMessage = function(msg){
			$('#chatMessage').append(msg+"<br/>");
		};
		//서버에서 메시지를 받을때마다 호출되는 함수 
		var receiveMessage= function(e){//e는 message이벤트 객체
			//서버로부터 받은 데이타는 이벤트객체(e).data속성에 저장되어 있다
			if(e.data.substring(0,4)=='msg:')
				appendMessage(e.data.substring(4));//서버로부터 받은 메시지를 msg:부분을 제외하고 div에 출력
		};
		//서버로 메시지 전송하는 함수]
		function sendMessage(){
			//서버로 메시지 전송
			wsocket.send("msg:"+nickname+'>>'+$('#message').val());//msg:Superman:안녕
			//DIV(대화영역)에 메시지 출력
			appendMessage($('#message').val());
			//기존 메시지 클리어			
			$('#message').val("");			
			//포커스 주기
			$('#message').focus();			
		}
	</script>
</body>
</html>