package classes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
        long dataNum = 0L;
        try {
            dataNum = WifiService.getList(key, docType, category);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if("list".equals(param)){
            HttpSession session = request.getSession();
            session.setAttribute("datanum", dataNum);
            response.sendRedirect("load-wifi.jsp");
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
            WifiService.search(Double.parseDouble(lat), Double.parseDouble(lnt));
        }
        response.sendRedirect("/");
    }
}