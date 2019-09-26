package gcxy.zst.admin.user_servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import gcxy.zst.user.model.User;
import gcxy.zst.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName AdminUserServlet
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2320:14
 * @Version 1.0
 **/
public class AdminUserServlet extends BaseServlet {
    UserServiceImpl userService=new UserServiceImpl();

    /**
     * 查询所有用户
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("user",userService.findAll());
        return "f:/adminjsps/admin/user/list.jsp";
    }


    /**
     * 修改用户信息：第一步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uid=req.getParameter("uid");
        User user=userService.load(uid);
        req.setAttribute("user",user);
        return "f:/adminjsps/admin/user/edit.jsp";
    }
    /**
     * 修改用户信息：第二步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String update(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user=CommonUtils.toBean(req.getParameterMap(),User.class);
        userService.update(user);
        return findAll(req,resp);
    }

    /**
     * 冻结用户
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String freezing(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uid=req.getParameter("uid");
        userService.freezing(uid);
        return findAll(req, resp);
    }


    /**
     * 解冻用户
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String unfreeze(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uid=req.getParameter("uid");
        userService.unfreeze(uid);
        return findAll(req, resp);
    }
}
