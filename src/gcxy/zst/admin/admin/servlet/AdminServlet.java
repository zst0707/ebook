package gcxy.zst.admin.admin.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import gcxy.zst.admin.admin.model.Admin;
import gcxy.zst.admin.admin.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AdminServlet
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2217:09
 * @Version 1.0
 **/
public class AdminServlet extends BaseServlet {
    AdminServiceImpl adminService=new AdminServiceImpl();
    /**
     * 登录功能
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Admin form= CommonUtils.toBean(req.getParameterMap(),Admin.class);
        Admin admin=adminService.login(form);
        if(admin==null){
           req.setAttribute("msg","用户名或密码错误！");
           return "f:/adminjsps/login.jsp";
        }
        req.getSession().setAttribute("admin",admin);
        return "r:/adminjsps/admin/index.jsp";
    }
}
