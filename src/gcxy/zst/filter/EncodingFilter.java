package gcxy.zst.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String character; // 字符集编码类型
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(character);
        resp.setCharacterEncoding(character);
        resp.setContentType("text/html; charset=UTF-8");
        chain.doFilter(req, resp); // 放行，转到下一个过滤器
    }

    public void init(FilterConfig config) throws ServletException {
        character = config.getInitParameter("character");
    }

}
