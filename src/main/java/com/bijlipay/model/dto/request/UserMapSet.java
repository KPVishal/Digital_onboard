package com.bijlipay.model.dto.request;

import com.bijlipay.model.Hierarchy;
import com.bijlipay.model.Role;
import com.bijlipay.model.User;

public class UserMapSet {
    private Role role;
    private User predecessor;
    private Hierarchy hierarchy;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(User predecessor) {
        this.predecessor = predecessor;
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    @Override
    public String toString() {
        return "UserMapSet{" + "role=" + role + ", predecessor=" + predecessor + ", hierarchy=" + hierarchy + '}';
    }
}