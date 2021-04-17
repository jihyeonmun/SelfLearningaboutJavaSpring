# 더 자바, 코드를 조작하는 다양한 방법

### 목표

- 자바 애플리케이션에서 테스트를 작성하는 방법
- 자바 애플리케이션 성능을 테스트하는 방법
- 운영 환경 이슈를 테스트하는 방법
- 애플리케이션 아키텍쳐를 테스트하는 방법
- 도커를 사용하여 테스트하는 방법
- Juni5, Mockitor, Testcontainers, Chaos Monkey for Spring Boot, JMeter, ArchUnit

### 내용

애플리케이션을 테스트하는 여러 방법과 도구를 설명합니다.

> - 먼저, 테스트 작성하는 자바 개발자 90%가 넘게 사용하는 JUnit 최신 버전을 학습하여 자바로 테스트를 작성하고 실행하는 방법을 소개합니다.
> - 다음으로 Mockito를 사용하여 테스트 하려는 코드의 의존성을 가짜로 만들어 테스트 하는 방법을 학습합니다.
> - 그리고 도커(Docker)를 사용하는 테스트에서 유용하게 사용할 수 있는 Testcontainers를 학습합니다.
> - 다음으로는 관점을 조금 바꿔서 JMeter를 사용해서 성능 테스트하는 방법을 학습합니다.
> - 카오스 멍키 (Chaos Monkey for Spring Boot)를 사용해서 운영 이슈를 로컬에서 재현하는 방법을 살펴보고
> - 마지막으로 ArchUnit으로 애플리케이션의 아키텍처를 테스트하는 방법에 대해 학습합니다.

> 이 강좌를 학습하고 나면 여러분은 자바 애플리케이션을 테스트 하는 다양한 방법을 습득할 수 있습니다. 실제 여러분의 업무와 프로젝트에 필요한 테스트를 작성할 수 있을 겁니다.

<br>
총 43개, 5시간 56분 수업

|    섹션    | 목차                                       |                                          링크 및 수강여부                                          |
| :--------: | :----------------------------------------- | :------------------------------------------------------------------------------------------------: |
| **섹션0**  | **JUnit 5**                                | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/03_JavaTest/Section0) |
|            | JUnit5 소개                                |                                                                                                    |
|            | JUnit5 시작하기 구조                       |                                                                                                    |
|            | JUnit5 테스트 이름 표시하기 로더           |                                                                                                    |
|            | JUnit5 Assertion                           |                                                                                                    |
|            | JUnit5 조건에 따라 테스트 실행하기         |                                                                                                    |
|            | JUnit5 태깅과 필터링                       |                                                                                                    |
|            | JUnit5 커스텀 태그                         |                                                                                                    |
|            | JUnit5 테스트 반복하기1                    |                                                                                                    |
|            | JUnit5 테스트 반복하기2                    |                                                                                                    |
|            | JUnit5 테스트 인스턴스                     |                                                                                                    |
|            | JUnit5 테스트 순서                         |                                                                                                    |
|            | JUnit5 junit-platform.properties           |                                                                                                    |
|            | JUnit5 확장 모델                           |                                                                                                    |
|            | JUnit5 마이그레이션                        |                                                                                                    |
|            | JUnit5 연습 문제                           |                                                                                                    |
| **섹션 1** | **Mockito**                                | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/03_JavaTest/Section1) |
|            | Mockito 소개                               |                                                                                                    |
|            | Mockito 시작하기                           |                                                                                                    |
|            | Mock 객체 만들기                           |                                                                                                    |
|            | Mock 객체 Stubbing                         |                                                                                                    |
|            | Mock 객체 Stubbing 연습 문제               |                                                                                                    |
|            | Mock 객체 확인                             |                                                                                                    |
|            | BDD 스타일 Mockito API                     |                                                                                                    |
|            | Mockito 연습 문제                          |                                                                                                    |
| **섹션2**  | **도커와 테스트**                          | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/03_JavaTest/Section2) |
|            | Testcontainers 소개                        |                                                                                                    |
|            | Testcontainers 설치                        |                                                                                                    |
|            | Testcontainers 기능 살펴보기               |                                                                                                    |
|            | 컨테이너 정보를 스플이 테스트에서 참조하기 |                                                                                                    |
|            | Testcontainers 도커 Commpose 사용하기1     |                                                                                                    |
|            | Testcontainers 도커 Compose 사용하기2      |                                                                                                    |
|            | Testcontainers 설치                        |                                                                                                    |
| **섹션3**  | **성능 테스트**                            | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/03_JavaTest/Section3) |
|            | JMeter 소개                                |                                                                                                    |
|            | JMeter 설치                                |                                                                                                    |
|            | JMeter 사용하기                            |                                                                                                    |
| **섹션4**  | **운영이슈테스트**                         | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/03_JavaTest/Section4) |
|            | Chaos Monkey 소개                          |                                                                                                    |
|            | CM4SB 설치                                 |                                                                                                    |
|            | CM4SB 응답 지연                            |                                                                                                    |
|            | CM4SB 에러 발생                            |                                                                                                    |
| **섹션5**  | **아키텍쳐 테스트**                        | [링크](https://github.com/jihyeonmun/SelfLearningaboutJavaSpring/tree/master/03_JavaTest/Section5) |
|            | ArchUnit 소개                              |                                                                                                    |
|            | ArchUnit 설치                              |                                                                                                    |
|            | ArchUnit 패키지 의존성 확인하기            |                                                                                                    |
|            | ArchUnit JUnit 5 연동                      |                                                                                                    |
|            | ArchUnit 클래스 의존성 확인하기            |                                                                                                    |
