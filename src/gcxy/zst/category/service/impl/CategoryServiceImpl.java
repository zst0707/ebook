package gcxy.zst.category.service.impl;

import gcxy.zst.category.dao.ICategoryDao;
import gcxy.zst.category.dao.impl.CategoryDaoImpl;
import gcxy.zst.category.model.Category;
import gcxy.zst.category.service.ICategoryService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/1917:13
 * @Version 1.0
 **/
public class CategoryServiceImpl implements ICategoryService {
    CategoryDaoImpl categoryDao = new CategoryDaoImpl();

    /**
     * 查询指定父分类下子分类的个数
     * @param pid
     * @return
     */
    public int findChildrenCountByParent(String pid) {
        try {
            return categoryDao.findChildrenCountByParent(pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除分类
     * @param cid
     */
    @Override
    public void delete(String cid) {
        try {
            categoryDao.delete(cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 编辑分类
     * @param category
     */
    @Override
    public void edit(Category category) {
        try {
            categoryDao.edit(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载分类
     * @param cid
     * @return
     */
    @Override
    public Category load(String cid) {
        try {
            return categoryDao.load(cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 增加分类
     * @param category
     */
    @Override
    public void add(Category category) {
        try {
            categoryDao.add(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<Category> findAll() {
        try {
            return categoryDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取所有父分类，不带子分类
     * @return
     */
    @Override
    public List<Category> findParents() {
        try {
            return categoryDao.findParents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询指定父分类下的所有子分类
     * @param pid
     * @return
     */
    @Override
    public List<Category> findChildren(String pid) {
        try {
            return categoryDao.findByParent(pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
