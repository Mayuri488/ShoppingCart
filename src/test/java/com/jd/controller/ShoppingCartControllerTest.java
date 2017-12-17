package com.jd.controller;

import com.jd.beans.ShoppingCart;
import com.jd.response.CartResponse;
import com.jd.service.ShoppingCartService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartControllerTest {


    @InjectMocks
    private ShoppingCartController shoppingCartController ;

    @Mock
    private ShoppingCartService shoppingCartService;


    @Test
    public void retriveCartByIdShouldReturnCartDetails(){

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"success", HttpStatus.OK.value());
        when(shoppingCartService.retrieveCartById(1)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse = shoppingCartController.retrieveCartById(1);

        Assert.assertEquals(expectedCartResponse,actualCartResponse);

    }

    @Test
    public void retrieveCartByIdFailedToGetCartDetails(){
        CartResponse expectedCartResponse = new CartResponse(anyObject(),"failure", HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(shoppingCartService.retrieveCartById(1)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse= shoppingCartController.retrieveCartById(1);

        Assert.assertEquals(expectedCartResponse,actualCartResponse);

    }

    @Test
    public void createCartededSuccesfully(){

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartName("test cart");

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Cart Created Successfully", HttpStatus.CREATED.value());

        when(shoppingCartService.createCart(shoppingCart)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse = shoppingCartController.createShoppingCart(shoppingCart);

        Assert.assertEquals(expectedCartResponse,actualCartResponse);

    }

    @Test
    public void failedToCreateCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartName("test cart");

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Failed to Create Cart", HttpStatus.INTERNAL_SERVER_ERROR.value());

        when(shoppingCartService.createCart(shoppingCart)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse = shoppingCartController.createShoppingCart(shoppingCart);

        Assert.assertEquals(expectedCartResponse,actualCartResponse);

    }

    @Test
    public void retrieveAllCartsSuccessfully(){

        CartResponse expectedCartResponse = new CartResponse(new CartResponse(),"success", HttpStatus.OK.value());
        when(shoppingCartService.retrieveAllCarts()).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse=shoppingCartController.retrieveAllCarts();

        Assert.assertEquals(expectedCartResponse,actualCartResponse);

    }

    @Test
    public void FailedretrieveAllCarts(){

        CartResponse expectedCartResponse = new CartResponse(new CartResponse(),"failure", HttpStatus.INTERNAL_SERVER_ERROR.value());

        when(shoppingCartService.retrieveAllCarts()).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse=shoppingCartController.retrieveAllCarts();
        Assert.assertEquals(expectedCartResponse,actualCartResponse);
    }

    @Test
    public void updatedShoppingCartSuccessfully(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartName("test cart");
        shoppingCart.setCartId(1);

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Cart Updated Successfully", HttpStatus.OK.value());
        when(shoppingCartService.updateCart(shoppingCart)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse =shoppingCartController.updateShoppingCart(1,shoppingCart);
        Assert.assertEquals(expectedCartResponse,actualCartResponse);
    }

    @Test
    public void failedToupdatedShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartName("test cart");
        shoppingCart.setCartId(1);

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Failed to Update Cart", HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(shoppingCartService.updateCart(shoppingCart)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse =shoppingCartController.updateShoppingCart(1,shoppingCart);
        Assert.assertEquals(expectedCartResponse,actualCartResponse);
    }

    @Test
    public void deletedShoppingCartSuccessfully(){

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartName("test cart");
        shoppingCart.setCartId(1);

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Cart Items Deleted Successfully", HttpStatus.OK.value());
        when(shoppingCartService.deleteCartById(shoppingCart.getCartId())).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse =shoppingCartController.deleteShoppingCart(1,shoppingCart);
        Assert.assertEquals(expectedCartResponse,actualCartResponse);
    }


    @Test
    public void failedTodeleteShoppingCart(){

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCartName("test cart");
        shoppingCart.setCartId(1);

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Failed to Delete Cart", HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(shoppingCartService.updateCart(shoppingCart)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse =shoppingCartController.updateShoppingCart(1,shoppingCart);
        Assert.assertEquals(expectedCartResponse,actualCartResponse);
    }
}