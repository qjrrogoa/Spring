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


# ViewResolver
--  
    뷰 정보 반환시 접두사 접미어 붙는다
    기본적으로 forward가 디폴트 값
    
    포워드로 파라미터 보낼 시 쿼리스트링으로 받지 않고 페이지에서 확인 가능
    리다이텍트로 파라미터 보낼 시 영역에 저장한 값은 쿼리스트링으로 받고 파라미터 값은 페이지에서 확인 가능
    
# Injection
--
    1] 생성자 인젝션를 통한 주입
    
    [1] web.xml
        <beans:bean id="내가 지정" class="com.kosmo.springapp. 주소">
            		<beans:qualifier value="person1"/>              //id가 같을때 value값으로 비교
                    <beans:constructor-arg type="타입" or index="숫자" value="변수 지정"/>
                    <beans:constructor-arg type="타입" or index="숫자" value="변수 지정"/>
        </beans:bean>

    [2] 컨트롤러
    [2-1] id로 지정한 멤버 변수 지정
        private 클래스명 id값1, id값2;
        
    [2-2] 인자 생성자 정의
        public 컨트롤러명(클래스명 id값1, 클래스명 id값2){
            this.id값1 = id값1
            this.id값2 = id값2
        }
    [2-3] 컨트롤러 메서드, 데이터 저장, 뷰 정보 반환
    
    
    2] 세터를 통한 주입
    
    [1] web.xml
        p객체 사용
        <beans:bean p:변수1 = "변수값1" p:변수2 = "변수값2" id="내가 지정" class="com.kosmo.springapp. 주소">
            		<beans:qualifier value="person3"/>              //id가 같을때 value값으로 비교
                    <beans:property name="변수3" value="변수값3"/>
        </beans:bean>
     
     [2] 컨트롤러
     [2-1] 멤버 변수 지정
        @Resource(name="id값1")
        private 클래스명 id값1;
        @Resource(name="id값2")
        private 클래스명 id값2;
    [2-2] 컨트롤러 메서드, 데이터 저장, 뷰 정보 반환        

    
    리다이텍트로 파라미터 보낼 시 영역에 저장한 값은 쿼리스트링으로 받고 파라미터 값은 페이지에서 확인 가ㅈ
    리다이텍트로 파라미터 보낼 시 영역에 저장한 값은 쿼리스트링으로 받고 파라미터 값은 페이지에서 확인 가
