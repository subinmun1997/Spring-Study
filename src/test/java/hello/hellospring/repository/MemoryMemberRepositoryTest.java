package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { // 콜백 메소드 메소드가 끝날때마다 어떤 동작을 하는 것
        repository.clearStore();
    }
    // 테스트는 순서에 의존하지 않고 실행이 돼야한다.
    // TDD : 테스트 주도 개발 테스트를 먼저 만들고 구현 클래스를 만들어 실행시켜보는 것
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring!!!");

        repository.save(member); // repository에 저장

        Member result = repository.findById(member.getId()).get(); // repository에 잘 저장되었는지 id로 찾아서 확인
        assertThat(result).isEqualTo(member); // member객체에 저장한 값과 db에서 꺼낸 값과 같으면 true
        //System.out.println("result = " + (result == member));
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // .get() 하면 Optional 한번 까고 꺼냄
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
