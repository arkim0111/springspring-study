package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 동시성도 생각해야하는데, 예제라서 hashmap 사용
    private static long sequence = 0L; // 자동으로 0, 1 생성해주는 객체

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id에 +1 씩 추가한다
        store.put(member.getId(), member); // store에 id를 저장한다 -> map에 저장됨
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { // 아이디 찾는 기능 (실제 아이디가 아닌 시스템에서 저장할 때 등록됨)
        return Optional.ofNullable(store.get(id)); // optional 하면 null 일 때 감싸서 반환해준다.
    }

    @Override
    public Optional<Member> findByName(String name) { // 이름 찾는 기능
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // member의 getName과 일치하는 name 을 필터해서
                .findAny(); // 하나라도 찾아준다. 끝까지 찾았는데 없으면 null 반환
    }

    @Override
    public List<Member> findAll() { // 조건 걸어서 전체 리스트 나오게 하는 기능
        return new ArrayList<>(store.values());
    }

    public void clearStore() { // store 안에 저장되어 있는 것들을 모두 clear
        store.clear();
    }
}
