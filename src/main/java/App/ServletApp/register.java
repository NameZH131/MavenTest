package App.ServletApp;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "register", urlPatterns = "/register")
public class register extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        // 使用StringBuilder来提高性能
        String responseHtml = getResponseHtml();
        out.println(responseHtml);
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
