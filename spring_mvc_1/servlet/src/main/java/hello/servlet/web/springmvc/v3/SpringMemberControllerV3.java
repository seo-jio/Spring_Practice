package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping(value = "/new-form", method = RequestMethod.GET) // Get 요청만 처리하도록 제한
    @GetMapping("/new-form") // 위 주석 처리한 걸 더 간편하게 대체
    public String newForm() {
        return "new-form";
    }

    // @RequestParam으로 파라미터를 request에서 가져옴
    // Model을 사용하여 컨트롤러에서 데이터를 담아 view로 전달
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }
}
