package gcxy.zst.order.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import gcxy.zst.cart.model.CartItem;
import gcxy.zst.cart.service.impl.CartItemServiceImpl;
import gcxy.zst.order.model.Order;
import gcxy.zst.order.model.OrderItem;
import gcxy.zst.order.service.impl.OrderServiceImpl;
import gcxy.zst.pager.PageBean;
import gcxy.zst.user.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName OrderServlet
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/208:27
 * @Version 1.0
 **/
public class OrderServlet extends BaseServlet {
    OrderServiceImpl orderService=new OrderServiceImpl();
    CartItemServiceImpl cartItemService=new CartItemServiceImpl();
    /**
     * 获取当前页码
     * @param req
     * @return
     */
    private int getPc(HttpServletRequest req) {
        int pc=1;
        String param=req.getParameter("pc");
        if(param != null && !param.trim().isEmpty()){
            try {
                pc = Integer.parseInt(param);
            } catch(RuntimeException e) {}
        }
        return pc;
    }
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
     * 支付准备
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String paymentPre(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String oid=req.getParameter("oid");
        req.setAttribute("order", orderService.load(oid));
        return "f:/jsp/order/pay.jsp";
    }

    /**
     * 支付
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String payment(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String oid=req.getParameter("oid");
        orderService.updateStatus(oid,2);
        req.setAttribute("msg", "支付成功！");
        req.setAttribute("code", "success");
        return "f:/jsp/msg.jsp";
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
        if(status!=1){
            req.setAttribute("code", "error");
            req.setAttribute("msg", "已支付状态，不能取消！");
            return "f:/jsp/msg.jsp";
        }
        orderService.updateStatus(oid, 5);//设5为取消
        req.setAttribute("code", "success");
        req.setAttribute("msg", "您的订单已取消！");
        return "f:/jsp/msg.jsp";
    }
    /**
     * 确认收货
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String confirm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String oid=req.getParameter("oid");
        int status=orderService.findStatus(oid);
        if(status != 3){
            req.setAttribute("code", "error");
            req.setAttribute("msg", "状态不对，不能取消！");
            return "f:/jsp/msg.jsp";
        }
        orderService.updateStatus(oid,4);
        req.setAttribute("code","success");
        req.setAttribute("msg", "恭喜，交易成功！");
        return "f:/jsp/msg.jsp";
    }
    /**
     * 加载订单
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String load(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String oid=req.getParameter("oid");
        Order order=orderService.load(oid);
        req.setAttribute("order", order);
        String btn = req.getParameter("btn");//btn说明了用户点击哪个超链接来访问本方法的
        req.setAttribute("btn", btn);
        return "/jsp/order/desc.jsp";
    }

    /**
     * 生成订单
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String createOrder(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cartItemIds = req.getParameter("cartItemIds");
        List<CartItem> cartItemList = cartItemService.loadCartItems(cartItemIds);
        if(cartItemList.size() == 0) {
            req.setAttribute("code", "error");
            req.setAttribute("msg", "请先选择您要购买的图书");
            return "f:/jsp/msg.jsp";
        }

        Order order = new Order();
        order.setOid(CommonUtils.uuid());//设置主键
        order.setOrdertime(String.format("%tF %<tT", new Date()));//下单时间
        order.setStatus(1);
        order.setAddress(req.getParameter("address"));
        User owner = (User)req.getSession().getAttribute("sessionUser");
        order.setOwner(owner);//设置订单所有者
        BigDecimal total = new BigDecimal("0");
        for(CartItem cartItem : cartItemList) {
            total = total.add(new BigDecimal(cartItem.getSubtotal() + ""));
        }
        order.setTotal(total.doubleValue());//设置总计
        /*
         * 3. 创建List<OrderItem>
         * 一个CartItem对应一个OrderItem
         */
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for(CartItem cartItem : cartItemList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItemId(CommonUtils.uuid());//设置主键
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setBook(cartItem.getBook());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        }
        order.setOrderItemList(orderItemList);
        orderService.createOrder(order);
        cartItemService.batchDelete(cartItemIds);
        req.setAttribute("order", order);
        return "f:/jsp/order/ordersucc.jsp";
    }

    /**
     * 我的订单
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String myOrders(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pc = getPc(req);
        String url = getUrl(req);
        User user = (User)req.getSession().getAttribute("sessionUser");
        PageBean<Order> pb = orderService.myOrders(user.getUid(), pc);
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "f:/jsp/order/list.jsp";

    }}
