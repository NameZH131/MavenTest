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
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class userHasLogIn implements Filter {

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
        // ...

        // 继续执行下一个 Filter 或 Servlet
        chain.doFilter(request, response);
    }

    public void destroy() {
        // 销毁 Filter
        System.out.println("Filter userHasLogIn destroyed.");
    }
}
