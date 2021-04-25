# Section 4

# Optional 소개

```java
public class Progress {
    private Duration studyDuration;
    private boolean finished;
    public Duration getStudyDuration(){
        return studyDuration;
    }
    public void setStudyDuration(Duration studyDuration){
        this.studyDuration = studyDuration;
    }

}

public class OnlineClass {
    private Integer id;
    private String title;
    private boolean closed;
    public Progress progress;
    public OnlineClass(Integer id, String title, boolean closed){
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

        public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}

public class App {
    public static void main(String[] args) {
        List<OnlineClass> classes = new ArrayList<>();
        classes.add(new OnlineClass(1, "spring boot", true));
        classes.add(new OnlineClass(2, "spring data jpa", true));
        classes.add(new OnlineClass(3, "spring mvc", false));
        classes.add(new OnlineClass(4, "spring core", false));
        classes.add(new OnlineClass(5, "rest api development", false));

        OnlineClass spring = new OnlineClass(1,"spring boot", true);
        Duration studyDuration = spring.getProgress().getStudyDuration();
        System.out.println(studyDuration);
    }
}

```

> NullPointException 발생한다!!(Progress가 null이라서!)

자바 프로그래밍에서 NullPointerException을 종종 보게 되는 이유

- null을 리턴하니까! && null 체크를 깜빡했으니까!

메소드에서 작업 중 특별한 상황에서 값을 제대로 리턴할 수 없는 경우 선택할 수 있는 방법

- 예외를 던진다. (**비싸다, 스택트레이스를 찍어두니까.**)

```java
public Progress getProcess(){
    if(this.progress==null){
        throw new IllegalStateException();
    }
    return progress;
}
```

- null을 리턴한다. (비용 문제가 없지만 그 코드를 사용하는 클리어인트 코드가 주의해야 한다.)
- (자바 8부터) Optional을 리턴한다. (클라이언트에 코드에게 명시적으로 빈 값일 수도 있다는 걸 알려주고, 빈 값인 경우에 대한 처리를 강제한다.)

```java
public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }
```

> Optional이라는 박스를 만들어서 객체를 담는다. null일 수도 있고 들어있을수도 있다.
> ofNullable넣는 타입이 null일 수가 있다!
> of는 null이면 안됨!! 그래서 ofNullable 선호함!

## Optional

- 오직 값 한 개가 들어있을 수도 없을 수도 있는 컨네이너.

## 주의할 것

- 리턴값으로만 쓰기를 권장한다. (메소드 매개변수 타입, 맵의 키 타입->맵의 가장 큰 특징은 키는 널이 아니다인데 이를 깨면 안됨!, 인스턴스 필드 타입으로 쓰지 말자.-> 있을수도 있고 없을 수도 있다.)

```java
public void setProgress(Optional<Progress> progress){
    if(progress != null ){
        progress.isPresent(p -> this.progress = p);
    }
}
```

> 가능은 한데, 체크 해야한다. 위처럼 해도 이 또한 위험....(이해가 잘 안된다.)

- Optional을 리턴하는 메소드에서 null을 리턴하지 말자.
- 프리미티브 타입용 Optional을 따로 있다. OptionalInt, OptionalLong,...
- Collection, Map, Stream Array, Optional은 Opiontal로 감싸지 말 것.

[Optional](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
[Java8-Optional](https://www.oracle.com/technical-resources/articles/java/java8-optional.html)
이팩티브 자바 3판, 아이템 55 적절한 경우 Optional을 리턴하라.

---

# Optional API

## Optional 만들기

- Optional.of()
- Optional.ofNullable()
- Optional.empty()

## Optional에 값이 있는지 없는지 확인하기

- isPresent()
- isEmpty() (Java 11부터 제공)

## Optional에 있는 값 가져오기

- get()
- 만약에 비어있는 Optional에서 무언가를 꺼낸다면??

## Optional에 값이 있는 경우에 그 값을 가지고 ~~를 하라.

- ifPresent(Consumer)
- 예) Spring으로 시작하는 수업이 있으면 id를 출력하라.

## Optional에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라.

- orElse(T)
- 예) JPA로 시작하는 수업이 없다면 비어있는 수업을 리턴하라.

## Optional에 값이 있으면 가져오고 없는 경우에 ~~를 하라.

- orElseGet(Supplier)
- 예) JPA로 시작하는 수업이 없다면 새로 만들어서 리턴하라.

## Optional에 값이 있으면 가졍고 없는 경우 에러를 던져라.

- orElseThrow()

## Optional에 들어있는 값 걸러내기

- Optional filter(Predicate)

## Optional에 들어있는 값 변환하기

- Optional map(Function)
- Optional flatMap(Function): Optional 안에 들어있는 인스턴스가 Optional인 경우에 사용하면 편리하다.
