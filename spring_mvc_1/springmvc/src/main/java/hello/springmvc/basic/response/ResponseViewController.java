package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    // 스프링 리소스 탐색 방식

    // 1. 정적 리소스 : 클래스 path가 밑에 예시와 같은 디렉토리에 정적 리소스를 저장
    // ex. /static, /public, /resources, /META-INF/resources

    // 2. 동적 리소스(뷰 템플릿) : 뷰 템플릿을 거쳐 HTML이 생성되고, 뷰가 응답을 만들어서 전달한다.
    // 기본 경로 : src/main/resources/templates

    // @ResponseBody 없이 사용하면 return 하는 String(뷰의 논리이름)에 해당하는 View를 ViewResolver가 찾아서 랜더링한다.
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String  responseViewV2(Model model) {
        model.addAttribute("data", "hello!!");
        return "response/hello"; // 뷰의 논리명 return
    }

    // void를 return하는 이 방법은 권장하지 않는다!!
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }
}
