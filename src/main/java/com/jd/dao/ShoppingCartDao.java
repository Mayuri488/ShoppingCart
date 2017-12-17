package com.jd.dao;

import com.jd.beans.ShoppingCart;
import com.jd.response.CartResponse;

import java.util.List;

public interface ShoppingCartDao {

    public ShoppingCart retrieveCartById(Integer id);
    public int createCart(String cartName);
    public List<ShoppingCart> retrieveAllCarts();
    public int updateCart(ShoppingCart shoppingCart);
    public int  deleteCartById(Integer cartId);
}
