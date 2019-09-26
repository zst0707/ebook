package gcxy.zst.cart.service;

import gcxy.zst.cart.model.CartItem;

import java.util.List;

public interface ICartItemService {
    public List<CartItem> loadCartItems(String cartItemIds);
    public CartItem updateQuantity(String cartItemId, int quantity);
    public void batchDelete(String cartItemIds);
    public void add(CartItem cartItem);
    public List<CartItem> myCart(String uid);

}
