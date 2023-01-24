package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // <type, 엔티티에서 식별자 pk>
    // interface가 interface를 받을 때는 implements가 아닌 extends
    // interface는 다중 상속이 가능함
    // JpaRepository를 받고 있으면 스프링 데이터 jpa가 자동으로 빈을 생성해줌 (자기가 구현체를 만듦. 내가 빈 생성 안해줘도 됨)


    @Override
    Optional<Member> findByName(String name);
    // JPQL select m from Member m where m.name = ?
}
