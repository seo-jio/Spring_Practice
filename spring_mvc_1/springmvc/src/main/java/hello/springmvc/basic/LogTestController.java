package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller : 메소드(컨트롤러)에서 return하는 String에 해당하는 뷰를 찾아 return
// @RestController : 응답 메세지에 메소드에서 return하는 String을 그대로 담아 클라이언트에게 전달
@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        // 그냥 출력을 하게되면 운영, 개발 서버 모두에서 로그가 남게 되어 구분이 어렵고 너무 많은 로그가 생성된다.
        System.out.println("name = " + name);

//        log.trace("trace log=" + name); // 로그를 사용하지 않는 경우에도 + 연산이 수행되어 cpu를 낭비하게 된다 => 따라서 사용 X

        // 로그 레벨 지정
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
