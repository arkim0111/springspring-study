package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // jpa는 entityManager가 다 처리한다
    // 라이브러리에 data-jpa 설치하면 스프링부트가 entityManager를 통해 다 알아서 해준다. 현재 데이터베이스랑 연결 다 해줌
    // 우리는 이것을 통해서 injection 받으면 됨! 내부적으로 데이터베이스랑 통신해서 연결해준다
    // 결론 : jpa 쓰려면 entityManager를 주입받아야 한다..!

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 영속적으로 저장해줌. id까지 다 셋팅해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);// find(조회할 타입, 식별자 pk)
        // id가 pk라서 jpql문을 작성할 필요가 없고, findByName이나 findAll은 jpql문 작성해야한다.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { // name이 있을 경우 하나만 추출
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // createQuery("jpql문", 조회할 타입)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // cmd + opt + n -> 인라인
        // jpql : sql은 테이블 대상으로 날리지만, 객체 대상으로 쿼리를 날린다. m은 멤버 엔티티 대상으로 가져옴.

       /* List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result; */
    }
}
