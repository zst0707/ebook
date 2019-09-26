package gcxy.zst.cart.service.impl;

import cn.itcast.commons.CommonUtils;
import gcxy.zst.cart.dao.impl.CartItemDaoImpl;
import gcxy.zst.cart.model.CartItem;
import gcxy.zst.cart.service.ICartItemService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName CartItemServiceImpl
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/2011:05
 * @Version 1.0
 **/
public class CartItemServiceImpl implements ICartItemService {
    CartItemDaoImpl cartItemDao=new CartItemDaoImpl();

    /**
     * 加载购物车
     * @param cartItemIds
     * @return
     */
    @Override
    public List<CartItem> loadCartItems(String cartItemIds) {
        try {
            return cartItemDao.loadCartItems(cartItemIds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改购物车商品数量
     * @param cartItemId
     * @param quantity
     * @return
     */
    @Override
    public CartItem updateQuantity(String cartItemId, int quantity) {
        try {
            cartItemDao.updateQuantity(cartItemId, quantity);
            return  cartItemDao.findByCartItemId(cartItemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量删除购物商品
     * @param cartItemIds
     */
    @Override
    public void batchDelete(String cartItemIds) {
        try {
            cartItemDao.batchDelete(cartItemIds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加购物车商品
     * @param cartItem
     */
    @Override
    public void add(CartItem cartItem) {
        try {
            CartItem _cartItem1=cartItemDao.findByUidAndBid(cartItem.getUser().getUid(),
                    cartItem.getBook().getBid());
            if(_cartItem1 ==null){
                cartItem.setCartItemId(CommonUtils.uuid());
                cartItemDao.addCartItem(cartItem);
            }else {
                int quantity=cartItem.getQuantity()+_cartItem1.getQuantity();
                cartItemDao.updateQuantity(_cartItem1.getCartItemId(),quantity);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 我的购物车
     * @param uid
     * @return
     */
    @Override
    public List<CartItem> myCart(String uid) {
        try {
            return cartItemDao.findByUser(uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
