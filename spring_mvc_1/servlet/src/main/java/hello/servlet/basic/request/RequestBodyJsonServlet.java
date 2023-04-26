package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {


    // 스프링 부트가 기본으로 사용하는 json library : Jackson
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // http message body의 내용을 byte code로 읽어들임
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // byte code => String

        // Json도 결국엔 문자열이다.
        // messageBody = {
        //    "username" : "hello",
        //    "age" : 20
        // }
        System.out.println("messageBody = " + messageBody);

        // objectMapper가 message의 body에 담긴 data를 자바 객체로 변환해준다.
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        System.out.println("helloData.getUsername() = " + helloData.getUsername());
        System.out.println("helloData.getAge() = " + helloData.getAge());

    }
}
