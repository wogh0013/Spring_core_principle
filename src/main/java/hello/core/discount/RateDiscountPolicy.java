package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int dicountPercent = 10;

    @Override
    //discount 커서 위에서 Ctrl+Shift+T 하면 자동으로 JUnit 테스트 생성
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * dicountPercent / 100; //그냥 단순 %계산해준 거임.
        } else{
            return 0;
        }
    }
}
