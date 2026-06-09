package com.cheeto.linkedin.service.dao;

import com.cheeto.linkedin.service.models.UserDB;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface UserDao {

    @Insert("""
            INSERT INTO users (
                email,
                display_name
            )
            VALUES (
                #{email},
                #{displayName}
            )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserDB user);

    @Select("""
            SELECT
                id,
                email,
                display_name AS displayName,
                created_at AS createdAt,
                updated_at AS updatedAt
            FROM users
            WHERE id = #{id}
            """)
    Optional<UserDB> findById(Long id);

    @Select("""
            SELECT
                id,
                email,
                display_name AS displayName,
                created_at AS createdAt,
                updated_at AS updatedAt
            FROM users
            WHERE email = #{email}
            """)
    Optional<UserDB> findByEmail(String email);
}