package App.ServletApp;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/5/29
 * Time: 下午2:24
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


@WebServlet(name = "courseArrangeInformationUpdate_search", urlPatterns = "/courseArrangeInformationUpdate_search")
public class courseArrangeInformationUpdate_search extends HttpServlet {

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


        String StrsId = request.getParameter("sId");

        if (StrsId != null && !StrsId.isEmpty() && StrsId.matches("\\d+")) {
            Integer sId = null;
            // 处理学生信息查询请求
            try {
                sId = Math.abs(Integer.parseInt(StrsId)); // 将字符串转换为整数
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "请输入正确的学号");
                request.getRequestDispatcher("/courseArrangeInformationUpdate.jsp").forward(request, response);
            }
            // 查询学生信息Service层
            try {
                Optional<Student> student = new StudentService().getStudentById(sId);
                if (!student.isPresent()) {
                    request.setAttribute("errorMessage", "Id不存在哎");
                    request.getRequestDispatcher("/courseArrangeInformationUpdate.jsp").forward(request, response);
                }
                List<Serve> serveList = new ServeService().getServesByStudentIdId(sId);
                List<Course> courseList = new CourseService().getCoursesByStudentId(sId);
                List<Teacher> teacherList = new TeacherService().getTeacherByStudensId(sId);


                List<Course> allCourseList =new CourseService().getAllCourses();
                List<Teacher> allTeacherList =new TeacherService().getAllTeachers();
//                遍历List
//                courseList.forEach(course -> System.out.println(course.toString()));

                
                if (courseList.size() > 0) {
                    request.setAttribute("student", student.get());
                    request.setAttribute("serveList", serveList);
                    request.setAttribute("courseList", courseList);
                    request.setAttribute("teacherList", teacherList);


                    request.setAttribute("allCourseList", allCourseList);
                    request.setAttribute("allTeacherList",allTeacherList);

                    request.getRequestDispatcher("/courseArrangeInformationUpdate.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "该学生没有选课");
                    
                    request.setAttribute("student", student.get());

                    request.setAttribute("allCourseList", allCourseList);
                    request.setAttribute("allTeacherList",allTeacherList);
                    request.getRequestDispatcher("/courseArrangeInformationUpdate.jsp").forward(request, response);
                }


            } catch (Exception e) {
                request.setAttribute("errorMessage", "查询学生信息失败");
                //请求转发
                request.getRequestDispatcher("/courseArrangeInformationUpdate.jsp").forward(request, response);

                System.out.println("查询学生信息失败 Servlet: informationUpdate");
                e.printStackTrace();
            }


        } else {
            request.setAttribute("errorMessage", "Id在我的预料之外");
            request.getRequestDispatcher("/courseArrangeInformationUpdate.jsp").forward(request, response);
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