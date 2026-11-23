package com.bijlipay.model;

import com.bijlipay.model.dto.request.UserRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "email", nullable = false)
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
   // @NotBlank
    @JsonIgnore
    private String password;

    @Column(name = "contact_number", nullable = false)
    @NotBlank
    private String contactNumber;

    @Column(name = "alternate_contact_number", nullable = true)
    private String alternateContactNumber;


    @Column(name = "profile_picture", nullable = true)
    private String profilePicture;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default 1")
    private boolean isActive;

    @Column(name = "state_name", nullable = true)
    private String state;

    @Column(name = "user_address", nullable = true)
    private String userAddress;

    @Column(name = "latitude", nullable = true)
    private Double latitude;

    @Column(name = "longitude", nullable = true)
    private Double longitude;

    @Column(name = "city", nullable = false)
    @NotBlank
    private String city;

    @Column(name = "pincode", nullable = true, columnDefinition = "BIGINT(20) default 0")
    private long pincode;

    @Column(name = "employee_id", nullable = false)
    @NotBlank
    private String employeeID;

    @Column(name = "login_at", nullable = true)
    private Date loginAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @JsonIgnore
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @JsonIgnore
    private Date updatedAt;

    @Column(name = "forget_password_token")
    @JsonIgnore
    private String forgetPasswordToken;

    @Column(name = "device_token", nullable = true)
    private String deviceToken;

    @Column(name = "isAccountNonExpired", columnDefinition = "tinyint(1) default 0")
    private boolean isAccountNonExpired;

    @Column(name = "isAccountNonLocked", columnDefinition = "tinyint(1) default 0")
    private boolean isAccountNonLocked;

    @Column(name = "isCredentialsNonExpired", columnDefinition = "tinyint(1) default 0")
    private boolean isCredentialsNonExpired;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles_table", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> role;

    @Transient
    //@JsonIgnore
    private Set<SimpleGrantedAuthority> roles;

    public static User from(UserRequest request) {
        User user = new User();
        user.name = request.getName();
        user.email = request.getEmail();
        user.employeeID = request.getEmployeeID();
        user.contactNumber = request.getContactNumber();
        user.state = request.getState();
        user.city = request.getCity();
        user.userAddress = request.getUserAddress();
        user.latitude = request.getLatitude();
        user.longitude = request.getLongitude();
        user.pincode = request.getPincode();
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    /* @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public String getUsername() {
            return null;
        }

        @Override
        public boolean isAccountNonExpired() {
            return false;
        }

        @Override
        public boolean isAccountNonLocked() {
            return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }
    */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPincode() {
        return pincode;
    }

    public void setPincode(long pincode) {
        this.pincode = pincode;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getForgetPasswordToken() {
        return forgetPasswordToken;
    }

    public void setForgetPasswordToken(String forgetPasswordToken) {
        this.forgetPasswordToken = forgetPasswordToken;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public Set<SimpleGrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Set<SimpleGrantedAuthority> roles) {
        this.roles = roles;
    }

    public Set<String> getRole() {
        return role;

    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", alternateContactNumber='" + alternateContactNumber + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", isActive=" + isActive +
                ", state='" + state + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", pincode=" + pincode +
                ", employeeID='" + employeeID + '\'' +
                ", loginAt=" + loginAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", forgetPasswordToken='" + forgetPasswordToken + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", roles=" + roles +
                ", role=" + role +
                '}';
    }
}
