package App.ServletApp;


import bean.Serve;
import service.ServeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "courseArrangeInformationUpdate", urlPatterns = "/courseArrangeInformationUpdate")
public class courseArrangeInformationUpdate extends HttpServlet {

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
        // 检查必要参数是否为空
        if (sId == null) {
            request.setAttribute("errorMessageForUpdate", "请先进行学生ID查询");
            request.getRequestDispatcher("/courseArrangeInformationUpdate.jsp").forward(request, response);
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters.");
            return;
        }
        
        /**
         *
         *   更新课程不能必须先清空课程，不论是否有勾选课程选择（课程数组不论==null）
         */
        try {
            new ServeService().deleteServe(Integer.parseInt(sId));
        } catch (NumberFormatException e) {
            System.out.println("删除失败，查询系统过程Bug");
            request.setAttribute("errorMessage", "删除失败，查询系统过程Bug");
            request.getRequestDispatcher("/courseArrangeInformationUpdate.jsp").forward(request, response);
            throw new RuntimeException(e);
        }

        if (checkboxCoursescId == null) {
            request.setAttribute("successMessageForUpdate", "已成功清空学生课程安排");
            request.getRequestDispatcher("/courseArrangeInformationUpdate.jsp").forward(request, response);
        } else {
            for (int i = 0; i < checkboxCoursescId.length; i++) {
                if (checkboxCoursescId[i] == null || selectTeacherstId[i] == null) {
                    request.setAttribute("errorMessageForUpdate", "课程参数无效");
                    request.getRequestDispatcher("/courseArrangeInformationUpdate.jsp").forward(request, response);
                    System.out.println("更新课程参数无效");
                }
            }
            try {
                for (int i = 0; i < checkboxCoursescId.length; i++) {
//                    System.out.println("课程cId:"+checkboxCoursescId[i]+" ");
//                    System.out.println("老师tId"+selectTeacherstId[i]+" ");
//                    System.out.println("分数:"+coursesScore[i]+" ");
                    new ServeService().addServe(new Serve(Integer.parseInt(sId), Integer.parseInt(checkboxCoursescId[i]), Integer.parseInt(selectTeacherstId[i]), coursesScore[i]));
                }
            } catch (Exception e) {
                System.out.println("最终课程更新失败");
                request.setAttribute("errorMessageForUpdate", "最终课程更新失败");
                throw new RuntimeException(e);
            }
            request.setAttribute("successMessageForUpdate", "该学生课程安排更新成功");
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