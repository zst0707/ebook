package gcxy.zst.order.dao.ipml;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import gcxy.zst.book.model.Book;
import gcxy.zst.order.dao.IOrderDao;
import gcxy.zst.order.model.Order;
import gcxy.zst.order.model.OrderItem;
import gcxy.zst.pager.Expression;
import gcxy.zst.pager.PageBean;
import gcxy.zst.pager.PageConstants;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderDaoImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/20 15:21
 * @Version 1.0
 **/
public class OrderDaoImpl implements IOrderDao {
    QueryRunner runner=new TxQueryRunner();
    private PageBean<Order> findByCriteria(List<Expression> exprList, int pc) throws SQLException {
        /*
         * 1. 得到当前页码
         * 2. 得到总记录数
         * 3. 得到beanList
         * 4. 创建PageBean，返回
         */
        int ps = PageConstants.ORDER_PAGE_SIZE;//每页记录数
        /*
         * 2. 通过exprList来生成where子句
         */
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

        String sql = "select count(*) from t_order" + whereSql;
        Number number = (Number)runner.query(sql, new ScalarHandler(), params.toArray());
        int tr = number.intValue();//总记录数
        /*
         * 4. 得到beanList，即当前页记录
         */
        sql = "select * from t_order" + whereSql + " order by ordertime desc limit ?,?";
        params.add((pc-1) * ps);//当前页首行记录的下标
        params.add(ps);
        List<Order> beanList = runner.query(sql, new BeanListHandler<Order>(Order.class), params.toArray());
        // 虽然已经获取所有的订单，但每个订单中并没有订单条目。遍历每个订单，为其加载它的所有订单条目
        for(Order order : beanList) {
            loadOrderItem(order);
        }

        PageBean<Order> pb = new PageBean<Order>();
        pb.setBeanList(beanList);
        pb.setPc(pc);
        pb.setPs(ps);
        pb.setTr(tr);
        return pb;
    }

    /*
     * 为指定的order载它的所有OrderItem
     */
    private void loadOrderItem(Order order) throws SQLException {
        String sql = "select * from t_orderitem where oid=?";
        List<Map<String,Object>> mapList = runner.query(sql, new MapListHandler(), order.getOid());
        List<OrderItem> orderItemList = toOrderItemList(mapList);
        order.setOrderItemList(orderItemList);
    }

    /**
     * 把多个Map转换成多个OrderItem
     * @param mapList
     * @return
     */
    private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for(Map<String,Object> map : mapList) {
            OrderItem orderItem = toOrderItem(map);
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    /*
     * 把一个Map转换成一个OrderItem
     */
    private OrderItem toOrderItem(Map<String, Object> map) {
        OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
        Book book = CommonUtils.toBean(map, Book.class);
        orderItem.setBook(book);
        return orderItem;
    }

    /**
     *查询订单状态
     * @param oid
     * @return
     * @throws SQLException
     */
    @Override
    public int findStatus(String oid) throws SQLException {
        String sql="select status from t_order where oid=?";
        Number number=(Number) runner.query(sql,new ScalarHandler(),oid);
        return number.intValue();
    }

    /**
     * 修改订单状态
     * @param oid
     * @param status
     * @throws SQLException
     */
    @Override
    public void updateStatus(String oid, int status) throws SQLException {
        String sql="update t_order set status=? where oid=?";
        runner.update(sql,status,oid);
    }

    /**
     * 加载订单
     * @param oid
     * @return
     * @throws SQLException
     */
    @Override
    public Order load(String oid) throws SQLException {
        String sql="select *from t_order where oid=?";
        Order order=runner.query(sql,new BeanHandler<Order>(Order.class),oid);
        loadOrderItem(order);//为当前订单加载它的所有订单条目
        return order;
    }

    /**
     *增加订单
     * @param order
     * @throws SQLException
     */
    @Override
    public void add(Order order) throws SQLException {
        String sql="insert into t_order values(?,?,?,?,?,?)";
        Object[] params={order.getOid(),order.getOrdertime(),order.getTotal(),order.getStatus(),
        order.getAddress(),order.getOwner().getUid()};
        runner.update(sql,params);

        sql = "insert into t_orderitem values(?,?,?,?,?,?,?,?)";
        int len = order.getOrderItemList().size();
        Object[][] objs = new Object[len][];
        for(int i = 0; i < len; i++){
            OrderItem item = order.getOrderItemList().get(i);
            objs[i] = new Object[]{item.getOrderItemId(),item.getQuantity(),
                    item.getSubtotal(),item.getBook().getBid(),
                    item.getBook().getBname(),item.getBook().getCurrPrice(),
                    item.getBook().getImage_b(),order.getOid()};
        }
        runner.batch(sql, objs);
    }

    /**
     * 按用户名查找订单
     * @param uid
     * @param pc
     * @return
     * @throws SQLException
     */
    @Override
    public PageBean<Order> findByUser(String uid, int pc) throws SQLException {
        List<Expression> exprList = new ArrayList<Expression>();
        exprList.add(new Expression("uid", "=", uid));
        return findByCriteria(exprList, pc);
    }

    /**
     * 查找所有订单
     * @param pc
     * @return
     * @throws SQLException
     */
    @Override
    public PageBean<Order> findAll(int pc) throws SQLException {
        List<Expression> expressionList=new ArrayList<Expression>();
        return findByCriteria(expressionList,pc);
    }
    /**
     * 按状态查询
     * @param status
     * @param pc
     * @return
     * @throws SQLException
     */
    @Override
    public PageBean<Order> findByStatus(int status, int pc) throws SQLException {

        List<Expression> expressionList=new ArrayList<Expression>();
        expressionList.add(new Expression("status","=",status +""));
        return findByCriteria(expressionList,pc);
    }
}
