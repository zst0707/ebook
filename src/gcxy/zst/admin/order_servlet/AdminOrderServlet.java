package gcxy.zst.admin.order_servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import gcxy.zst.order.model.Order;
import gcxy.zst.order.service.impl.OrderServiceImpl;
import gcxy.zst.pager.PageBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AdminOrderServlet
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2220:40
 * @Version 1.0
 **/
public class AdminOrderServlet extends BaseServlet {
    OrderServiceImpl orderService=new OrderServiceImpl();
    /**
     * 获取当前页码
     * @param req
     * @return
     */
    private int getPc(HttpServletRequest req) {
        int pc = 1;
        String param = req.getParameter("pc");
        if(param != null && !param.trim().isEmpty()) {
            try {
                pc = Integer.parseInt(param);
            } catch(RuntimeException e) {}
        }
        return pc;
    }

    /**
     * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
     * @param req
     * @return
     */
    /*
     * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
     * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
     */
    private String getUrl(HttpServletRequest req) {
        String url = req.getRequestURI() + "?" + req.getQueryString();
        /*
         * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
         */
        int index = url.lastIndexOf("&pc=");
        if(index != -1) {
            url = url.substring(0, index);
        }
        return url;
    }

    /**
     * 查看所有订单
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pc=getPc(req);
        String url=getUrl(req);
        PageBean<Order> pb= orderService.findAll(pc);
        pb.setUrl(url);
        req.setAttribute("pb",pb);
        return "f:/adminjsps/admin/order/list.jsp";
    }
    /**
     * 按状态查询
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByStatus(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pc=getPc(req);
        String url=getUrl(req);
        int status= Integer.parseInt(req.getParameter("status"));
        PageBean<Order> pb=orderService.findByStatus(status,pc);
        pb.setUrl(url);
        req.setAttribute("pb",pb);
        return "f:/adminjsps/admin/order/list.jsp";
    }
    /**
     * 查看订单详细信息
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String load(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pc=getPc(req);
        String url=getUrl(req);
        String oid=req.getParameter("oid");
        Order order=orderService.load(oid);
        req.setAttribute("order", order);
        String btn = req.getParameter("btn");//btn说明了用户点击哪个超链接来访问本方法的
        req.setAttribute("btn", btn);
        return "/adminjsps/admin/order/desc.jsp";
    }
    /**
     * 取消订单
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String cancel(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String oid=req.getParameter("oid");
        int status=orderService.findStatus(oid);
        if(status != 1) {
            req.setAttribute("code", "error");
            req.setAttribute("msg", "状态不对，不能取消！");
            return "f:/adminjsps/msg.jsp";
        }
        orderService.updateStatus(oid,5);
        req.setAttribute("code", "success");
        req.setAttribute("msg", "您的订单已取消，您不后悔吗！");
        return "f:/adminjsps/msg.jsp";
    }

    /**
     * 发货功能
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deliver(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String oid=req.getParameter("oid");
        int status=orderService.findStatus(oid);
        if(status!=2){
            req.setAttribute("code", "error");
            req.setAttribute("msg", "状态不对，不能发货！");
            return "f:/adminjsps/msg.jsp";
        }
        orderService.updateStatus(oid, 3);//设置状态为取消！
        req.setAttribute("code", "success");
        req.setAttribute("msg", "订单已发货！");
        return "f:/adminjsps/msg.jsp";
    }

}
