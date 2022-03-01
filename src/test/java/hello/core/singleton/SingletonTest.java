package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

//스프링 없는 순수한 DI 컨테이너인 AppConfig는 새로운 요청이 올 때마다 객체 생성
// -> 메모리 낭비
// -> 해당 객체가 딱 1개만 생성되고, 공유하도록 설계 (싱글톤 패턴)
public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인 (둘이 다른 객체이다라는 것을 확인)
        //이건 단순 출력해서 눈으로 확인하는 용도일 뿐임. 검증을 해야 함.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //검증(자동화)
        // memberService1 != memberService2
        //Assertions.assertThat(memberService1).isNotSameAs(memberService2);
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // singletonService1 == singletonService2
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

        //AppConfig appConfig = new AppConfig(); -> 얘를 스프링 형태로 바꿈
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것을 확인 (둘이 다른 객체이다라는 것을 확인)
        //이건 단순 출력해서 눈으로 확인하는 용도일 뿐임. 검증을 해야 함.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //검증(자동화)
        // memberService1 == memberService2
        //Assertions.assertThat(memberService1).isNotSameAs(memberService2);
        assertThat(memberService1).isSameAs(memberService2);
    }
}

