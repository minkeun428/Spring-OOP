package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 조회
        Member member = memberRepository.findById(memberId);

        // 주문을 할 때, 할인이 변경되도 주문은 전혀 영향을 받지 않도록 설계함.
        // 단일 책임 원칙을 잘 지킨 것.
        int dicountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, dicountPrice);
    }
}
