package com.jd.beans;

import java.util.Date;

public class ShoppingCart {

    private Integer cartId;
    private String cartName;
    private Date createdAt;
    private Date updatedAt;

    public ShoppingCart() {
    }

    public ShoppingCart(Integer cartId, String cartName, Date createdAt, Date updatedAt) {
        this.cartId = cartId;
        this.cartName = cartName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "createdAt=" + createdAt +
                ", cartId=" + cartId +
                ", cartName='" + cartName + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
