package App.FilterApp;
/*
 * Created by IntelliJ IDEA.
 * User: jake
 * Date: 2024/5/30
 * Time: 上午12:14
 * To change this template use File | Settings | File Templates.
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//想阻止没登录的人
//@WebFilter(urlPatterns = "/*")
@WebFilter(urlPatterns = {"/logOut","/studentInformationDisplay","/courseArrangeInformationUpdate","/courseArrangeInformationUpdate_search","/mainUi.jsp","/navigationBar.jsp","/studentInformationDisplay.jsp","/courseArrangeInformationUpdate.jsp"})
public class userHasLogInFilter1 implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化 Filter
        System.out.println("Filter userHasLogIn initialized.");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 设置请求和响应的编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 这里是 Filter 的处理逻辑，例如添加日志、权限检查等
        // 这里假设用户已经登录，如果用户未登录，则重定向到登录页面


        HttpSession session = ((HttpServletRequest) request).getSession();
//        try{
//            session.getAttribute("user");
//        } catch (Exception e) {
//            System.out.println("userHasLogIn filter catch exception.session is null.");
//            e.printStackTrace();
//        }
        if (session.getAttribute("user") == null) {
            // 用户未登录，重定向到登录页面
            System.out.println("抓到用户未登录，重定向到登录页面。");
            request.setAttribute("errorMessage", "权限不足，请先登录！");
            request.getRequestDispatcher("logIn.jsp").forward(request, response);

        }else if(session.getAttribute("user") != null){
            System.out.println("userHasLogIn filter pass.");
            // 继续执行下一个 Filter 或 Servlet
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        // 销毁 Filter
        System.out.println("Filter userHasLogIn destroyed.");
    }
}
