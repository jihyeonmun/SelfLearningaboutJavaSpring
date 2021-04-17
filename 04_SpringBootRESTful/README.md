# Spring Boot를 이용한 RESTful Web Services 개발

### 목표

- Spring Boot
- REST API
- RESTful services
- JPA

### 내용

애플리케이션을 테스트하는 여러 방법과 도구를 설명합니다.

> 2011년 베니스에서 소프트웨어 아키텍처 워크숍이 개최된 이후 마이크로서비스라는 용어를 사용하기 시작했습니다. 애플리케이션 구축에 전통적인 모놀리스 개발 방식 대신 마이크로서비스 개발 방식을 도입함으로써, 개발뿐만 아니라 유지/보수에 있어서도 비용 절감 및 효율성 증가가 이루어졌으며 고객의 요구사항 또한 빠르게 처리할 수 있게 되었습니다. 이러한 마이크로 서비스의 인기와 더불어서 RESTful이라는 개발 방식도 많이 사용되고 있습니다.

> Microservice Architecture란 기존 하나의 큰 단위로 개발/운영되던 애플리케이션을 수십, 수백, 많게는 수천게의 작은 서비스 단위로 쪼개서 애플리케이션을 개발/운영하는 방식을 말합니다. 이러한 Microservice를 개발하기 위해서는 각 서비스끼리 독립적으로 개발될 수 있어야 하며, 각 서비스에 최적화된 개발 언어와 데이터베이스를 선택할 수도 있는 Polyglot Programming(폴리글랏 프로그래밍)의 요건을 갖추어야 합니다.

> 이렇게 이기종(異機種)으로 개발된 애플리케이션 간에 데이터 통신을 위해서 표준화된 HTTP 프로토콜을 사용하는 RESTful 서비스를 널리 사용하고 있습니다.
> Spring Boot는 독립 운영 가능한 Spring 기반 애플리케이션을 쉽게 개발할 수 있도록 지원해주는 개발 도구이자 Spring Project입니다. Spring Boot를 사용하면 바로 실행 가능한 독립형 Spring 애플리케이션을 쉽게 작성할 수 있으며, Spring framework와 기타 Third-party 라이브러리에 대한 설정 작업을 최소화 할 수 있으며, 비즈니스 로직에 더 많은 시간을 투자할 수 있습니다. 또한 Spring Boot의 Starter 컴포넌트들을 이용하면, 상용화에 필요한 통계, 상태 체크, 외부 설정 등에 관한 작업도 쉽게 구성할 수 있습니다.

<br>
총 48개, 6시간 43분 수업

|   섹션    | 목차                                                       |                                              링크 및 수강여부                                               |
| :-------: | :--------------------------------------------------------- | :---------------------------------------------------------------------------------------------------------: |
| **섹션0** | **Web Service & Web Application**                          | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/04_SpringBootRESTful/Section0) |
|           | Web Service와 Web Application의 개요                       |                                                                                                             |
| **섹션1** | **Spring Boot로 개발하는 RESTful Service**                 | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/04_SpringBootRESTful/Section1) |
|           | Spring Boot 개요                                           |                                                                                                             |
|           | REST API 설계                                              |                                                                                                             |
|           | Spring Boot Project 생성                                   |                                                                                                             |
|           | Spring Boot Project 구조 확인과 실행 방법                  |                                                                                                             |
|           | HelloWorld Controller 추가                                 |                                                                                                             |
|           | HelloWorld Bean 추가                                       |                                                                                                             |
|           | DipatcherServlet과 프로젝트 동작의 이해                    |                                                                                                             |
|           | Path Variable 사용                                         |                                                                                                             |
| **섹션2** | **User Service API 구현**                                  | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/04_SpringBootRESTful/Section2) |
|           | User 도메인 클래스 생성                                    |                                                                                                             |
|           | 사용자 목록 조회를 위한 API 구현 - GET                     |                                                                                                             |
|           | 사용자 목록 조회를 위한 API 구현 - POST                    |                                                                                                             |
|           | HTTP Status Code 제어                                      |                                                                                                             |
|           | HTTP Status Code 제어를 위한 Exception Handling            |                                                                                                             |
|           | Spring AOP를 이용한 Exception Handling                     |                                                                                                             |
|           | 사용자 삭제를 위한 API 구현 - DELETE                       |                                                                                                             |
| **섹션3** | **RESTful Service 기능 확장**                              | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/04_SpringBootRESTful/Section3) |
|           | Section 3 수업 소개                                        |                                                                                                             |
|           | 유효성 체크를 위한 Validation API 사용                     |                                                                                                             |
|           | 다국어 처리를 위한 Internationalization 구현 방법          |                                                                                                             |
|           | Response 데이터 형식 변환                                  |                                                                                                             |
|           | Response 데이터 제어를 위한 필터링                         |                                                                                                             |
|           | 프로그래밍을 제어하는 필터링 방법 - 개별                   |                                                                                                             |
|           | 프로그래밍을 제어하는 필터링 방법 - 전체                   |                                                                                                             |
|           | URI를 이용한 REST API Version 관리                         |                                                                                                             |
|           | Request Parameter와 Header를 이용한 API Version 관리       |                                                                                                             |
| **섹션4** | **Spring Boot API 사용**                                   | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/04_SpringBootRESTful/Section4) |
|           | Section4 수업 소개                                         |                                                                                                             |
|           | Level3 단계의 REST API 구현을 위한 HATEOAS 적용            |                                                                                                             |
|           | REST API DOCUMENTATION을 위한 Swagger                      |                                                                                                             |
|           | Swagger Documenation 구현 방법                             |                                                                                                             |
|           | REST API Monitoring을 위한 Actuator 설정                   |                                                                                                             |
|           | HAL Browser를 이용한 HATEOAS 기능 구현                     |                                                                                                             |
|           | Spring Security를 이용한 인증 처리                         |                                                                                                             |
|           | Configuration 클래스를 이용한 사용자 인증 처리             |                                                                                                             |
| **섹션5** | **Java Persistence API 사용**                              | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/04_SpringBootRESTful/Section5) |
|           | Section 5 수업 소개                                        |                                                                                                             |
|           | Java Persistence API의 개요                                |                                                                                                             |
|           | JPA 사용을 위한 의존성 추가와 설정                         |                                                                                                             |
|           | Spring Data JPA를 이용한 엔티니 설정과 초기 데이터 설정    |                                                                                                             |
|           | JPA Service 구현을 위한 컨트롤러, 레포지토리 생성          |                                                                                                             |
|           | JPA를 이용한 사용자 목록 조회 - GET                        |                                                                                                             |
|           | JPA를 이욯나 사용자 추가와 삭제 - POST/DELETE              |                                                                                                             |
|           | 게시물 관리를 위한 Post Entity 추가와 초기 데이터 생성     |                                                                                                             |
|           | 게시물 조회를 위한 Post Entity와 User Entity와의 관계 설정 |                                                                                                             |
|           | JPA를 이용한 새 게시물 추가 - POSt                         |                                                                                                             |
| **섹션6** | **RESTful API 설계 가이드**                                | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/04_SpringBootRESTful/Section6) |
|           | Section6 수업 소개                                         |                                                                                                             |
|           | Richardson Maturity Model 소개                             |                                                                                                             |
|           | REST API 설계 시 고려해야 할 사항                          |                                                                                                             |
