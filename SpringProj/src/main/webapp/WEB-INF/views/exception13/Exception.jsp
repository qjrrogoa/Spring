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
<title>Exception.jsp</title>

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
            스프링<small>예외처리</small>
         </h1>
      </div>
      <fieldset>
         <legend>예외처리 ${message}</legend>
         <form method="post" action="<c:url value='/Exception/Exception.do'/>">
            나이를 입력하세요? <input type="text" name="years" /> 
            <input type="submit" value="확인" />
         </form>
         ${errorMsg }
      </fieldset>
   </div>
   <!-- 실제 내용 끝 -->
   <!--  푸터 시작 -->
   <jsp:include page="/WEB-INF/views/templates/Footer.jsp" />
   <!-- 푸터 끝 -->
</body>
</html>