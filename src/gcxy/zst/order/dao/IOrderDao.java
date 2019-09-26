package gcxy.zst.order.dao;

import gcxy.zst.order.model.Order;
import gcxy.zst.pager.PageBean;

import java.sql.SQLException;

public interface IOrderDao {
    public int findStatus(String oid) throws SQLException;
    public void updateStatus(String oid, int status) throws SQLException;
    public Order load(String oid) throws SQLException;
    public void add(Order order) throws SQLException;
    public PageBean<Order> findByUser(String uid, int pc) throws SQLException;
    public PageBean<Order> findAll(int pc) throws SQLException;
    public PageBean<Order> findByStatus(int status, int pc) throws SQLException;


}
