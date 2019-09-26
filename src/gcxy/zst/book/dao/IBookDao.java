package gcxy.zst.book.dao;

import gcxy.zst.book.model.Book;
import gcxy.zst.pager.PageBean;

import java.sql.SQLException;

/**
 * @ClassName IBookDao
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/20 8:33
 * @Version 1.0
 **/
public interface IBookDao {
    public void delete(String bid) throws SQLException;
    public void edit(Book book) throws SQLException;
    public Book findByBid(String bid) throws SQLException;
    public int findBookCountByCategory(String cid) throws SQLException;
    public PageBean<Book> findByCategory(String cid, int pc) throws SQLException;
    public PageBean<Book> findByBname(String bname, int pc) throws SQLException;
    public PageBean<Book> findByAuthor(String author, int pc) throws SQLException;
    public PageBean<Book> findByPress(String press, int pc) throws SQLException;
    public PageBean<Book> findByCombination(Book criteria, int pc) throws SQLException;
    public void add(Book book) throws SQLException;


}
