# Spring


    요청 -> DispatcherServelt -> 사용자의 모든 요청을 받는다. (어떤 서블릿으로 받아야 하는지 판단)
    HandlerMapping -> DispatcherServelt -> 어떤 컨트롤러로 사용하는지 판단
    Controller -> DispatcherServelt -> 요청받은 컨트롤러 전달
    ModalAndView -> DispatcherServelt -> 데이터와 뷰 부분을 받음
    ViewResolver -> DispatcherServelt -> 어떤 뷰 객체 사용하는지 (jsp, excel, pdf등등)
    View -> 응답


    Server시작 -> application 시작 -> web.xml
    HomeController.java -> Home.jsp -> IndexController.java -> Controller.jsp
    1. Home.jsp에서
        .do 서블릿 만듬
    2. IndexController.java
        @Controller 클래스
        @RequestMapping 메서드 뷰 정보 반환
    3. .jsp 뷰딴 만듬
        서블릿 호출
    4. 컨트롤러 페이지.java
        @Controller 클래스
        @RequestMapping 매서드 뷰 정보 반환

    컨트롤러가 하는 일 
    필요한 로직 호출
    데이터 저장
    뷰 정보 반환
