package gcxy.zst.admin.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.Filter;

/**
 * @ClassName AdminLoginFilter
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2220:40
 * @Version 1.0
 **/
public class AdminLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Object admin = req.getSession().getAttribute("admin");
        if(admin == null) {
            request.setAttribute("msg", "您还没有登录！");
            request.getRequestDispatcher("/adminjsps/login.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

}
