package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status]
        response.setStatus(HttpServletResponse.SC_OK); // 200이라고 숫자를 적기보단 status 이름을 사용하자

        //[response-header]
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        content(response);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        cookie(response);

        redirect(response);

        PrintWriter writer = response.getWriter(); // response의 message body에 text 작성
        writer.println("ok");

    }

    // http 응답의 content-type 설정
    private void content(HttpServletResponse response) {

        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2

        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        //위에처럼 하지 않고 밑에와 같이 설정할 수 있다.
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        //response.setContentLength(2); //(생략시 자동으로 생성된다, 주로 생략)
    }

    // 쿠키 설정
    private void cookie(HttpServletResponse response) {

        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 쿠키의 만료 기간을 설정(600초)
        response.addCookie(cookie);
    }

    // redirect 설정
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }

}
