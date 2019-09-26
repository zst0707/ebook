package gcxy.zst.book.service.impl;

import gcxy.zst.book.dao.impl.BookDaoImpl;
import gcxy.zst.book.model.Book;
import gcxy.zst.book.service.IBookService;
import gcxy.zst.pager.PageBean;

import java.sql.SQLException;

/**
 * @ClassName BookServiceImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/20 10:21
 * @Version 1.0
 **/
public class BookServiceImpl implements IBookService {
    BookDaoImpl bookDao=new BookDaoImpl();
    /**
     * 删除图书
     * @param bid
     */
    @Override
    public void delete(String bid) {
        try {
            bookDao.delete(bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 编辑图书
     * @param book
     */
    @Override
    public void edit(Book book) {
        try {
            bookDao.edit(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回当前分类的图书数量
     * @param cid
     * @return
     */
    @Override
    public int findBookCountByCategory(String cid) {
        try {
            return bookDao.findBookCountByCategory(cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *加载图书
     * @param bid
     * @return
     */
    @Override
    public Book load(String bid) {
        try {
            return bookDao.findByBid(bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按照分类查找图书
     * @param cid
     * @param pc
     * @return
     */
    @Override
    public PageBean<Book> findByCategory(String cid, int pc) {
        try {
            return bookDao.findByCategory(cid,pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按照书名查找图书
     * @param bname
     * @param pc
     * @return
     */
    @Override
    public PageBean<Book> findByBname(String bname, int pc) {
        try {
            return bookDao.findByBname(bname,pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按照作者查找图书
     * @param author
     * @param pc
     * @return
     */
    @Override
    public PageBean<Book> findByAuthor(String author, int pc) {
        try {
            return bookDao.findByAuthor(author,pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 按照出版社查找图书
     * @param press
     * @param pc
     * @return
     */
    @Override
    public PageBean<Book> findByPress(String press, int pc) {
        try {
            return bookDao.findByPress(press, pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 组合查询
     * @param criteria
     * @param pc
     * @return
     */
    @Override
    public PageBean<Book> findByCombination(Book criteria, int pc) {
        try {
            return bookDao.findByCombination(criteria, pc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加图书
     * @param book
     */
    @Override
    public void add(Book book) {
        try {
            bookDao.add(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
