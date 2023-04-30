package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

// RequestMapping은 메소드 단위로 적용되기 때문에 컨트롤러 하나로 통합
@Controller
// 클래스 단에서 설정한 RequestMapping의 url을 클래스 내 모든 메소드에서 사용한 RequestMapping url 앞에 붙여준다.
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form") // 해당 URL이 호출되면 이 메서드가 호출된다.
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    // /springmvc/v2/members, 메소드에 별도로 url을 지정해주지 않으면 클래스 단에 적용한 RequestMapping의 url이 그대로 내려온다.
    @RequestMapping
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}
