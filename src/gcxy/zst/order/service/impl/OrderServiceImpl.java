package gcxy.zst.order.service.impl;

import cn.itcast.jdbc.JdbcUtils;
import gcxy.zst.order.dao.ipml.OrderDaoImpl;
import gcxy.zst.order.model.Order;
import gcxy.zst.order.service.IOrderService;
import gcxy.zst.pager.PageBean;

import java.sql.SQLException;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2015:22
 * @Version 1.0
 **/
public class OrderServiceImpl implements IOrderService {
    OrderDaoImpl orderDao=new OrderDaoImpl();

    /**
     * 更新订单状态
     * @param oid
     * @param status
     */
    @Override
    public void updateStatus(String oid, int status) {
        try {
            orderDao.updateStatus(oid, status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询订单状态
     * @param oid
     * @return
     */
    @Override
    public int findStatus(String oid) {
        try {
            return orderDao.findStatus(oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载订单
     * @param oid
     * @return
     */
    @Override
    public Order load(String oid) {
        try {
            JdbcUtils.beginTransaction();
            Order order = orderDao.load(oid);
            JdbcUtils.commitTransaction();
            return order;
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException e1) {}
            throw new RuntimeException(e);
        }
    }

    /**
     * 增加订单
     * @param order
     */
    @Override
    public void createOrder(Order order) {
        try {
            JdbcUtils.beginTransaction();
            orderDao.add(order);
            JdbcUtils.commitTransaction();
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException e1) {}
            throw new RuntimeException(e);
        }
    }
    /**
     * 查找我的订单
     * @param uid
     * @param pc
     * @return
     */
    @Override
    public PageBean<Order> myOrders(String uid, int pc) {
        try {
            JdbcUtils.beginTransaction();
            PageBean<Order> pageBean= orderDao.findByUser(uid, pc);
            JdbcUtils.commitTransaction();
            return pageBean;
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException ex) {}
            throw new RuntimeException(e);
        }
    }

    /**
     * 按照订单状态查询
     * @param status
     * @param pc
     * @return
     */
    @Override
    public PageBean<Order> findByStatus(int status, int pc) {
        try {
            JdbcUtils.beginTransaction();
            PageBean<Order> pageBean= orderDao.findByStatus(status, pc);
            JdbcUtils.commitTransaction();
            return pageBean;
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException ex) {}
            throw new RuntimeException(e);
        }
    }

    /**
     * 所有订单
     * @param pc
     * @return
     */
    @Override
    public PageBean<Order> findAll(int pc) {
        try {
            JdbcUtils.beginTransaction();
            PageBean<Order> pageBean= orderDao.findAll(pc);
            JdbcUtils.commitTransaction();
            return pageBean;
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException ex) {}
            throw new RuntimeException(e);
        }
    }
}
