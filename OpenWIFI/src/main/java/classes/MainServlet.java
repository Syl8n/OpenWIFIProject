package classes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import classes.WifiService;
import org.json.simple.parser.ParseException;

@WebServlet("/MainServlet.do")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("comm");
        String key = "68716c586f6d616737375644517162";
        String docType = "json";
        String category = "TbPublicWifiInfo";
        try {
            WifiService.getList(key, docType, category);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if("list".equals(param)){
            response.sendRedirect("load-wifi.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
