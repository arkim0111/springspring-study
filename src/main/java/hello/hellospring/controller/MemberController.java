package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

        @Autowired // 스프링 컨테이너를 만들 때 controller를 생성해줌.
    // 그 때 생성자를 호출하는데, 스프링 컨테이너가 memberservice(스프링 빈에 있음)랑 membercontroller를 연결을 시켜줌 => "의존성주입"
        public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
