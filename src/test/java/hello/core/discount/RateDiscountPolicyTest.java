package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    //DIP : 의존관계 주입이 명확해야 한다.
    //OCP : 확장은 가능, 변경은 불가능.
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.") //이 어노테이션은 한글 가능
    void vip_o(){ //성공 TEST
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        //Assertions.assertThat(discount).isEqualTo(1000);
        // └ Alt + Enter 로 static import를 해준다 !!
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 10% 할인이 적용되지 않아야 한다.")
    void vip_x(){ // 실패 TEST
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        //assertThat(discount).isEqualTo(1000); //이렇게 하면 당연히 실패!
        assertThat(discount).isEqualTo(0);
    }
}