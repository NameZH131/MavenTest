package App.ServletApp;


import bean.Student;
import service.StudentService;

import javax.servlet.RequestDispatcher;
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
//        String responseHtml = getResponseHtml();
//        out.println(responseHtml);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String grade = request.getParameter("grade");
        int ageInt =0;
        try {
            ageInt = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            System.out.println("学生注册没有填写年龄，默认0，只异常信息打印到控制台，不抛出异常，继续执行");
            e.printStackTrace();
//   throw new RuntimeException(e);
        }
        if (username != null && password != null) {
            try {
                new StudentService().addStudent(new Student(Integer.parseInt(username), password, name, ageInt, gender, Integer.parseInt(grade)));

            } catch (NumberFormatException e) {
                System.out.println("学生注册失败，系添加学生功能异常");
                request.setAttribute("errorMessageForRegister", "注册失败，请填写完整信息");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                out.println("<alert>学生注册失败，系添加学生功能异常</alert>");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            request.setAttribute("successMessageForRegister", "注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            out.println("<alert>学生注册成功，请登录</alert>");
        } else {
            request.setAttribute("", "");
            request.getRequestDispatcher("/register.jsp").include(request, response);
            out.println("<alert>学生注册失败，请填写完整信息，注册表单js脚本故障</alert>");
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
