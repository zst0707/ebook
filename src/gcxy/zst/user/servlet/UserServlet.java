package gcxy.zst.user.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import gcxy.zst.user.model.User;
import gcxy.zst.user.service.exception.UserException;
import gcxy.zst.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends BaseServlet {
    UserServiceImpl userService = new UserServiceImpl();

    /**
     * 加载用户信息
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String load(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uid=req.getParameter("uid");
        User user=userService.load(uid);
        req.setAttribute("user",user);
        return "f:/jsp/user/info.jsp";
    }
    /**
     * ajax用户名是否注册校验
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String ajaxValidateLoginname(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String loginname = req.getParameter("loginname");
        boolean b = userService.ajaxValidateLoginname(loginname);
        resp.getWriter().print(b);
        return null;
    }

    /**
     * ajax Email是否注册校验
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String ajaxValidateEmail(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        boolean b = userService.ajaxValidateEmail(email);
        resp.getWriter().print(b);
        return null;
    }

    /**
     * 注册功能
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String regist(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
         * 1. 封装表单数据到User对象
         */
        User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
        Map<String, String> errors = validateRegist(formUser, req.getSession());
        if (errors.size() > 0) {
            req.setAttribute("form", formUser);
            req.setAttribute("errors", errors);
            return "f:/jsp/user/regist.jsp";
        }
        userService.regist(formUser);
        req.setAttribute("code", "success");
        req.setAttribute("msg", "注册成功！");
        return "f:/jsp/msg.jsp";
    }

    /*
     * 注册校验
     * 对表单的字段进行逐个校验，如果有错误，使用当前字段名称为key，错误信息为value，保存到map中
     * 返回map
     */
    private Map<String, String> validateRegist(User formUser, HttpSession session) {
        Map<String, String> errors = new HashMap<String, String>();
        String loginname = formUser.getLoginname();
        if (loginname == null || loginname.trim().isEmpty()) {
            errors.put("loginname", "用户名不能为空！");
        } else if (loginname.length() < 2 || loginname.length() > 20) {
            errors.put("loginname", "用户名长度必须在2~20之间！");
        } else if (!userService.ajaxValidateLoginname(loginname)) {
            errors.put("loginname", "用户名已被注册！");
        }

        /*
         * 2. 校验登录密码
         */
        String loginpass = formUser.getLoginpass();
        if (loginpass == null || loginpass.trim().isEmpty()) {
            errors.put("loginpass", "密码不能为空！");
        } else if (loginpass.length() < 3 || loginpass.length() > 20) {
            errors.put("loginpass", "密码长度必须在3~20之间！");
        }

        /*
         * 3. 确认密码校验
         */
        String reloginpass = formUser.getReloginpass();
        if (reloginpass == null || reloginpass.trim().isEmpty()) {
            errors.put("reloginpass", "确认密码不能为空！");
        } else if (!reloginpass.equals(loginpass)) {
            errors.put("reloginpass", "两次输入不一致！");
        }
        /*
         * 4. 校验email
         */
        String email = formUser.getEmail();
        if (email == null || email.trim().isEmpty()) {
            errors.put("email", "Email不能为空！");
        } else if (!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
            errors.put("email", "Email格式错误！");
        } else if (!userService.ajaxValidateEmail(email)) {
            errors.put("email", "Email已被注册！");
        }
        return errors;
    }

    /**
     * 修改密码
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String updatePassword(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
        User user = (User) req.getSession().getAttribute("sessionUser");
        try {
            userService.updatePassword(user.getUid(), formUser.getNewpass(),
                    formUser.getLoginpass());
            req.setAttribute("msg", "修改密码成功");
            req.setAttribute("code", "success");
            return "f:/jsp/msg.jsp";
        } catch (UserException e) {
            req.setAttribute("msg", e.getMessage());
            req.setAttribute("user", formUser);
            return "f:/jsp/user/pwd.jsp";
        }
    }

    /**
     * 退出功能
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String quit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().invalidate();
        return "r:/index.jsp";
    }

    /**
     * 登录功能
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String login(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
        User user = userService.login(formUser);
        if (user == null) {
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("user", formUser);
            return "f:/jsp/user/login.jsp";
        } else {
            if (user.getStatus() == 0) {
                req.setAttribute("msg", "该账号已被冻结！");
                req.setAttribute("user", formUser);
                return "f:/jsp/user/login.jsp";
            } else {
                req.getSession().setAttribute("sessionUser", user);
                String loginname = user.getLoginname();
                loginname = URLEncoder.encode(loginname, "utf-8");
                Cookie cookie = new Cookie("loginname", loginname);
                cookie.setMaxAge(60 * 60 * 24 * 10);//保存10天
                resp.addCookie(cookie);
                return "r:/index.jsp";
            }
        }
    }
}
