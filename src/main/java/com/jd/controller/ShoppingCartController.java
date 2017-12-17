package com.jd.controller;

import com.jd.beans.ShoppingCart;
import com.jd.response.CartResponse;
import com.jd.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mayuri.patil on 19-09-2017.
 */
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {


    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping(value = "/test", method = RequestMethod.GET, consumes = "application/json")
    public String test() {
        return "Hi";
    }

    @RequestMapping(value = "/shopping_carts/{cartId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public CartResponse retrieveCartById(@PathVariable("cartId") Integer cartId) {
        CartResponse cartResponse = shoppingCartService.retrieveCartById(cartId);
        return cartResponse;
    }

    @RequestMapping(value = "/shopping_carts", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public CartResponse createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        CartResponse cartResponse;
        return cartResponse = shoppingCartService.createCart(shoppingCart);
    }

    @RequestMapping(value = "/shopping_carts", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public CartResponse retrieveAllCarts() {
        CartResponse cartResponse = shoppingCartService.retrieveAllCarts();
        return cartResponse;
    }

    @RequestMapping(value = "/shopping_carts/{cartId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public CartResponse updateShoppingCart(@PathVariable("cartId") Integer cartId, @RequestBody ShoppingCart shoppingCart) {
        shoppingCart.setCartId(cartId);
        CartResponse cartResponse = shoppingCartService.updateCart(shoppingCart);
        return cartResponse;
    }

    @RequestMapping(value = "/shopping_carts/{cartId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
    public CartResponse deleteShoppingCart(@PathVariable("cartId") Integer cartId, @RequestBody ShoppingCart shoppingCart) {
        CartResponse cartResponse = shoppingCartService.deleteCartById(cartId);
        return cartResponse;
    }


}
