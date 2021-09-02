package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원을 저장하면 저장된 회원 반환
    Optional<Member> findById(Long id); // id로 회원 찾기 Optional : 없으면 Null을 Optional로 감싸서 반환
    Optional<Member> findByName(String name); // name으로 회원 찾기
    List<Member> findAll();

}
