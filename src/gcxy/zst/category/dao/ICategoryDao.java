package gcxy.zst.category.dao;

import gcxy.zst.category.model.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ICategoryDao {
    public List<Category> findAll() throws SQLException;
    public List<Category> findByParent(String pid) throws SQLException;
    public void add(Category category) throws SQLException ;
    public List<Category> findParents() throws SQLException;
    public Category load(String cid) throws SQLException ;
    public void edit(Category category) throws SQLException ;
    public int findChildrenCountByParent(String pid) throws SQLException ;
    public void delete(String cid) throws SQLException ;
}
