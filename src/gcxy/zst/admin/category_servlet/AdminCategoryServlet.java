package gcxy.zst.admin.category_servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import gcxy.zst.book.service.impl.BookServiceImpl;
import gcxy.zst.category.model.Category;
import gcxy.zst.category.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName AdminCategoryServlet
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2220:38
 * @Version 1.0
 **/
public class AdminCategoryServlet extends BaseServlet {
    BookServiceImpl bookService = new BookServiceImpl();
    CategoryServiceImpl categoryService = new CategoryServiceImpl();

    /**
     * 查询所有分类
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findAll(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("parents", categoryService.findAll());
        return "f:/adminjsps/admin/category/list.jsp";
    }
    /**
     * 添加一级分类
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String addParent(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Category parent= CommonUtils.toBean(req.getParameterMap(),Category.class);
        parent.setCid(CommonUtils.uuid());//设置cid
        categoryService.add(parent);
        return findAll(req, resp);
    }



    /**
     * 添加第二分类：第一步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String addChildPre(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pid=req.getParameter("pid");
        List<Category> parents=categoryService.findParents();
        req.setAttribute("pid",pid);
        req.setAttribute("parents",parents);
        return "f:/adminjsps/admin/category/add2.jsp";
    }

    /**
     * 添加第二分类：第二步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String addChild(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Category child=CommonUtils.toBean(req.getParameterMap(),Category.class);
        child.setCid(CommonUtils.uuid());//设置cid
        String pid = req.getParameter("pid");
        Category parent=new Category();
        parent.setCid(pid);
        child.setParent(parent);
        categoryService.add(child);
        return findAll(req,resp);
    }


    /**
     * 修改一级分类：第一步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String editParentPre(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cid = req.getParameter("cid");
        Category parent = categoryService.load(cid);
        req.setAttribute("parent", parent);
        return "f:/adminjsps/admin/category/edit.jsp";
    }

    /**
     * 修改一级分类：第二步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String editParent(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Category parent=CommonUtils.toBean(req.getParameterMap(),Category.class);
        categoryService.edit(parent);
        return findAll(req, resp);
    }

    /**
     * 修改二级分类：第一步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String editChildPre(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cid = req.getParameter("cid");
        Category child = categoryService.load(cid);
        req.setAttribute("child", child);
        req.setAttribute("parents", categoryService.findParents());
        return "f:/adminjsps/admin/category/edit2.jsp";
    }
    /**
     * 修改二级分类：第二步
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String editChild(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Category child=CommonUtils.toBean(req.getParameterMap(),Category.class);
        String pid=req.getParameter("pid");
        Category parent = new Category();
        parent.setCid(pid);
        child.setParent(parent);
        categoryService.edit(child);
        return findAll(req, resp);
    }
    /**
     * 删除一级分类
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteParent(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cid = req.getParameter("cid");
        int cnt = categoryService.findChildrenCountByParent(cid);
        if (cnt > 0) {
            req.setAttribute("msg", "该分类下还有子分类，不能删除！");
            return "f:/adminjsps/msg.jsp";
        } else {
            categoryService.delete(cid);
            return findAll(req, resp);
        }
    }

    /**
     * 删除2级分类
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String deleteChild(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String cid=req.getParameter("cid");
        int cnt=bookService.findBookCountByCategory(cid);
        if(cnt>0){
            req.setAttribute("msg", "该分类下还有子分类，不能删除！");
            return "f:/adminjsps/msg.jsp";
        }else {
            categoryService.delete(cid);
            return findAll(req,resp);
        }

    }
}
