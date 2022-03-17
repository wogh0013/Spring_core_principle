package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        //프로토타입 빈은 스프링 컨테이너에 요청할 때마다 새로 생성된다.
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);

        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        // prototypeBean1과 prototypeBean2는 다르다. (서로 다른 스프링 빈)

        //종료 메서드를 실행하려면 클라이언트가 직접 실행해야 함
        prototypeBean1.destroy();
        prototypeBean2.destroy();

        ac.close();
        //말 그대로 만들고 버림.
        //프로토타입 빈 -> 스프링 컨테이너가 생성과 의존성관계 주입, 초기화까지만 관여
        // -> 스프링 컨테이너가 종료될 때 @PreDestroy 같은 종료 메서드 실행 X
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
