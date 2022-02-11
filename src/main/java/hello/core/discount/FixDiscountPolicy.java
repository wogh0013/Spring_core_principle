package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; //1000원 할인인

   @Override
    public int discount(Member member, int price) {
       if(member.getGrade() == Grade.VIP){ //enum은 ==을 써야 함.
           return discountFixAmount; //VIP는 1000원 할인
       } else {
           return 0;
       }
    }
}
