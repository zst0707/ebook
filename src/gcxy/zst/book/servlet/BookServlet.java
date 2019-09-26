package gcxy.zst.book.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.sun.xml.internal.bind.v2.model.core.ID;
import gcxy.zst.book.model.Book;
import gcxy.zst.book.service.impl.BookServiceImpl;
import gcxy.zst.category.model.Category;
import gcxy.zst.pager.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookServlet extends BaseServlet {
    BookServiceImpl bookService=new BookServiceImpl();
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
    /**
     * 截取url，页面中的分页导航中需要使用它做为超链接的目标
     * @param req
     * @return
     */
    private String getUrl(HttpServletRequest req) {
        String url = req.getRequestURI() + "?" + req.getQueryString();
        int index = url.lastIndexOf("&pc=");
        if(index != -1) {
            url = url.substring(0, index);
        }
        return url;
    }
    /**
     * 按bid查询
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String load(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bid=req.getParameter("bid");
        Book book=bookService.load(bid);
        req.setAttribute("book",book);
        return "f:/jsp/book/desc.jsp";
    }
    /**
     * 按分类查
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByCategory(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pc=getPc(req);
        String url = getUrl(req);
        String cid=req.getParameter("cid");
        PageBean<Book> pb = bookService.findByCategory(cid, pc);
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "f:/jsp/book/list.jsp";
    }
    /**
     * 按作者查
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByAuthor(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pc=getPc(req);
        String author=req.getParameter("author");
        String url=getUrl(req);
        PageBean<Book> pb=bookService.findByAuthor(author,pc);
        pb.setUrl(url);
       req.setAttribute("pb",pb);
       return "f:/jsp/book/list.jsp";
    }
    /**
     * 按出版社查询
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByPress(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pc=getPc(req);
        String url=getUrl(req);
        String press=req.getParameter("press");
        PageBean<Book> pb=bookService.findByPress(press,pc);
        pb.setUrl(url);
        req.setAttribute("pb",pb);
        return "f:/jsp/book/list.jsp";
    }
    /**
     * 按图名查
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByBname(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pc=getPc(req);
        String url=getUrl(req);
        String bname=req.getParameter("bname");
        PageBean<Book> pb=bookService.findByBname(bname,pc);
        pb.setUrl(url);
        req.setAttribute("pb",pb);
        return "f:/jsp/book/list.jsp";
    }
    /**
     * 多条件组合查询
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByCombination(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int pc=getPc(req);
        String url=getUrl(req);
        Book criteria = CommonUtils.toBean(req.getParameterMap(), Book.class);
        PageBean<Book> pb=bookService.findByCombination(criteria,pc);
        pb.setUrl(url);
        req.setAttribute("pb",pb);
        return "f:/jsp/book/list.jsp";
    }
}

