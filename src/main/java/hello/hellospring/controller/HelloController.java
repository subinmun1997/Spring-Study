package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // 웹 어플리케이션에서 /hello라고 들어오면 이 메서드 호출
    public String hello(Model model) {
        model.addAttribute("data","hello!");
        return "hello"; // templates/hello.html를 찾아서 화면을 실행시켜라
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http body부에 이 내용을 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체 넘김 JSON으로 반환
    }

    static class Hello {
        private String name; // private 선언이라 외부에서 접근x

        public String getName() { // 자바Bean 표준 방식 property 접근 방식
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
