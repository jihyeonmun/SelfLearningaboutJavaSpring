# Section 3

# Stream 소개

## Stream

- sequence of elements supporting sequential and parallel aggregate operations (Element들의 연속!)
- 데이터를 담고 있는 저장소 (컬렉션)이 아니다.
- Funtional in nature(근본적으로 functional 하다. -> 소스를 변경하지 않는다!), 스트림이 처리하는 데이터 소스를 변경하지 않는다.
- 스트림으로 처리하는 데이터는 오직 한번만 처리한다.

```java
public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("jihyeon");
        names.add("watson");
        names.add("jason");
        names.add("suzy");
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        names.forEach(System.out::println);
    }
}
```

- 무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한할 수 있다.<br>ex)처음 10개만 본다던가...)
- 중개 오퍼레이션은 근본적으로 lazy 하다. (중개형이라 터미널 전까지 안 나오는 것!)
- 손쉽게 병렬 처리할 수 있다.(??)

```java
public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("jihyeon");
        names.add("watson");
        names.add("jason");
        names.add("suzy");
        //parallelStream -> JVM이 알아서 병렬로 처리해줌!
        List<String> collect= names.parallelStream().map(String::toUpperCase)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
```

#### 병렬 쓰레드로 확인하기!

```java
public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("jihyeon");
        names.add("watson");
        names.add("jason");
        names.add("suzy");
        //parallelStream -> JVM이 알아서 병렬로 처리해줌!
        List<String> collect= names.parallelStream().map((s)->{
            System.out.println(s+" "+ Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
/*watson ForkJoinPool.commonPool-worker-19
jihyeon ForkJoinPool.commonPool-worker-23
jason main
suzy ForkJoinPool.commonPool-worker-5
JIHYEON
WATSON
JASON
SUZY
*/
```

> 쓰레드가 병렬적으로 할당된것을 확인할 수 있음!, stream으로 변경하면 모두 main임. 그러나 쓰레드를 쓴다고(병렬적으로 한다고) 속도가 빨라지는 것은 보장할 수 없음!

> 데이터가 정말 방대한 경우에서는 쓰는 것을 추천(쓰레드를 위해 공간을 마련하고 이를 처리하고 수집하면서 달라질 수 있기에 엄청 권장하지는 않는다!)

## 스트림 파이프라인

- 0 또는 다수의 중개 오퍼레이션 (intermediate operation)과 한개의 종료 오퍼레이션 (terminal operation)으로 구성한다.
- **스트림의 데이터 소스는 오직 터미널 오퍼네이션을 실행할 때에만 처리한다.**

```java
public class App {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("jihyeon");
        names.add("watson");
        names.add("jason");
        names.add("suzy");
        names.stream().map((s)->{
            System.out.println(s);
            return s.toUpperCase();
        //}); //중계형이라 불가!
        }).collect(Collectors.toList());//출력 가능!

        names.forEach(System.out::println);
    }
}
```

> 출력되어지지 않는다.!

## 중개 오퍼레이션

- Stream을 리턴한다.
- Stateless / Stateful 오퍼레이션으로 더 상세하게 구분할 수도 있다. (대부분은 Stateless지만 distinct나 sorted 처럼 이전 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션이다.)
- filter, map, limit, skip, sorted, ...

## 종료 오퍼레이션

- Stream을 리턴하지 않는다.
- collect, allMatch, count, forEach, min, max, ...

[package](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
[Strean](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)

---

## Stream Vs Collection

: Collection은 데이터를 모음으로 가지고 있다면, Stream은 이를 처리한다고 보면 된다!

# 9. Stream API

## 걸러내기

- Filter(Predicate)
- 예) 이름이 3글자 이상인 데이터만 새로운 스트림으로

## 변경하기

- Map(Function) 또는 FlatMap(Function)
- 예) 각각의 Post 인스턴스에서 String title만 새로운 스트림으로
- 예) List<Stream<String>>을 String의 스트림으로

## 생성하기

- generate(Supplier) 또는 Iterate(T seed, UnaryOperator)
- 예) 10부터 1씩 증가하는 무제한 숫자 스트림
- 예) 랜덤 int 무제한 스트림

## 제한하기

- limit(long) 또는 skip(long)
- 예) 최대 5개의 요소가 담긴 스트림을 리턴한다.
- 예) 앞에서 3개를 뺀 나머지 스트림을 리턴한다.

## 스트림에 있는 데이터가 특정 조건을 만족하는지 확인

- anyMatch(), allMatch(), nonMatch()
- 예) k로 시작하는 문자열이 있는지 확인한다. (true 또는 false를 리턴한다.)
- 예) 스트림에 있는 모든 값이 10보다 작은지 확인한다.

## 개수 세기

- count()
- 예) 10보다 큰 수의 개수를 센다.

## 스트림을 데이터 하나로 뭉치기

- reduce(identity, BiFunction), collect(), sum(), max()
- 예) 모든 숫자 합 구하기
- 예) 모든 데이터를 하나의 List 또는 Set에 옮겨 담기
