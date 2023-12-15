package com.example.springboottutorial.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

import java.util.Objects;

@Entity
public class OrderLine extends BaseEntity {

    private int quantityOrdered;

    @ManyToOne
    private OrderHeader orderHeader;

    @Version
    private Integer version;

    @ManyToOne
    private Product product;

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine orderLine)) return false;
        if (!super.equals(o)) return false;
        return getQuantityOrdered() == orderLine.getQuantityOrdered() && Objects.equals(getOrderHeader(), orderLine.getOrderHeader()) && Objects.equals(getProduct(), orderLine.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getQuantityOrdered());
    }
}
