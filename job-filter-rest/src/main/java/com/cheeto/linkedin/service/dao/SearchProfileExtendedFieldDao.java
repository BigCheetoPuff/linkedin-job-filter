package com.cheeto.linkedin.service.dao;

import com.cheeto.linkedin.service.models.SearchProfileExtendedFieldDB;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SearchProfileExtendedFieldDao {

    @Insert("""
            INSERT INTO search_profile_config_values (
                search_profile_id,
                field_key,
                value,
                label,
                weight,
                is_required
            )
            VALUES (
                #{searchProfileId},
                #{fieldKey},
                #{value},
                #{label},
                #{weight},
                #{isRequired}
            )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SearchProfileExtendedFieldDB configValue);

    @Select("""
            SELECT
                id,
                search_profile_id AS searchProfileId,
                field_key AS fieldKey,
                value,
                label,
                weight,
                is_required AS isRequired,
                created_at AS createdAt,
                updated_at AS updatedAt
            FROM search_profile_config_values
            WHERE search_profile_id = #{searchProfileId}
            ORDER BY field_key, id
            """)
    List<SearchProfileExtendedFieldDB> findBySearchProfileId(Long searchProfileId);

    @Delete("""
            DELETE FROM search_profile_config_values
            WHERE search_profile_id = #{searchProfileId}
            """)
    int deleteBySearchProfileId(Long searchProfileId);
}
