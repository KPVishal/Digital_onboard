package com.bijlipay.model.dto.response;

import com.bijlipay.model.Region;
import com.bijlipay.model.Role;
import com.bijlipay.model.ServiceRequestClients;
import com.bijlipay.model.User;

import java.util.List;

public class UserRoleMappingResponse {

    private long userId;
    private User user;
    private Region region;
    private List<Role> roles;
    private List<Long> bankOpsUser;
    private ServiceRequestClients serviceReqClients;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Long> getBankOpsUser() {
        return bankOpsUser;
    }

    public void setBankOpsUser(List<Long> bankOpsUser) {
        this.bankOpsUser = bankOpsUser;
    }

    public void setServiceReqClients(ServiceRequestClients serviceReqClients) {
        this.serviceReqClients = serviceReqClients;
    }

    @Override
    public String toString() {
        return "UserRoleMappingResponse{" +
                "userId=" + userId +
                ", user=" + user +
                ", region=" + region +
                ", roles=" + roles +
                ", bankOpsUser=" + bankOpsUser +
                '}';
    }


}
