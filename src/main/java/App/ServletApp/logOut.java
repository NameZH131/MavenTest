package App.ServletApp;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/6/4
 * Time: 下午1:06
 * To change this template use File | Settings | File Templates.
 */

import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;


@WebServlet(name = "logOut", urlPatterns = "/logOut")
public class logOut extends HttpServlet {

    // 用于构建响应的HTML内容，提高代码的可读性和可维护性
    private String getResponseHtml() {
        return "<html><head><title>Idea Servlet</title></head><body>" +
                "<h1>Hello, this is an Idea Servlet!</h1>" +
                "</body></html>";
    }

    // 处理GET请求
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        // 使用StringBuilder来提高性能
//        String responseHtml = getResponseHtml();
//        out.println(responseHtml);


        HttpSession session = request.getSession();
        try {
            // 移除会话中的用户属性，确保数据一致性
            session.removeAttribute("user");

            // 使会话无效，终止当前用户的会话
            session.invalidate();

            // 重定向到登录页面
//        response.sendRedirect("/login.jsp");
            request.setAttribute("successMessage", "You have logged out successfully.");
            request.getRequestDispatcher("/logIn.jsp").forward(request, response);


        } catch (IllegalStateException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
            e.printStackTrace();
        } catch (NullPointerException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
            e.printStackTrace();
        }


    }

    // 处理POST请求
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            doGet(request, response);
        } catch (Exception e) {
            // 异常处理逻辑与doGet中一致
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred.");
        }
    }
}
