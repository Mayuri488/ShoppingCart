package com.jd.service;

import com.jd.beans.Items;
import com.jd.response.CartResponse;

public interface ShoppingCartItemsService {

    public CartResponse retrieveCartItemById(Integer id);
    public CartResponse createCartItem(Items items);
    public CartResponse updateCartItem(Items items);
    public CartResponse  deleteCartItemById(Integer itemId);
    public CartResponse retrieveAllCartsItems(Integer cartId);
}
