# 로또 게임

## 규칙
- 1등 : 6개 번호 모두 일치
- 2등 : 5개 번호 일치 + 나머지 1개가 보너스 번호 일치
- 3등 : 5개 번호 일치
- 4등 : 4개 번호 일치
- 5등 : 3개 번호 일치
- 로또 당첨 금액은 고정되어 있는 것으로 가정한다.
  - 1등 : 2,000,000,000원
  - 2등 : 30,000,000원
  - 3등 : 1,500,000원
  - 4등 : 50,000원
  - 5등 : 5,000원

## 구현할 기능 목록
- 로또 구입 금액을 입력받는다.
- 입력받은 구입 금액이 적절한 값인지 겅증해야 한다.
- 구입 금액에 해당하는 로또를 발급해야 한다(로또 1장의 가격은 1000원이다).
- 발급된 로또의 번호는 랜덤으로 정해지며 서로 다른 6개의 숫자로 구성되어야 한다.
- 당첨 번호를 입력받는다.
- 보너스 볼의 번호를 입력받는다.
- 로또 번호가 서로 다른 6개의 숫자인지 검증해야 한다.
- 로또 번호가 1 ~ 45 사이의 자연수인지 검증해야 한다.
- 구입 금액이 1,000의 배수인지 검증해야 한다.
- 규칙에 따른 당첨 여부를 확인해야 한다.
- 당첨금 총액을 계산해야 한다.
- 수익률을 계산해야 한다(%로 표시해도 된다).

## 피드백 및 개선사항
- 객체의 상태값을 객체 외부에서 비교하는 것을 조심하자.
- 메서드를 구현할 때 `정적 팩토리 메서드`를 이용하는 것 또한 고려해보자.
- 드문드문 보이는 `하드코딩`에 주의하자.
- 객체의 불변성과 안전성을 보장하기 위해 깊은 복사의 개념을 유의하도록 하자.
- 클래스의 크기가 커졌다면 세부적으로 클래스를 분리하는 것도 고려해보자.
- 예외를 처리할 때 발생할 수 있는 다양한 시나리오(`Stack Over flow` 등)에 대해서도 고려하도록 하자.
- [객체지향 생활 체조 원칙](https://developerfarm.wordpress.com/2012/02/03/object_calisthenics_summary/)의 규칙3을 적용할 수 있도록 노력해보자. 좀 더 객체지향에 가까워질 것이다.
- 코드의 가독성에 신경 쓰자(코드 컨벤션 참조).
- 화폐단위의 계산 또는 실수형 계산을 할 때는, `BigDecimal`을 사용하는 것이 좋다.
- 객체 생성 시 뿐만 아니라 입력을 받을 때도 유효성을 검증해주면 좀 더 안전한 설계가 될 것이다.
- 메서드 내에서만 사용되는 변수를 `전역 변수(static)`로 선언하는 것은 좋지 못하다.
- 테스트를 작성할 때 `@BeforeEach`등의 어노테이션을 활용할 수 있도록 하자.
- 특별히 비즈니스 로직이 있지 않다면 테스트 작성을 굳이 할 필요가 있는지에 대해 생각해보자.
- 확실하게 경우의 수가 존재하는 테스트의 경우, 모든 경우의 수를 검증하는 것이 좋을 것이다.
- 객체의 상태값으로 갖는 `Integer`도 `Wrapping`하게 된다면, 해당 상태에 대한 유효성 검증을 따로 분리할 수 있게 된다. 이는 [객체지향 생활 체조 총정리](https://developerfarm.wordpress.com/2012/02/03/object_calisthenics_summary/)에서 `규칙 3(모든 원시값과 문자열을 포장한다)`에 대한 이야기를 하는 것이다.
- `getter`로 가져온 값을 변경하여도 본래의 값에는 영향이 없도록 해야한다.
- 메서드 내에서만 사용되는 변수를 전역변수, static 영역으로 가져오는 것은 좋지 못하다.
- 비즈니스 로직이 없다면 테스트 작성을 과연 해야하는지에 대해서 고민해보자.
- 경우의 수가 확실하게 정해져있는 경우, 모든 경우의 수에 대해서 테스트를 하는 것이 신뢰도를 높일 수 있다.
- 리팩토링을 할 때, 테스트 코드 또한 리팩토링의 대상이다.
- 무작정 메서드 추출을 한다고해서 좋은 것이 아니다. 적절하게 사용해야 한다.
- `Arrays.asList()` 등의 팩토리 메서드를 적절히 이용하도록 하자.
- 적절한 변수 네이밍을 통해 불필요한 변수할당을 줄이도록 하자.
- 상수 역시 변수와 마찬가지로 의미있는 네이밍을 통해 가독성을 높이도록 하자.
- `Optional`을 좀 더 숙지하고 효율적으로 사용하도록 하자. 
  - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r610879882)
  - [Java Optional 바르게 쓰기](http://homoefficio.github.io/2019/10/03/Java-Optional-%EB%B0%94%EB%A5%B4%EA%B2%8C-%EC%93%B0%EA%B8%B0/)
- 메서드가 배치되는 순서 또한 가독성에 영향을 미친다. 일반적으로 `getter`, `setter` 는 최하단으로 내리며, 만약 `@Override`한 메서드가 있다면 해당 메서드가 `getter`, `setter` 보다 더 하단에 위치해야 한다. 그리고 호출되는 순서에 맞게 배치하는 것이 좋다.
- `equals`를 재정의하려거든 `hashCode`도 재정의 하도록 하자. - [아이템11 - equals를 재정의하려거든 hashCode도 재정의하라](https://rok93.tistory.com/entry/%EC%95%84%EC%9D%B4%ED%85%9C11-equals%EB%A5%BC-%EC%9E%AC%EC%A0%95%EC%9D%98%ED%95%98%EB%A0%A4%EA%B1%B0%EB%93%A0-hashCode%EB%8F%84-%EC%9E%AC%EC%A0%95%EC%9D%98%ED%95%98%EB%9D%BC)
- 자주 쓰이는 인스턴스 같은 경우, 미리 보관해뒀다가 바로 꺼내쓸 수 있게 하면 효율적이다. 캐싱 개념을 사용해보자. - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r610918693)
- `View`에 의존하는 코드란, `View`에 변화가 생겼을 때 `Model`에 영향을 미치게 되는 코드를 말한다. `View`와 `Model`은 독립적이어야 하며, 그렇지 않을 경우 `View`에 `Model`이 영향을 받게되는 상황(출력 포맷이 변한다던지 하는 변화)에 대응하기 위해 일일이 모든 코드를 수정하게 될 것이다. 따라서 `View`에 의존하는 코드를 작성하지 말아야 한다. 이는 `MVC 패턴`에 어긋난다. - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r610919595)
- `View`와 `Model`의 책임 소재를 명확하게 해야한다(`InputView`에서는 입력(요청)에 대한 모든 처리를 담당하는 식의..) - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r610992858)
- `Set`을 비롯한 자료구조를 사용하는 방법도 고려해보자. - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r610993439)
- 제대로 된 `VO(Value Object)` 객체가 무엇인지 알고 제대로 사용하자.
  - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r610993808)
  - [VO(Value Ojbect)란 무엇일까?](https://woowacourse.github.io/javable/post/2020-06-11-value-object/)
- `정적 팩토리 메서드`는 생성자의 다음 위치에 있어야 한다.
- 조건의 변경에 따라 상수명에 변화가 없도록 적절한 상수명을 고려하도록 하자. 상수명에 조건이 들어간다면 조건이 변경될 때 상수명도 변경되어야 할 것이다.
- 테스트하기 힘든 것을 최대한 테스트할 수 있도록 분리함으로써 안정성을 보장하려고 노력하자. 
  - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r611003230)
  - [인터페이스를 분리하여 테스트하기 좋은 메서드로 만들기](https://woowacourse.github.io/javable/post/2020-05-17-appropriate_method_for_test_by_interface/)
  - [무엇을 테스트할 것인가? 어떻게 테스트할 것인가? (권용근)](https://www.youtube.com/watch?v=YdtknE_yPk4&t=639s)
- 객체에게 메시지를 보내자!!!
  - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r611005913)
  - [디미터 법칙](https://woowacourse.github.io/javable/post/2020-06-02-law-of-demeter/)
- 객체를 생성하는 `정적 팩토리 메서드`를 사용함에 있어서, 기존의 생성자를 외부에서 사용하지 않게 될 경우 해당 생성자를 `private`로 접근 제한을 해야한다. 이는 해당 코드를 사용해야하는 개발자들에게 혼란을 야기할 수 있기 때문이다. 그리고 객체를 생성하는 경우의 수가 하나뿐인데 굳이 `정적 팩토리 메서드`를 사용해야 하는지에 대해 충분히 고민해보고 사용하도록 하자.
  - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r611008912)
  - [정적 팩토리 메서드(Static Factory Method)는 왜 사용할까?](https://woowacourse.github.io/javable/post/2020-05-26-static-factory-method/)
- 가능한한 노가다(...)식 코딩은 지양하고 적절한 파라미터의 상정을 통해 추상화하도록 노력하자. - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r611009831)
- `View`단에서 도메인 로직이 수행되는지 잘 검증하도록 하자. `View`단은 단순히 결과를 파라미터로 받아와서 출력하는 역할만 하는 것이 바람직하다. - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r611010047)
- 테스트시 불필요한 어노테이션을 추가하는 것보다는 적절한 메서드 선언을 통해 처리해주는게 좋지 않을까? - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r611010616)
- 하나의 테스트코드에서 여러 테스트를 동시에 진행할 경우에는 가급적 `assertAll()`을 사용해주는 것이 좋다. - [Assertion 메소드(assertTrue, assertEquals, assertAll 등)](https://sas-study.tistory.com/316)
- 테스트 케이스를 작성할 때, 오름차순 또는 내림차순 등으로 가독성이 좋게 작성하도록 하자.
- `private` 메서드는 실제로 호출되는 위치에 가까이 있어야 한다. 이는 가독성에 큰 영향을 미칠 확률이 높다.
- `@ParameterizedTest`와 적절한 `@xxxSource` 어노테이션을 이용해서 테스트를 해보자.
  - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r611012978)
  - [JUnit5 사용법 - Parameterized Tests](https://gmlwjd9405.github.io/2019/11/27/junit5-guide-parameterized-test.html)
- public 메서드를 private 메서드보다 더 위에 명시해주도록 하자.
- 반복적으로 사용되는 인스턴스는 캐싱하여 사용하도록 해보자. 
  - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r613224437)
  - [반복적으로 사용되는 인스턴스는 캐싱하기](https://woowacourse.github.io/javable/post/2020-06-24-caching-instance/)
- `try-catch` 문으로 예외처리할 때, 만약 재귀의 형태로 처리한다면 `StackOverflow`가 발생할 수 있으므로 주의하여 반복문이나, `꼬리 재귀` 등과 같은 형태로 코드를 작성하도록 하자.
  - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r613380223)
  - [반복문(iteration) vs 재귀(recursion)](https://woowacourse.github.io/javable/post/2020-04-30-iteration_vs_recursion/)
- `MVC`패턴에서 `View`와 `Model`은 `Controller`를 통해서 객체를 주고받아야 한다. 그러니까, `View`와 `Model`은 서로를 몰라야 한다. 즉, `View`에서 바로 `Model` 객체를 리턴하면 안된다는 뜻이다.
  - [해당 코멘트](https://github.com/banjjoknim/java-lotto-game/pull/2#discussion_r613664458)
- 예외처리나 단순한 출력 문자열의 경우, 상수로 사용하지 말고 하드코딩을 해도 괜찮을 것 같다. 다만, `자주 사용된다`, `의미를 파악하기 어렵다` 라는 두 가지 경우에 해당하는 경우 상수로 처리하는 것이 적절해보인다.
- 테스트 코드의 경우에는 번거롭게 상수 추출을 하지않아도 될 것 같다.
- 적절한 `일급컬렉션`의 사용을 고려해보자.
- `전략 패턴`등을 사용하는 것은 좋지만, 목적을 명확하게 하고 그에 맞게 사용하도록 하자(`Lotto`를 만들고 싶은건지, `LottoNumber`를 만들고 싶은건지...).
- 숫자형의 경우, 중간에 `_`를 추가해도 동일한 값으로 본다. 가독성을 높일 수 있게 작성하도록 해보자.





## 참고자료
- [디자인 패턴 : 전략패턴이란?](https://velog.io/@kyle/%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4-%EC%A0%84%EB%9E%B5%ED%8C%A8%ED%84%B4%EC%9D%B4%EB%9E%80)
- [정적 메소드, 너 써도 될까?](https://woowacourse.github.io/javable/2020-07-16/static-method)
- [얕은 복사 VS 깊은 복사](https://rok93.tistory.com/entry/%EC%96%95%EC%9D%80%EB%B3%B5%EC%82%AC-VS-%EA%B9%8A%EC%9D%80%EB%B3%B5%EC%82%AC)
- [BigDecimal이란?](https://12soso12.tistory.com/12)
- [BigDecimal (feat. 소수점 계산)](https://ellune.tistory.com/25)
- [A Guide to JUnit 5](https://www.baeldung.com/junit-5#1-beforeall-and-beforeeach)
- [07-4 예외처리 (Exception)](https://wikidocs.net/229)
- [@BeforeAll and @BeforeEach](https://www.baeldung.com/junit-5#1-beforeall-and-beforeeach)
- [Java Optional 바르게 쓰기](http://homoefficio.github.io/2019/10/03/Java-Optional-%EB%B0%94%EB%A5%B4%EA%B2%8C-%EC%93%B0%EA%B8%B0/)
- [아이템11 - equals를 재정의하려거든 hashCode도 재정의하라](https://rok93.tistory.com/entry/%EC%95%84%EC%9D%B4%ED%85%9C11-equals%EB%A5%BC-%EC%9E%AC%EC%A0%95%EC%9D%98%ED%95%98%EB%A0%A4%EA%B1%B0%EB%93%A0-hashCode%EB%8F%84-%EC%9E%AC%EC%A0%95%EC%9D%98%ED%95%98%EB%9D%BC)
- [VO(Value Ojbect)란 무엇일까?](https://woowacourse.github.io/javable/post/2020-06-11-value-object/)
- [인터페이스를 분리하여 테스트하기 좋은 메서드로 만들기](https://woowacourse.github.io/javable/post/2020-05-17-appropriate_method_for_test_by_interface/)
- [무엇을 테스트할 것인가? 어떻게 테스트할 것인가? (권용근)](https://www.youtube.com/watch?v=YdtknE_yPk4&t=639s)
- [디미터 법칙](https://woowacourse.github.io/javable/post/2020-06-02-law-of-demeter/)
- [정적 팩토리 메서드(Static Factory Method)는 왜 사용할까?](https://woowacourse.github.io/javable/post/2020-05-26-static-factory-method/)
- [JUnit5 사용법 - Parameterized Tests](https://gmlwjd9405.github.io/2019/11/27/junit5-guide-parameterized-test.html)
- [Assertion 메소드(assertTrue, assertEquals, assertAll 등)](https://sas-study.tistory.com/316)
- [반복적으로 사용되는 인스턴스는 캐싱하기](https://woowacourse.github.io/javable/post/2020-06-24-caching-instance/)
- [반복문(iteration) vs 재귀(recursion)](https://woowacourse.github.io/javable/post/2020-04-30-iteration_vs_recursion/)





---
