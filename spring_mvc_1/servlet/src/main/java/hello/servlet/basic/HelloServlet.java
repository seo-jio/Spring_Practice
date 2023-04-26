package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// name : 서블릿 이름 (다른 서블릿과 겹치면 안된다)
// urlPatterns : URL 매핑할 이름 (다른 서블릿과 겹치면 안된다)
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override // servlet이 실행되면 service 메소드가 호출된다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // HttpServletRequest
        // Http 요쳥 메세지를 편리하게 사욯할 수 있도록 한다.
        // Http 요청이 시작부터 끝날 때 까지 유지되는 데이터를 임시 저장소 기능 수행
        // session 기능 제공(request.getSession())

        // HttpServletResponse
        // Http 응답 메세지를 편리하게 사욯할 수 있도록 한다.
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");

        // 응답 메세지 설정 및 생성
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username); // body에 데이터를 담음

    }
}
