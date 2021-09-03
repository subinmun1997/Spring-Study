package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

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
        return new MemoryMemberRepository();
    }
}
