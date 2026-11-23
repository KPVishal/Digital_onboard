/*
package com.bijlipay.service;

import com.bijlipay.model.UserAttempts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface UserAttemptsRepos  extends JpaRepository<UserAttempts, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_attempts SET attempts = attempts + 1, last_modified = ? WHERE username = ?", nativeQuery = true)
    void updateAttempts(Date date, String username);

    @Query(value = "SELECT * FROM user_attempts WHERE username = ?", nativeQuery = true)
    UserAttempts getUserAttempts(String username);

    @Query(value = "SELECT count(*) FROM user WHERE email = ?", nativeQuery = true)
    int getUserCount(String username);
}
*/
