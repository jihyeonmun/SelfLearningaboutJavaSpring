# Section4

# 애노테이션 프로세서

## 19. 롬북은 어떻게 동작하는 걸까?

### Lombok

● @Getter, @Setter, @Builder 등의 애노테이션과 애노테이션 프로세서를 제공하여 표준적으로 작성해야 할 코드를 개발자 대신 생성해주는 라이브러리.

- 롬북 프러그인 추가하고, annotation processors enable 하는 것을 습관화하자!

- 코드가 장황해지다 보니, 넘어가는 경우가 발생한다. 롬북은 내가 짠 비즈니스 로직만을 보여줘서 boilerplate code를 사전에 방지하도록 도움 준다!

```java
@Getter
@Setter
@EqualsAndHashCode
public class Member {
    private String name;
    private int age;
    public boolean isSameAge(Member member){
        return this.age == member.age;
    }
}


public class MemberTest {
    @Test
    public void getterSetter(){
        Member member = new Member();
        member.setName("jihyeon");

        Assert.assertEquals(member.getName(),"jihyeon");
    }
}
```

### 롬복 사용하기

● 의존성 추가

```java
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.8</version>
    <scope>provided</scope>
</dependency>
```

● IntelliJ lombok 플러그인 설치
● IntelliJ Annotation Processing 옵션 활성화

### 롬복 동작 원리

● 컴파일 시점에 애노테이션 프로세서를 사용하여 소스코드의 AST(abstract syntax tree)를 조작한다.

### 논란 거리

● 공개된 API가 아닌 컴파일러 내부 클래스를 사용하여 기존 소스 코드를 조작한다.
● 특히 이클립스의 경우엔 java agent를 사용하여 컴파일러 클래스까지 조작하여 사용한다. 해당 클래스들 역시 공개된 API가 아니다보니 버전 호환성에 문제가 생길 수 있고 언제라도 그런 문제가 발생해도 이상하지 않다.
● 그럼에도 불구하고 엄청난 편리함 때문에 널리 쓰이고 있으며 대안이 몇가지 있지만 롬복의 모든 기능과 편의성을 대체하진 못하는 현실이다.(아래 방식은 소스 코드르 수정하지 않고 다른 클래스를 만들어야 해서 정석에 가깝다.)

    ○	AutoValue
    ■	https://github.com/google/auto/blob/master/value/userguide/index.md
    ○	Immutables
    ■	https://immutables.github.io

참고
● https://docs.oracle.com/javase/8/docs/api/javax/annotation/processing/Processor.html
● https://projectlombok.org/contributing/lombok-execution-path
● https://stackoverflow.com/questions/36563807/can-i-add-a-method-to-a-class-from-a-compile-time-annotation
● http://jnb.ociweb.com/jnb/jnbJan2010.html#controversy
● https://www.oracle.com/technetwork/articles/grid/java-5-features-083037.html

---

## 20. 애노태이션 프로세서 1부

### Processor 인터페이스

● 여러 라운드(rounds)에 거쳐 소스 및 컴파일 된 코드를 처리 할 수 있다.

### 유틸리티

● AutoService: 서비스 프로바이더 레지스트리 생성기

```java
<dependency>
  <groupId>com.google.auto.service</groupId>
  <artifactId>auto-service</artifactId>
  <version>1.0-rc6</version>
</dependency>
```

```java
@AutoService(Processor.class)
public class MagicMojaProcessor extends AbstractProcessor {
...
}
```

● 컴파일 시점에 애노테이션 프로세서를 사용하여 META-INF/services/javax.annotation.processor.Processor 파일 자동으로 생성해 줌.

ServiceProvider
● https://itnext.io/java-service-provider-interface-understanding-it-via-code-30e1dd45a091

참고
● http://hannesdorfmann.com/annotation-processing/annotationprocessing101
● http://notatube.blogspot.com/2010/12/project-lombok-creating-custom.html
● https://medium.com/@jintin/annotation-processing-in-java-3621cb05343a
● https://medium.com/@iammert/annotation-processing-dont-repeat-yourself-generate-your-code-8425e60c6657
● https://docs.oracle.com/javase/7/docs/technotes/tools/windows/javac.html#processing

---

# 21. 애노태이션 프로세서 2부

## Filer 인터페이스

● 소스 코드, 클래스 코드 및 리소스를 생성할 수 있는 인터페이스

```java
@Override
public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);
    for (Element element : elements) {
        Name elementName = element.getSimpleName();
        if (element.getKind() != ElementKind.INTERFACE) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Magic annotation can not be used on " + elementName);
        } else {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing " + elementName);
        }

        TypeElement typeElement = (TypeElement)element;
        ClassName className = ClassName.get(typeElement);

        MethodSpec pullOut = MethodSpec.methodBuilder("pullOut")
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addStatement("return $S", "Rabbit!")
                .build();

        TypeSpec magicMoja = TypeSpec.classBuilder("MagicMoja")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(className)
                .addMethod(pullOut)
                .build();

        Filer filer = processingEnv.getFiler();
        try {
            JavaFile.builder(className.packageName(), magicMoja)
                    .build()
                    .writeTo(filer);
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "FATAL ERROR: " + e);
        }
    }
    return true;
}
```

## 유틸리티

● Javapoet: 소스 코드 생성 유틸리티

---

## 22. 애노태이션 프로세서 정리

### 애노테이션 프로세서 사용 예

● 롬복
● AutoService: java.util.ServiceLoader용 파일 생성 유틸리티
● @Override
○ https://stackoverflow.com/questions/18189980/how-do-annotations-like-override-work-internally-in-java/18202623
● Dagger 2: 컴파일 타임 DI 제공
● 안드로이드 라이브러리
○ ButterKinfe: @BindView (뷰 아이디와 애노테이션 붙인 필드 바인딩)
○ DeepLinkDispatch: 특정 URI 링크를 Activity로 연결할 때 사용

### 애노테이션 프로세서 장점

● 런타임 비용이 제로

### 애노테이션 프로세서 단점

● 기존 클래스 코드를 변경할 때는 약간의 hack이 필요하다.
