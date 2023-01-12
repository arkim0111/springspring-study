package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach // 메소드 test가 끝날 때마다 repository clear 해주기
    public void afterEach() {
        repository.clearStore();
    }

        @Test
        public void save() {
            Member member = new Member();
            member.setName("spring");

            repository.save(member); // id = id , name = spring 저장

            Member result = repository.findById(member.getId()).get(); // result에 member의 id와 같은 id를 찾아서 넣어라
            Assertions.assertThat(member).isEqualTo(result); // member 와 result 값이 일치하는지 확인

        }

        @Test
        public void findByName() {
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            Member result = repository.findByName("spring1").get();
            Assertions.assertThat(result).isEqualTo(member1);
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
            Assertions.assertThat(result.size()).isEqualTo(2);
        }

}
