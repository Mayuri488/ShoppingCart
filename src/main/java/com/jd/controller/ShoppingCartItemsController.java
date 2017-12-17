package com.jd.controller;

import com.jd.beans.Items;
import com.jd.response.CartResponse;
import com.jd.service.ShoppingCartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartItemsController {

    @Autowired
    private ShoppingCartItemsService shoppingCartItemsService;

    @RequestMapping(value="/shopping_carts/{cartId}/items", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public CartResponse createShoppingCartItem(@RequestBody Items items, @PathVariable("cartId") Integer cartId){
        items.setCartId(cartId);
        CartResponse cartResponse=shoppingCartItemsService.createCartItem(items);
        return cartResponse;

    }

    @RequestMapping(value="/shopping_carts/{cartId}/items/{itemId}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public CartResponse updateShoppingCartItem(@RequestBody Items items, @PathVariable("cartId") Integer cartId, @PathVariable("itemId") Integer itemId){
        items.setCartId(cartId);
        items.setItemId(itemId);
        CartResponse cartResponse=shoppingCartItemsService.updateCartItem(items);
        return cartResponse;

    }

    @RequestMapping(value="/shopping_carts/{cartId}/items/{itemId}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
    public CartResponse deleteShoppingCartItem(@RequestBody Items items, @PathVariable("cartId") Integer cartId, @PathVariable("itemId") Integer itemId){
        CartResponse cartResponse=shoppingCartItemsService.deleteCartItemById(itemId);
        return cartResponse;
    }


    @RequestMapping(value="/shopping_carts/{cartId}/items", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public CartResponse retrieveAllCartItems(@PathVariable("cartId") Integer cartId){
        CartResponse cartResponse=shoppingCartItemsService.retrieveAllCartsItems(cartId);
        return cartResponse;
    }

    @RequestMapping(value="/shopping_carts/{cartId}/items/{itemId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public CartResponse retrieveCartItems(@PathVariable("cartId") Integer cartId,@PathVariable("itemId") Integer itemId){
        CartResponse cartResponse=shoppingCartItemsService.retrieveCartItemById(itemId);
        return cartResponse;
    }
}
