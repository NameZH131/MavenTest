package App.ServletApp;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/5/29
 * Time: 上午12:49
 * To change this template use File | Settings | File Templates.
 */

import bean.Course;
import bean.Serve;
import bean.Student;
import bean.Teacher;
import service.CourseService;
import service.ServeService;
import service.StudentService;
import service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet(name = "informationDisplay", urlPatterns = "/informationDisplay")
public class informationDisplay extends HttpServlet {

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

        // 使用StringBuilder来提高性能
//        String responseHtml = getResponseHtml();
//        out.println(responseHtml);


        String StrsId = request.getParameter("sId");

        if (StrsId != null && !StrsId.isEmpty() && StrsId.matches("\\d+")) {
            int sId = Integer.parseInt(StrsId);

            // 处理学生信息查询请求
            try {
                sId = Integer.parseInt(request.getParameter("sId"));
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "请输入正确的学号");
                request.getRequestDispatcher("/informationDisplay.jsp").forward(request, response);
            }


            // 查询学生信息Service层
            try {
                Optional<Student> Optstudent=new StudentService().getStudentById(sId);
                Student student=Optstudent.get();
                Optional<Serve> Optserve= new ServeService().getServeById(sId);
                if( Optstudent.isPresent() &&Optserve.isPresent()){
                    Optional<Course> Optcourse = new CourseService().getCourseByCoursecId((Optserve.get().getcId()));
                    Optional<Teacher> Optteacher=new TeacherService().getTeacherByTeacherId(Optserve.get().gettId());
                    if(Optcourse.isPresent() && Optteacher.isPresent()){
                        request.setAttribute("student",student);
                        request.setAttribute("course",Optcourse.get());
                        request.setAttribute("teacher",Optteacher.get());
                        //请求转发
                        request.getRequestDispatcher("/informationDisplay.jsp").forward(request,response);
                    }
                }
             } catch (Exception e) {
                request.setAttribute("errorMessage", "Id不存在哎");
                //请求转发
                request.getRequestDispatcher("/informationDisplay.jsp").forward(request, response);

                System.out.println("查询学生信息失败 Servlet: informationDisplay");
                e.printStackTrace();
            }
        } else {
            request.setAttribute("errorMessage", "Id在我的预料之外");
            request.getRequestDispatcher("/informationDisplay.jsp").forward(request, response);
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
