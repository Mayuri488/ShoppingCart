package com.jd.service;

import com.jd.beans.ShoppingCart;
import com.jd.response.CartResponse;

public interface ShoppingCartService {
    public CartResponse retrieveCartById(Integer id);
    public CartResponse createCart(ShoppingCart shoppingCart);
    public CartResponse retrieveAllCarts();
    public CartResponse updateCart(ShoppingCart shoppingCart);
    public CartResponse deleteCartById(Integer cartId);
}
