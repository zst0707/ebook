package gcxy.zst.category.dao.impl;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import gcxy.zst.category.dao.ICategoryDao;
import gcxy.zst.category.model.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryDaoImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/1917:12
 * @Version 1.0
 **/
public class CategoryDaoImpl implements ICategoryDao {
    QueryRunner runner = new TxQueryRunner();
    /*
     * 把一个Map中的数据映射到Category中
     */
    private Category toCategory(Map<String,Object> map) {
        Category category = CommonUtils.toBean(map, Category.class);
        String pid = (String)map.get("pid");// 如果是一级分类，那么pid是null
        if(pid != null) {

            Category parent = new Category();
            parent.setCid(pid);
            category.setParent(parent);
        }
        return category;
    }
    private List<Category> toCategoryList(List<Map<String,Object>> mapList) {
        List<Category> categoryList = new ArrayList<Category>();//创建一个空集合
        for(Map<String,Object> map : mapList) {
            Category c = toCategory(map);//把Map转换成Category
            categoryList.add(c);
        }
        return categoryList;
    }
    /**
     * 返回所有分类
     * @return
     * @throws SQLException
     */
    @Override
    public List<Category> findAll() throws SQLException {
        String sql="select * from t_category where pid is null order by orderBy";
        List<Map<String,Object>> mapList=runner.query(sql,new MapListHandler());
        List<Category> parents=toCategoryList(mapList);
        for(Category parent:parents){
            List<Category> chidren=findByParent(parent.getCid());
            parent.setChildren(chidren);
        }
        return parents;
    }
    /**
     * 通过父分类查询子分类
     * @param pid
     * @return
     * @throws SQLException
     */
    @Override
    public List<Category> findByParent(String pid) throws SQLException {
        String sql="select * from t_category where pid=? order by orderBy";
        List<Map<String,Object>> mapList=runner.query(sql,new MapListHandler(),pid);
        return toCategoryList(mapList);
    }
    /**
     * 添加分类
     * @param category
     * @throws SQLException
     */
    @Override
    public void add(Category category) throws SQLException {
        String sql="insert into t_category(cid,cname,pid,desk)values(?,?,?,?)";
        String pid=null;//一级分类
        if(category.getParent()!=null){
            pid=category.getParent().getCid();
        }
        Object[] params={category.getCid(),category.getCname(),pid,category.getDesk()};
        runner.update(sql,params);
    }
    /**
     * 获取所有父分类，但不带子分类的！
     * @return
     * @throws SQLException
     */
    @Override
    public List<Category> findParents() throws SQLException {
        String sql="select *from t_category where pid is null order by orderBy";
        List<Map<String,Object>> mapList=runner.query(sql,new MapListHandler());
        return toCategoryList(mapList);
    }
    /**
     * 加载分类
     * 即可加载一级分类，也可加载二级分类
     * @param cid
     * @return
     * @throws SQLException
     */
    @Override
    public Category load(String cid) throws SQLException {
        String sql="select *from t_category where cid=? ";
        return toCategory(runner.query(sql,new MapHandler(),cid));
    }
    /**
     * 修改分类
     * 即可修改一级分类，也可修改二级分类
     * @param category
     * @throws SQLException
     */
    @Override
    public void edit(Category category) throws SQLException {
        String sql="update t_category set cname=?,pid=?,desk=? where cid=?";
        String pid=null;
        if(category.getParent()!=null){
            pid=category.getParent().getCid();
        }
        Object [] params={category.getCname(),pid,category.getDesk(),category.getCid()};
        runner.update(sql,params);

    }
    /**
     * 查询指定父分类下子分类的个数
     * @param pid
     * @return
     * @throws SQLException
     */
    @Override
    public int findChildrenCountByParent(String pid) throws SQLException {
        String sql="select count(*) from t_category where pid=?";
        Number cnt=(Number)runner.query(sql,new ScalarHandler(),pid);
        return cnt==null ? 0: cnt.intValue();
    }
    /**
     * 删除分类
     * @param cid
     * @throws SQLException
     */
    @Override
    public void delete(String cid) throws SQLException {
        String sql="delete from t_category where cid=?";
        runner.update(sql,cid);
    }






}
