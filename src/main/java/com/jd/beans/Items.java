package com.jd.beans;

import java.util.Date;

public class Items {

    private Integer cartId;
    private Date createdAt;
    private Integer itemId;
    private String itemName;
    private Date updatedAt;

    public Items() {
    }

    public Items(Integer cartId, Date createdAt, Integer itemId, String itemName, Date updatedAt) {
        this.cartId = cartId;
        this.createdAt = createdAt;
        this.itemId = itemId;
        this.itemName = itemName;
        this.updatedAt = updatedAt;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Items{" +
                "cartId=" + cartId +
                ", createdAt=" + createdAt +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}