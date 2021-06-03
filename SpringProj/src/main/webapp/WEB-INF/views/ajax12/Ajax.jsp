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
<title>Ajax.jsp</title>

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
				스프링<small>jQuery Ajax(스프링에 Ajax로 비동기 요청)</small>
			</h1>
		</div>
		<!-- 
	    ajax로 서버에 요청시에는
	    form태그가 의미 없다(왜냐하면 자바스크립트 객체인 XMLHttpRequest가 서버에 요청하기 때문에).
	        단, 전송할 데이타가 많을시에는 form태그 추가시  유리함.

	    -->
		<form id="frm">
			아이디 <input type="text" name="id" /> 
			비빌번호 <input type="password" name="pwd" />
		</form>
		<ul style="list-style-type: upper-roman;">
			<li><input type="button" id="btnNoAjax" value="회원여부(AJAX미사용)" /></li>
			<!-- 
			   Spring:반환타입을 void로 하거나
			                   반환타입을 String으로하고 @ResponseBody어노테이션 사용
			-->
			<li><a href="#" id="btnAjaxText">회원여부(AJAX사용-TEXT로 응답받기)</a></li>
			<li><input type="button" id="btnAjaxJson"
				value="회원여부(AJAX사용-JSON으로 응답받기)" /></li>

		</ul>

		<hr />
		<span id="lblDisplay" style="color: red; font-size: 2em; font-weight: bold">${isLogin}</span>
		<h3>JSON형식(JSON배열타입)</h3>
		<a href="#">목록가져오기</a><br /> 
		<span id="list"></span>
		<h3>Ajax폴링을 이용한 실시간 업데이트 웹 구현</h3>
		<button id="ajaxPolling">실시간 웹 업데이트</button>
		<span id="polling"></span>
		<!-- 
		    문]
		    닷넷과정 선택시  커리큘럼에
		    <option value="d01">C#</option>
		    <option value="d02">ASP.NET</option>
		    <option value="d03">WPF4</option>
		   사물인터넷과정 선택시  커리큘럼에
		    <option value="i01">라즈베리 파이</option>
		    <option value="i02">파이썬</option>
		    이	 보이도록 AJAX로구현하여라, 단, 서버에서 데이타를 JSON타입으로
		    받아라.   
	     -->
		<h3>AJAX 실습하기</h3>
		과정 <select name="course" id="course">
			<option value="java">자바과정</option>
			<option value="dotnet">닷넷과정</option>
			<option value="iot">사물인터넷과정</option>
		</select> 
		커리큘럼 
		<select name="curriculum" id="curriculum">
			<option value="j01">자바</option>
			<option value="j02">JSP</option>
			<option value="j03">스프링</option>
		</select>			
	
		<h3>Jackson라이브러리 실습</h3>
		<h4>자바객체를 JSON으로 변환</h4>
		<h5>form 태그로 요청</h5>
		<form id="ajaxForm" action="<c:url value='/Ajax/form.do'/>" method="post">
			제목 <input type="text" name="title"/>
			이름 <input type="text" name="name"/>
			<br/>
			내용<br/>
			<textarea rows="10" name="content"></textarea>
			<input type="submit" value="확인"/>
		</form>
		<button type="button" id="btnAjaxKeyValue">AJAX로 요청(KEY=VALUE)</button>
		<button type="button" id="btnAjaxRequestJson">AJAX로 요청(JSON)</button>
		<button type="button" id="btnAjaxArray">JSON배열로 받기</button>
		<h4>JSON을 자바객체로 변환</h4>
		<button type="button" id="btnJsonToJava">Map혹은 DTI으로 변환</button>
		<script>
			//key:value로 데이터 보내기
            $('#btnAjaxKeyValue').click(function(){
               $.ajax({
                  url:"<c:url value="/Ajax/ajaxRequest.do"/>",
                  data:$('#ajaxForm').serialize(),
                  type:"post",
                  dataType:"json"
                  
               }).done(function(data){
                  console.log(data);
                  
               });
            });
            //json으로 데이터 보내기
            $('#btnAjaxRequestJson').click(function(){
            	var json = {"title":$(":input[name=title]").val(),"content":$(":input[name=content]").val(),"name":$(":input[name=name]").val()};
            	console.log(JSON.stringify(json))
            	$.ajax({
                  url:"<c:url value="/Ajax/ajaxRequestJson.do"/>",
                  data:JSON.stringify(json), //반드시 문자열로 변환
                  contentType:"application/json;",
                  type:"post",
                  dataType:"json"
                  
               }).done(function(data){
                  console.log(data);
               });
            	
            });
            
            //json배열로 받기
            $('#btnAjaxArray').click(function(){
            	$.ajax({
                  url:"<c:url value="/Ajax/ajaxRequestJsonArray.do"/>",
                  type:"post",
                  dataType:"json"
               }).done(function(data){
                  console.log(data);
               });
            	
            });
            
            //json을 맵으로 변환
            $("#btnJsonToJava").click(function(){
            	var json = {"title":$(":input[name=title]").val(),"content":$(":input[name=content]").val(),"name":$(":input[name=name]").val()};
            	console.log(JSON.stringify(json))
            	$.ajax({
                  url:"<c:url value="/Ajax/jsonToJava.do"/>",
                  data:JSON.stringify(json), //반드시 문자열로 변환
                  contentType:"application/json;",
                  type:"post",
                  dataType:"json"
               }).done(function(data){
                  console.log(data);
               });
            	
            })
            
            
         </script>
		<hr/>
		${formRequestResult}
	</div>
	<!-- 실제 내용 끝 -->
	<script>
	
	
	
	//1]AJAX 미사용
	$('#btnNoAjax').click(function(){
		//form태그의 action속성 및 method속성 설정
		$('#frm').prop({action:"<c:url value="/Ajax/NoAjax.do"/>",method:"post"});
		//폼객체의 submit()함수로 서버로 전송
		$("#frm").submit();
	});
		/*
		 2]AJAX사용-서버로 부터 응답은 TEXT로 받기 
		 
		   ※POST방식으로 전송시
		   type:"post" 그리고
		   contentType은 디폴트로 즉 설정 불필요
		   ※GET방식(디폴트)으로 전송시
		   type:"get"로
		   contentType는 전송하는 컨텐타입으로(생략가능)...
		   
		   ※전송할 데이타가 여러개인 경우
		   <form>태그로 감싸주고
		   
		   $("form선택자").serialize()함수 사용
		   이름1=값1&이름2=값2&이름3=값3.....쿼리 스트링 형태로 반환
		*/
	
	$('#btnAjaxText').on('click',function(){
		console.log("serialize()함수:",$('#frm').serialize());
		//a태그 클릭시 ajax요청
		$.ajax({
			url:"<c:url value="/Ajax/AjaxText.do"/>",//요청할 서버의 URL주소
			type:"post",//데이타 전송방식(디폴트는 get방식) 
			dataType:'text',//서버로 부터 응답 받을 데이타의 형식 설정
			data:
				//1]쿼리스트링 문자열로 전송-데이타가 적을때
				//"id="+$(':input[name=id]').val()+"&pwd="+$('input[type=password]').val(),
				//2]JSON데이타 형식으로 전달-데이타가 적을때
				//{id:$(':input[name=id]').val(),pwd:$('input[type=password]').val()},
				//3]$("form선택자").serialize()함수 사용-데이타가 많을때
				$('#frm').serialize(),
			success:function(data){//서버로부터 정상적인 응답(200)을 받았을때 호출되는 콜백함수
				/*여기 함수안에 요청 결과를 표시해줄 코드 구현]
		     	서버로부터 정상적으로 응답을 받을때
			 	자동으로 호출되는 콜백 메소드]
			 	여기 안에서 서버로 부터 받은 데이타를
			 	원하는 위치에 뿌려주면 된다.
			 	※서버로 부터 받은 데이타는 매개변수로 전달됨(data)*/
				console.log('서버로 부터 받은 데이타:',data);
				console.log('서버로 부터 받은 데이타의 타입:',typeof(data));
				//서버에서 "Y" 혹은 "N"으로 응답할때]	
				//$('#lblDisplay').html(data=='Y'? $(':input[name=id]').val()+'님 방가방가' : '회원이 아니야');
				//서버에서 메시지로 응답할때]
				$('#lblDisplay').html(data);
			},
			error:function(error){//서버로부터 비정상적인 응답을 받았을때 호출되는 콜백함수
				console.log('%O:',error);
				console.log('에러:',error.responseText);
			}
			
		});
	});
	//3]AJAX사용-JSON데이타로 응답받기     
	$('#btnAjaxJson').click(function(){
		$.ajax({
			url:"<c:url value="/Ajax/AjaxJson.do"/>",
			data:$('#frm').serialize(),
			dataType:'text',
			success:successCallBack,//함수명만(()는 제외)
			error:function(request,status,error){
				console.log('응답코드:%s,에러메시지:%s,error:%s,status:%s',
							 request.status,
							 request.responseText,
							 error,
							 status
				)
			}
		});
	});	
	
	//4]AJAX사용 - 서버로 부터 응답은 JSON 배열로 받기  
	$('a:last').click(function(){
		$.ajax({
			url:"<c:url value="/Ajax/AjaxJsonArray.do"/>",
			dataType:'json',
			success:function(data){successAjax(data,'list');},
			error:function(request,error){
				console.log('상태코드:',request.status);
				console.log('서버로부터 받은 HTML:',request.responseText);
				console.log('에러:',error);
			}			
		});
	});	
	//5]AJAX폴링 구현하기
	$('#ajaxPolling').one('click',function(){
		//아래 요청을 특정시간 간격으로 서버에 보내자
		window.setInterval(function() {
			//ajax로 요청]
			$.ajax({
				url:"<c:url value="/Ajax/AjaxJsonArray.do"/>",
				dataType:'json',
				success:function(data){successAjax(data,'polling');},
				error:function(request,error){
					console.log('상태코드:',request.status);
					console.log('서버로부터 받은 HTML:',request.responseText);
					console.log('에러:',error);
				}			
			});
			
		}, 500);
		
	});
	
	function successCallBack(data){
		/*data는 서버측에서 전송한 데이타(JSON형식)
		data는 dataType:"json"로 지정했기때문에
		JSON데이타 타입(object)임.
		만약 dataType:"text"로 설정하면 data는 string객체 타입임.
		string타입을 JSON타입으로 변환하려면
		JSON.parse(string객체)
		즉 data.키값 으로 value값을 꺼내온다.]
		{isMember:"메시지"}형태로 서버에서 응답 
		data=JSON.parse(data);//dataType:"text"일때 
		
		※string JSON.stringify(JSON객체 즉 {}):
		 {}타입의 객체를 string타입으로 변환하는 메소드
		*/	
		console.log('서버로부터 받은 데이타:%s,객체:%O,타입:%s',data,data,typeof data);
		//json으로 받을때:dataType:"json"
		//console.log('서버로부터 받은 데이타(JSON.stringify()):%s',JSON.stringify(data));
		//$('#lblDisplay').html(data.isLogin);
		//dataType:'text'로 받을때
		//JSON형태의 문자열을 자바스크립트객체로 변경
		data=JSON.parse(data);
		$('#lblDisplay').html(data.isLogin);
	}
	
	var successAjax = function(data,id){
		console.log('서버로부터 받은 데이타(JSON.stringify()):',JSON.stringify(data));
		/*JSON배열을 출력할때는 $.each(data,function(index,index에 따른 요소값){}); 
		사용]
		data:서버로부터 전송받은 데이타(JSON배열타입)
		index:JSON배열의 인덱스(0부터 시작)	
		index에 따른 요소값:JSON배열에서 하나씩 꺼내온거를 담은 인자		
	    */
	    var tableString="<table class='table table-bordered'>";
	    tableString+="<tr>";
	    tableString+="<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th>";
	    tableString+="</tr>";
	    $.each(data,function(index,element){	    	
	    	var date = new Date(element["postDate"]);
	    	var postDate=date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	    	tableString+="<tr>";
	 	    tableString+="<td>"+(index+1)+"</td><td>"+element["title"]+"</td><td>"+element["name"]+"</td><td>"+postDate+"</td>";
	 	    tableString+="</tr>";
	    });
	    tableString+="</table>";
	    $('#'+id).html(tableString);
	};
	
	
	$("#course").change(function(){
		console.log("과정 선택 : ",$(this).val());
		$.ajax({
			url:"<c:url value="/Ajax/AjaxCourse.do"/>",
			data:{course:$(this).val()},//json
			}).done(function(data){
				console.log("서버로부터 받은 데이터 : ",data);
				var options = "";
				$.each(data,function(key,value){
					options+="<option value ='"+key+"'>"+value+"</option>";
				});
				$('#curriculum').html(options);
			}).fail(function(request,error){
				console.log('상태코드:',request.status);
				console.log('서버로부터 받은 HTML:',request.responseText);
				console.log('에러:',error);
			})
	})
	</script>
	
	
	<!--  푸터 시작 -->
	<jsp:include page="/WEB-INF/views/templates/Footer.jsp" />
	<!-- 푸터 끝 -->
</body>
</html>