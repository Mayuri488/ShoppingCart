package com.jd.service;

import com.jd.beans.Items;
import com.jd.dao.ShoppingCartItemsDao;
import com.jd.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartItemsServiceImpl implements ShoppingCartItemsService {

    @Autowired
    private ShoppingCartItemsDao shoppingCartItemsDao;

    @Autowired
    private Environment env;

    @Override
    public CartResponse retrieveCartItemById(Integer itemId) {
        CartResponse cartResponse=new CartResponse();
        Items items=shoppingCartItemsDao.retrieveCartItemById(itemId);
        if(items==null){
            cartResponse.setMessage(env.getProperty("cart.items.failure"));
            cartResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            cartResponse.setData(null);
        }
        else {
            cartResponse.setMessage(env.getProperty("cart.items.success"));
            cartResponse.setStatusCode(HttpStatus.OK.value());
            cartResponse.setData(items);
        }
        return  cartResponse;

    }

    @Override
    public CartResponse createCartItem(Items items)
    {
        CartResponse cartResponse = new CartResponse();
        int updateFlag = shoppingCartItemsDao.createCartItem(items.getItemName(),items.getCartId());
        if (updateFlag == 1) {
            cartResponse.setMessage(env.getProperty("cart.items.create.success.message"));
            cartResponse.setStatusCode(HttpStatus.CREATED.value());
        } else {
            cartResponse.setMessage(env.getProperty("cart.items.create.failure.message"));
            cartResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return cartResponse;
    }

    @Override
    public CartResponse updateCartItem(Items items) {
        int updateFlag;
        CartResponse cartResponse=new CartResponse();
        updateFlag=shoppingCartItemsDao.updateCartItem(items);
        if (updateFlag == 1) {
            cartResponse.setMessage(env.getProperty("cart.items.update.success.message"));
            cartResponse.setStatusCode(HttpStatus.OK.value());
        } else {
            cartResponse.setMessage(env.getProperty("cart.items.update.failure.message"));
            cartResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return cartResponse;
    }

    @Override
    public CartResponse deleteCartItemById(Integer itemId) {

        int updateFlag;
        CartResponse cartResponse=new CartResponse();
        updateFlag=shoppingCartItemsDao.deleteCartItemById(itemId);
        if (updateFlag == 1) {
            cartResponse.setMessage(env.getProperty("cart.items.delete.success.message"));
            cartResponse.setStatusCode(HttpStatus.OK.value());
        } else {
            cartResponse.setMessage(env.getProperty("cart.items.delete.failure.message"));
            cartResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return cartResponse;
    }

    @Override
    public CartResponse retrieveAllCartsItems(Integer cartId) {

        List<Items> itemsList = shoppingCartItemsDao.retrieveAllCartsItemsById(cartId);
        CartResponse cartResponse = null;
        if (itemsList == null) {
            cartResponse = new CartResponse();
            cartResponse.setData(itemsList);
            cartResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            cartResponse.setMessage(env.getProperty("cart.items.failure"));
        } else {
            cartResponse = new CartResponse();
            cartResponse.setData(itemsList);
            cartResponse.setStatusCode(HttpStatus.OK.value());
            cartResponse.setMessage(env.getProperty("cart.items.success"));
        }
        return cartResponse;
    }


}
