# 스프링 핵심 원리 기본편


본 저장소는 인프런에서 강의하는 김영한님의 "스프링 핵심 원리 기본편"을 수강하면서,
스프링을 깊게 이해하는 내용을 남기는 곳입니다.

## 기술

- https://start.spring.io
- Intelli J
- Gradle 7.0
- SpringBoot 2.5.1
- Java 11
- JUnit 5
- Packaging : Jar


## 목표

- 순수 자바 코드로 도메인을 개발할 때, SOLID 원칙이 잘 지켜지는 확인하자
- 스프링을 들어가기 전에, 객체 지향의 원리를 이해하자
- SOLID 원칙이 안 지켜질 때마다, 스프링 프레임워크의 도움을 받아보자
- 하나씩 스프링 기술을 접목 시키면서, 스프링의 필요성과 편리함을 이해하자
- 혼자서도 스프링으로 좋은 코드를 짤 때까지 반복 숙달하자




## 좋은 객체 지향 설계의 5가지 원칙 (SOLID)
클린코드로 유명한 로버트 마틴이 정리

> SRP: 단일 책임 원칙(single responsibility principle)
- 한 클래스는 하나의 책임만 가져야 한다.
- 변경이 있을 때, 파급 효과가 적으면 이 원칙을 잘 따른 것.

    
> OCP: 개방-폐쇄 원칙 (Open/closed principle)
- 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
  
  
> LSP: 리스코프 치환 원칙 (Liskov substitution principle)
- 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
  
  
> ISP: 인터페이스 분리 원칙 (Interface segregation principle)
- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
- 인터페이스가 명확해지고, 대체 가능성이 높아진다.


> DIP: 의존관계 역전 원칙 (Dependency inversion principle)
- 추상화에 의존해야지, 구체화에 의존하면 안된다.
- 변경이 아주 어려워지기 때문이다.






## DIP, OCP 위반 예시
```java
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 조회
        Member member = memberRepository.findById(memberId);
        int dicountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, dicountPrice);
    }
}
```

- OrderServiceImpl는 FixDiscountPolicy라는 구현 클래스에 의존하고 있으므로,
- RateDiscountPolicy라는 다른 구현 클래스로 변경하고자 할 때, (할인 정책 : 할인액->할인률)
- 해당 클래스를 변경해야 하는 문제가 발생한다.

## 출처

https://www.inflearn.com/course/스프링-핵심-원리-기본편



