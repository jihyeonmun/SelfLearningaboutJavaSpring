#Section 1

[참고 링크](https://github.com/whiteship/java8)

## 자바 8

- LTS 버전
- 출시일 : 2014년 3월(출시까지 3년반이 걸렸음)
- 현재 자바 개발자 중 83%가 사용중

### LTS(Long-Term-Support)와 비-LTS 버전의 차이

- 비-LTS는 업데이트 제공 기간이 짧다.
- 비-LTS 배포 주기 6개월
- 비-LTS 지원 기간은 배포 이후 6개월
- LTS 배포 주기 3년 (매 6번째 배포판이 LTS가 된다.)
- LTS 지원 기간은 5년이상으로 JDK를 제공하는 밴더와 이용하는 서비스에 따라 다르다.
- 실제 서비스 운영 환경(production)에서는 LTS 버전을 권장한다.
- [Moving Java Forward Faster-Mark Reinhold](https://mreinhold.org/blog/forward-faster)
  > 자바를 다양한 feature에 넣으면서 배포주기도 빨라지고, 기능도 추가하고, LTS도 관리하며, 안정화시키는 것!
  > 자바를 조금 더 계속해서 패러다임에 맞춰나가는 것. 안정화시키는 작업이라고 봄
  > 자바 9버전 이후 부터는 6개월마다 출시되고 있음.
  > 8,11을 제외하곤 비-LTS다 보니 8의 점유율이 상당히 높다.
- 다음 LTS: 자바 17
- 매년 3월과 9월에 새 버전 배포

## 주요 기능

- 람다 표현식
- 메소드 레퍼런스
- 스트림 API
- Optional<T>

# 2부 함수형 인터페이스와 람다 현식

## 2. 함수형 인터페이스와 람다 표현식 소개

### 함수형 인터페이스(Functional Interface)

- 추상 메서드를 딱 하나만 가지고 있는 인터페이스(중요)
  - 두 개가 있으면 안된다! (abstract 생략되어있음)
  - public, static의 경우도 적용 가능함.

```java
@FunctionalInterface
public interface RunSomething {
    void doIt();

    static void printName(){
        System.out.println("Jihyeon");
    }
    default void printAge(){
        System.out.println("40");
    }
}
```

> 추상메서드가 몇개인지가 중요하다.(여기서는 1개)

- SAM(Single Abstract Method) 인터페이스
- @FunctionalInterface 애노테이션을 가지고 있는 인터페이스

### 람다 표현식(Lambda Expressions) ->option + enter 누르면 람다로 변환 가능!!

```java
public class Foo {
    public static void main(String[] args){
        //익명 내부 클래스
        RunSomething runSomething = () -> {
            System.out.println("Hello");
            System.out.println("Lambda");
        };
    }
}
```

> 특수한 형태의 오브젝트로 보면 된다!

- 함수형 인터페잇의 인스턴스를 만드는 방법으로 쓰일 수 있다.
- 코드를 줄일 수 있다.
- 메소드 매개변수, 리턴 타입, 변수로 만들어 사용할 수 있다.

### 자바에서 함수형 프로그래밍

- 함수를 First Class Object로 사용할 수 있다.
- 순수함수(Pure function)
  - 사이드 이펙트가없다.(함수 밖에 있는 값을 변경하지 않는다.)
  - 상태가 없다.(함수 밖에 있는 값을 사용하지 않는다.)
    > 함수 밖의 변수(외부변수)를 활용하는 경우 순수한 함수라고 보기 어렵다.
    > 익명 내부 클래스안이면서 함수 밖의 변수이면 문법적으로 문제가 없기는 하지만, 그럼에도 불구하고 순수한 함수라고 보긴 어려움.
    > 또한 외부 변수를 final로 가정하기에, 함수 외부에서 또다른 조작은 하면 안된다!
- 고차 함수(Higher-Order Function)
  - 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수도 있다.
- 불변성

```java
public class Foo {
    public static void main(String[] args){
        //익명 내부 클래스
        RunSomething runSomething = (number) -> {
            return number + 10;
        };
        System.out.println(runSomething.doIt(1));
        System.out.println(runSomething.doIt(1));
    }
}
```

> 같은 값을 넣은 경우 같은 결과가 나오는 것도 함수의 역할이다! (여기서는 doit을 int로 리턴함)

## 3. 자바에서 제공하는 함수형 인터페이스

### Java가 기본으로 제공하는 함수형 인터페이스

- java.util.function 패키지
- 자바에서 미리 정의해둔 자주 사용할만한 함수 인터페이스
- Function <T,R>
- BiFunction <T,U,R>
- Consumer <T>
- Supplier<T>
- Predicate<T>
- UnaryOperator<T>
- BinaryOperator<T>

#### Function<T,R>

- T타입을 받아서 R 타입으로 리턴하는 함수 인터페이스
  - R apply(T t)
- 함수 조합용 메소드
  - andThen
  - compose

#### BiFunction<T,U,R>

- 두 개의 값(T,U)를 받아서 R 타입을 리턴하는 함수 인터페이스
  - R apply(T t, U u)

#### Consumer<T>

- T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
  - void Accept(T t)
- 함수 조합용 메소드
  - andThen

#### Supplier<T>

- T 타입의 값을 제공하는 함수 인터페이스
  - T get()

#### Predicate<T>

- T 타입을 받아서 boolean을 리턴하는 함수 인터페이스
  - boolean test(T t)
- 함수 조합용 메소드
  - And
  - Or
  - Negate

#### UnaryOperator<T>

- Function<T,R>의 특수한 형태로, 입력값 하나를 받아서 동일한 타입을 리턴하는 함수 인터페이스

#### BinaryOperator<T>

- BiFunction<T,U,R>의 특수한 형태로, 동일한 타입의 입력값 두개를 받아 리턴하는 함수 인터페이스

# 4. 람다 표현식

## 람다

- (인자 리스트) -> {바디}

## 인자리스트

- 인자가 없을 때 : ()
- 인자가 한개일 때 : (one) 또는 one
- 인자가 여러개 일 때 : (one,two)
- 인자의 타입은 생략 가능, 컴파일러가 추론하지만 명시할 수 도 있다. (Integer one, Integer two)

## 바디

- 화살표 오른쪽에 함수 본문을 정의한다.
- 여러 줄인 경우에 {}를 사용해서 묶는다.
- 한 줄인 경우에 생략 가능, return도 생략 가능

## 변수 캡쳐(variable Capture)

- 로컬 변수 캡처

  - final이거나 effective final인 경우에만 참조할 수 있다.
  - 그렇지 않을 경우 concurrency 문제가 생길 수 있어서 컴파일러가 방지한다.

- effective final
  - 자바 8부터 지원하는 기능르로 "사실상"final인 변수
  - final 키워드 사용하지 않은 변수를 익명 클래스 구현체 또는 람다에서 참조할 수 있다.
- 익명 클래스 구현체와 달리 shadowing하지 않는다.
  - 익명 클래스는 새로 scope을 만들지만, 람다는 람다를 감싸고 있는 scope과 같다.

[참고 1](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html#shadowing)
[참고 2](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)

# 메소드 레퍼런스

람다가 하는 일이 기존 메소드 또는 생성자를 호출하는 거라면, 메소드 레퍼런스를 사용해서 매우 간결하게 표현할 수 있다.

### 메소드 참조하는 방법

| 스태틱 메소드 참조               | 타입:스태틱 메소드             |
| -------------------------------- | ------------------------------ |
| 특정 개게의 인스턴스 메소드 참조 | 객체 레퍼런스::인스턴스 메소드 |
| 임의 객체의 인스턴스 메소드 참조 | 타입::인스턴스 메소드          |
| 생성자 참조                      | 타입::new                      |

- 메소드 또는 생성자의 매개변수로 람다의 입력값을 받는다.
- 리턴값 또는 생성한 객체는 람다의 리턴값이다.
  [참고](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html)
