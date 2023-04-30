package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody
@RestController // @Controller + @ResponseBody
public class ResponseBodyController {

    // Http Message Converter

    // @ResponseBody 사용 시 viewResolver 대신 HttpMessageConverter가 동작하여 Http BODY에 문자 내용을 직접 변환한다.

    // Request, Response 모두에서 사용하며 클라이언트의 미디어 타입에 의해 결정된다.
    // @ResponseBody(응답)에서는 Http 요청의 Accept(클라이언트가 응답으로 받을 수 있는 body에 담긴 데이터 타입)과 컨트롤러의 반환 타입 정보를 조합해서 컨버터를 선택한다.
    // @RequestBody(요청)에서는 Http 요청의 Content-Type(클라이언트가 요청으로 보내는 body에 담긴 데이터 타입)을 확인하여 컨버터를 선택한다.


    // Http 메세지 컨버터 인터페이스가 최상위에 존재하고 이를 구현하는 클래스들이 존재
    // canRead() : 지원하는 컨버터인지 확인하는 인터페이스의 메소드

    // ByteArrayHttpMessageConverter : byte 처리
    // StringMessageConverter : 기본 문자 처리
    // MappingJackson2HttpMessageConverter : 기본 객체 처리

    // 스프링 MVC에서 Http 메시지 컨버터 적용 경우
    // HTTP 요청 : @RequestBody, HttpEntity(RequestEntity)
    // HTTP 응답 : @ResponseBody, HttpEntity(ResponseEntity)


    // Argument Resolver(HandlerMethodArgumentResolver)
    // 어노테이션 기반의 컨트롤러는 매우 다양한 파라미터(Model, @RequestParam, HttpServletRequest...)를 사용할 수 있었는데
    // 이러한 기능을 지원하는 역할을 Argument Resolver가 수행한다.
    // 이떄 @RequestBody, HttpEntity를 사용하는 컨트롤러라면 Http Message Converter를 사용하게된다.

    // RequestMappingHandlerAdapter가 Argument Resolver를 호출하여 컨트롤러가 필요로 하는 다양한 파라미터 값(객체)를 생성하고
    // 이렇게 생성된 파리미터 값(객체)들을 담아서 컨트롤러를 호출하게 된다.

    // Return Value Handler(HandlerMethodReturnValueHandler)
    // 어노테이션 기반 컨트롤러가 다양한 타입을 반환할 수 있도록 지원
    // 이떄 @ResponseBody, HttpEntity를 사용하는 컨트롤러라면 Http Message Converter를 사용하게된다.
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

//    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    // response status 코드를 동적으로 설정하려면 ResponseEntity를 사용하자
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("Seojio");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }


    // Rest API 만들 때 주로 사용하는 스타일(with @RestController)
    @ResponseStatus(HttpStatus.OK) // @ResponseBody 사용시 HttpStatus를 지정할 수 없어서 @ResponseStatus를 사용한다.
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("Seojio");
        helloData.setAge(20);
        return helloData;
    }
}


