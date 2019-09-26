package gcxy.zst.admin.book_servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import gcxy.zst.book.model.Book;
import gcxy.zst.book.service.impl.BookServiceImpl;
import gcxy.zst.category.model.Category;
import gcxy.zst.category.service.impl.CategoryServiceImpl;
import gcxy.zst.pager.PageBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * @ClassName AdminBookServlet
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2220:38
 * @Version 1.0
 **/
public class AdminBookServlet extends BaseServlet {
    BookServiceImpl bookService=new BookServiceImpl();
    CategoryServiceImpl categoryService=new CategoryServiceImpl();
    /**
     * 删除图书
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bid=req.getParameter("bid");
        Book book = bookService.load(bid);
        String savepath = this.getServletContext().getRealPath("/");//获取真实的路径
        new File(savepath, book.getImage_w()).delete();//删除文件
        new File(savepath, book.getImage_b()).delete();//删除文件
        bookService.delete(bid);
        req.setAttribute("msg", "成功删除图书！");
        return "f:/adminjsps/msg.jsp";
    }
    /**
     * 修改图书
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String edit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Book book= CommonUtils.toBean(req.getParameterMap(),Book.class);
        Category category=CommonUtils.toBean(req.getParameterMap(),Category.class);
        book.setCategory(category);
        bookService.edit(book);
        req.setAttribute("msg", "修改图书成功！");
        return "f:/adminjsps/msg.jsp";
    }


    /**
     * 加载图书
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
        req.setAttribute("book", book);
        req.setAttribute("parents", categoryService.findParents());//获取所有一级分类，保存之
        String pid = book.getCategory().getParent().getCid();
        req.setAttribute("children", categoryService.findChildren(pid));//获取当前图书所属的一级分类下所有2级分类
        return "f:/adminjsps/admin/book/desc.jsp";
    }
    /**
     * 添加图书：第一步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String addPre(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Category> parents = categoryService.findParents();
        req.setAttribute("parents", parents);
        return "f:/adminjsps/admin/book/add.jsp";
    }

    /**
     * 找二级分类
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
         * 1. 获取pid
         * 2. 通过pid查询出所有2级分类
         * 3. 把List<Category>转换成json，输出给客户端
         */
        String pid = req.getParameter("pid");
        List<Category> children = categoryService.findChildren(pid);
        String json = toJson(children);
        resp.getWriter().print(json);
        return null;
    }

    private String toJson(Category category) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"cid\"").append(":").append("\"").append(category.getCid()).append("\"");
        sb.append(",");
        sb.append("\"cname\"").append(":").append("\"").append(category.getCname()).append("\"");
        sb.append("}");
        return sb.toString();
    }

    private String toJson(List<Category> categoryList) {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < categoryList.size(); i++) {
            sb.append(toJson(categoryList.get(i)));
            if(i < categoryList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 显示所有分类
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findCategoryAll(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Category> parents=categoryService.findAll();
        req.setAttribute("parents", parents);
        return "f:/adminjsps/admin/book/left.jsp";
    }


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
        String url=getUrl(req);
        String cid=req.getParameter("cid");
        PageBean<Book> pb =bookService.findByCategory(cid,pc);
        pb.setUrl(url);
        req.setAttribute("pb",pb);
        return "f:/adminjsps/admin/book/list.jsp";
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
        String url=getUrl(req);
        String author=req.getParameter("author");
        PageBean<Book> pb= bookService.findByAuthor(author,pc);
        pb.setUrl(url);
        req.setAttribute("pb",pb);
        return "f:/adminjsps/admin/book/list.jsp";
    }
    /**
     * 按出版社查
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
        PageBean<Book> pb= bookService.findByAuthor(press,pc);
        pb.setUrl(url);
        req.setAttribute("pb",pb);
        return "f:/adminjsps/admin/book/list.jsp";
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
        PageBean<Book> pb= bookService.findByAuthor(bname,pc);
        pb.setUrl(url);
        req.setAttribute("pb",pb);
        return "f:/adminjsps/admin/book/list.jsp";
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
        Book criteria=CommonUtils.toBean(req.getParameterMap(),Book.class);
        PageBean<Book> pb=bookService.findByCombination(criteria,pc);
        pb.setUrl(url);
        req.setAttribute("pb",pb);
        return "f:/adminjsps/admin/book/list.jsp";
    }

}
