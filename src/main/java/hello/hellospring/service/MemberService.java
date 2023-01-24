package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private MemberRepository memberRepository;

        public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 생성자 주입
    }

    /*
        회원가입
         */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원은 X
        /* Optional<Member> result = memberRepository.findByName(member.getName()); // member에 있는 이름을 찾아서 result에 저장
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 검증 통과하면 저장
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // member에 있는 이름을 찾아서
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

       /*
    전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

       /*
    한 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
