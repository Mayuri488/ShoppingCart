package com.jd.service;

import com.jd.beans.ShoppingCart;
import com.jd.dao.ShoppingCartDao;
import com.jd.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@PropertySource({"classpath:default.properties","classpath:application.properties"})
@PropertySource(value = {"classpath:application.properties"})
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private Environment env;


    @Override
    public CartResponse retrieveCartById(Integer cartId)
    {
        ShoppingCart shoppingCart = shoppingCartDao.retrieveCartById(cartId);
        CartResponse cartResponse = getCartResponse(shoppingCart);
        return cartResponse;
    }

    private CartResponse getCartResponse(ShoppingCart shoppingCart) {
        CartResponse cartResponse = null;
        if (shoppingCart == null) {
            cartResponse = new CartResponse();
            cartResponse.setData(shoppingCart);
            cartResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            cartResponse.setMessage(env.getProperty("cart.failure"));
        } else {
            cartResponse = new CartResponse();
            cartResponse = new CartResponse();
            cartResponse.setData(shoppingCart);
            cartResponse.setStatusCode(HttpStatus.OK.value());
            cartResponse.setMessage(env.getProperty("cart.success"));
        }
        return cartResponse;
    }

    @Override
    public CartResponse createCart(ShoppingCart shoppingCart) {
        CartResponse cartResponse = new CartResponse();
        int updateFlag = shoppingCartDao.createCart(shoppingCart.getCartName());
        if (updateFlag == 1) {
            cartResponse.setMessage(env.getProperty("cart.create.success.message"));
            cartResponse.setStatusCode(HttpStatus.CREATED.value());
        } else {
            cartResponse.setMessage(env.getProperty("cart.create.failure.message"));
            cartResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return cartResponse;
    }

    @Override
    public CartResponse retrieveAllCarts() {
        List<ShoppingCart> shoppingCartList = shoppingCartDao.retrieveAllCarts();
        CartResponse cartResponse = null;
        if (shoppingCartList == null) {
            cartResponse = new CartResponse();
            cartResponse.setData(shoppingCartList);
            cartResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            cartResponse.setMessage(env.getProperty("cart.failure"));
        } else {
            cartResponse = new CartResponse();
            cartResponse = new CartResponse();
            cartResponse.setData(shoppingCartList);
            cartResponse.setStatusCode(HttpStatus.OK.value());
            cartResponse.setMessage(env.getProperty("cart.success"));//cart.failure
        }
        return cartResponse;
    }

    @Override
    public CartResponse updateCart(ShoppingCart shoppingCart) {
        int updateFlag;
        CartResponse cartResponse=new CartResponse();
        updateFlag=shoppingCartDao.updateCart(shoppingCart);
        if (updateFlag == 1) {
            cartResponse.setMessage(env.getProperty("cart.update.success.message"));
            cartResponse.setStatusCode(HttpStatus.OK.value());
        } else {
            cartResponse.setMessage(env.getProperty("cart.update.failure.message"));
            cartResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return cartResponse;
    }

    @Override
    public CartResponse deleteCartById(Integer cartId) {
        int updateFlag;
        CartResponse cartResponse=new CartResponse();
        updateFlag=shoppingCartDao.deleteCartById(cartId);
        if (updateFlag == 1) {
            cartResponse.setMessage(env.getProperty("cart.delete.success.message"));
            cartResponse.setStatusCode(HttpStatus.OK.value());
        } else {
            cartResponse.setMessage(env.getProperty("cart.delete.failure.message"));
            cartResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return cartResponse;
    }
}
