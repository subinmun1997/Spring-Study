package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional // jpa는 join될 떄 모든 데이터 변경이 Transaction 안에서 실행되어야 함
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired // MemberService를 스프링이 생성을 할 때 스프링 컨테이너에 있는 MemberRepository를 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // memberRepository를 직접 new해서 생성하는게 아니라 외부에서 넣어주도록 함
                                                  // 이것을 Dependency Injection (DI)라고 함
    }

    /*
    * 회원가입
    */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 검증 통과하면 저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 같은 이름이 있는 중복 회원은 안된다고 가정
        memberRepository.findByName(member.getName()) // findByName의 결과는 Optional
                .ifPresent(m -> { // 만약 존재한다면 (Null이 아니라면)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    * 전체 회원 조회
    */

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

