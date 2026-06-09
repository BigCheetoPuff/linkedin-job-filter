package com.cheeto.linkedin.service.dao;

import com.cheeto.linkedin.service.models.SearchProfileDB;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SearchProfileDao {

    @Insert("""
            INSERT INTO search_profiles (
                user_id,
                name,
                is_default,
                min_score,
                custom_instructions
            )
            VALUES (
                #{userId},
                #{name},
                #{isDefault},
                #{minScore},
                #{customInstructions}
            )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SearchProfileDB profile);

    @Select("""
            SELECT
                id,
                user_id AS userId,
                name,
                is_default AS isDefault,
                min_score AS minScore,
                custom_instructions AS customInstructions,
                created_at AS createdAt,
                updated_at AS updatedAt
            FROM search_profiles
            WHERE id = #{id}
            """)
    @Results(id = "SearchProfileResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "name", column = "name"),
            @Result(property = "isDefault", column = "isDefault"),
            @Result(property = "minScore", column = "minScore"),
            @Result(property = "customInstructions", column = "customInstructions"),
            @Result(property = "createdAt", column = "createdAt"),
            @Result(property = "updatedAt", column = "updatedAt"),
            @Result(
                    property = "extendedFields",
                    column = "id",
                    many = @Many(select = "com.cheeto.linkedin.service.dao.SearchProfileExtendedFieldDao.findBySearchProfileId")
            )
    })
    Optional<SearchProfileDB> findById(Long id);

    @Select("""
            SELECT
                id,
                user_id AS userId,
                name,
                is_default AS isDefault,
                min_score AS minScore,
                custom_instructions AS customInstructions,
                created_at AS createdAt,
                updated_at AS updatedAt
            FROM search_profiles
            WHERE user_id = #{userId}
            """)
    @ResultMap("SearchProfileResultMap")
    List<SearchProfileDB> findByUserId(Long userId);

    @Update("""
            UPDATE search_profiles
            SET
                name = #{name},
                is_default = #{isDefault},
                min_score = #{minScore},
                custom_instructions = #{customInstructions},
                updated_at = CURRENT_TIMESTAMP
            WHERE id = #{id}
            """)
    int update(SearchProfileDB profile);

    @Delete("""
            DELETE FROM search_profiles
            WHERE id = #{id}
            """)
    int deleteById(Long id);
}
