package com.example.springboottutorial.domain;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class OrderHeader extends BaseEntity {

    private String customerName;

    public OrderHeader() {
    }

    public OrderHeader(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderHeader that = (OrderHeader) o;
        return Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerName);
    }
}
