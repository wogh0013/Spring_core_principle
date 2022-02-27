package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        //기존 코드
        //기존에는 메인 메서드에서 인터페이스와 구현클래스를 모두 의존
        // MemberService memberService = new MemberServiceImpl();

        //변경 코드
        //변경 후에는 인터페이스(memberService)만 의존하고 있음. -> 매개체 역할 : appConfig
        //appConfig에서 memberService인터페이스를 주는데, 거기서 memberServiceImpl객체를 반환해준다.
        //반환해준 memberServiceImpl 객체의 매개변수가 MemoryMemberRepository()임!
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        //AppConfig의 설정 정보들을 가지고, AppConfig에 있는 객체들을 생성한 뒤에 스프링 컨테이너에
        //다 집어넣어서 관리해줌.
        //찾아올 때 기존에는 appConfig.memberService()로 직접 찾아왔다면, 지금은 스프링 컨테이너를 통해 찾아온다.
        //ApplicationContext는 스프링 컨테이너다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // -> memberService 이름은 appConfig의 메서드의 이름임
                                        // 이름이 memberService이고, 타입이 MemberService인 bean을 가져오라는 뜻이다.

        //회원가입하기
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //회원가입이 잘됐는지 확인하기
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
