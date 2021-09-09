package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // SpringDataJpa가 JpaRepository를 받고 있으면 SpringDataJpa가 구현체를 자동으로 만들어 준다.
    // SpringDataJpa가 스프링 Bean에 자동으로 등록해줌. 그냥 가져다 쓰면 된다.
    @Override
    Optional<Member> findByName(String name);
    // JPQL select m from Member m where m.name = ?
}
