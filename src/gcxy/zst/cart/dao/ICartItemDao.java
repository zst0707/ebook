package gcxy.zst.cart.dao;

import gcxy.zst.cart.model.CartItem;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ICartItemDao
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2011:04
 * @Version 1.0
 **/
public interface ICartItemDao {
    public List<CartItem> loadCartItems(String cartItemIds) throws SQLException;
    public CartItem findByCartItemId(String cartItemId) throws SQLException ;
    public void batchDelete(String cartItemIds) throws SQLException ;
    public CartItem findByUidAndBid(String uid, String bid) throws SQLException;
    public void updateQuantity(String cartItemId, int quantity) throws SQLException;
    public void addCartItem(CartItem cartItem) throws SQLException;
    public List<CartItem> findByUser(String uid) throws SQLException;
}
