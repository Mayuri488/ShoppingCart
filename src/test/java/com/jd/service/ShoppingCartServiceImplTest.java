package com.jd.service;

import com.jd.beans.ShoppingCart;
import com.jd.dao.ShoppingCartDao;
import com.jd.response.CartResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class ShoppingCartServiceImplTest {

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private ShoppingCartDao shoppingCartDao;

    @Mock
    private Environment env;

    @Test
    public void retriveCartByIdShuoldReturnShoppingCart(){
        ShoppingCart shoppingCartData=new ShoppingCart(1,"first cart",new Date(),new Date());

        CartResponse expectedCartResponse = new CartResponse(shoppingCartData,"success", HttpStatus.OK.value());
        when(env.getProperty("cart.success")).thenReturn("success");
        when(shoppingCartDao.retrieveCartById(1)).thenReturn(shoppingCartData);

        CartResponse actualCartResponse=shoppingCartService.retrieveCartById(1);

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());
    }

    @Test
    public void retriveCartByIdShuoldReturnShoppingCartValueNull(){
        ShoppingCart shoppingCartData=null;
        CartResponse expectedCartResponse = new CartResponse(shoppingCartData,"failure", HttpStatus.INTERNAL_SERVER_ERROR.value());

        when(env.getProperty("cart.failure")).thenReturn("failure");
        when(shoppingCartDao.retrieveCartById(1)).thenReturn(shoppingCartData);

        CartResponse actualCartResponse=shoppingCartService.retrieveCartById(1);

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());
    }

    @Test
    public void createCartShouldReturnOne(){

        CartResponse expectedCartResponse=new CartResponse(anyObject(),"Cart Created Successfully",HttpStatus.CREATED.value());
        when(shoppingCartDao.createCart("my cart")).thenReturn(1);
        when(env.getProperty("cart.create.success.message")).thenReturn("Cart Created Successfully");

        CartResponse actualCartResponse=shoppingCartService.createCart(new ShoppingCart());

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());

    }

    @Test
    public void createCartShouldReturnZero(){

        CartResponse expectedCartResponse=new CartResponse(anyObject(),"Failed to Create Cart",HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(shoppingCartDao.createCart("my cart")).thenReturn(0);
        when(env.getProperty("cart.create.failure.message")).thenReturn("Failed to Create Cart");

        CartResponse actualCartResponse=shoppingCartService.createCart(new ShoppingCart());

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());

    }

    @Test
    public void retrieveAllCartsShouldReturnListInResponse() {

        when(shoppingCartDao.retrieveAllCarts()).thenReturn(getShoppingCartList());

        CartResponse expectedCartResponse=new CartResponse(getShoppingCartList(),"success",HttpStatus.OK.value());
        when(env.getProperty("cart.success")).thenReturn("success");


        CartResponse actualCartResponse= shoppingCartService.retrieveAllCarts();
        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        List<ShoppingCart> expectedCartResponseForList=(ArrayList<ShoppingCart>)expectedCartResponse.getData();
        List<ShoppingCart> actualCartResponseForList=(ArrayList<ShoppingCart>)actualCartResponse.getData();
        Assert.assertEquals(expectedCartResponseForList.size(),actualCartResponseForList.size());
        Assert.assertNotNull(expectedCartResponse);


    }
    public List<ShoppingCart> getShoppingCartList()
    {
        ShoppingCart shoppingCart=new ShoppingCart();
        List<ShoppingCart> listOfShoppingCartList=new ArrayList<>();
        shoppingCart.setCartName("abc");
        shoppingCart.setCartId(1);
        shoppingCart.setCreatedAt(new Date());
        listOfShoppingCartList.add(shoppingCart);
        return listOfShoppingCartList;
    }
    @Test
    public void retrieveAllCartListNullInResponse() {

        CartResponse expectedCartResponse=new CartResponse(null,"failure",HttpStatus.INTERNAL_SERVER_ERROR.value());
        when(shoppingCartDao.retrieveAllCarts()).thenReturn(null);
        when(env.getProperty("cart.failure")).thenReturn("failure");

        CartResponse actualCartResponse= shoppingCartService.retrieveAllCarts();

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());
        Assert.assertNull(expectedCartResponse.getData());

    }

    @Test
    public void updateCartShouldRetunOne(){
        CartResponse expectedCartResponse=new CartResponse(null,"Cart Updated Successfully",HttpStatus.OK.value());

        when(shoppingCartDao.updateCart(anyObject())).thenReturn(1);
        when(env.getProperty("cart.update.success.message")).thenReturn("Cart Updated Successfully");

        CartResponse actualCartResponse=shoppingCartService.updateCart(new ShoppingCart());

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());


    }

    @Test
    public void updateCartShouldRetunZero(){

        CartResponse expectedCartResponse=new CartResponse(null,"Failed to Update Cart",HttpStatus.INTERNAL_SERVER_ERROR.value());

        when(shoppingCartDao.updateCart(anyObject())).thenReturn(0);
        when(env.getProperty("cart.update.failure.message")).thenReturn("Failed to Update Cart");

        CartResponse actualCartResponse=shoppingCartService.updateCart(new ShoppingCart());

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());

    }

    @Test
    public void deleteCartByIdShuouldReturnOne(){
        CartResponse expectedCartResponse=new CartResponse(null,"Cart Items Deleted Successfully",HttpStatus.OK.value());

        when(shoppingCartDao.deleteCartById(1)).thenReturn(1);
        when(env.getProperty("cart.delete.success.message")).thenReturn("Cart Items Deleted Successfully");

        CartResponse actualCartResponse= shoppingCartService.deleteCartById(1);

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());


    }

    @Test
    public void deleteCartByIdShuouldReturnZero(){
        CartResponse expectedCartResponse=new CartResponse(null,"Failed to Delete Cart",HttpStatus.INTERNAL_SERVER_ERROR.value());

        when(shoppingCartDao.deleteCartById(1)).thenReturn(0);
        when(env.getProperty("cart.delete.failure.message")).thenReturn("Failed to Delete Cart");

        CartResponse actualCartResponse= shoppingCartService.deleteCartById(1);

        assertEquals(expectedCartResponse.getMessage(),actualCartResponse.getMessage());
        assertEquals(expectedCartResponse.getStatusCode(),actualCartResponse.getStatusCode());
        assertEquals(expectedCartResponse.getData(),actualCartResponse.getData());
    }

}