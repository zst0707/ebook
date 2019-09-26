package gcxy.zst.category.servlet;

import cn.itcast.servlet.BaseServlet;
import gcxy.zst.category.model.Category;
import gcxy.zst.category.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends BaseServlet {
    CategoryServiceImpl categoryService=new CategoryServiceImpl();
    /**
     * 查询所有分类
     */
    public String findAll(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Category> parents=categoryService.findAll();
        req.setAttribute("parents",parents);
        return "f:/jsp/left.jsp";
    }

}
