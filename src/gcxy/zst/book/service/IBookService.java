package gcxy.zst.book.service;

import gcxy.zst.book.model.Book;
import gcxy.zst.pager.PageBean;

public interface IBookService {
    public void delete(String bid);
    public void edit(Book book);
    public int findBookCountByCategory(String cid);
    public Book load(String bid);
    public PageBean<Book> findByCategory(String cid, int pc);
    public PageBean<Book> findByBname(String bname, int pc);
    public PageBean<Book> findByAuthor(String author, int pc);
    public PageBean<Book> findByPress(String press, int pc);
    public PageBean<Book> findByCombination(Book criteria, int pc);
    public void add(Book book);

}
