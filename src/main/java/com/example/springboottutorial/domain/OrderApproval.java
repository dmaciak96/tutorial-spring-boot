package com.example.springboottutorial.domain;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class OrderApproval extends BaseEntity{

    private String approvedBy;

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
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
