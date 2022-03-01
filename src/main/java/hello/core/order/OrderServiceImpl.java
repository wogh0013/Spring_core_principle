package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Test 만드는 법 = Crtl + Shift + T
//주문 서비스 구현체 클래스
@Component
public class OrderServiceImpl implements OrderService{

    //회원 저장소, 할인 정책 객체 생성
    //기존 코드
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //변경 코드
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId);

        //단일체계원칙이 잘 지켜짐 -> 나중에 할인정책이 바뀌어도 Order쪽은 수정할 필요X -> 할인쪽만 수정!!
        //할인에 member 자체를 넘길지, 등급만 넘길지는 프로젝트에서 결정할 일
        int discountPrice = discountPolicy.discount(member, itemPrice);

        //주문 객체를 생성해 결과값으로 반환한다.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
