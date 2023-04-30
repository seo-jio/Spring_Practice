package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/springmvc/old-controller") // 스프링 빈 이름 지정
public class OldController implements Controller {

    // 1. 핸들러 매핑에서 핸들러(컨트롤러)를 찾음
    // 2. 핸들러 어댑터에서 support 가능한 어댑터를 찾음
    // 3. 찾은 어댑터에서 handle을 호출하여 1번에서 찾은 핸들러 내 로직 수행

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
