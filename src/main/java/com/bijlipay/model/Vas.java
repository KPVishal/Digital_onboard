package com.bijlipay.model;

import javax.persistence.*;

@Entity
@Table(name = "vas_list")
public class Vas {

    @Id
    @Column(name="vas_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "is_active", columnDefinition = "tinyint(1) default 1")
    private boolean isActive;
    @Column(name = "is_vas", columnDefinition = "tinyint(1) default 1")
    private boolean isVas;
    @Column(name = "vas_code")
    private String vasCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isVas() {
        return isVas;
    }

    public void setVas(boolean vas) {
        isVas = vas;
    }

    public String getVasCode() {
        return vasCode;
    }

    public void setVasCode(String vasCode) {
        this.vasCode = vasCode;
    }

    @Override
    public String toString() {
        return "Vas{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", isVas=" + isVas +
                ", vasCode='" + vasCode + '\'' +
                '}';
    }
}
