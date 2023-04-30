package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// RequestMappingHandlerMapping은 클래스 레벨에 @Controller 또는 @RequestMapping이 있는 클래스들을 핸들러로 인식한다.


// @Controller의 기능
// 스프링이 자동으로 스프링 빈으로 등록(안에 @Component 내포)
// 스프링 MVC에서 어노테이션 기반 컨트롤러(핸들러)로 인식하여 핸들러 매핑에서 이를 읽어올 수 있다.
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form") // 해당 URL이 호출되면 이 메서드가 호출된다.
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
