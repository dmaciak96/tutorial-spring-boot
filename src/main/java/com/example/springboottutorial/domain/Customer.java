package com.example.springboottutorial.domain;

import com.example.springboottutorial.domain.embeded.Address;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AttributeOverrides({
        @AttributeOverride(name = "address.address", column = @Column(name = "address")),
        @AttributeOverride(name = "address.city", column = @Column(name = "city")),
        @AttributeOverride(name = "address.state", column = @Column(name = "state")),
        @AttributeOverride(name = "address.zipCode", column = @Column(name = "zip_code")),
})
public class Customer extends BaseEntity {

    private String customerName;
    private Address address;
    private String phone;
    private String email;

    @Version
    private Integer version;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private Set<OrderHeader> orderHeaders;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<OrderHeader> getOrderHeaders() {
        return orderHeaders;
    }

    public void setOrderHeaders(Set<OrderHeader> orderHeaders) {
        this.orderHeaders = orderHeaders;
    }

    public void addOrderHeader(OrderHeader orderHeader) {
        if (orderHeaders == null) {
            orderHeaders = new HashSet<>();
        }
        orderHeaders.add(orderHeader);
        orderHeader.setCustomer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCustomerName(), customer.getCustomerName()) && Objects.equals(getAddress(), customer.getAddress()) && Objects.equals(getPhone(), customer.getPhone()) && Objects.equals(getEmail(), customer.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCustomerName(), getAddress(), getPhone(), getEmail());
    }
}
