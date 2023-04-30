package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.util.SpringRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    // 기존 서블릿
    // 내장 톰캣 사용 시 resources/static 경로 안에 html들을 담아둔다.
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // return 하는 문자를 응답 body에 바로 넣어준다(@RestController와 유사)
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }

    // HTTP 파리미터 이름이 변수 이름과 같다면 @RequestParamger(name="xx") 생략 가능
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }


    // String, int, Integer 등 단순 타입이면 @RequestParam도 생략 가능
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    // HTTP 스펙 지정
    // @RequestParam(required = true) : 요청을 보낼 때 필수로 보내야 하는 파리미터, default 값이 true이다.
    // @RequestParam(required = false) : 요청을 보낼 때 보내지 않아도 정상 동작하는 파라미터
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // null과 빈 문자열("")을 구분하자!
            @RequestParam(required = false) Integer age) { // int를 사용하면 null을 넣을 수 없으므로 Wrapper Class를 사용한다.

        log.info("username={}, age={}", username, age);

        return "ok";
    }


    // defaultValue 사용 시 required와 무관하게 항상 값이 채워지게 된다.
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username, // 빈 문자가 들어와 defaultValue 값으로 채워진다.
            @RequestParam(defaultValue = "-1") Integer age) {

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    // 모든 파라미터를 받을 경우
    // Map, MultiValueMap 모두 가능
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }


    // ModelAttribute 사용 하여 요청 파라미터를 특정 클래스에 매핑하여 받아온다.
    // 동작과정
    // 1. 데이터를 담을 객체 생성
    // 2. 요청 파라미터의 이름으로 객체 프로퍼티를 찾고, 해당 프로퍼티의 setter를 호출하여 값을 입력
    // ex. 파라미터 이름이 age이면 setAge()를 호출 => 객체 내 setter 생성 필수

    // 파라미터 타입이 맞지 않으면 BindingException 발생
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

//        ModelAttribute가 대신 해준다.
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);

        log.info("helloData={}", helloData);

        return "ok";
    }

    // String, int Integer 같은 단순 타입은 @RequestParam
    // 나머지(개발자가 만든 클래스)는 @ModelAttribute (단 argument resolver로 지정해둔 타입(ex. HttpServletRequest)은 예외)
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {

        log.info("helloData={}", helloData);

        return "ok";
    }
}
