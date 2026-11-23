package com.bijlipay.model;


import javax.persistence.*;

@Entity
@Table(name = "onboard_instance")
public class OnboardInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String bankName;

    private long institutionCode;

    private boolean Active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public long getInstitutionCode() {
        return institutionCode;
    }

    public void setInstitutionCode(long institutionCode) {
        this.institutionCode = institutionCode;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }
}
