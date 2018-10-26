package com.veebirakendus.Attempt1.repositories;

import com.veebirakendus.Attempt1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT * FROM users WHERE google_uid=(:googleUid)", nativeQuery = true)
    User findByGoogleId(@Param("googleUid") String googleId);
}