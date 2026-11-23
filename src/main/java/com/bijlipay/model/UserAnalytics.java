/*
package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_analytics")
public class UserAnalytics {

    @Id
    @GeneratedValue
    @Column(name = "analytics_id")
    private Long id;

    @Column(name = "category", nullable = false)
    private String category;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createDate;


    @Column(name = "extras", columnDefinition = "json", nullable = true)
    private String extras;

    public UserAnalytics() {
    }

    public UserAnalytics(String category, User user) {
        this.category = category;
        this.user = user;
    }

    public UserAnalytics(String category, User user, String extras) {
        this.category = category;
        this.user = user;
        this.extras = extras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }


}
*/
