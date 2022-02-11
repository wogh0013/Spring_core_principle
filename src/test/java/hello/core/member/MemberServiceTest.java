package hello.core.member;

import hello.core.AppConfig;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //기존 코드
    //MemberService memberService = new MemberServiceImpl();

    //변경 코드
    MemberService memberService;

    @BeforeEach //테스트 코드 실행 전에 먼저 실행함
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given (~한 환경이 주어졌을 때)
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when (~했을 때)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then (~ 된다)
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
