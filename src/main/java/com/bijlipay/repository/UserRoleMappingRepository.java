package com.bijlipay.repository;

import com.bijlipay.model.Role;
import com.bijlipay.model.User;
import com.bijlipay.model.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping , Long> {

    @Query("Select u from UserRoleMapping u where u.user=?1")
    List<UserRoleMapping> findAllByUser(User user);


    @Query("Select u from UserRoleMapping u where u.role=?1")
    List<User> findAllByRole(Role role1);
}
