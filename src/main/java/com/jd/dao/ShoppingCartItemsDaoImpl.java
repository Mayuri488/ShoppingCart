package com.jd.dao;

import com.jd.beans.Items;
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
public class ShoppingCartItemsDaoImpl implements ShoppingCartItemsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ShoppingCart shoppingCart;


    @Override
    public Items retrieveCartItemById(Integer itemId) {
        Items items = null;
        try {
            items = jdbcTemplate.queryForObject("SELECT * FROM items where item_id = ? ", new Object[]{itemId}, new BeanPropertyRowMapper<Items>(Items.class));
        } catch (Exception d) {
            LOGGER.error(d.getMessage());
        }
        return items;
    }

    @Override
    public int createCartItem(String itemName, int cartId) {
        int updateFlag=0;
        try {
            String insertQuery = "INSERT INTO items(item_name,created_at,cart_id) VALUES (?, ?, ?)";
             updateFlag = jdbcTemplate.update(insertQuery, itemName, new Date(), cartId);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return updateFlag;
    }

    @Override
    public int updateCartItem(Items items) {
        int updateFlag=0;
        try {
            String updateQuery = "UPDATE items set item_name=?, updated_at=? where item_id=?";
            updateFlag = jdbcTemplate.update(updateQuery, items.getItemName(), new Date(), items.getItemId());
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return updateFlag;
    }

    @Override
    public int deleteCartItemById(Integer itemId) {
        int updateFlag=0;
        try {
            String deleteQuery = "DELETE from items where item_id = ?";
            updateFlag = jdbcTemplate.update(deleteQuery, itemId);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return updateFlag;
    }

    @Override
    public List<Items> retrieveAllCartsItemsById(Integer cartId) {
        List<Items> itemsList=null;
        String sql = "SELECT * FROM items where cart_id = ?";
        try {

             itemsList = jdbcTemplate.query(sql, new Object[]{cartId}, new BeanPropertyRowMapper<>(Items.class));
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return itemsList;
    }

}
