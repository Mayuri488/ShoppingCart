package com.jd.service;

import com.jd.beans.Items;
import com.jd.dao.ShoppingCartItemsDao;
import com.jd.response.CartResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartItemsServiceImplTest {

    @Mock
    private Environment env;

    @InjectMocks
    private ShoppingCartItemsServiceImpl shoppingCartItemsService;

    @Mock
    private ShoppingCartItemsDao cartItemsDao;

    @Test
    public void retrieveCartItemByIdShouldReturnCartDataInResponse() throws Exception {
        Items items=new Items(1,new Date(),1,"My Cart",null);
        CartResponse expectedCartResponse=new CartResponse(items,"success", HttpStatus.OK.value());

        when(cartItemsDao.retrieveCartItemById(1)).thenReturn(items);
        when(env.getProperty("cart.items.success")).thenReturn("success");

        CartResponse actualCartResponse=shoppingCartItemsService.retrieveCartItemById(1);

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());

    }

    @Test
    public void retrieveCartItemByIdShouldReturnNullDataInResponse() throws Exception {

        CartResponse expectedCartResponse=new CartResponse(anyObject(),"failure", HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(cartItemsDao.retrieveCartItemById(1)).thenReturn(null);
        when(env.getProperty("cart.items.failure")).thenReturn("failure");

        CartResponse actualCartResponse=shoppingCartItemsService.retrieveCartItemById(1);

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());
        Assert.assertNull(expectedCartResponse.getData());
    }

    @Test
    public void createCartItemShouldReturnOneForCartCreationSuccess() throws Exception {

        when(cartItemsDao.createCartItem(anyString(),anyInt())).thenReturn(1);

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Cart Item Created Successfully", HttpStatus.CREATED.value());
        when(env.getProperty("cart.items.create.success.message")).thenReturn("Cart Item Created Successfully");

        Items items=new Items();
        items.setItemName("abc");
        items.setCartId(1);
        CartResponse actualCartResponse = shoppingCartItemsService.createCartItem(items);
        assertEquals(expectedCartResponse.getMessage(), actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(), actualCartResponse.getStatusCode());
    }


    @Test
    public void createCartItemShouldReturnZeroForCartCreationFailed() throws Exception {
        when(cartItemsDao.createCartItem(anyString(),anyInt())).thenReturn(0);

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Failed to Create Cart Items", HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(env.getProperty("cart.items.create.failure.message")).thenReturn("Failed to Create Cart Items");


        Items items=new Items();
        items.setItemName("abc");
        items.setCartId(1);
        CartResponse actualCartResponse = shoppingCartItemsService.createCartItem(items);
        assertEquals(expectedCartResponse.getMessage(), actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(), actualCartResponse.getStatusCode());
    }

    @Test
    public void updateCartItemShouldReturnOneForCartUpdationSuccess() throws Exception {
        when(cartItemsDao.updateCartItem(any(Items.class))).thenReturn(1);

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Cart Item Updated Successfully", HttpStatus.OK.value());
        when(env.getProperty("cart.items.update.success.message")).thenReturn("Cart Item Updated Successfully");


        CartResponse actualCartResponse = shoppingCartItemsService.updateCartItem(new Items());
        assertEquals(expectedCartResponse.getMessage(), actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(), actualCartResponse.getStatusCode());

    }

    @Test
    public void updateCartItemShouldReturnZeroForCartUpdationFailed() throws Exception {
        when(cartItemsDao.updateCartItem(null)).thenReturn(1);

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Failed to Update Cart Item", HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(env.getProperty("cart.items.update.failure.message")).thenReturn("Failed to Update Cart Item");


        CartResponse actualCartResponse = shoppingCartItemsService.updateCartItem(new Items());
        assertEquals(expectedCartResponse.getMessage(), actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(), actualCartResponse.getStatusCode());
    }


    @Test
    public void deleteCartItemShouldReturnOneForCartDeletionSuccess() throws Exception {
        when(cartItemsDao.deleteCartItemById(anyInt())).thenReturn(1);

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Cart Items Deleted Successfully", HttpStatus.OK.value());
        when(env.getProperty("cart.items.delete.success.message")).thenReturn("Cart Items Deleted Successfully");


        CartResponse actualCartResponse = shoppingCartItemsService.deleteCartItemById(1);
        assertEquals(expectedCartResponse.getMessage(), actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(), actualCartResponse.getStatusCode());

    }


    @Test
    public void deleteCartItemShouldReturnOneForCartDeletionFailed() throws Exception {
        when(cartItemsDao.deleteCartItemById(anyInt())).thenReturn(0);

        CartResponse expectedCartResponse = new CartResponse(anyObject(),"Failed to Delete Cart Item", HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(env.getProperty("cart.items.delete.failure.message")).thenReturn("Failed to Delete Cart Item");


        CartResponse actualCartResponse = shoppingCartItemsService.deleteCartItemById(1);
        assertEquals(expectedCartResponse.getMessage(), actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(), actualCartResponse.getStatusCode());
        assertNull(actualCartResponse.getData());

    }

    @Test
    public void retrieveAllCartsItemsShouldReturnListOfCartItemsInResponse() throws Exception {

        when(cartItemsDao.retrieveAllCartsItemsById(1)).thenReturn(getItemsList());

        CartResponse expectedCartResponse = new CartResponse(getItemsList(), "success", HttpStatus.OK.value());
        when(env.getProperty("cart.items.success")).thenReturn("success");

        CartResponse actualCartResponse = shoppingCartItemsService.retrieveAllCartsItems(1);
        assertEquals(expectedCartResponse.getMessage(), actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(), actualCartResponse.getStatusCode());
        List<Items> expectedCartResponseForList = (ArrayList<Items>) expectedCartResponse.getData();
        List<Items> actualCartResponseForList = (ArrayList<Items>) actualCartResponse.getData();
        Assert.assertEquals(expectedCartResponseForList.size(), actualCartResponseForList.size());
        Assert.assertNotNull(expectedCartResponse);
    }

    @Test
    public void retrieveAllCartsItemsShouldReturnNullInResponse() throws Exception {
        when(cartItemsDao.retrieveAllCartsItemsById(1)).thenReturn(null);

        CartResponse expectedCartResponse = new CartResponse(anyObject(), "failure", HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(env.getProperty("cart.items.failure")).thenReturn("failure");


        CartResponse actualCartResponse = shoppingCartItemsService.retrieveAllCartsItems(1);
        assertEquals(expectedCartResponse.getMessage(), actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(), actualCartResponse.getStatusCode());
        List<Items> expectedCartResponseForList = (ArrayList<Items>) expectedCartResponse.getData();
        List<Items> actualCartResponseForList = (ArrayList<Items>) actualCartResponse.getData();
        assertEquals(expectedCartResponse.getMessage(), actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(), actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(), actualCartResponse.getData());
        Assert.assertNull(expectedCartResponse.getData());
    }

    private List<Items> getItemsList()
    {
            Items items=new Items();
            List<Items> itemsList=new ArrayList<>();
            items.setCartId(1);;
            items.setItemId(1);
            items.setItemName("abc");
            itemsList.add(items);
            return itemsList;
    }
}