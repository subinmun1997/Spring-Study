package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository(); // 각 테스트를 실행하기 전에 MemoryMemberRepository를 만들고
        memberService = new MemberService(memberRepository); // memberService에 넣어줌
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() { // 테스트는 메소드명 한글로 가능 ex) void 회원가입() {}
        //given (무언가가 주어졌을 때)
        Member member = new Member();
        member.setName("hello");

        //when (이거를 실행했을 때)
        Long saveId = memberService.join(member);

        //then (결과가 이렇게 나와야 돼)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); // 이 메소드를 태울때 IllegalStateException 클래스가 터져야됨
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}