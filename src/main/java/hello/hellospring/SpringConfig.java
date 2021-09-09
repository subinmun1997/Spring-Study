package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    //private DataSource dataSource; // 스프링이 자체적으로 database와 연결할 수 있는 bean을 생성함

    //@Autowired
    //public SpringConfig(DataSource dataSource) { // 주입(DI)
        //this.dataSource = dataSource;
    //}
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean // 스프링이 뜰 때 @Configuration 읽고 @Bean을 보면 이건 스프링 빈에 등록하라는 것이네 하고 인식함
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 이 로직을 읽고 MemberService를 스프링 빈에 등록함
        /*
         * 스프링이 뜰 때 MemberService와 MemberRepository를 스프링 빈에 둘 다 등록하고
         * 스프링 빈에 등록되어있는 memberRepository를 MemberService에 넣어줌
         */

    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        /* 스프링을 사용하는 이유: 객체지향적인 설계가 좋다. 다형성을 활용한다.
         * 인터페이스를 두고 구현체를 바꿔끼는 기능을 굉장히 편리하게 하도록 스프링 컨테이너가 지원한다. (코드의 수정 없이)
         * 기존의 코드는 손대지 않고 애플리케이션 조립하는 코드의 간단한 수정만으로도 변경이 가능하다.
         */
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);


    }
}
