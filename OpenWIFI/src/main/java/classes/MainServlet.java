package classes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import classes.WifiService;
import org.json.simple.parser.ParseException;

@WebServlet("/MainServlet.do")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");
        String param = request.getParameter("comm");
        String key = "68716c586f6d616737375644517162";
        String docType = "json";
        String category = "TbPublicWifiInfo";


        if("list".equals(param)){
            long dataNum = 0L;
            try {
                dataNum = WifiService.getList(key, docType, category);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            HttpSession session = request.getSession();
            session.setAttribute("datanum", dataNum);
            response.sendRedirect("load-wifi.jsp");
        } else if("log".equals(param)){
            // DB에서 로그 출력 & 저장 후 history.jsp로 리디렉트
            WifiService.getLogs();
            response.sendRedirect("history.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf8");
        response.setCharacterEncoding("utf8");

        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        System.out.println(lat);
        System.out.println(lnt);
        if(!lat.isEmpty() && !lnt.isEmpty()) {
            WifiService.search(Float.parseFloat(lat), Float.parseFloat(lnt));
        } else{
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('LAT, LNT를 입력해주세요.');");
            out.println("location.href = \"index.jsp\";");
            out.println("</script>");
            out.close();
        }
        response.sendRedirect("/");
    }
}
