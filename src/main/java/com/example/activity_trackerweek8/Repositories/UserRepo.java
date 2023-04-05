package com.example.activity_trackerweek8.Repositories;

import com.example.activity_trackerweek8.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query(value  = "SELECT * FROM user_table s WHERE s.email = ?1", nativeQuery = true)
    User findByEmail(String email);
}
