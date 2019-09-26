package gcxy.zst.book.dao.impl;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import gcxy.zst.book.dao.IBookDao;
import gcxy.zst.book.model.Book;
import gcxy.zst.category.model.Category;
import gcxy.zst.pager.Expression;
import gcxy.zst.pager.PageBean;
import gcxy.zst.pager.PageConstants;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BookDaoImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/208:32
 * @Version 1.0
 **/
public class BookDaoImpl implements IBookDao {
     QueryRunner runner = new TxQueryRunner();

    /**
     * 删除图书
     * @param bid
     * @throws SQLException
     */
    public void delete(String bid) throws SQLException {
        String sql = "delete from t_book where bid=?";
        runner.update(sql, bid);
    }

    /**
     * 修改图书
     * @param book
     * @throws SQLException
     */
    public void edit(Book book) throws SQLException {
        String sql = "update t_book set bname=?,author=?,price=?,currPrice=?," +
                "discount=?,press=?,publishtime=?,edition=?,pageNum=?,wordNum=?," +
                "printtime=?,booksize=?,paper=?,cid=? where bid=?";
        Object[] params = {book.getBname(),book.getAuthor(),
                book.getPrice(),book.getCurrPrice(),book.getDiscount(),
                book.getPress(),book.getPublishtime(),book.getEdition(),
                book.getPageNum(),book.getWordNum(),book.getPrinttime(),
                book.getBooksize(),book.getPaper(),
                book.getCategory().getCid(),book.getBid()};
        runner.update(sql, params);
    }

    /**
     * 按bid查询
     * @param bid
     * @return
     * @throws SQLException
     */
    public Book findByBid(String bid) throws SQLException {
        String sql = "SELECT * FROM t_book b, t_category c WHERE b.cid=c.cid AND b.bid=?";
        Map<String,Object> map = runner.query(sql, new MapHandler(), bid);
        Book book = CommonUtils.toBean(map, Book.class);
        Category category = CommonUtils.toBean(map, Category.class);
        book.setCategory(category);// 两者建立关系
        if(map.get("pid") != null) {
            Category parent = new Category();
            parent.setCid((String)map.get("pid"));
            category.setParent(parent);
        }
        return book;
    }

    /**
     * 查询指定分类下图书的个数
     * @param cid
     * @return
     * @throws SQLException
     */
    public int findBookCountByCategory(String cid) throws SQLException {
        String sql = "select count(*) from t_book where cid=?";
        Number cnt = (Number)runner.query(sql, new ScalarHandler(), cid);
        return cnt == null ? 0 : cnt.intValue();
    }

    /**
     * 按分类查询
     * @param cid
     * @param pc
     * @return
     * @throws SQLException
     */
    public PageBean<Book> findByCategory(String cid, int pc) throws SQLException {
        List<Expression> exprList = new ArrayList<Expression>();
        exprList.add(new Expression("cid", "=", cid));
        return findByCriteria(exprList, pc);
    }

    /**
     * 按书名模糊查询
     * @param bname
     * @param pc
     * @return
     * @throws SQLException
     */
    public PageBean<Book> findByBname(String bname, int pc) throws SQLException {
        List<Expression> exprList = new ArrayList<Expression>();
        exprList.add(new Expression("bname", "like", "%" + bname + "%"));
        return findByCriteria(exprList, pc);
    }

    /**
     * 按作者查
     * @param author
     * @param pc
     * @return
     * @throws SQLException
     */
    public PageBean<Book> findByAuthor(String author, int pc) throws SQLException {
        List<Expression> exprList = new ArrayList<Expression>();
        exprList.add(new Expression("author", "like", "%" + author + "%"));
        return findByCriteria(exprList, pc);
    }

    /**
     * 按出版社查
     * @param press
     * @param pc
     * @return
     * @throws SQLException
     */
    public PageBean<Book> findByPress(String press, int pc) throws SQLException {
        List<Expression> exprList = new ArrayList<Expression>();
        exprList.add(new Expression("press", "like", "%" + press + "%"));
        return findByCriteria(exprList, pc);
    }

    /**
     * 多条件组合查询
     * @param criteria
     * @param pc
     * @return
     * @throws SQLException
     */
    public PageBean<Book> findByCombination(Book criteria, int pc) throws SQLException {
        List<Expression> exprList = new ArrayList<Expression>();
        exprList.add(new Expression("bname", "like", "%" + criteria.getBname() + "%"));
        exprList.add(new Expression("author", "like", "%" + criteria.getAuthor() + "%"));
        exprList.add(new Expression("press", "like", "%" + criteria.getPress() + "%"));
        return findByCriteria(exprList, pc);
    }

    /**
     * 通用的查询方法
     * @param exprList
     * @param pc
     * @return
     * @throws SQLException
     */
    private PageBean<Book> findByCriteria(List<Expression> exprList, int pc) throws SQLException {
        int ps = PageConstants.BOOK_PAGE_SIZE;//每页记录数
        StringBuilder whereSql = new StringBuilder(" where 1=1");
        List<Object> params = new ArrayList<Object>();//SQL中有问号，它是对应问号的值
        for(Expression expr : exprList) {
            whereSql.append(" and ").append(expr.getName())
                    .append(" ").append(expr.getOperator()).append(" ");
            if(!expr.getOperator().equals("is null")) {
                whereSql.append("?");
                params.add(expr.getValue());
            }
        }
        String sql = "select count(*) from t_book" + whereSql;
        Number number = (Number)runner.query(sql, new ScalarHandler(), params.toArray());
        int tr = number.intValue();//得到了总记录数
        sql = "select * from t_book" + whereSql + " order by orderBy limit ?,?";
        params.add((pc-1) * ps);//当前页首行记录的下标
        params.add(ps);//每页记录数
        List<Book> beanList = runner.query(sql, new BeanListHandler<Book>(Book.class),
                params.toArray());
        PageBean<Book> pb = new PageBean<Book>();
        pb.setBeanList(beanList);
        pb.setPc(pc);
        pb.setPs(ps);
        pb.setTr(tr);
        return pb;
    }

    /**
     * 添加图书
     * @param book
     * @throws SQLException
     */
    public void add(Book book) throws SQLException {
        String sql = "insert into t_book(bid,bname,author,price,currPrice," +
                "discount,press,publishtime,edition,pageNum,wordNum,printtime," +
                "booksize,paper,cid,image_w,image_b)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {book.getBid(),book.getBname(),book.getAuthor(),
                book.getPrice(),book.getCurrPrice(),book.getDiscount(),
                book.getPress(),book.getPublishtime(),book.getEdition(),
                book.getPageNum(),book.getWordNum(),book.getPrinttime(),
                book.getBooksize(),book.getPaper(), book.getCategory().getCid(),
                book.getImage_w(),book.getImage_b()};
        runner.update(sql, params);
    }
}
