package gcxy.zst.category.service;

import gcxy.zst.category.model.Category;

import java.util.List;

public interface ICategoryService {
    public void delete(String cid);
    public void edit(Category category);
    public Category load(String cid);
    public void add(Category category);
    public List<Category> findAll();
    public List<Category> findParents();
    public List<Category> findChildren(String pid);
}
