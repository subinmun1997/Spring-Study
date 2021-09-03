package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    // 스프링 컨테이너가 생성될 때 @Controller가 있으면 MemberController 객체를 생성해서 컨테이너에 넣어놓은다.
    // 스프링이 관리함

    // private final MemberService memberService = new MemberService();
    /*
     * 스프링이 관리를 하게되면 스프링 컨테이너에 등록하고 컨테이너로부터 받아서 쓰도록 바꿔야함
     * new로 생성해서 쓰게되면 MemberController말고도 MemberService를 생성해 쓰는 다른 컨트롤러가 있을 수 있다
     * 예를 들면 주문서비스...
     * 여러개 생성해서 쓰는 것보다 하나를 공용으로 사용하는게 good
     */
    private final MemberService memberService;

    @Autowired // @Autowired를 사용하면 MemberService를 스프링이 스프링 컨테이너에서 가져와 연결시켜준다.
    /*
     * memberController와 memberService 연결
     * Dependency Injection 의존 관계 주입
     */
    public MemberController(MemberService memberService) { // 생성자 주입
        this.memberService = memberService;
    }
}
