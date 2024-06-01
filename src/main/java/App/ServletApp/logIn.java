package App.ServletApp;

import service.StudentService;
import util.PasswordHashing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "logIn", urlPatterns = "/logIn")
public class logIn extends HttpServlet {

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
//        PrintWriter out = response.getWriter();

//             使用StringBuilder来提高性能
//        String responseHtml = getResponseHtml();
//        out.println(responseHtml);

        String id = request.getParameter("id");
        String password = request.getParameter("password");
//        String name = request.getParameter("name");
//        String age = request.getParameter("age");
//        String gender = request.getParameter("gender");
//        String grade = request.getParameter("grade");
        // 检查参数是否为空
        if (!id.isEmpty() && !password.isEmpty()) {
            // 将参数存储到请求中
//            request.setAttribute("sId", id);
//            request.setAttribute("sName", name);
//            request.setAttribute("sAge", age);
//            request.setAttribute("sGender", gender);
//            request.setAttribute("sGrade", grade);
//            request.setAttribute("sPassword", password);

            // 检查密码是否为数字
            try {
                int pint  = Math.abs(Integer.parseInt(id));// 使用Math.abs获取密码的绝对值
//              密码转为hash数值
                String pfromDb = PasswordHashing.hashPassword(new StudentService().getStudentPassword(pint));
                if (pfromDb == null) {
                    request.setAttribute("errorMessage", "用户不存在！or Maybe密码错了");
                    request.getRequestDispatcher("/logIn.jsp").forward(request, response);
                }
                // 密码验证
                if (PasswordHashing.hashPassword(password).equals(pfromDb)) {
                    request.setAttribute("successMessage", "欢迎您！😘用户id: ");
                    request.getRequestDispatcher("/mainUi.jsp").forward(request, response);
                }
                else {
                    request.setAttribute("errorMessage", "密码错误！");
                    request.getRequestDispatcher("/logIn.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Id必须为数字！");
                request.getRequestDispatcher("/logIn.jsp").forward(request, response);
            }

        } else {
                // 若参数为空，则返回错误信息
                request.setAttribute("errorMessage", "请输入用户名和密码！");
                request.getRequestDispatcher("/logIn.jsp").include(request, response);
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
    //你用debug模式运行，你是开发人员啊，你知道你在做什么吗？
}
