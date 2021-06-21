package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 공연 기획자 역할
 * 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성" 한다.
 * 생성한 객체 인스턴스의 참조를 "생성자를 통해서 주입(연결)" 해준다.
 * 메서드를 통해 역할과 구현 클래스가 한눈에 보인다.
 * */
@Configuration
public class AppConfig {


    // 메서드를 주입
    @Bean   // IoC컨테이너에 등록
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 메서드가 구현
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
