package com.jd.dao;

import com.jd.beans.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public ShoppingCart retrieveCartById(Integer id) {
        ShoppingCart shoppingCart=null;
        try {
            shoppingCart=jdbcTemplate.queryForObject("SELECT * FROM shopping_cart where cart_id = ? ", new Object[]{id}, new BeanPropertyRowMapper<ShoppingCart>(ShoppingCart.class));
            LOGGER.info("ShoppingCart : "+ shoppingCart);
        }
        catch (Exception d){
            LOGGER.error(d.getMessage());
            d.printStackTrace();
        }
        return shoppingCart;
    }

    @Override
    public int createCart(String cartName) {
        int updateFlag=0;
        try {
            String insertQuery = "INSERT INTO shopping_cart(cart_name, created_at) VALUES (?, ?)";
             updateFlag = jdbcTemplate.update(insertQuery, cartName, new Date());
            System.out.println("craete Flag:"+updateFlag);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return updateFlag;
    }

    @Override
    public List<ShoppingCart> retrieveAllCarts() {
        String sql = "SELECT * FROM shopping_cart";
        List<ShoppingCart> shoppingCartList=null;
        try{
            shoppingCartList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShoppingCart.class));
            System.out.println("shoppingCartList : "+shoppingCartList);
        }catch (Exception e){
           e.printStackTrace();
        }

        return shoppingCartList;
    }

    @Override
    public int updateCart(ShoppingCart shoppingCart) {
        int updateFlag=0;
        try {
            String insertQuery = "UPDATE shopping_cart set cart_name=?, updated_at=? where cart_id=?";
            updateFlag = jdbcTemplate.update(insertQuery, shoppingCart.getCartName(), new Date(), shoppingCart.getCartId());
            System.out.println("update Flag:"+updateFlag);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return updateFlag;
    }

    @Override
    public int deleteCartById(Integer cartId) {
        int updateFlag=0;
        try {
            String deleteQuery = "DELETE from shopping_cart where cart_id = ?";
             updateFlag = jdbcTemplate.update(deleteQuery, cartId);
            System.out.println("craete Flag:"+updateFlag);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return updateFlag;
    }


}
