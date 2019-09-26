package gcxy.zst.order.service;

import gcxy.zst.order.model.Order;
import gcxy.zst.pager.PageBean;

public interface IOrderService {
    public void updateStatus(String oid, int status) ;
    public int findStatus(String oid);
    public Order load(String oid);
    public void createOrder(Order order);
    public PageBean<Order> myOrders(String uid, int pc);
    public PageBean<Order> findByStatus(int status, int pc);
    public PageBean<Order> findAll(int pc);

}
