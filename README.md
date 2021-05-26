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


#Annotation

2. RequestParam 
---
	파라미터명이랑 변수명이랑 다를 때
	매개변수에서 조절 해줘야 한다
	@RequestParam("파라미터명" or value="파라미터명") 자료형 변수명 or @RequestParam(required=false,defalutValue="내가 정한 디폴트 값") 자료형 변수명

3. ModelAttribute
---
	check받을때는 cmd로 받아야한다, map으로 받을 시 맨 처음꺼 밖에 받질 못함
	1] cmd
	파라미터를 맵으로 모두 받기
	Command 클래스를 만든다(DTO)

4. autowired(타입 -> id -> Qualifer)
---
	2개 이상의 값일 때 
	id값으로 구분
	@Autowired(required = false)

	2개 이상의 값일 때
	id값이 같거나 없을 땐 Qualifier
	@Qualifier("qualifier값")

	제일 먼저 servlet-context.xml

	<beans:bean id="fCommand" p:name="가길동" p:years="20" class="com.kosmo.springapp.basic.annotation.Command">
		<beans:qualifier value="fCommand"/>
	</beans:bean>

	<beans:bean id="sCommand" class="com.kosmo.springapp.basic.annotation.Command">
		<beans:qualifier value="fCommand"/>
		<beans:property name="name" value="나길동"/>
		<beans:property name="years" value="30"/>
	</beans:bean>


	1] 필드에 붙일 경우
	@Autowired(required = false)
	@Qualifier("fCommand")
	private Command fCommand;
	
	@Autowired(required = false)
	@Qualifier("sCommand")
	private Command sCommand;

	2]세터에 붙일 경우
	private Command fCommand;
	private Command sCommand;

	@Autowired
	@Qualifier("fCommand")
	public void setfCommand(Command fCommand) {
	this.fCommand = fCommand;
	}

	@Autowired
	@Qualifier("sCommand")
	public void setsCommand(Command sCommand) {
	this.sCommand = sCommand;
	}

	3]생성자에 부착(단, @Qualifier 부착 불가, 무조건 id값 부여해야한다.)
	@Autowired
	private Command fCommand;
	private Command sCommand;
	
	public AutoWiredController(Command fCommand, Command sCommand) {
	this.fCommand = fCommand;
	this.sCommand = sCommand;
	}
	
	@RequestMapping("")
	public String 매서드명(Model model){
		model.addAttribute(,);
		return " " ;
	}

	1], 2], 3] 공통
	@RequestMapping("")
	public String 매서드명(Model model){
		model.addAttribute("message",String.format("%s,%s",fCommand,sCommand));
		return " " ;
	}


5. Resource (id -> 타입-> Qualifer)
---
	<beans:bean id="fCommand" p:name="가길동" p:years="20" class="com.kosmo.springapp.basic.annotation.Command">
		<beans:qualifier value="fCommand"/>
	</beans:bean>

	<beans:bean id="sCommand" class="com.kosmo.springapp.basic.annotation.Command">
		<beans:qualifier value="fCommand"/>
		<beans:property name="name" value="나길동"/>
		<beans:property name="years" value="30"/>
	</beans:bean>


	1] 변수 이름 지정X
	@Resource
	private Command fCommand;
	@Resource
	private Command sCommand;
	
	@RequestMapping("")
	public String 매서드명(Model model){
		model.addAttribute("message",String.format("%s,%s",fCommand,sCommand));
		return " " ;
	}

	2] 변수 이름 지정 O
	@Resource(name="fCommand")
	private Command fCmd;
	@Resource(name="sCommand")
	private Command sCmd;
	
	@RequestMapping("")
	public String 매서드명(Model model){
		model.addAttribute("message",String.format("%s,%s",fCmd,sCmd));
		return " " ;
	}
    
6. SessionAttribute
---
	공통으로 에러 잡자
	@ExceptionHandler({Exception.class})
	public String error(Execption e, Model model){
		model.addAttribute("isLoginMessage","로그인 하세요");
		return "annotation06/Annotation";
	}
	
	1] 서블릿 API 사용 (선호 X)
	[1] 로그인 처리
	@RequestMapping("")
	public String login(HttpSession session, @RequestParam Map map, Model model){
		if("KIM".eqals(map.get("user")) && "1234".equals(map.get("pass"))){
			session.setAttribute("user",map.get("user"))
		}
		else
			model.addAttribute("errorMessage","아이디와 비번 불일치");
			
		return "";
	}	
	
	[2] 로그아웃 처리
	@RequestMapping("")
	public String logout(HttpSession session){
		session.invalidate();
		return "";
	}
	
	[3] 로그인 판단 여부
	@RequestMapping("")
	public String isLogin(HttpSession session,Model model){
		model.addAttribute("","");
		return "";
	}
	
	2] SessionAttribute 어노테이션 사용, Command 사용 X
	@SessionAttributes({"user","pass"})(@Controller위에)
	[1] 로그인 처리
	@RequestMapping("")
	public String login(@RequestParam Map map, Model model){
		if("KIM".eqals(map.get("user")) && "1234".equals(map.get("pass"))){
			model.addAllattributes(map);
		}
		else
			model.addAttribute("errorMessage","아이디와 비번 불일치");
			
		return "";
	}	
	
	[2] 로그아웃 처리
	@RequestMapping("")
	public String logout(SessionStatus status){
		status.setComplete();
		return "";
	}
	
	[3] 로그인 판단 여부
	@RequestMapping("")
	public String isLogin(@ModelAttribute("user") String id,Model model){
		model.addAttribute("",id+"로그인");
		return "";
	}
	
	
	3] SessionAttribute 어노테이션 사용, Command 사용 O
	[1]
	LoginCommand 클래스 생성 
	@SessionAttributes(types=LoginCommand.class)(@Controller위에)
	또, 빈 설정 파일에 <annotation-driven/>태그 추가해야됨

	[1] 로그인 처리
	//매개변수의 LoginCommand 객체가 무조건 자동으로 세션 영역에 저장됨
	//로그인 처리시 회원이 아닐때라고 if문 설정 해야 한다.
	
	@RequestMapping("")
	public String login(LoginCommand cmd, Model model, SessionStatus satus){
		if(!("KIM".eqals(map.get("user")) && "1234".equals(map.get("pass")))){
			model.addAllattributes("errorMessage","아이디 비번 불일치");
			status.setComplete();
		}
		return "";
	}	
	
	[2] 로그아웃 처리
	@RequestMapping("")
	public String logout(SessionStatus status){
		status.setComplete();
		return "";
	}
	
	[3] 로그인 판단 여부
	@RequestMapping("")
	public String isLogin(@ModelAttribute("loginCommand") LoginCommand cmd,Model model){
		model.addAttribute("",cmd.getUser()+"로그인");
		return "";
	}

#database

1. JNDI 연결
---
	1] root-context.xml
	<bean id="" class="org.springframework.jndi.JndiObjectFactoruBean">
		<property name="" value="계정 이름"/>
		<property name="resourceRef" value="ture"/> // 이 문장 안해주면 value="java:/comp/env/계정이름" 해줘야함
	</bean>
		
	2] Controller
	@Resource(name="아이디값")
	private Datasource 변수;
	
	@RequestMapping("")
	public String 메서드명(@RequestParam String method,Model model){
		Connection conn = jndi.getConnection();
		model.addAttribute("","");
		if(conn != null)
			conn.close();
		return "";
	}
	
#resource

	1] Command생성 
	
	2-1] src/main/resources/ 파일명.properties 파일 생성
	key = value 값으로 값 지정
	
	2-2] servlet-context 빈 파일 생성
	<beans:bean id="" p:속성명1 = "${키1}" class="">
		<beans:property name="속성명2" value="{키2}"/>
	</beans:bean>
	
	3] Controller
	
	@Value("${키1}")
	private 자료형 속성명1;
	@Value("${키2}")
	private 자료형 속성명2;
	
	@Resource(name="id값")
	private 클래스명 command명
	
	@RequestMapping()
	public 자료형 매서드명(Model model){
		//데이터 저장
		model.addAttribute
		//뷰 정보 반환
		return "";
	}

#validate

	1] Command생성
	//checkbox 하기 위해서 체크박스는 배열로 선언
	
	2] Controller
	// 메서드 2개 필요
	// 첫번째 메서드 boolean 반환하는 메서드
	// 두번째 메서드 첫번째 메서드가 false면 페이지 유지, true면 페이지 넘기기
	
	@RequestMapping("")
	public boolean validate(클래스명 cmd, Model model){
		if(cmd.getName().trim().equlas(""){
			model.attribute("nameError","이름을 입력하세요")
			return false;
		}
		// 조건 여러개 쭈욱~
		return ture;
	}
	
	@RequestMapping("")
	public String exec(클래스명 cmd, Model model){
		
		if(!validate(cmd,model)){
			model.addAttribute("inters",Arrays.toString(cmd.getInters()));
			return "";
		}
		
		cmd.setSelf(cmd.getSelf().replace("\r\n","<br/>"));
		model.addAttribute("cmd",cmd);
		return "";
	}
	
	
	
#mybatis
---

1] pop.xml

lombook 라이브러리 설치

cmd,터미널 이용해서 lombook설치

	//터미널에서
	cd /Users/bagdaemin/.m2/repository/org/projectlombok/lombok/1.18.20/
	java -jar lombok-1.18.20.jar

2] src/main/sesources에서 onememo.mybatis 패키지 생성

3] configulation.xml생성

	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE configuration
	  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
	  "http://mybatis.org/dtd/mybatis-3-config.dtd">
	<configuration>
	  <environments default="development">
	    <environment id="development">
	      <transactionManager type="JDBC"/>
	      <dataSource type="POOLED">
		<property name="driver" value="${driver}"/>
		<property name="url" value="${url}"/>
		<property name="username" value="${username}"/>
		<property name="password" value="${password}"/>
	      </dataSource>
	    </environment>
	  </environments>
	  <mappers>
	    <mapper resource="org/mybatis/example/BlogMapper.xml"/>
	  </mappers>
	</configuration>
붙여넣기

4]connect.properties 생성

	driver = oracle.jdbc.OracleDriver
	url = jdbc:oracle:thin:@localhost:1521:xe
	username = maven
	password = maven
	JNDI_NAME = java:comp/env/maven
	
	
	
	
root컨텍스트 주석 푼다
sqlSessionFactory 복사 

onememodao  주석 묶는다
@Resource(name="복붙")
private 복붙 sqlMapper

configuration
root컨텍스트
root컨텍스트 주석 푼
