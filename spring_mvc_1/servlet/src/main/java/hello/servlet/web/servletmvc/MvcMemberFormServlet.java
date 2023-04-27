package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // WEB-INF : 이 패키지 안에 있는 jsp 파일들을 무조건 controller를 거쳐야만 호출이 가능하다.(브라우저에서 직접 접근 X)
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // forward : 다른 서블릿이나 jsp로 이동, 서버 내부에서 다시 호출이 발생한다.(클라이언트로 나가지 않는다!)
        // redirect : 클라이언트에 응답이 나갔다가 클라이언트가 응답 메세지에 있는 Location으로 다시 요청을 보낸다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
