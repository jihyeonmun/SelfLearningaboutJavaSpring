# Section 7

# 애노테이션의 변화

## 애노테이션 관련 두가지 큰 변화

- 자바 8부터 애노테이션을 타입 선언부에도 사용할 수 있게 됨.
- 자바 8부터 애노테이션을 중복해서 사용할 수 있게 됨.

## 타입 선언 부

- 제네릭 타입
- 변수 타입
- 매개변수 타입
- 예외 타입
- ...

## 타입에 사용할 수 있으려면

- TYPE_PARAMETER: 타입 변수에만 사용할 수 있다.
- TYPE_USE: 타입 변수를 포함해서 모든 타입 선언부에 사용할 수 있다.

## 중복 사용할 수 있는 애노테이션을 만들기

- 중복 사용할 애노테이션 만들기
- 중복 애노테이션 컨테이너 만들기
  - 컨테이너 애노테이션은 중복 애노테이션과 @Retention 및 @Target이 같거나 더 넓어야 한다.

## Chicken.java (중복 사용할 애노테이션)

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Repeatable(ChickenContainer.class)
public @interface Chicken {
    String value();
}
```

```java
ChickenContainer.java (중복 애노테이션의 컨테이너 애노테이션)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface ChickenContainer {

    Chicken[] value();
}
```

컨테이너 애노테이션으로 중복 애노테이션 참조하기

```java
@Chicken("양념")
@Chicken("마늘간장")
public class App {

    public static void main(String[] args) {
        ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });
    }

}
```

---

# 배열 Parallel 정렬

## Arrays.parallelSort()

- Fork/Join 프레임워크를 사용해서 배열을 병렬로 정렬하는 기능을 제공한다.

## 병렬 정렬 알고리듬

- 배열을 둘로 계속 쪼갠다.
- 합치면서 정렬한다.

sort()와 parallelSort() 비교

```java
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers);
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers);
        System.out.println("parallel sorting took " + (System.nanoTime() - start));
```

- serial sorting took 548957
- parallel sorting took 364074
- 알고리즘 효율성은 같다. 시간 O(n logN) 공간 O(n)

---

## Metaspace

JVM의 여러 메모리 영역 중에 PermGen 메모리 영역이 없어지고 Metaspace 영역이 생겼다.

## PermGen

- permanent generation, 클래스 메타데이터를 담는 곳.
- Heap 영역에 속함.
- 기본값으로 제한된 크기를 가지고 있음.
- -XX:PermSize=N, PermGen 초기 사이즈 설정
- -XX:MaxPermSize=N, PermGen 최대 사이즈 설정

## Metaspace

- 클래스 메타데이터를 담는 곳.
- Heap 영역이 아니라, Native 메모리 영역이다.
- 기본값으로 제한된 크기를 가지고 있지 않다. (필요한 만큼 계속 늘어난다.)
- 자바 8부터는 PermGen 관련 java 옵션은 무시한다.
- -XX:MetaspaceSize=N, Metaspace 초기 사이즈 설정.
- -XX:MaxMetaspaceSize=N, Metaspace 최대 사이즈 설정.

http://mail.openjdk.java.net/pipermail/hotspot-dev/2012-September/006679.html
https://m.post.naver.com/viewer/postView.nhn?volumeNo=23726161&memberNo=36733075
https://m.post.naver.com/viewer/postView.nhn?volumeNo=24042502&memberNo=36733075
https://dzone.com/articles/java-8-permgen-metaspace

---

# 요약

## 자바 8의 주요 기능의 의미

- 함수형 프로그래밍
  - 함수형 인터페이스
  - 람다 표현식
  - 메소드 레퍼런스
- 비동기 프로그래밍
  - CompletableFuture
- 편의성 개선
  - Optional
  - Date & Time
  - 인터페이스
  - ...

앞으로
당분간은 자바8이 가장 많이 쓰이는 버전으로 남을 가능성이 높다.
하지만 시간이 지날수록 계속해서 자바 11로 넘어가는 비율이 높아질 것이다.
따라서 자바 9부터 자바 11까지의 기능도 학습해 둘 것!
