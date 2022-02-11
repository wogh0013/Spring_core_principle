package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        //기존 코드
        //기존에는 메인 메서드에서 인터페이스와 구현클래스를 모두 의존
        // MemberService memberService = new MemberServiceImpl();

        //변경 코드
        //변경 후에는 인터페이스(memberService)만 의존하고 있음. -> 매개체 역할 : appConfig
        //appConfig에서 memberService인터페이스를 주는데, 거기서 memberServiceImpl객체를 반환해준다.
        //반환해준 memberServiceImpl 객체의 매개변수가 MemoryMemberRepository()임!
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + findMember.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
