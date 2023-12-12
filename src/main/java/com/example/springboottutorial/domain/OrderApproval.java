package com.example.springboottutorial.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class OrderApproval extends BaseEntity {

    private String approvedBy;

    @OneToOne
    @JoinColumn(name = "order_header_id")
    private OrderHeader orderHeader;

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderApproval that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getApprovedBy(), that.getApprovedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getApprovedBy());
    }
}
