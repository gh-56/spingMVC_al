## H2 DataBase
- 설치가 필요없다
- 용량이 가벼워서 개발용 로컬DB로 사용하기 좋은 DBMS
- 자바 기반 오픈소스 RDBMS
- in-Memory DB 기능을 지원
- 2Mb 내외의 저용량 데이터베이스
- 표준 SQL 지원
- 로컬 및 테스트 환경에서 많이 사용  

#### application.properties 파일  
H2 데이터베이스 콘솔 보기 http://localhost:8080/h2-console  
```spring.h2.console.enabled=true```  
고정 URL 설정하기  
```spring.datasource.url=jdbc:h2:mem:testdb```  

### form 데이터 전달
- HTML 폼 데이터에서 전달 받은 데이터는 name 속성의 이름에 맞춰 http로 전달
- 컨트롤러가 객체(DTO)에 담아 받는다.
- DTO를 DB에 저장
  - JPA를 사용하는 경우
    - Entity 객체 : 자바 객체를 DB가 이해할 수 있게 만든 것
    - Repository : 엔티티가 DB 테이블에 접근, 저장 관리 할 수 있게 하는 인터페이스
- 외부에서 만들어진 객체(Controller에 Repository)를 DI(의존성 주입)

## Lombok
- 프로젝트 롬복의 축약어
- 자바 코드를 **더 간단하고 간결하게** 사용하게 만들어주는 라이브러리
  - 어노테이션 기반으로 코드 생성
  - 코드 중복 제거, 가독성 향상, 실수 방지
  - @Data : getter, setter, equals, hashCode, toString까지 자동으로 생성
  - @Getter , @Setter
  - @ToString
  - @AllArgsConstructor : 모든 필드 포함한 생성자
  - NoArgsConstructor : 기본 생성자
  - @Builder : 빌더 패턴을 쉽게 적용할 수 있게 빌더 클래스 생성
  - @Slf4g : 로깅 모듈 
    - 등 다양한 어노테이션 지원
```java
@AllArgsConstructor
@ToString
public class Main{
    // lombok이 자동으로
    // 모든 필드가 포함된 생성자와
    // toString()을 포함해줌
}
 ```

#### @Slf4g
```java
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerTest {
    public static void main(String[] args){
        log.trace("trace"); // 1레벨(가장 낮은 레벨), 실행 흐름을 자세히 추적
        log.debug("debug"); // 2레벨. 디버깅을 목적으로 사용 (프로덕션에서는 필요한 경우만 활성화)
        log.info("info"); // 3레벨(기본 레벨), 중요한 실행 정보
        log.warn("warn"); // 4레벨. 주의해야할 상황
        log.error("error"); // 5레벨 중요한, 오류 또는 예외 상황(프로덕션에서도 활성화) 중요한 문제 식별
    }
}
```
#### application.properties 파일
로깅 레벨 설정하기 [OFF / ERROR / WARN / INFO / DEBUG / TRACE / ALL] 
```logging.level.root=DEBUG```

## 데이터 조회 과정
1. 사용자가 URL 요청(HTTP Request (메소드는 GET 또는 POST))
2. 컨트롤러가 요청을 받아 데이터정보를 Repository에 전달
3. 리파지토리는 데이터를 DB에 조회 요청
4. DB는 해당 데이터를 찾아와서 Entity로 반환
5. 반환된 엔티티는 모델을 통해 뷰 템플릿으로 전달
6. 뷰 페이지가 완성되어 사용자 화면에 출력된다.

## 알아둘 것
- @PathVariable : URL 요청을 동적으로 받는 경로 매개변수
- findById() : JPA 제공 메서드. id 기준으로 데이터 검색
  - Optional로 반환
- findByAll : JPA 메서드. 엔티티의 모든 데이터를 조회
  - Iterable로 반환
- {{#  }} {{/}} : 머스테치 문법. 해당 범위 내 데이터를 사용 가능
- Entity에 기본 생성자가 있어야 객체 사용이 가능하다.
  - Java Bean
    - 특정 형태의 클래스(DTO, VO)
      - public 접근가능한 기본 생성자가 있어야 함
      - 모든 필드는 private이어야 함
      - getter / setter가 있어야 함
    - Spring Bean과는 다르다 (스프링빈 : 스프링에서 관리하는 객체)


## PRG 패턴 (POST - Redirect - GET)
- 웹 앱에서 사용자 상호작용을 다루는 디자인패턴
  1. POST : HTTP POST 메서드로 서버에 양식을 제출
  2. 서버에서 로직 실행 : POST 요청을 받고 필요한 로직 수행
  3. Redirect : HTTP 리다이렉트 응답 생성해 다른 페이지로 이동
  4. GET : HTTP GET 요청을 통해 결과 확인
- 새로고침을 통한 **중복 제출 방지**
- UX 개선

## 생성자 주입
- 생성자가 1개만 있을 경우
  - @Autowired 생략해도 주입이 가능한 편의성 제공
- 롬복의 @RequiredArgsConstructor를 사용
  - 생성자 코드를 생략할 수 있다.
  - 의존성 주입 필드는 반드시 private final로 선언해야한다

## HTTP 상태코드
- 1XX(정보) : 요청 수신
- 2XX(성공) : 요청이 정상적으로 처리
- 3XX(리다이렉션) : 요청을 완료하기 위해 추가행동이 필요
- 4XX(클라이언트 요청 오류) : 요청이 잘못되어 서버 수행 불가.
- 5XX(서버 응답 오류) : 서버 내부 에러 발생하여 요청 수행 불가

## HTTP 메서드
- GET : 리소스 조회 (요청에 Body 없음, 응답에 Body 있음)
  - 안전하다. (리소스를 변경하지 않는다.)
- POST : 요청한 데이터 처리하고 리소스 생성
- PUT : 리소스를 대체(변경)하고, 리소스가 없으면 생성
- PATCH : 리소스의 부분 변경
- DELETE : 리소스 삭제 (요청에 Body 없음, 응답에 Body 있음)

## REST API
- REST(REpresentational State Transfer) : HTTP URL을 통해 서버의 자원(Resource)을 명시하고, HTTP 메서드(GET, POST, PUT, PATCH, DELETE)로 해당 자원을 CRUD(생성, 조회, 수정, 삭제)하는 것
- API는 클라이언트가 서버 자원을 요청할 수 있도록 서버 측에서 제공하는 인터페이스
- 리소스(자원) 지향, 상태 없음(Stateless)
- 웹 개발 및 서비스에서 통신 단순화, 확장성 등으로 중요부분 차지

## REST 컨트롤러
- REST API로 설계된 URL의 요청을 받아서 처리하는 컨트롤러
- 뷰 대신에 텍스트나 JSON을 반환한다.
- @RestController

## ResponsEntity
- REST 컨트롤러의 리턴 타입, REST API응답을 위해 사용하는 클래스, HTTP 상태코드, 헤더, 바디 등을 실어보낼 수 있다.

## HttpStatus
- 상태코드를 관리하는 클래스, Enum 타입 200 => HttpStatus.OK
