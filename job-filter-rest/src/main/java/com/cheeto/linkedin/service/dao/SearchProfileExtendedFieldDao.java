package com.cheeto.linkedin.service.dao;

import com.cheeto.linkedin.service.models.SearchProfileExtendedFieldDB;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SearchProfileExtendedFieldDao {

    @Insert("""
            <script>
            INSERT INTO search_profile_extended_flds (
                search_profile_id,
                field_key,
                value,
                label,
                weight,
                is_required
            )
            VALUES 
            <foreach collection="extendedFields" item="extendedField" separator=",">
            (
                #{extendedField.searchProfileId},
                #{extendedField.fieldKey},
                #{extendedField.value},
                #{extendedField.label},
                #{extendedField.weight},
                #{extendedField.isRequired}
            )
            </foreach>
            </script>
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertAll(@Param("extendedFields") List<SearchProfileExtendedFieldDB> extendedFields);

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
            FROM search_profile_extended_flds
            WHERE search_profile_id = #{searchProfileId}
            ORDER BY field_key, id
            """)
    List<SearchProfileExtendedFieldDB> findBySearchProfileId(Long searchProfileId);

    @Delete("""
            DELETE FROM search_profile_extended_flds
            WHERE search_profile_id = #{searchProfileId}
            """)
    int deleteBySearchProfileId(Long searchProfileId);
}
