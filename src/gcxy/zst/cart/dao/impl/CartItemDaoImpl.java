package gcxy.zst.cart.dao.impl;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import gcxy.zst.book.model.Book;
import gcxy.zst.cart.dao.ICartItemDao;
import gcxy.zst.cart.model.CartItem;
import gcxy.zst.user.model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CartItemDaoImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2011:05
 * @Version 1.0
 **/
public class CartItemDaoImpl implements ICartItemDao {
    QueryRunner runner = new TxQueryRunner();

    /*
     * 用来生成where子句
     */
    private String toWhereSql(int len) {
        StringBuilder sb = new StringBuilder("cartItemId in(");
        for(int i = 0; i < len; i++) {
            sb.append("?");
            if(i < len - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
    /*
     * 把一个Map映射成一个Cartitem
     */
    private CartItem toCartItem(Map<String,Object> map) {
        if(map == null || map.size() == 0) return null;
        CartItem cartItem = CommonUtils.toBean(map, CartItem.class);
        Book book = CommonUtils.toBean(map, Book.class);
        User user = CommonUtils.toBean(map, User.class);
        cartItem.setBook(book);
        cartItem.setUser(user);
        return cartItem;
    }

    /*
     * 把多个Map(List<Map>)映射成多个CartItem(List<CartItem>)
     */
    private List<CartItem> toCartItemList(List<Map<String,Object>> mapList) {
        List<CartItem> cartItemList = new ArrayList<CartItem>();
        for(Map<String,Object> map : mapList) {
            CartItem cartItem = toCartItem(map);
            cartItemList.add(cartItem);
        }
        return cartItemList;
    }

    /**
     * 加载多个CartItem
     * @param cartItemIds
     * @return
     * @throws SQLException
     */
    @Override
    public List<CartItem> loadCartItems(String cartItemIds) throws SQLException {
        Object[] cartItemIdArray=cartItemIds.split(",");
        String whereSql = toWhereSql(cartItemIdArray.length);
        String sql="select* from t_cartitem c, t_book b where c.bid=b.bid and " + whereSql;
        return toCartItemList(runner.query(sql, new MapListHandler(), cartItemIdArray));
    }
    /**
     * 按id查询
     * @param cartItemId
     * @return
     * @throws SQLException
     */
    @Override
    public CartItem findByCartItemId(String cartItemId) throws SQLException {
        String sql="select *from  t_cartItem c, t_book b where c.bid=b.bid and c.cartItemId=?";
        Map<String,Object> map=runner.query(sql,new MapHandler(),cartItemId);
        return toCartItem(map);
    }

    /**
     * 批量删除购物车商品
     * @param cartItemIds
     * @throws SQLException
     */
    @Override
    public void batchDelete(String cartItemIds) throws SQLException {
        Object[] cartItemIdArray=cartItemIds.split(",");
        String wheresql=toWhereSql(cartItemIdArray.length);
        String sql="delete from t_cartitem where "+wheresql;
        runner.update(sql,cartItemIdArray);
    }

    /**
     * 查询某个用户的某本图书的购物车条目是否存在
     * @throws SQLException
     */
    @Override
    public CartItem findByUidAndBid(String uid, String bid) throws SQLException {
        String sql="select *from t_cartitem where uid=? and bid=?";
        Map<String,Object> map=runner.query(sql,new MapHandler(),uid,bid);
        CartItem cartItem=toCartItem(map);
        return cartItem;
    }

    /**
     * 修改指定条目的数量
     * @param cartItemId
     * @param quantity
     * @throws SQLException
     */
    @Override
    public void updateQuantity(String cartItemId, int quantity) throws SQLException {
        String sql="update t_cartitem set quantity=? where cartItemId=?";
        runner.update(sql,quantity,cartItemId);
    }

    /**
     * 添加到购物车
     * @param cartItem
     * @throws SQLException
     */
    @Override
    public void addCartItem(CartItem cartItem) throws SQLException {
        String sql="insert into t_cartitem (cartItemId,quantity,bid,uid) values(?,?,?,?)";
        Object[] params={cartItem.getCartItemId(),cartItem.getQuantity(),cartItem.getBook().getBid(),cartItem.getUser().getUid()};
        runner.update(sql,params);
    }
    /**
     * 通过用户查询购物车条目
     * @param uid
     * @return
     * @throws SQLException
     */
    @Override
    public List<CartItem> findByUser(String uid) throws SQLException {
        String sql="select *from t_cartitem a,t_book b where a.bid=b.bid and uid=? order by a.orderBy";
        List<Map<String,Object>> list=runner.query(sql,new MapListHandler(),uid);
        return toCartItemList(list);
    }
}
