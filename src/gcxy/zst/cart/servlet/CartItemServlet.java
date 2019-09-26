package gcxy.zst.cart.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import gcxy.zst.book.model.Book;
import gcxy.zst.cart.model.CartItem;
import gcxy.zst.cart.service.impl.CartItemServiceImpl;
import gcxy.zst.user.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CartItemServlet
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/208:28
 * @Version 1.0
 **/
public class CartItemServlet extends BaseServlet {
    CartItemServiceImpl cartItemService=new CartItemServiceImpl();
    /**
     * 加载多个CartItem
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String loadCartItems(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cartItemIds=req.getParameter("cartItemIds");
        double total=Double.parseDouble(req.getParameter("total"));
        List<CartItem> cartItemList=cartItemService.loadCartItems(cartItemIds);
        req.setAttribute("cartItemList",cartItemList);
        req.setAttribute("total",total);
        req.setAttribute("cartItemIds",cartItemIds);
        return "f:/jsp/cart/showitem.jsp";
    }

    /**
     * 修改购物车商品数量
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String updateQuantity(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cartItemId=req.getParameter("cartItemId");
        int quantity= Integer.parseInt(req.getParameter("quantity"));
        CartItem cartItem=cartItemService.updateQuantity(cartItemId,quantity);
        // 给客户端返回一个json对象
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"quantity\"").append(":").append(cartItem.getQuantity());
        sb.append(",");
        sb.append("\"subtotal\"").append(":").append(cartItem.getSubtotal());
        sb.append("}");
        resp.getWriter().print(sb);
        return null;
    }
    /**
     * 批量删除功能
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String batchDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cartItemIds=req.getParameter("cartItemIds");
        cartItemService.batchDelete(cartItemIds);
        return myCart(req, resp);
    }
    /**
     * 添加购物车条目
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String add(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map map =req.getParameterMap();
        CartItem cartItem= CommonUtils.toBean(map,CartItem.class);
        Book book=CommonUtils.toBean(map,Book.class);
        User user=(User)req.getSession().getAttribute("sessionUser");
        cartItem.setBook(book);
        cartItem.setUser(user);
        cartItemService.add(cartItem);
        return myCart(req, resp);
    }
    /**
     * 我的购物车
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String myCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user=(User)req.getSession().getAttribute("sessionUser");
        String uid=user.getUid();
        List<CartItem> cartItemList=cartItemService.myCart(uid);
        req.setAttribute("cartItemList",cartItemList);
        return "f:/jsp/cart/list.jsp";
    }
}
