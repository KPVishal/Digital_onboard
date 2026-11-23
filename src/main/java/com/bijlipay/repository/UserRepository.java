package com.bijlipay.repository;

import com.bijlipay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    int countByEmailOrEmployeeID(String email,String employeeId);


    @Query("Select u from User u where u.isActive=1")
    List<User> getActiveList();

    @Query("SELECT u from User u Where u.isActive=0")
    List<User> getInActiveList();

    @Query(value="Select * from user u  where u.email= ?1 order by  u.user_id desc limit 1",nativeQuery = true)
    User findSessionUser(String username);

    User findByEmail(String username);

    @Query(value="Select * from user u  where u.email= ?1 order by  u.user_id desc limit 1",nativeQuery = true)
    User findUsername(String username);


}
