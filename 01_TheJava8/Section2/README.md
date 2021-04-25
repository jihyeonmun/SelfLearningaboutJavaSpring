# Section 2

# 인터페이스 기본 메소드와 스태틱 메소드

## 기본 메소드(Default Methods)

- 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
- 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다.
- 기본 메소드는 구현체가 모르게 추가된 기능으로 그만큼 리스크가 있다.
  - 컴파일 에러는 아니지만 구현체에 따라 런타임 에러가 발생할 수 있다.
  - 반드시 문서화할 것. (@impleSpec 자바독 태그 사용)
- Object가 제공하는 기능(equals,hasCode)는 기본 메소드로 제공할 수 없다.
  - 구현체가 재정의해야 한다.
- 본인이 수정할 수 있는 인터페이스에만 기본 메소드를 제공할 수 있다.
- 인터페이스를 상속받는 인터페이스에서 다시 추상 메소드로 변경할 수 있다.
- 인터페이스 구현체가 재정의할 수 있다.

## 스태틱 메소드

- 해당 타입 관련 헬퍼 또는 유틸리티 메소드를 제공할 때 인터페이스에 스태틱 메소드를 제공할 수 있다.

[참고](https://docs.oracle.com/javase/tutorial/java/IandI/nogrow.html)
[참고2](https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)

# 자바 8 API의 기본 메소드와 스태틱 메소드

> 자바 8에서 추가한 기본 메소드로 인한 API 변화

## Iterable의 기본 메소드

- forEach()
- spliterator()

## Collection의 기본 메소드

- stream()/parallelStream()
- removeIf(Predicate)
- spilterator()

## Comparator의 기본 메소드 및 스태틱 메소드

- reversed()
- thenComparing()
- static reverseOrder()/naturalOrder()
- static nullsFirst()/ nullsLast()
- static comparing()

[Spliterator](https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html)
[Iterable](https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html)
[Collection](https://docs.oracle.com/javase/8/docs/api/java/util/Collection.html)
[Comparator](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)

---

# Stream 소개

## Stream

- sequence of elements supporting sequential and parallel aggregate operations
- 데이터를 담고 있는 저장소 (컬렉션)이 아니다.
- Funtional in nature, 스트림이 처리하는 데이터 소스를 변경하지 않는다.
- 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
- 무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한할 수 있다.)
- 중개 오퍼레이션은 근본적으로 lazy 하다.
- 손쉽게 병렬 처리할 수 있다.

## 스트림 파이프라인

- 0 또는 다수의 중개 오퍼레이션 (intermediate operation)과 한개의 종료 오퍼레이션 (terminal operation)으로 구성한다.
- 스트림의 데이터 소스는 오직 터미널 오퍼네이션을 실행할 때에만 처리한다.

## 중개 오퍼레이션

- Stream을 리턴한다.
- Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다. (대부분은 Stateless지만 distinct나 sorted 처럼 이전 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.)
- filter, map, limit, skip, sorted, ...

## 종료 오퍼레이션

- Stream을 리턴하지 않는다.
- collect, allMatch, count, forEach, min, max, ...

[package](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
[Strean](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
