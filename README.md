# quarkus--inject-mock-jwt

reproducer for [47739](https://github.com/quarkusio/quarkus/issues/47739) with `@InjectMock JsonWebToken jwt`

## 3.21.3

[JwtBeanInjectMockTest](src/test/java/org/acme/JwtBeanInjectMockTest.java) works fine

```
mvn test

[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
```

## 3.21.4, 3.22.1

change version in [pom.xml](pom.xml#L18)

[JwtBeanInjectMockTest](src/test/java/org/acme/JwtBeanInjectMockTest.java) does not work any more.
However there is a workaround [JwtBeanInjectMockWorkaroundTest](src/test/java/org/acme/JwtBeanInjectMockWorkaroundTest.java) still running which installs the mock dynamically with `QuarkusMock.installMockForType()`.

```
mvn test

[ERROR] Errors: 
[ERROR]   JwtBeanInjectMockTest.shouldReturnName » TestInstantiation Failed to create test instance
[INFO] 
[ERROR] Tests run: 4, Failures: 0, Errors: 1, Skipped: 0

org.junit.jupiter.api.extension.TestInstantiationException: Failed to create test instance
        at io.quarkus.test.junit.QuarkusTestExtension.initTestState(QuarkusTestExtension.java:767)
        at io.quarkus.test.junit.QuarkusTestExtension.interceptTestClassConstructor(QuarkusTestExtension.java:733)
        at java.base/java.util.Optional.orElseGet(Optional.java:364)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
Caused by: jakarta.enterprise.context.ContextNotActiveException: RequestScoped context was not active when trying to obtain a bean instance for a client proxy of PRODUCER_METHOD bean [class=io.quarkus.smallrye.jwt.runtime.auth.JwtPrincipalProducer, id=BxDM8UQSQwnNG8ZE6VKFZ8wrsgg]
        - you can activate the request context for a specific method using the @ActivateRequestContext interceptor binding
```

