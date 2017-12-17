package com.jd.controller;

import com.jd.beans.Items;
import com.jd.response.CartResponse;
import com.jd.service.ShoppingCartItemsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.mockito.Matchers.anyObject;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartItemsControllerTest {
    @Mock
    private ShoppingCartItemsService shoppingCartItemsService;

    @InjectMocks
    ShoppingCartItemsController shoppingCartItemsController;

    @Test
    public void createShoppingCartItemSuccess() throws Exception {

        Items items=new Items();
        items.setItemId(1);
        items.setItemName("abc");

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Cart Item Created Successfully", HttpStatus.CREATED.value());

        Mockito.when(shoppingCartItemsService.createCartItem(items)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse = shoppingCartItemsController.createShoppingCartItem(items,1);

        Assert.assertEquals(expectedCartResponse,actualCartResponse);

    }

    @Test
    public void updateShoppingCartItemSuccess() throws Exception {
        Items items=new Items();
        items.setItemName("abc");

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Cart Item Updated Successfully", HttpStatus.OK.value());

        Mockito.when(shoppingCartItemsService.updateCartItem(items)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse = shoppingCartItemsController.updateShoppingCartItem(items,1,1);

        Assert.assertEquals(expectedCartResponse,actualCartResponse);


    }

    @Test
    public void deleteShoppingCartItemShouldReturnSuccess() throws Exception {
        Items items=new Items();
        items.setItemName("abc");

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Cart Items Deleted Successfully", HttpStatus.OK.value());

        Mockito.when(shoppingCartItemsService.deleteCartItemById(1)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse = shoppingCartItemsController.deleteShoppingCartItem(items,1,1);

        Assert.assertEquals(expectedCartResponse,actualCartResponse);
    }

    @Test
    public void retrieveCartItemsByIdReturnsSuccess() throws Exception {


        CartResponse expectedCartResponse = new CartResponse(new CartResponse(),"success", HttpStatus.OK.value());

        Mockito.when(shoppingCartItemsService.retrieveAllCartsItems(1)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse = shoppingCartItemsController.retrieveAllCartItems(1);

        Assert.assertEquals(expectedCartResponse,actualCartResponse);
    }



    @Test
    public void retrieveAllCartItemsByIdShouldReturnSuccess() throws Exception {
        CartResponse expectedCartResponse = new CartResponse(anyObject(),"success", HttpStatus.OK.value());

        Mockito.when(shoppingCartItemsService.retrieveCartItemById(1)).thenReturn(expectedCartResponse);

        CartResponse actualCartResponse = shoppingCartItemsController.retrieveCartItems(1,1);

        Assert.assertEquals(expectedCartResponse,actualCartResponse);
    }

}