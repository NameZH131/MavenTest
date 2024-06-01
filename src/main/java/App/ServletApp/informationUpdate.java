package App.ServletApp;


import bean.Serve;
import service.ServeService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "informationUpdate", urlPatterns = "/informationUpdate")
public class informationUpdate extends HttpServlet {

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
//
//        // 使用StringBuilder来提高性能
//        String responseHtml = getResponseHtml();
//        out.println(responseHtml);


        String sId = request.getParameter("studentCRUD");
        String[] checkboxCoursescId = request.getParameterValues("checkboxCourse");
        String[] selectTeacherstId = request.getParameterValues("selectTeacher");
        String[] coursesScore = request.getParameterValues("score");
        if (sId != null || checkboxCoursescId != null || selectTeacherstId != null) {
            try {
                new ServeService().deleteServe(Integer.parseInt(sId));
            } catch (NumberFormatException e) {
                System.out.println("删除学生原来all课程计划失败！");
                out.println("删除学生原来all课程计划失败！");
                response.setHeader("Refresh", "2;url=/informationUpdate.jsp");
                throw new RuntimeException(e);
            }
            try {
                for (int i = 0; i < checkboxCoursescId.length; i++) {
//                    System.out.println("课程cId:"+checkboxCoursescId[i]+" ");
//                    System.out.println("老师tId"+selectTeacherstId[i]+" ");
//                    System.out.println("分数:"+coursesScore[i]+" ");
                    new ServeService().addServe(new Serve(Integer.parseInt(sId), Integer.parseInt(checkboxCoursescId[i]), Integer.parseInt(selectTeacherstId[i]), coursesScore[i]));
                }
            } catch (Exception e) {
                System.out.println("重新安排学生课程计划失败！");
                out.println("重新安排学生课程计划失败！");
                response.setHeader("Refresh", "2;url=/informationUpdate.jsp");
                throw new RuntimeException(e);
            }
            request.setAttribute("successMessageForUpdate", "更新成功");
            request.getRequestDispatcher("/informationUpdate.jsp").forward(request, response);
        } else {
            System.out.println("更新" + sId + "课程参数无效");
//            out.println("更新" + sId + "课程参数无效");
//            五秒后请求转发
//            response.setHeader("Refresh", "2;url=/informationUpdate.jsp");
            request.setAttribute("errorMessageForUpdate", "请先进行学生ID查询");
            request.getRequestDispatcher("/informationUpdate.jsp").forward(request, response);
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters.");
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
