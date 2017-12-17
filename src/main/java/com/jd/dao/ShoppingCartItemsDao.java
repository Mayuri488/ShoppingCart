package com.jd.dao;

import com.jd.beans.Items;

import java.util.List;

public interface ShoppingCartItemsDao {

    public Items retrieveCartItemById(Integer id);
    public int createCartItem(String cartName, int cartId);
    public int updateCartItem(Items items);
    public int  deleteCartItemById(Integer itemId);
    public List<Items> retrieveAllCartsItemsById(Integer cartId);
}
