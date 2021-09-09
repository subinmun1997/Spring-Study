package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // JPA는 EntityManager라는 것으로 모든게 동작함
    /*
     * jpa library를 받으면 스프링 부트가 현재 데이터베이스와 연결돼있는 EntityManager를 자동으로 생성해준다.
     * 만들어진 것을 injection 받으면 됨
     * 즉, jpa를 사용하려면 EntityManager를 주입받아야 한다.
     */

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // jpa가 insert 쿼리 만들어서 DB에 넣고 Member에 setId까지 다 해준다.
         return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회할 타입, 식별자(PK)
        return Optional.ofNullable(member);
    }

    /*
     * id(PK) 같은 경우는 위와 같이 조회가 가능한데
     * findByName은 JPQL이라는 객체지향 쿼리를 사용해야한다.
     *
     */

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // JPQL
                .getResultList();
        // JPQL : 객제를 대상으로 쿼리를 날리는 것(엔티티를 대상으로 쿼리를 날림)
        // select m: Member 엔티티 자체를 select
    }
}
