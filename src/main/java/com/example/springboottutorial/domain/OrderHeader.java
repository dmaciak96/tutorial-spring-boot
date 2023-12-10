package com.example.springboottutorial.domain;

import com.example.springboottutorial.domain.embeded.Address;
import com.example.springboottutorial.domain.enumtype.OrderStatus;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "shippingAddress.address", column = @Column(name = "shipping_address")),
        @AttributeOverride(name = "shippingAddress.city", column = @Column(name = "shipping_city")),
        @AttributeOverride(name = "shippingAddress.state", column = @Column(name = "shipping_state")),
        @AttributeOverride(name = "shippingAddress.zipCode", column = @Column(name = "shipping_zip_code")),
        @AttributeOverride(name = "billToAddress.address", column = @Column(name = "bill_to_address")),
        @AttributeOverride(name = "billToAddress.city", column = @Column(name = "bill_to_city")),
        @AttributeOverride(name = "billToAddress.state", column = @Column(name = "bill_to_state")),
        @AttributeOverride(name = "billToAddress.zipCode", column = @Column(name = "bill_to_zip_code"))
})
public class OrderHeader extends BaseEntity {

    private String customerName;
    private Address shippingAddress;
    private Address billToAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

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

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(Address billToAddress) {
        this.billToAddress = billToAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderHeader that = (OrderHeader) o;
        return Objects.equals(customerName, that.customerName) && Objects.equals(shippingAddress, that.shippingAddress) && Objects.equals(billToAddress, that.billToAddress) && orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerName, shippingAddress, billToAddress, orderStatus);
    }
}
