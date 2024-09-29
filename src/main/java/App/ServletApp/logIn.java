package App.ServletApp;

import bean.Student;
import service.StudentService;
import util.PasswordHashing;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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

            // 检查密码
            try {
                int pint = Math.abs(Integer.parseInt(id));// 使用Math.abs获取绝对值，避免负数账户id
//              密码转为hash数值
                String pfromDb = PasswordHashing.hashPassword(new StudentService().getStudentPassword(pint));
                if (pfromDb == null) {
                    request.setAttribute("errorMessage", "用户不存在！or Maybe密码错了");
                    request.getRequestDispatcher("/logIn.jsp").forward(request, response);
                }
                // 密码验证
                if (PasswordHashing.hashPassword(password).equals(pfromDb)) {
                    request.setAttribute("successMessage", "欢迎您！😘用户id: ");
//                    建立cookie和session
                    HttpSession session = request.getSession();
                    Student student = new Student();
                    student.setsId(pint);
                    student.setsPassword(password);
                    session.setAttribute("user", student);
//                   cookie,下次自动登录
                    // 创建一个名为"autoLogIn"的 Cookie 对象，其值为 pint+"."+PasswordHashing.hashPassword(password)
                    Cookie cookie = new Cookie("autoLogIn", pint + "." + PasswordHashing.hashPassword(password));
                    // 设置 Cookie 的最大存活时间为 7 天
                    cookie.setMaxAge(60 * 60 * 24 * 7);
                    /**
                     * 设置Cookie的域名属性。
                     * 通过将域名设置为".idea.com"，使得该Cookie适用于所有idea.com旗下的子域名。
                     * 这是一种常见的实践，可以确保Cookie在多个相关联的子域名下都能被识别和使用。
                     *
                     * @param domain Cookie的有效域名，使用".idea.com"的形式来表示适用于所有idea.com子域名。
                     */
//                   cookie.setDomain(".idea.com");
                    // 设置 Cookie 的路径
                    cookie.setPath(request.getContextPath());
                    // 将 Cookie 添加到响应中
                    response.addCookie(cookie);



                    request.getRequestDispatcher("/mainUi.jsp").forward(request, response);
                } else {
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
