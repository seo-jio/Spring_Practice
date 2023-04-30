package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    // MultiValueMap : 같은 키에 value가 여러 개 있을 수 있는 Map

    // RequestMapping은 여러 형태의 파라미터를 받을 수 있다.
    // RequestMapping은 여러 형태(String, View, ModelAndView...)를 return할 수 있다.
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod, // 요청 타입
                          Locale locale, // 지역 정보
                          @RequestHeader MultiValueMap<String, String> headerMap, // 모든 헤더 정보
                          @RequestHeader("host") String host, // 특정 헤더 정보
                          @CookieValue(value = "myCookie", required = false) String cookie) { // 쿠키 정보

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";

    }
}
