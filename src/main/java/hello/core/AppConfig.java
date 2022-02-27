package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//저장소나 할인정책을 변경하고 싶으면 -> AppConfig만 변경해도 된다 !!
//DIP 만족
//OCP 만족
@Configuration
public class AppConfig {

    //리팩토링 전
//    //AppConfig에서 각 MemberServiceImpl, OrderServiceImpl의 생성자의 매개변수(인터페이스가 들어가있음)를 통해
//    //각 인터페이스의 구현클래스를 주입시킨다.
//    public MemberService memberService(){
//        //memberService를 불러다 쓰면 그 구현체인 MemberServiceImpl이 생성
//        // -> MemoryMemberRepository가 매개변수로 전달됨
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }

    //리팩토링 후
        //역할 부여
        @Bean
        public MemberService memberService(){
            return new MemberServiceImpl(memberRepository());
        }

        //구현 클래스
        @Bean
        public MemberRepository memberRepository(){
            //나중에 저장소를 변경하고 싶으면 이 코드만 수정해주면 됨.
            return new MemoryMemberRepository();
        }

        //역할 부여
        @Bean
        public OrderService orderService(){
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }

        //구현 클래스
        @Bean
        public DiscountPolicy discountPolicy(){
            //나중에 할인정책을 변경하고 싶으면 이 코드만 수정해주면 됨.
            //return new FixDiscountPolicy();
            return new RateDiscountPolicy();
        }


}
